import java.util.ArrayList;
public abstract class RecordedCommand implements Command{
	public abstract void undoMe();
	public abstract void redoMe();
	private static ArrayList<RecordedCommand> undoList=new ArrayList<>();
	private static ArrayList<RecordedCommand> redoList=new ArrayList<>();
	protected static void addUndoCommand(RecordedCommand cmd) {undoList.add(cmd);}
	protected static void addRedoCommand(RecordedCommand cmd) {redoList.add(cmd);}
	protected static void clearRedoList() {redoList.clear();}
	public static void undoOneCommand() {undoList.remove(undoList.size()-1).undoMe();}
	public static void redoOneCommand() {redoList.remove(redoList.size()-1).redoMe();}	
	public static boolean isUempty() {
		return (undoList.size()==0);
	}
	public static boolean isRempty() {
		return (redoList.size()==0);
	}
	public static void noU() {
		System.out.print("Nothing to undo.\n");
	}
	public static void noR() {
		System.out.print("Nothing to redo.\n");
	}
}