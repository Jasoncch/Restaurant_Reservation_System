
public class CmdStartNewDay extends RecordedCommand {

	String oday;
	String tday;
	BookingOffice bo = BookingOffice.getInstance();
	public void execute(String[] cmdParts)
	{	
	try {
		if (cmdParts.length<2)
			throw new ExInsufficientArguments();
		oday=SystemDate.getInstance().clone().toString();			//Store the day for undo
		SystemDate.createTheInstance(cmdParts[1]);
		tday=cmdParts[1];
		addUndoCommand(this);
		clearRedoList();

		System.out.println("Done.");
	}catch(ExInsufficientArguments e){
		System.out.println(e.getMessage());
	}
	
	}
	@Override
	public void undoMe()
	{	
		SystemDate.createTheInstance(oday);
		addRedoCommand(this);
	
	}
	@Override
	public void redoMe()
	{		
		SystemDate.createTheInstance(tday);
		addUndoCommand(this); 
 	}

}

