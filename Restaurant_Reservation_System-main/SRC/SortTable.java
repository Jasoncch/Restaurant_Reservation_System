import java.util.*; 


public class SortTable implements Comparator<String> 
{ 
    public int compare(String a, String b) 
    { 	
    	int tempA,tempB;
		if(a.charAt(0)=='T')
			tempA=2;
		else if(a.charAt(0)=='F')
			tempA=4;
		else 
			tempA=8;
		if(b.charAt(0)=='T')
			tempB=2;
		else if(b.charAt(0)=='F')
			tempB=4;
		else 
			tempB=8;
		return tempB-tempA;
    } 
} 