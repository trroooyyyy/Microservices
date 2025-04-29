package chnu.edu.anetrebin.anb.service.account.impl;

import chnu.edu.anetrebin.anb.dto.requests.AccountRequest;
import chnu.edu.anetrebin.anb.dto.requests.external.CurrencyExchangeRequest;
import chnu.edu.anetrebin.anb.dto.responses.AccountResponse;
import chnu.edu.anetrebin.anb.exceptions.account.AccountNotFoundException;
import chnu.edu.anetrebin.anb.model.Account;
import chnu.edu.anetrebin.anb.repository.AccountRepository;
import chnu.edu.anetrebin.anb.service.account.AccountService;
import chnu.edu.anetrebin.anb.service.external.CurrencyExchangeService;
import chnu.edu.anetrebin.anb.service.user.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final CurrencyExchangeService currencyExchangeService;
    private final UserServiceImpl userService;

    @Transactional
    @Override
    public void deleteAccount(Long id) {
        Account account = repository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));
        repository.delete(account);
    }

    @Transactional(readOnly = true)
    @Override
    public AccountResponse getAccountById(Long id) {
        return AccountResponse.toResponse(repository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id)));
    }

    @Transactional
    @Override
    public AccountResponse updateAccount(Long id, AccountRequest accountRequest) {
        Account account = repository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + id));

        BigDecimal balance = account.getBalance();
        if (!account.getCurrency().equals(accountRequest.currency())) {
            balance = currencyExchangeService.getExchangedValue(new CurrencyExchangeRequest(account.getCurrency(), accountRequest.currency(), balance));
            account.setAccountNumber(userService.getAccountNumber(accountRequest));
            account.setCurrency(accountRequest.currency());
        }

        account.setAccountName(accountRequest.accountName());
        account.setBalance(balance);

        // TODO: Create Notifications
        return AccountResponse.toResponse(repository.save(account));
    }

    @Transactional(readOnly = true)
    @Override
    public List<AccountResponse> getAllAccounts() {
        return repository.findAllByOrderByIdAsc().stream().map(AccountResponse::toResponse).collect(Collectors.toList());
    }
}
