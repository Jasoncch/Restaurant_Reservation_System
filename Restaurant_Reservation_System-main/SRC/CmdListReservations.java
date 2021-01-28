
public class CmdListReservations implements Command  {
	
	BookingOffice bo = BookingOffice.getInstance();
	public void execute(String[] cmdParts)
	{
		 bo.listReservations();
	}
}
