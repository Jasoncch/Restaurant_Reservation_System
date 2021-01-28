import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class BookingOffice{

	private ArrayList<Reservation> allReservations;
	private ArrayList<TableManager> allTM;
	
	private static BookingOffice instance = new BookingOffice(); 
	private BookingOffice() { 
		allReservations = new ArrayList<Reservation>();
		allTM=new ArrayList<TableManager>();
	}
	public static BookingOffice getInstance(){
		return instance;   } 
	public Reservation addReservation(String name,String phone,int tot,String day)  
	{	
		if(findTM(day)==null) {		
			allTM.add(new TableManager(day)); 					//add a new TableManager per day;
		}
		Reservation r = new Reservation(name,phone,tot,day);
		allReservations.add(r);
		Collections.sort(allReservations); 
		TableManager TM=findTM(day);
		TM.addRes(r);
		return r; 
	}

	public void listReservations()
	{
		Collections.sort(allReservations,new SortReservations());
		System.out.println(Reservation.getListingHeader()); 
		for (Reservation r: allReservations)
			System.out.println(r.toString());
	}
	public void removeReservation(Reservation r) {
		 allReservations.remove(r);
	}
	public void addReservation(Reservation r,String day) {
		if(findTM(day)==null) {									//check for existing TableManager
							
			allTM.add(new TableManager(day)); 					//add a new TableManager per day;
		}
		
		allReservations.add(r);
		Collections.sort(allReservations);
		TableManager TM=findTM(day);
		TM.addRes(r);
	}
	public boolean findCustomer(String name,String tele,String Dinedate) {
		if (allReservations.size()==0)
			return false;
		else {
			for(Reservation R : allReservations) {
				if(R.getName().equals(name)&&R.getTele().equals(tele)&&R.getDay().equals(Dinedate))
					return true;
				}
			return false;
		} 
	}
	public TableManager findTM(String rday) {
		for(TableManager T:allTM) {
			if(T.getDay().equals(rday))
				return T;
		}
		return null;
	}
	
	
	
}