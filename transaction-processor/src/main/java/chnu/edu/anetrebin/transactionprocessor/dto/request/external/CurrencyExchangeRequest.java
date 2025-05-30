package chnu.edu.anetrebin.transactionprocessor.dto.request.external;


import chnu.edu.anetrebin.transactionprocessor.enums.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CurrencyExchangeRequest(
        @NotNull(message = "FromCurrency cannot be null")
        Currency fromCurrency,

        @NotNull(message = "ToCurrency cannot be null")
        Currency toCurrency,

        @DecimalMin(value = "0.01", message = "Value must be greater than 0")
        @NotNull(message = "Value is required")
        BigDecimal value) {}
