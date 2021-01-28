public class RStatePending implements RState//It should be: public class RLeader implements Role 
{
	public String toString()
	{
		return "Pending";
	}	
	public void addTable(Table t) {}
	public void removeTable(Table t) {}
	public void removeAllTable() {}
}