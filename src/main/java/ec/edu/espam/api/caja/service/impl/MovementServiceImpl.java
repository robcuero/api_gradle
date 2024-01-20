package ec.edu.espam.api.caja.service.impl;

import ec.edu.espam.api.caja.domain.Account;
import ec.edu.espam.api.caja.domain.Movement;
import ec.edu.espam.api.caja.domain.dto.MovementDto;
import ec.edu.espam.api.caja.exceptions.EntityNotFoundException;
import ec.edu.espam.api.caja.exceptions.PreconditionFailedException;
import ec.edu.espam.api.caja.repository.MovementRepository;
import ec.edu.espam.api.caja.service.AccountService;
import ec.edu.espam.api.caja.service.MovementService;
import ec.edu.espam.api.caja.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovementServiceImpl implements MovementService {

    private final MovementRepository repository;


    @Override
    public MovementDto create(MovementDto dto) {
        if (dto.getType().compareTo(Movement.Type.DEBIT) == 0) {
            validateDebit(dto);
            dto.setAmount(dto.getAmount().multiply(new BigDecimal(-1)));
        } else {
            validateCredit(dto);
        }

        dto.setDate(LocalDate.now());
        Movement movement = convertDtoToEntity(dto);
        return convertEntityToDto(repository.save(movement));
    }

    @Override
    public Movement createCredit(Account account, BigDecimal amount) {
        Movement movement = Movement.builder()
                .date(LocalDate.now())
                .type(Movement.Type.CREDIT)
                .amount(amount)
                .account(account)
                .balance(amount)
                .build();

        return repository.save(movement);
    }

    private void validateDebit(MovementDto dto) {
        BigDecimal balance = BigDecimal.ZERO;
        Optional<Movement> optional = repository.findByAccountOrderByDateDesc(dto.getAccountId());
        if (optional.isPresent()) {
            balance = optional.get().getBalance();
        }

        if (balance.compareTo(BigDecimal.ZERO) <= 0 || balance.compareTo(dto.getAmount()) < 0) {
            throw new PreconditionFailedException("Balance not available");
        }

        BigDecimal dailyAmount = dailyAmount(dto.getAccountId());
        
        if ((dailyAmount.add(dto.getAmount())).compareTo(new BigDecimal("1000")) > 0) {
            throw new PreconditionFailedException("Daily withdrawal limit exceeded (1000)");
        }
        dto.setBalance(balance.subtract(dto.getAmount()));
    }

    private BigDecimal dailyAmount(Long accountId) {
        List<Movement> movements = repository.findByAccountIdAndDate(accountId, LocalDate.now());
        BigDecimal sumDebits = BigDecimal.ZERO;

        for (Movement movement : movements) {
            if (movement.getType() == Movement.Type.DEBIT) {
                sumDebits = sumDebits.add(movement.getAmount());
            }
        }
        return sumDebits.abs();
    }

    private void validateCredit(MovementDto dto) {
        BigDecimal balance = BigDecimal.ZERO;
        Optional<Movement> optional = repository.findByAccountOrderByDateDesc(dto.getAccountId());
        if (optional.isPresent()) {
            balance = optional.get().getBalance();
        }

        dto.setBalance(balance.add(dto.getAmount()));
    }

    @Override
    public MovementDto update(Long id, MovementDto dto) {
        Movement movement = getEntityById(id);
        movement.setDate(dto.getDate());
        return convertEntityToDto(repository.save(movement));
    }

    @Override
    public void delete(Long id) {
        Movement movement = getEntityById(id);
        repository.delete(movement);
    }

    @Override
    public MovementDto getById(Long id) {
        return convertEntityToDto(getEntityById(id));
    }

    @Override
    public List<MovementDto> getAll(Long accountId) {
        if (accountId == null) {
            return repository.findAll().stream().map(this::convertEntityToDto).toList();
        } else {
            //Account account = Mapper.modelMapper().map(accountService.getById(accountId), Account.class);
            return repository.findByAccountId(accountId).stream().map(this::convertEntityToDto).toList();
        }
    }

    private Movement getEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movement not found"));
    }

    private MovementDto convertEntityToDto(Movement entity) {
        return Mapper.modelMapper().map(entity, MovementDto.class);
    }

    private Movement convertDtoToEntity(MovementDto dto) {
        return Mapper.modelMapper().map(dto, Movement.class);
    }

}
