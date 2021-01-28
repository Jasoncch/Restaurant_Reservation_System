import java.util.Arrays;
//import java.util.Collections;

public class CmdCancel extends RecordedCommand {
	BookingOffice bo = BookingOffice.getInstance();
	TableManager TM;
	Reservation R;
	//private int total;
	String[] temp;
	private int tempTicket;
	String day;
	public void execute(String[] cmdParts)
	{
	try {
		 if(cmdParts.length<3) 
			throw new ExInsufficientArguments();
		day=cmdParts[1];
		Day tempDay = new Day(day);
		if(!SystemDate.getInstance().clone().isLater(tempDay))					//Date passed
			throw new ExDatepassed();
		TM=bo.findTM(cmdParts[1]);
		tempTicket=Integer.parseInt(cmdParts[2]);
		if(TM==null) 															//No booking on that day
			throw new ExBookingNotFound();
		if(TM.checkBooking(tempTicket)==null) 									//Booking cant be find on that day
			throw new ExBookingNotFound();
		R=TM.checkBooking(tempTicket);
		String str=TM.bufferAllTable(tempTicket);
		temp =str.split("\\|");
		TM.UnassignTable(tempTicket);								//Remove assignment from Table
		bo.removeReservation(R);									//Remove from BookingOffice
		TM.removeRes(R);											//Remove from TableManager;
		System.out.println("Done.");
		addUndoCommand(this); 
		clearRedoList();
		 
	} catch (NumberFormatException e) {
		System.out.println("Wrong number format!");
	} catch (ExInsufficientArguments e) {
		System.out.println(e.getMessage());
	} catch (ExDatepassed e) {
		System.out.println(e.getMessage());
	}catch (ExBookingNotFound e) {
 		System.out.println(e.getMessage());
	}
	
	}
	@Override
	public void undoMe()
	{	bo.addReservation(R,day);	
		Arrays.sort(temp,1,temp.length,new SortTable());			//Sort the table
		for(int i=1;i<temp.length;i++) {
			TM.assignTable(tempTicket, temp[i]); 					//assign Table
		}
		addRedoCommand(this); 
	}
	@Override
	public void redoMe()
	{		
		TM.UnassignTable(tempTicket);								//Remove assignment from Table
		bo.removeReservation(R);									//Remove from BookingOffice
		TM.removeRes(R);											//Remove from TableManager
		addUndoCommand(this); 
	 }
}

