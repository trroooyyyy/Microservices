package chnu.edu.anetrebin.currencyexchange.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CurrencyExchangeUpdateRequest(
        @DecimalMin(value = "0.01", message = "Value must be greater than 0")
        @NotNull(message = "Value is required")
        BigDecimal value) {}
