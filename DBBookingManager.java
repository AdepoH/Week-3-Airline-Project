import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.*;

public class DBBookingManager implements IBookingManager{
	Connection connection;

	public DBBookingManager(Connection connection){
		this.connection=connection;
	}

	public List<Booking> getAll(){
		List<Booking>bookings = new ArrayList<Booking>();
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			ResultSet resultSet = statement.executeQuery("select id, bookingnum, passengerID, flight_Number, Date, seat_Number from booking");

			// Iterate through the result 
			while (resultSet.next()){
			int id=resultSet.getInt(1);
			int bookingNum=resultSet.getInt(1);
		    String passengerID=resultSet.getString(2);
		    String flightNo=resultSet.getString(2);
		    Date bookingDate=resultSet.getDate(4);
		    int seatNum=resultSet.getInt(5);
		    Booking booking = new Booking(id, bookingNum, passengerID, flightNo, bookingDate, seatNum);
		    bookings.add(booking);
			}
   		}
    	catch(SQLException ex){
       		ex.printStackTrace();
    	}
    	return bookings;
   	}

	public boolean create(int bookingNum, String passengerID, String flightNo , Date bookingDate, int seatNum){
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			int count= statement.executeUpdate("insert into booking (bookingnum, passengerID, flight_Number, Date, seat_Number)values ('"+bookingNum+"','"+passengerID+"','"+flightNo+"','"+bookingDate+"','"+seatNum+"')");
			if(count >0)
			return true;
			}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}
	public boolean update(int bookingNum, String passengerID, String flightNo , Date bookingDate, int seatNum){
	    try{
		    // Create a statement
		    Statement statement = connection.createStatement();

		    // Execute a statement
		    int count= statement.executeUpdate("update booking set bookingnum='"+bookingNum+"' PassengerID='"+passengerID+"',flight_Number='"+flightNo+"',Date ='"+bookingDate+"',seat_Number='"+seatNum+"' where bookingnum='"+bookingNum+"'");
		    if(count >0)
		       return true;
	    }
	    catch(SQLException ex){
	        ex.printStackTrace();
	    }
	    return false;
   }

	public boolean remove(int bookingNum){
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			int count= statement.executeUpdate("remove from booking where bookingnum = ('"+bookingNum+"'");
			if(count >0)
			return true;
			}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}

	public Booking find(int bookingNum){

		try{
		    // Create a statement
		    Statement statement = connection.createStatement();

		    // Execute a statement
		    ResultSet resultSet = statement.executeQuery("select id, bookingnum, passengerID, flight_Number, Date, seat_Number from booking where bookingnum = '"+bookingNum+"'");

		    // Iterate through the result 
		    while (resultSet.next()){

		    int id=resultSet.getInt(1);
			bookingNum=resultSet.getInt(1);
		    String passengerID=resultSet.getString(2);
		    String flightNo=resultSet.getString(2);
		    Date bookingDate=resultSet.getDate(4);
		    int seatNum=resultSet.getInt(5);
		    Booking booking = new Booking(id, bookingNum, passengerID, flightNo, bookingDate, seatNum);
		    return booking;

	    	}
	    }
	    catch(SQLException ex){
	        ex.printStackTrace();
		}
    	return null;
	}

	public void displayAll(){
		List<Booking> bookings = getAll();
		for(Booking b: bookings)

			System.out.printf("%d \t %d \t %s \t %s \t %tc \t %d \n", b.getId(), b.getBookingNum(), b.getPassengerId(), b.getFlightNo(), b.getBookingDate(), b.getSeatNum());
	}
}