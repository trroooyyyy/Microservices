package chnu.edu.anetrebin.transactionprocessor.dto.response.external;

import chnu.edu.anetrebin.transactionprocessor.enums.AccountStatus;
import chnu.edu.anetrebin.transactionprocessor.enums.Currency;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountResponse(
        Long id,
        String accountName,
        String accountNumber,
        BigDecimal balance,
        Currency currency,
        AccountStatus status
) {}
