import java.util.ArrayList;
//import java.util.Collections;
//import java.util.*;

public class TableManager {
	private ArrayList<Table> allTables=new ArrayList<Table>();
	private Day date;
	private ArrayList<Reservation> allRes=new ArrayList<Reservation>();
	
	TableManager(String day)
	{
		this.date= new Day(day);
		IniTable();
	}
	public void IniTable() {								//initiate tables
		allTables.add(new Table("T01"));
		allTables.add(new Table("T02"));
		allTables.add(new Table("T03"));
		allTables.add(new Table("T04"));
		allTables.add(new Table("T05"));
		allTables.add(new Table("T06"));
		allTables.add(new Table("T07"));
		allTables.add(new Table("T08"));
		allTables.add(new Table("T09"));
		allTables.add(new Table("T10"));
		allTables.add(new Table("F01"));
		allTables.add(new Table("F02"));
		allTables.add(new Table("F03"));
		allTables.add(new Table("F04"));
		allTables.add(new Table("F05"));
		allTables.add(new Table("F06"));
		allTables.add(new Table("H01"));
		allTables.add(new Table("H02"));
		allTables.add(new Table("H03"));
	}
	public Table findTable(String Tname) {					//find table with tableCode
		for(Table t:allTables) {
			if(t.getName().equals(Tname))
				return t;
		}
		return null;
	}
	public int findTotalTable(int size) {					//get number of available table with size x
		int count=0;
		for(Table t:allTables) {
			if(t.getSize()==size&&t.getA()==0)
				count++;
		}
		return count;
	}
	public String getTable(int size,int num) {				//get x tables with size x
		int count =num;
		String output="";
		for(Table t:allTables) {
			if(t.getSize()==size&&!t.isPlan()&&count>0&&t.getA()==0) {
				t.suggest();
				output+=t.getName()+" ";
				count--;
			}
		}
		return output;
	}
	public void UnsuggestAll() {
		for(Table t:allTables) {
			if(t.isPlan())
				t.unsuggest();
		}
	}
	
	public Reservation findRes(int ticket) {				//find reservation with ticket
		for(Reservation R:allRes) {
			if(R.getTicket()==ticket)
				return R;
		}
		return null;
	}
	public String getDay() {
		return date.toString();
	}
	
	public void addRes(Reservation R) {
		allRes.add(R);
	}
	public void removeRes(Reservation R) {
		allRes.remove(R);
	}
	
	public void assignTable(int ticket,String table) {
		Table t=findTable(table);
		t.assignTicket(ticket);
		Reservation R= findRes(ticket);
		if(R.getRState() instanceof RStatePending) {
			R.setStatus(new RStateTableAllocated());
		}
		R.assignTable(t);
	}
	public void UnassignTable(int ticket) {
		for(Table t:allTables)
			if(t.getA()==ticket) {
		t.assignTicket(0);
		}
		Reservation R= findRes(ticket);
		if(R.getRState() instanceof RStateTableAllocated) {
			R.setStatus(new RStatePending());
		}
		R.UnassignAllTable();
	}
	public String bufferAllTable(int ticket) {
		String output="buffer|";
		for(Table t:allTables)
			if(t.getA()==ticket) {
				output+=t.getName()+"|";
		}
		return output;
	}
	public int getPendingRes() {								//get total number of pending reservation
		int count=0;
		for(Reservation R:allRes) {
			if(R.getState().equals("Pending"))
				count+=1;
		}
		return count;
	}
	public int getPendingTotal() {								//get total number of people in pending reservations
		int count=0;
		for(Reservation R:allRes) {
			if(R.getState().equals("Pending"))
				count+=R.getTotal();
		}
		return count;
	}
	public Reservation checkExsit(int ticket) {					//check if the reservation has assigned table
		for(Reservation R: allRes)
			if (R.getTicket()==ticket&&R.getRState()instanceof RStateTableAllocated)
				return R;
		return null;
	}
	public Reservation checkBooking(int ticket) {				//check if the reservation is on that day
		for(Reservation R: allRes)
			if (R.getTicket()==ticket)
				return R;
		return null;
	}
	
	
	public void listTableAllocations() {						//print tables
		System.out.println("Allocated tables:");
		boolean isEmpty=true;
		for(Table t:allTables) {
			if(t.getA()!=0) {
				System.out.println(t.toString());
				isEmpty=false;
			}
		}
		if(isEmpty)
			System.out.println("[None]\n");
		System.out.println("\nAvailable tables:");
		isEmpty=true;
		for(Table t:allTables) {
			if(t.getA()==0) {
				System.out.print(t.getName()+" ");	
				isEmpty=false;
			}
		}
		if(isEmpty)
			System.out.println("[None]\n");
		String output=String.format("\n\nTotal number of pending requests = %d (Total number of persons = %d)",getPendingRes(),getPendingTotal());
		System.out.println(output);
	}
	
}
