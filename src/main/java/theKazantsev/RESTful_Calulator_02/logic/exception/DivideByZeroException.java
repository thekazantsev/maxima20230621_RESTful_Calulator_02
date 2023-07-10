package theKazantsev.RESTful_Calulator_02.logic.exception;

public class DivideByZeroException extends RuntimeException {
    public DivideByZeroException(String message) {
        super(message);
    }
}
