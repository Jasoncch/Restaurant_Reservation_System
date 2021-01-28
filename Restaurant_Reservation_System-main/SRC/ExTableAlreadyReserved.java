
public class ExTableAlreadyReserved extends Exception{
	private static final long serialVersionUID = 1L;
	public ExTableAlreadyReserved() {super(" is already reserved by another booking!\n");}
	public ExTableAlreadyReserved(String message) {super(message);}
}
