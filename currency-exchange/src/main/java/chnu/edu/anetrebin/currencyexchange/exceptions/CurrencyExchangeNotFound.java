package chnu.edu.anetrebin.currencyexchange.exceptions;

public class CurrencyExchangeNotFound extends RuntimeException {
    public CurrencyExchangeNotFound(String message) {
        super(message);
    }
}
