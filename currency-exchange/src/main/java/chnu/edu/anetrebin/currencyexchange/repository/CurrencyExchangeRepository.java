package chnu.edu.anetrebin.currencyexchange.repository;

import chnu.edu.anetrebin.currencyexchange.enums.Currency;
import chnu.edu.anetrebin.currencyexchange.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    List<CurrencyExchange> findAllByCreatedAt(LocalDate createdAt);

    boolean existsByFromCurrencyAndToCurrencyAndCreatedAt(Currency fromCurrency, Currency toCurrency, LocalDate createdAt);

    CurrencyExchange findByFromCurrencyAndToCurrencyAndCreatedAt(Currency fromCurrency, Currency toCurrency, LocalDate date);
}