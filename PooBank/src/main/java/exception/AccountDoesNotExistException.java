package exception;

public class AccountDoesNotExistException extends Exception{
    public AccountDoesNotExistException(String msg){
        super(msg);
    }
}
