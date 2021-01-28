
public class ExTableCodeNotFound extends Exception{
	private static final long serialVersionUID = 1L;
	public ExTableCodeNotFound() {super(" not found!\n");}
	public ExTableCodeNotFound(String message) {super(message);}
}
