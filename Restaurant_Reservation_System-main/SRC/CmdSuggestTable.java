
public class CmdSuggestTable implements Command  {
	BookingOffice bo = BookingOffice.getInstance();
	TableManager TM;
	private int target;
	private int tempTicket;
	
	public void execute(String[] cmdParts)
	{
	try {
		if(cmdParts.length<3) 													//check input length
			throw new ExInsufficientArguments(); 
		Day tempDay = new Day(cmdParts[1]);
		if(!SystemDate.getInstance().clone().isLater(tempDay))					//Check the date
			throw new ExDatepassed();
		TM=bo.findTM(cmdParts[1]);												//get the TableManager of the day
		tempTicket=Integer.parseInt(cmdParts[2]); 
		if(TM==null) 															//No booking on that day
			throw new ExBookingNotFound();
		if(TM.checkBooking(tempTicket)==null) 									//Booking cant be find on that day
			throw new ExBookingNotFound();
		if(TM.checkExsit(tempTicket)!=null) {									//The Ticket already has table assigned
			throw new ExTableAlreadyAssigned();
		}
		target=TM.checkBooking(tempTicket).getTotal();
		if(target%2==1)															//Deal with tricky cases
			target++;
		int T=0,F=0,H=0;
		int t,f,h; 
		t=TM.findTotalTable(2);
		f=TM.findTotalTable(4);
		h=TM.findTotalTable(8);
			while(target>=8&&h>0) {												//assign H tables
				target-=8;
				H++;
				h--;
			}
			while(target>=4&&f>0) {												//assign F tables
				target-=4;
				F++;
				f--;
			}
			while(target>0&&t>0) {												//assign T tables
				target-=2;
				T++;
				t--;
			}
			if(target>0&&f>0) {													//assign F tables when there are no T tables left
				target-=4;
				F++;
				f--;
			}else if(target>0&&h>0) {											//assign H tables when there are only H tables left
				target-=8;
				H++;
				h--;
			}else if(target>0) {												//Not all people can be assigned
				System.out.println("Suggestion for "+TM.checkBooking(tempTicket).getTotal()+" persons: ");
				throw new ExNotEnoughTables();
			}
		String output=" ";
		if(H>0)																	//get tables from TableManager base on the result
			output+=TM.getTable(8, H);
		if(F>0)
			output+=TM.getTable(4,F);
		if(T>0)
			output+=TM.getTable(2,T);
		System.out.println("Suggestion for "+TM.checkBooking(tempTicket).getTotal()+" persons:"+output);
		TM.UnsuggestAll();
	 } catch (ExNotEnoughTables e) {
		 	System.out.println(e.getMessage());
	 } catch (NumberFormatException e) {
	 		System.out.println("Wrong number format!");
	 } catch (ExBookingNotFound e) {
		 	System.out.println(e.getMessage());
	 } catch (ExDatepassed e) {
	 		System.out.println(e.getMessage());
	 } catch (ExTableAlreadyAssigned e) {
	 		System.out.println(e.getMessage());	
	 } catch (ExInsufficientArguments e) {
	 		System.out.println(e.getMessage());
	 }
	
	}
}
