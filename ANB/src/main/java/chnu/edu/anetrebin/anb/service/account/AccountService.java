package chnu.edu.anetrebin.anb.service.account;

import chnu.edu.anetrebin.anb.dto.requests.AccountRequest;
import chnu.edu.anetrebin.anb.dto.responses.AccountResponse;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    void deleteAccount(Long id);

    AccountResponse getAccountById(Long id);

    AccountResponse updateAccount(Long id, AccountRequest accountRequest);

    List<AccountResponse> getAllAccounts();

    void subtractBalance(Long id, BigDecimal request);

    void addBalance(Long id, BigDecimal request);
}
