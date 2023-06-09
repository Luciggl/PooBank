package exception;

public class ContaJaExisteException extends Exception{
	private static final long serialVersionUID = 1L;

	public ContaJaExisteException(String msg){
        super(msg);
    }
}
