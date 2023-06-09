package exception;

public class SaldoInvalidoException extends Exception{
	private static final long serialVersionUID = 1L;

	public SaldoInvalidoException(String msg){
        super(msg);
    }
}
