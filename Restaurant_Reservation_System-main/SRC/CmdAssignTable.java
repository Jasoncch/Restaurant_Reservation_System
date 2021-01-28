
public class CmdAssignTable extends RecordedCommand {
	BookingOffice bo = BookingOffice.getInstance();
	TableManager TM;
	private int total;
	private String[] temp= {"1","1","1","1","1","1","1","1","1","1","1","1"};
	private int tempTicket;
	private int size;
	public void execute(String[] cmdParts)
	{
	
	try {
		total=cmdParts.length;
		if(cmdParts.length<4) 
			throw new ExInsufficientArguments(); 
		Day tempDay = new Day(cmdParts[1]);
		if(!SystemDate.getInstance().clone().isLater(tempDay))
			throw new ExDatepassed();
		 TM=bo.findTM(cmdParts[1]);
		 tempTicket=Integer.parseInt(cmdParts[2]); 
		 //System.out.println("Finding TM for "+cmdParts[1]);
		 if(TM==null) 															//No booking on that day
			 throw new ExBookingNotFound();
		 if(TM.checkBooking(tempTicket)==null) 									//Booking cant be find on that day
			 throw new ExBookingNotFound();
		 if(TM.checkExsit(tempTicket)!=null) {									//The Ticket already has table assigned
			 throw new ExTableAlreadyAssigned();
		 }
		
			 
			 
		size=0;
		for(int i=3;i<total;i++) {
			 temp[i]=cmdParts[i];
			 if(TM.findTable(temp[i])==null) {									//Table code not found 
				 System.out.print("Table code "+temp[i]);
				 throw new ExTableCodeNotFound();
			 	}
			 if(TM.findTable(temp[i]).getA()!=0) {								//Table found but assigned
				 System.out.print("Table "+temp[i]);
				 throw new ExTableAlreadyReserved();
			 	} 
			 size+=TM.findTable(temp[i]).getSize();								//count the size of all tables
		 }
		 if(size<TM.checkBooking(tempTicket).getTotal())					
			 throw new ExNotEnoughSeats();
		 
		 for(int i=3;i<total;i++) {
		 TM.assignTable( Integer.parseInt(cmdParts[2]),cmdParts[i]);			//assign the table
		 //System.out.println("assigning "+cmdParts[i]+"to ticket"+cmdParts[2]);
		 }
		 System.out.println("Done.");
		 addUndoCommand(this); 
		clearRedoList();
		 
		
	 } catch (NumberFormatException e) {
	 		System.out.println("Wrong number format!");
	 } catch (ExBookingNotFound e) {
	 		System.out.println(e.getMessage());
	 } catch (ExDatepassed e) {
	 		System.out.println(e.getMessage());
	 } catch (ExTableAlreadyAssigned e) {
	 		System.out.println(e.getMessage());	
	 }catch (ExTableCodeNotFound e) {
	 		System.out.print(e.getMessage());
	 }catch (ExTableAlreadyReserved e) {
	 		System.out.print(e.getMessage());
	 } catch (ExNotEnoughSeats e) {
	 		System.out.println(e.getMessage());
	 }  catch (ExInsufficientArguments e) {
	 		System.out.println(e.getMessage());
	 }
	
	}
	
	@Override
	public void undoMe()
	{	
		TM.UnassignTable(tempTicket);
		addRedoCommand(this); 
	}
	@Override
	public void redoMe()
	{		
		for(int i=3;i<total;i++) {
			TM.assignTable(tempTicket,temp[i]);
		}
		addUndoCommand(this); 
		
	 }
	
	
	
	
	
	
	
	
	
	
	
}


