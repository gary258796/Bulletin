package gary.springframework.bulletin.normalstuff.exception;

public class UserAlreadyExistException extends Exception{

    public UserAlreadyExistException() {
    }

    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }

}
