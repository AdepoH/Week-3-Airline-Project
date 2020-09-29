import java.util.Date;

public class Flight{

	private int id;
	private String flightNum;
	private String regNum;
	private double amount;
	private String takeOffPoint;
	private Date departureTime;
	private String destination;
	private Date arrivalTime;


	public Flight(){

	}

	public Flight(double amount){
		this.amount =amount;
	}

	public Flight(int id, String flightNum, String regNum, double amount, String takeOffPoint, Date departureTime, String destination, Date arrivalTime){

		this.id=id;
		this.flightNum=flightNum;
		this.regNum=regNum;
		this.amount=amount;
		this.takeOffPoint=takeOffPoint;
		this.departureTime=departureTime;
		this.destination=destination;
		this.arrivalTime=arrivalTime;
	}

	public Flight(String flightNum, String regNum, double amount, String takeOffPoint, Date departureTime, String destination, Date arrivalTime){

		this.flightNum=flightNum;
		this.regNum=regNum;
		this.amount=amount;
		this.takeOffPoint=takeOffPoint;
		this.departureTime=departureTime;
		this.destination=destination;
		this.arrivalTime=arrivalTime;
	}


	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setFlightNum(String flightNum){
		this.flightNum=flightNum;
	}
	public String getFlightNum(){
		return flightNum;
	}
	public void setRegNum(String regNum){
		this.regNum=regNum;
	}
	public String getRegNum(){
		return regNum;
	}
	public void setAmount(double amount){
		this.amount=amount;
	}
	public double getAmount(){
		return amount;
	}
	public void setTakeOffPoint(String takeOffPoint){
		this.takeOffPoint=takeOffPoint;
	}
	public String getTakeOffPoint(){
		return takeOffPoint;
	}
	public void setDepartureTime(Date departureTime){
		this.departureTime=departureTime;
	}
	public Date getDepartureTime(){
		return departureTime;
	}
	public void setDestination( String destination){
		this.destination=destination;
	}
	public String getDestination(){
		return destination;
	}
	public void setArrivalTime(Date arrivalTime){
		this.arrivalTime=arrivalTime;
	}
	public Date getArrivalTime(){
		return arrivalTime;
	}
	@Override
    public String toString(){
    
    return String.format("%d \t %s \t %s \t %f \t %s \t %tc \t %s \t %tc ", id, flightNum, regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
	}
	/*public String toString(){
    
    return String.format("%s \t %s \t %f \t %s \t %tc \t %s \t %tc ", flightNum, regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
	}*/
}