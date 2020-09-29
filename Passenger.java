public class Passenger{

	private int id;
	private String passengerID;
	private String name;
	private String email; 
	private String address;
	private String phone;

	public Passenger(int id,String passengerID,String name,String email,String address,String phone){

		this.id = id;
		this.passengerID = passengerID;
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}
	
	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPassengerID(String passengerID){
		this.passengerID = passengerID;
	}

	public String getPassengerID(){
		return passengerID;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	@Override
	public String toString(){

		return String.format("%d \t %s \t %s \t %s \t %s \t %s \n",id, passengerID, name, email, address, phone);
	}
}