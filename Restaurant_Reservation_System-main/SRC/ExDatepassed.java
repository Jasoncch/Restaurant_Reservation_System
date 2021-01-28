
public class ExDatepassed extends Exception{
	private static final long serialVersionUID = 1L;
	public  ExDatepassed() {super("Date has already passed!");}
	public  ExDatepassed(String message) {super(message);}
}
