package chnu.edu.anetrebin.anb.service.external;

import chnu.edu.anetrebin.anb.dto.requests.external.TransactionRequest;
import chnu.edu.anetrebin.anb.exceptions.external.TransactionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {
    private final RestTemplate restTemplate;
    private final String transactionServiceUrl;

    public TransactionService(RestTemplate restTemplate, @Value("${transaction-service.url}") String transactionServiceUrl) {
        this.restTemplate = restTemplate;
        this.transactionServiceUrl = transactionServiceUrl;
    }

    public void createTransaction(TransactionRequest transactionRequest) {
        try {
            ResponseEntity<Void> response = restTemplate.exchange(
                    transactionServiceUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(transactionRequest),
                    Void.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new TransactionException("Transaction service returned status: " + response.getStatusCode());
            }

        } catch (HttpClientErrorException.BadRequest e) {
            String errorMsg = getString(e);

            throw new TransactionException(errorMsg);

        } catch (HttpServerErrorException e) {
            String errorMsg = "Transaction processing failed: " + e.getResponseBodyAsString();

            throw new TransactionException(errorMsg);
        } catch (ResourceAccessException e) {
            String errorMsg = "Transaction service unavailable";

            throw new TransactionException(errorMsg);
        }
    }

    private static String getString(HttpClientErrorException.BadRequest e) {
        String errorBody = e.getResponseBodyAsString();
        String errorMsg = "Invalid transaction request";

        if (errorBody.contains("One of the accounts is not active")) {
            errorMsg = "One of the accounts is not active";
        } else if (errorBody.contains("Sender and Receiver accounts are the same")) {
            errorMsg = "Sender and Receiver accounts are the same";
        } else if (errorBody.contains("Insufficient funds")) {
            errorMsg = "Insufficient funds";
        }

        return errorMsg;
    }
}




