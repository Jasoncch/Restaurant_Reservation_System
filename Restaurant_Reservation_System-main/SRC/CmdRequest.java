
public class CmdRequest extends RecordedCommand  {
	Reservation r;
	BookingOffice bo = BookingOffice.getInstance();
	String day;	
	public void execute(String[] cmdParts)
	{
	
	try {
		if(cmdParts.length<5) 																//Check the input length
			throw new ExInsufficientArguments();
		Day temp = new Day(cmdParts[4]);
		if(!SystemDate.getInstance().clone().isLater(temp))									//Check the date
			throw new ExDatepassed();
		if(bo.findCustomer(cmdParts[1],cmdParts[2],cmdParts[4]))							//Check repeat booking 
			throw new ExBookingAlreadyExist();
		day=cmdParts[4];
		r=new Reservation(cmdParts[1],cmdParts[2], Integer.parseInt(cmdParts[3]), cmdParts[4]);
		bo.addReservation(r,day);
		addUndoCommand(this); 
		clearRedoList(); 
		String output=String.format("Done. Ticket code for %s: %d\n",r.getDay(), r.getTicket());
		System.out.println(output);
		//System.out.println("Done. Ticket code for "+r.getDay()+": "+r.getTicket());
	} catch (NumberFormatException e) {
		System.out.println("Wrong number format!");
	} catch (ExBookingAlreadyExist e) {
		System.out.println(e.getMessage());
	} catch (ExDatepassed e) {
		System.out.println(e.getMessage());
	
	} catch (ExInsufficientArguments e) {
		System.out.println(e.getMessage());
	}
	}
	@Override
	public void undoMe()
	{	
		BookingTicketController.UntakeTicket(new Day(day));
		bo.removeReservation(r);
		addRedoCommand(this);
	
	}
	@Override
	public void redoMe()
	{	
		r.takeTicket(BookingTicketController.takeTicket(new Day(day)));
		bo.addReservation(r,day);
		addUndoCommand(this);	
	}
	
	
}
