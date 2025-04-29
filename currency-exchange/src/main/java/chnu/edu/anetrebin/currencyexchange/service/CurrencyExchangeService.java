package chnu.edu.anetrebin.currencyexchange.service;


import chnu.edu.anetrebin.currencyexchange.dto.request.CurrencyExchangeRequest;
import chnu.edu.anetrebin.currencyexchange.dto.request.CurrencyExchangeUpdateRequest;
import chnu.edu.anetrebin.currencyexchange.model.CurrencyExchange;

import java.math.BigDecimal;
import java.util.List;

public interface CurrencyExchangeService {
    void deleteCurrencyExchange(Long id);

    CurrencyExchange getCurrencyExchangeById(Long id);

    CurrencyExchange updateCurrencyExchange(Long id, CurrencyExchangeUpdateRequest request);

    List<CurrencyExchange> getAllCurrencyExchanges();

    void addCurrencyExchange(CurrencyExchangeRequest request);

    BigDecimal getExchangedValue(CurrencyExchangeRequest request);
}

