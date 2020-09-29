import java.util.*;
import java.sql.*;
import java.util.Date;
import java.text.*;

public class DBFlightManager implements IFlightManager{

    List<Flight> flights;
    DBAircraftManager aircraftmanager; 
    static Scanner reader = new Scanner(System.in);

	Connection connection;
	public DBFlightManager(Connection connection){
		this.connection=connection;
	}

	public List<Flight> getAll(){
		flights = new ArrayList<Flight>();
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			ResultSet resultSet = statement.executeQuery("select id, flightnum, aircraftRegNum, amount, takeoffpoint, departureTime, destination, arrivalTime from flight");

			// Iterate through the result 
			while (resultSet.next()){
			int id=resultSet.getInt(1);
		    String flightnum=resultSet.getString(2);
		    String regNum=resultSet.getString(3);
		    double amount=resultSet.getDouble(4);
		    String takeOffPoint=resultSet.getString(5);
		    Date departureTime=resultSet.getDate(6);
		    String destination=resultSet.getString(7);
		    Date arrivalTime=resultSet.getDate(8);
		    Flight flight = new Flight(id, flightnum, regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
		    flights.add(flight);
			}
   		}
    	catch(SQLException ex){
       		ex.printStackTrace();
    	}
    	return flights;
   	}

   	public DBFlightManager(DBAircraftManager aircraftmanager) {
        this.aircraftmanager = aircraftmanager;
    }
    public void displayAll(Flight f){
    	System.out.printf("%d %s %s %f %s %tc %s %tc \n", f.getId(), f.getFlightNum(), f.getRegNum(), f.getAmount(), f.getTakeOffPoint(), f.getDepartureTime(), f.getDestination(), f.getArrivalTime());
    }
    public void displayAll(){
		List<Flight> flights = getAll();
		for(Flight f: flights)

			System.out.printf("%d \t %s \t %f \t %s \t %tc \t %s \t %tc ", f.getId(), f.getFlightNum(), f.getRegNum(), f.getAmount(), f.getTakeOffPoint(), f.getDepartureTime(), f.getDestination(), f.getArrivalTime());
	}


	public boolean create(String flightNum, String regNum, double amount, String takeOffPoint, Date departureTime, String destination, Date arrivalTime){
		aircraftmanager = new DBAircraftManager(connection);
        Aircraft aircraft = aircraftmanager.find(flightNum);
        if(aircraft == null){
            System.out.printf("Flight %s cannot be found \n",flightNum); 
            return false;
        }   
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			int count= statement.executeUpdate("insert into flight (flightnum, aircraftRegNum, amount, takeoffpoint, departureTime, destination, arrivalTime)values ('"+flightNum+"','"+amount+"''"+takeOffPoint+"','"+departureTime+"''"+destination+"','"+arrivalTime+"')");
			if(count >0)
			return true;
			}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}

	public Flight find(String flightNum){

		try{
		    // Create a statement
		    Statement statement = connection.createStatement();

		    // Execute a statement
		    ResultSet resultSet = statement.executeQuery("select id, flightnum, aircraftRegNum, amount, takeoffpoint, departureTime, destination, arrivalTime from flight where flightnum = '"+flightNum+"'");

		    // Iterate through the result 
		    while (resultSet.next()){

		    int id=resultSet.getInt(1);
		    flightNum=resultSet.getString(2);
		    String regNum=resultSet.getString(3);
		    double amount=resultSet.getDouble(4);
		    String takeOffPoint=resultSet.getString(5);
		    Date departureTime=resultSet.getDate(6);
		    String destination=resultSet.getString(7);
		    Date arrivalTime=resultSet.getDate(8);
		    Flight flight = new Flight(id, flightNum, regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
		    return flight;

	    	}
	    }
	    catch(SQLException ex){
	        ex.printStackTrace();
		}
    	return null;
	}
	public boolean update(String flightNum, String regNum, double amount, String takeOffPoint, Date departureTime, String destination, Date arrivalTime){
	    try{
		    // Create a statement
		    Statement statement = connection.createStatement();

		    // Execute a statement
		    int count= statement.executeUpdate("update flight set flightnum='"+flightNum+"',aircraftRegNum='"+regNum+"',amount='"+amount+"',takeoffpoint='"+takeOffPoint+"',departureTime='"+departureTime+"',destination='"+destination+"',arrivalTime='"+arrivalTime+"' where flightnum='"+flightNum+"'");
		    if(count >0)
		       return true;
	    }
	    catch(SQLException ex){
	        ex.printStackTrace();
	    }
	    return false;
   }

	public boolean remove(String flightNum){
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			int count= statement.executeUpdate("remove from flight where flightnum = '"+flightNum+"'");
			if(count >0)
			return true;
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}

	public void showManageFlightsMenu(){

		System.out.println("Enter 0 to return to Main Menu");
		System.out.println("Enter 1 to Create Flight");
		System.out.println("Enter 2 to List Flights");
		System.out.println("Enter 3 to Remove Flight");
		System.out.println("Enter 4 to Find a Flight");
		System.out.println("Enter 5 to Update a Flight");
	}

	public void handleManageFlightsAction(String action){
		try{
			if(action.equals("0")){
                return;
				//showMainMenu();
			}else if(action.equals("2")){
				displayAll();
			}
			else if(action.equals("3")){
				System.out.println("Enter the Flight Number to remove Flight?");
				String flightNum = reader.nextLine();
				remove(flightNum);
			}
			else if(action.equals("4")){
				System.out.println("Enter the Flight Number to Find Flight?");
				String flightNum = reader.nextLine();
				find(flightNum);
			}
			else if(action.equals("5")){
				System.out.println("Enter the Flight Number to update Flight: ");
				String flightNum = reader.nextLine();

				System.out.println("Enter the Aircraft Registration Number:");
				String regNum = reader.nextLine();
				System.out.println("Enter the Flight Amount: ");
				double amount = reader.nextDouble();
				reader.nextLine();
				System.out.println("Enter the Take_Off Point: ");
				String takeOffPoint = reader.nextLine();
				System.out.println("Enter Date and Time (yyyy-mm-dd hh:mm:ss): ");
				String pDate = reader.nextLine();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                java.util.Date utildate = formatter.parse(pDate);
				Date departureTime = new Date(utildate.getTime());
				System.out.println("Enter the Destination: ");
                String destination = reader.nextLine();
                System.out.println("Enter Date and Time (yyyy-mm-dd hh:mm:ss): ");
				pDate = reader.nextLine();
                utildate = formatter.parse(pDate);
				Date arrivalTime = new Date(utildate.getTime());
				update(flightNum,regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
			}
			else if (action.equals("1")) {
				System.out.println("Enter the flight Number:");
				String flightNum = reader.nextLine();
				System.out.println("Enter the Aircraft Registration Number:");
				String regNum = reader.nextLine();
				System.out.println("Enter the Flight Amount: ");
				double amount = reader.nextDouble(); reader.nextLine();
				System.out.println("Enter the Take_Off Point: ");
				String takeOffPoint = reader.nextLine();
				System.out.println("Enter Date and Time (yyyy-mm-dd hh:mm:ss): ");
				String pDate = reader.nextLine();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                java.util.Date utildate = formatter.parse(pDate);
				Date departureTime = new Date(utildate.getTime());
				System.out.println("Enter the Destination: ");
				String destination = reader.nextLine();
				pDate = reader.nextLine();
                utildate = formatter.parse(pDate);
				Date arrivalTime = new Date(utildate.getTime());
				create(flightNum,regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
			}
		}
        catch(Exception ex){
            System.out.println(ex.getMessage());      
		}
	}

}