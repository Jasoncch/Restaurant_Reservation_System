

public class Day implements Cloneable {
	
	private int year;
	private int month;
	private int day;
	
	//Constructor
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";

	public void set(String sDay) //Set year,month,day based on a string like 01-Mar-2019
	{		
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
		this.year = Integer.parseInt(sDayParts[2]);
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;
	}

	public Day(String sDay) {
		set(sDay);
	}

	@Override
	public String toString() 
	{		
		return day+"-"+ MonthNames.substring((month-1)*3,(month)*3) + "-"+ year; // ,(month)*3
	}
	
	@Override
	public int hashCode() {
		return year*1000+month*100+day;
	}
	@Override
	public boolean equals(Object otherobj) {
	 Day otherDay = (Day)otherobj;
		
			return (this.day==otherDay.day&&this.year==otherDay.year&&this.month==otherDay.month);
	}
	
	public boolean isLater(Day d) {
		
		if(d.year>this.year)
			return true;
		else {
			if(d.year==this.year) {
				if(d.month>this.month)
					return true;
				else
				{
					if (d.month==this.month&&d.day>=this.day)
						return true;		
				}
			}
		}
		return false;
	}

	
	@Override
	public Day clone() 
	{
		Day copy=null;
		try {
		copy = (Day) super.clone();
		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return copy;
	}

	
}