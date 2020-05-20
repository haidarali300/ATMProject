/**
 * Exception thrown when there is insufficient fund to withdraw
 *
 */
public class InsufficientFundsException extends RuntimeException {
	public InsufficientFundsException(String s) {
		super(s);
	} // end constructor
} // end InsufficientFundsException
