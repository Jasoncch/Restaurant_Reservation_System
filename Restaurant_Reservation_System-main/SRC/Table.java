
public class Table {
	private int size;
	private String Tcode;
	private int Aticket=0;
	private boolean Plan=false;
	Table(String code){
		Tcode=code;
		char c=code.charAt(0);
		if(c=='T')
			this.size=2;
		else if(c=='F')
			this.size=4;
		else 
			this.size=8;
	}
	public int getSize() {
		return this.size;
	}
	public String getName() {
		return this.Tcode;
	}
	public void assignTicket(int t) {
		Aticket=t;
	}
	public int getA() {
		return Aticket;
	}
	public boolean isPlan() {
		return Plan;
	}
	public void suggest() {
		Plan=true;
	}
	public void unsuggest() {
		Plan=false;
	}
	public String toString() {
		return Tcode+" (Ticket "+ Integer.toString(Aticket)+")";
	}
}
