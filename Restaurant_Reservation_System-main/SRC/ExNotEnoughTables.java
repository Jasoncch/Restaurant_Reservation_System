
public class ExNotEnoughTables extends Exception{
	private static final long serialVersionUID = 1L;
	public ExNotEnoughTables() {super("Not enough tables");}
	public ExNotEnoughTables(String message) {super(message);}
}
