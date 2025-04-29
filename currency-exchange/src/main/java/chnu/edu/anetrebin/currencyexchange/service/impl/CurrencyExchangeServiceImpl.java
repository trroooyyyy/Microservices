package chnu.edu.anetrebin.currencyexchange.service.impl;

import chnu.edu.anetrebin.currencyexchange.dto.request.CurrencyExchangeRequest;
import chnu.edu.anetrebin.currencyexchange.dto.request.CurrencyExchangeUpdateRequest;
import chnu.edu.anetrebin.currencyexchange.exceptions.CurrencyExchangeException;
import chnu.edu.anetrebin.currencyexchange.exceptions.CurrencyExchangeNotFound;
import chnu.edu.anetrebin.currencyexchange.model.CurrencyExchange;
import chnu.edu.anetrebin.currencyexchange.repository.CurrencyExchangeRepository;
import chnu.edu.anetrebin.currencyexchange.service.CurrencyExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
    private final CurrencyExchangeRepository repository;

    @Transactional
    @Override
    public void deleteCurrencyExchange(Long id) {
        CurrencyExchange currencyExchange = repository.findById(id).orElseThrow(() -> new CurrencyExchangeNotFound("Currency exchange rate not found!"));
        repository.delete(currencyExchange);
    }

    @Transactional(readOnly = true)
    @Override
    public CurrencyExchange getCurrencyExchangeById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CurrencyExchangeNotFound("Currency exchange rate not found!"));
    }

    @Transactional
    @Override
    public CurrencyExchange updateCurrencyExchange(Long id, CurrencyExchangeUpdateRequest request) {
        CurrencyExchange exchange = repository.findById(id).orElseThrow(() -> new CurrencyExchangeNotFound("Currency exchange rate not found!"));

        exchange.setValue(request.value());

        return repository.save(exchange);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CurrencyExchange> getAllCurrencyExchanges() {
        return repository.findAllByCreatedAt(LocalDate.now());
    }

    @Transactional
    @Override
    public void addCurrencyExchange(CurrencyExchangeRequest request) {
        if (request.toCurrency().equals(request.fromCurrency())) {
            throw new CurrencyExchangeException("Currencies cannot be equal!");
        }

        if (repository.existsByFromCurrencyAndToCurrencyAndCreatedAt(request.fromCurrency()
                , request.toCurrency()
                , LocalDate.now())) {
            throw new CurrencyExchangeException("Currency exchange rate for today has been created!");

        }

        CurrencyExchange exchange = CurrencyExchange.builder()
                .fromCurrency(request.fromCurrency())
                .toCurrency(request.toCurrency())
                .value(request.value())
                .build();

        repository.save(exchange);
    }

    @Transactional(readOnly = true)
    public BigDecimal getExchangedValue(CurrencyExchangeRequest request) {
        CurrencyExchange exchangedValue = repository.findByFromCurrencyAndToCurrencyAndCreatedAt(request.fromCurrency(), request.toCurrency(), LocalDate.now());

        if (exchangedValue == null) {
            throw new CurrencyExchangeNotFound("Currency exchange rate not found!");
        }

        if (request.toCurrency().equals(request.fromCurrency())) {
            throw new CurrencyExchangeException("Currencies cannot be equal!");
        }

        return exchangedValue.getValue().multiply(request.value());
    }
}
