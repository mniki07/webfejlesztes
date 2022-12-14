package hu.unideb.inf.core.user;

public class UserAlreadyExistsException extends RuntimeException {

    private String message;

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
