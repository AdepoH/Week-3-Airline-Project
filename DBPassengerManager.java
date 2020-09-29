import java.sql.*;
import java.util.*;

public class DBPassengerManager implements IPassengerManager{

	Connection connection;

	public DBPassengerManager(Connection connection){
		this.connection = connection;
	}
	public List<Passenger> getAll(){

	List<Passenger> passengers = new ArrayList<Passenger>();

	try{

	Statement statement = connection.createStatement();

	ResultSet resultSet = statement.executeQuery("select id, passengers_ID, name, email, address, phone from Passenger");

	while (resultSet.next()){

		int id = resultSet.getInt(1);
		String passengerID = resultSet.getString(2);
		String name = resultSet.getString(3);
		String email = resultSet.getString(4);
    String address = resultSet.getString(5);
    String phone = resultSet.getString(6);
		Passenger passenger = new Passenger(id, passengerID, name, email, address, phone);
		passengers.add(passenger);
	}
  }catch(SQLException ex){
  	ex.printStackTrace();
  }
  return passengers;
 }

  public boolean create(String passengerID, String name, String email, String address, String phone){
  	try{

  		Statement statement = connection.createStatement();

  		int count = statement.executeUpdate("insert into Passenger (passengers_ID, name, email, address, phone)values ('"+passengerID+"','"+name+"','"+email+"','"+address+"','"+phone+"')");
  		if(count > 0)
  			return true;

  	}catch(SQLException ex){
  		ex.printStackTrace();
  	}
    return false;
 }

  public boolean update(String passengerID, String name, String email, String address, String phone){

  	try{
  		Statement statement = connection.createStatement();

  		int count = statement.executeUpdate("update Passenger set name ='"+name+"',email='"+email+"',address='"+address+"',phone='"+phone+"' where passengers_ID ='"+passengerID+"'");
  		if(count > 0)
  			return true;
  	}catch(SQLException ex){
  		ex.printStackTrace();
  	}
  return false;
 }

  public boolean remove(String passengerID){

  	try{
  		Statement statement = connection.createStatement();

  		int count = statement.executeUpdate("delete from Passenger where passengers_ID='"+passengerID+"'");
 		
 		if(count > 0)
 			return true;
 	}
 	catch(SQLException ex){
 		ex.printStackTrace();
 	}
 	return false;
  }
  		
  public Passenger find(String passengerID){

 	Passenger passenger = null;

 	try{

 		Statement statement = connection.createStatement();

 		ResultSet resultSet = statement.executeQuery("select id, passengers_ID, name, email, address, phone from Passenger where passengerID='"+passengerID+"'");
		
		if (resultSet.next()){
  	   
     int id = resultSet.getInt(1);
     String name = resultSet.getString(3);
     String email = resultSet.getString(4);
     String address = resultSet.getString(5);
     String phone = resultSet.getString(6);
     passenger = new Passenger(id, passengerID, name, email, address, phone);
    }
     
 	}catch(SQLException ex){
 		ex.printStackTrace();
 	}
 	return passenger;
  }
 
    public void displayAll(){

  	List<Passenger> passengers = getAll();
  	for (Passenger passenger : passengers) {
  		System.out.printf("%d \t %s \t %s \t %s \t %s \t %s \n",passenger.getId(), passenger.getPassengerID(), passenger.getName(), passenger.getEmail(), passenger.getAddress(), passenger.getPhone());
  	}
  }

}
