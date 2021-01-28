

public class CmdListTableAllocations implements Command {
	BookingOffice bo = BookingOffice.getInstance();
	public void execute(String[] cmdParts)
	{
		if(bo.findTM(cmdParts[1])==null){
			System.out.println("Allocated tables:\n[None]");
			
		}else {
		 TableManager TM=bo.findTM(cmdParts[1]);
		 TM.listTableAllocations();
		}
	}
}
