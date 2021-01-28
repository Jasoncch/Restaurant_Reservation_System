

public class Reservation implements Comparable<Reservation> {

	private String guestName;
	private String phoneNumber;
	private int ticketCode;
	private int totPersons;
	private Day dateDine;
	private Day dateRequest;
	private RState Status;

	public Reservation(String guestName, String phoneNumber, int totPersons, String sDateDine)
	{	
		this.guestName=guestName;
		this.phoneNumber=phoneNumber;
		this.totPersons=totPersons;
		dateDine = new Day(sDateDine);
		this.ticketCode=BookingTicketController.takeTicket(this.dateDine);
		setStatus(new RStatePending());
		dateRequest=SystemDate.getInstance().clone();
	
	}
	public String getName() {
		return guestName;
	}
	public String getTele() {
		return phoneNumber;
	}
	public String getState() {
		return this.Status.toString();
	}
	public RState getRState() {
		return this.Status;
	}
	public int getTicket() {
		return this.ticketCode;
	}
	public void takeTicket(int t) {
		this.ticketCode=t;
	}
	public int getTotal() {
		return this.totPersons;
	}
    public String showDine() {
    	return dateDine.toString()+" (Ticket "+Integer.toString(ticketCode)+")"; 
    }
	public void check() {
		System.out.println(dateRequest.toString());
	}
	public String getDay() {
		return this.dateDine.toString(); 
	}
	public void assignTable(Table t) {
		Status.addTable(t);
	}
	public void UnassignAllTable() {
		Status.removeAllTable();
	}
	public void setStatus(RState RS) {
		this.Status = RS;
	}
	@Override
	public String toString() 
	{//Learn: "-" means left-aligned
	return String.format("%-13s%-11s%-14s%-25s%4d       %s", guestName, phoneNumber, dateRequest, dateDine+String.format(" (Ticket %d)",ticketCode ), totPersons, this.Status.toString());
	}
	public static String getListingHeader() 
	{
	return String.format("%-13s%-11s%-14s%-25s%-11s%s", "Guest Name", "Phone","Request Date","Dining Date and Ticket","#Persons","Status");
	}
	public int compareTo(Reservation another)
	{
	return this.guestName.compareTo(another.guestName);
	}
	

}
