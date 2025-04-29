package chnu.edu.anetrebin.currencyexchange.controller;

import chnu.edu.anetrebin.currencyexchange.dto.request.CurrencyExchangeRequest;
import chnu.edu.anetrebin.currencyexchange.dto.request.CurrencyExchangeUpdateRequest;
import chnu.edu.anetrebin.currencyexchange.model.CurrencyExchange;
import chnu.edu.anetrebin.currencyexchange.service.CurrencyExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ce")
@RequiredArgsConstructor
@Validated
public class CurrencyExchangeController {
    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/")
    public ResponseEntity<List<CurrencyExchange>> getAllCurrencyExchanges() {
        return ResponseEntity.ok(currencyExchangeService.getAllCurrencyExchanges());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyExchange> getCurrencyExchange(@PathVariable Long id) {
        return ResponseEntity.ok(currencyExchangeService.getCurrencyExchangeById(id));
    }

    @PostMapping("/")
    public ResponseEntity<Void> createCurrencyExchange(@Valid @RequestBody CurrencyExchangeRequest currencyExchange) {
        currencyExchangeService.addCurrencyExchange(currencyExchange);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrencyExchange(@PathVariable Long id) {
        currencyExchangeService.deleteCurrencyExchange(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyExchange> updateCurrencyExchange(@PathVariable Long id, @Valid @RequestBody CurrencyExchangeUpdateRequest currencyExchange) {
        return ResponseEntity.ok(currencyExchangeService.updateCurrencyExchange(id, currencyExchange));
    }

    @PostMapping("/getExchangedValue")
    public ResponseEntity<BigDecimal> getExchangedValue(@Valid @RequestBody CurrencyExchangeRequest currencyExchange) {
        BigDecimal value = currencyExchangeService.getExchangedValue(currencyExchange);

        return ResponseEntity.ok(value);

    }

}
