import java.util.ArrayList;

public class RStateTableAllocated implements RState//It should be: public class RLeader implements Role 
{
	private ArrayList<Table> allTable=new ArrayList<Table>();
	public String toString()
	{
		return "Table assigned: "+getTable();
	}	
	public String getTable() {
		String result=" ";
		for(Table t:allTable)
			result+=t.getName()+" ";
		return result;
	}
	public void addTable(Table t) {
		this.allTable.add(t);
	}
	public void removeTable(Table t) {
		this.allTable.remove(t);
	}
	public void removeAllTable() {
		this.allTable.clear();
	}
}