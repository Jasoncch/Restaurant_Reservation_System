
public interface RState //It should be: public interface Role 
{
	public String toString();
	public void addTable(Table t);
	public void removeTable(Table t);
	public void removeAllTable();

}