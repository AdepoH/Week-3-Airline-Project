import java.util.Date;

public class Booking{

	private int id;
	private int bookingNum;
	private String passengerID;
	private String flightNo;
	private Date bookingDate;
	private int seatNum;

	public Booking(int id, int bookingNum, String passengerID, String flightNo , Date bookingDate, int seatNum){

		this.bookingNum = bookingNum;
		this.passengerID = passengerID;
		this.flightNo = flightNo;
		this.bookingDate = bookingDate;
		this.seatNum = seatNum;
	}

	public Booking(int bookingNum, String passengerID, String flightNo , Date bookingDate, int seatNum){

		this.bookingNum = bookingNum;
		this.passengerID = passengerID;
		this.flightNo = flightNo;
		this.bookingDate = bookingDate;
		this.seatNum = seatNum;
	}

	public Booking(){	
	}

	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setBookingNum(int bookingNum){
		this.bookingNum=bookingNum;
	}
	public int getBookingNum(){
		return bookingNum;
	}
	public void setPassengerId(String passengerID){
		this.passengerID=passengerID;
	}
	public String getPassengerId(){
		return passengerID;
	}
	public void setFlightNo(String flightNo){
		this.flightNo=flightNo;
	}
	public String getFlightNo(){
		return flightNo;
	}
	public void setBookingDate(Date bookingDate){
		this.bookingDate=bookingDate;
	}
	public Date getBookingDate(){
		return bookingDate;
	}
	public void setSeatNum(int seatNum){
		this.seatNum=seatNum;
	}
	public int getSeatNum(){
		return seatNum;
	}

	@Override
    public String toString(){
    
    return String.format("%d \t %d \t %s \t %s \t %tc \t %d", id, bookingNum, passengerID, flightNo, bookingDate, seatNum);
	}
}