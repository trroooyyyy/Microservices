package chnu.edu.anetrebin.transactionprocessor.service.external;

import chnu.edu.anetrebin.transactionprocessor.dto.response.external.AccountResponse;
import chnu.edu.anetrebin.transactionprocessor.exceptions.external.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final RestTemplate restTemplate;
    private final String accountServiceUrl;

    public AccountService(RestTemplate restTemplate, @Value("${account-service.url}") String accountServiceUrl) {
        this.restTemplate = restTemplate;
        this.accountServiceUrl = accountServiceUrl;
    }

    public AccountResponse getAccountById(Long id) {
        String url = accountServiceUrl + "/" + id;

        try {
            return restTemplate.getForObject(url, AccountResponse.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new AccountNotFoundException("Account not found with id: " + id);
        }
    }

    public void addBalance(Long id, BigDecimal request) {
        String url = accountServiceUrl + "/addBalance/" + id;
        try {
            restTemplate.put(url, request);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new AccountNotFoundException("Account not found with id: " + id);
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Failed to add balance: " + ex.getMessage());
        }
    }

    public void subtractBalance(Long id, BigDecimal request) {
        String url = accountServiceUrl + "/subtractBalance/" + id;
        try {
            restTemplate.put(url, request);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new AccountNotFoundException("Account not found with id: " + id);
        } catch (HttpClientErrorException ex) {
            throw new RuntimeException("Failed to subtract balance: " + ex.getMessage());
        }
    }
}
