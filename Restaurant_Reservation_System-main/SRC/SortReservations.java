import java.util.*; 
public class SortReservations implements Comparator<Reservation>{
	public int compare(Reservation a, Reservation b) 
    { 
         if(!a.getName().equals(b.getName()))
        	return a.getName().compareTo(b.getName());
         else
        	 if (!a.getTele().equals(b.getTele()))
        		 return a.getTele().compareTo(b.getTele());
        	 else
        		 if(new Day(a.getDay()).isLater(new Day(b.getDay())))
        			 return -1;
        		 else
        			 return 1;
    } 
}

//name
//phone
//date