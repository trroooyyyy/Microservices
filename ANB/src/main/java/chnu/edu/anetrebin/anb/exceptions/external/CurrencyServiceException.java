package chnu.edu.anetrebin.anb.exceptions.external;

public class CurrencyServiceException extends RuntimeException {
    // Exceptions like NOT_FOUND. Conflicts are not handled
    public CurrencyServiceException(String message) {
        super(message);
    }
}
