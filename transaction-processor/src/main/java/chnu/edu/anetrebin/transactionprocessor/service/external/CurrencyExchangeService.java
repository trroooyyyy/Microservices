package chnu.edu.anetrebin.transactionprocessor.service.external;

import chnu.edu.anetrebin.transactionprocessor.dto.request.external.CurrencyExchangeRequest;
import chnu.edu.anetrebin.transactionprocessor.exceptions.external.CurrencyServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyExchangeService {
    private final RestTemplate restTemplate;
    private final String currencyServiceUrl;

    public CurrencyExchangeService(RestTemplate restTemplate, @Value("${currency-service.url}") String currencyServiceUrl) {
        this.restTemplate = restTemplate;
        this.currencyServiceUrl = currencyServiceUrl;
    }

    public BigDecimal getExchangedValue(CurrencyExchangeRequest request) {
        try {
            ResponseEntity<BigDecimal> response = restTemplate.exchange(
                    currencyServiceUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(request),
                    BigDecimal.class
            );

            if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
                throw new CurrencyServiceException("Invalid response from currency service!");
            }

            return response.getBody();

        } catch (HttpClientErrorException.NotFound e) {
            throw new CurrencyServiceException("Currency rate not found for today!");
        } catch (RestClientException e) {
            throw new CurrencyServiceException("Currency service unavailable: " + e.getMessage());
        }
    }
}
