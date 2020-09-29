import java.text.*;
import java.util.*;
import java.util.Scanner;
import java.util.Date;
import java.sql.*;

public class TestAirlineManagementSystem{

	static Scanner reader = new Scanner(System.in);

	static Connection connection;

	static IAircraftManager aircraftmanager; 
	static IFlightManager flightmanager;
	static IBookingManager bookingmanager;
	static IPassengerManager passengermanager;

	public static void main(String[] args) throws ClassNotFoundException, SQLException{

		// Load the JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

		// Connect to a database
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/AirlineManagementSystem?useTimeZone=UTC&serverTimezone=UTC" , "root", "Adepola11");
		System.out.println("Database connected");
	    aircraftmanager= new DBAircraftManager(connection); 
	    flightmanager= new DBFlightManager(connection);
	    bookingmanager= new DBBookingManager(connection);
	    passengermanager = new DBPassengerManager(connection);


		boolean flag = true;
		while(flag){
		    showMainMenu();
		    String option = reader.nextLine();
		    if(option.equals("0")) {
			    flag=false;
		    }else {
                showSubMenu(option);
		    }
		}
	}


	public static void showMainMenu(){

		System.out.println("Enter 0 to exit");
		System.out.println("Enter 1 to Manage Aircrafts");
		System.out.println("Enter 2 to Manage Flights");
		System.out.println("Enter 3 to Manage Bookings");
		System.out.println("Enter 4 to Manage Passenger");
	}

	public static void showSubMenu(String option){

		if(option.equals("1")){
            showManageAirCraftsMenu();
            String subOption = reader.nextLine();
            handleManageAirCraftsAction(subOption);
		}
		else if (option.equals("2")) {
            showManageFlightMenu();
            String subOption = reader.nextLine();
            handleManageFlightAction(subOption);
        }
        else if (option.equals("4")) {
            showManagePassengerMenu();
            String subOption = reader.nextLine();
            handleManagePassengerAction(subOption);
        }
        else if (option.equals("3")) {
            showManageBookingMenu();
            String subOption = reader.nextLine();
            handleManageBookingsAction(subOption);
        }
	
	}

	public static void showManageAirCraftsMenu(){

		System.out.println("Enter 0 to return to Main Menu");
		System.out.println("Enter 1 to create Aircrafts");
		System.out.println("Enter 2 to list Aircraft");
		System.out.println("Enter 3 to Remove Aircraft");
		System.out.println("Enter 4 to Find an Aircraft");
		System.out.println("Enter 5 to Update an Aircraft");
	}

	public static void handleManageAirCraftsAction(String action){
		try{
			if(action.equals("0")){
				return;
				//showMainMenu();
			}
			else if(action.equals("2")){
				aircraftmanager.displayAll();
			}
			else if(action.equals("3")){
				System.out.println("Enter the Registration Number of Aircraft to remove: ");
				String regNum = reader.nextLine();
				aircraftmanager.remove(regNum);
			}
			else if(action.equals("4")){
				System.out.println("Enter the Registration Number of Aircraft to Find: ");
				String regNum = reader.nextLine();
				aircraftmanager.find(regNum);
			}
			else if (action.equals("5")){
				System.out.println("Enter the Registration Number of Aircraft to update Aircraft: ");
				String regNum = reader.nextLine();
				System.out.println("Enter the Aircraft Name?");
				String name = reader.nextLine();
				System.out.println("Enter the Aircraft Capacity?");
				int capacity = reader.nextInt();
				reader.nextLine();
				aircraftmanager.update( name, regNum, capacity);
				
			}
			else if (action.equals("1")){
				System.out.println("Enter the Details of the Aircraft you want to create: ");

				System.out.println("Enter the Aircraft Name?");
				String name = reader.nextLine();
				System.out.println("Enter the Registration Number of Aircraft to create: ");
				String regNum = reader.nextLine();
				System.out.println("Enter the Aircraft Capacity?");
				int capacity = reader.nextInt();
				reader.nextLine();
				aircraftmanager.create( name, regNum, capacity);
			}
		}
		catch(Exception ex){
          System.out.println(ex.getMessage());      
		}
	}

	public static void showManageFlightMenu(){

		System.out.println("Enter 0 to return to Main Menu");
		System.out.println("Enter 1 to Create Flight");
		System.out.println("Enter 2 to List Flights");
		System.out.println("Enter 3 to Remove Flight");
		System.out.println("Enter 4 to Find a Flight");
		System.out.println("Enter 5 to Update a Flight");
	}

	public static void handleManageFlightAction(String action){
		try{
			if(action.equals("0")){
                return;
				//showMainMenu();
			}else if(action.equals("2")){
				flightmanager.displayAll();
			}
			else if(action.equals("3")){
				System.out.println("Enter the Flight Number to remove Flight?");
				String flightNum = reader.nextLine();
				flightmanager.remove(flightNum);
			}
			else if(action.equals("4")){
				System.out.println("Enter the Flight Number to Find Flight?");
				String flightNum = reader.nextLine();
				flightmanager.find(flightNum);
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
				System.out.println("Enter the Departure Date and Time (dd/mm/yyyy hh:mm:ss): ");
				String pDate = reader.nextLine();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
                java.util.Date utildate = formatter.parse(pDate);
				Date departureTime = new Date(utildate.getTime());
				System.out.println("Enter the Destination: ");
                String destination = reader.nextLine();
                System.out.println("Enter the Arrival Date and Time (dd/mm/yyyy hh:mm:ss): ");
				pDate = reader.nextLine();
                utildate = formatter.parse(pDate);
				Date arrivalTime = new Date(utildate.getTime());
				flightmanager.update(flightNum,regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
			}
			else if (action.equals("1")) {
				System.out.println("Create Your Flight Here (N.B: Enter the correct details)");

				System.out.println("Enter the flight Number:");
				String flightNum = reader.nextLine();
				System.out.println("Enter the Aircraft Registration Number:");
				String regNum = reader.nextLine();
				System.out.println("Enter the Flight Amount: ");
				double amount = reader.nextDouble(); reader.nextLine();
				System.out.println("Enter the Take_Off Point: ");
				String takeOffPoint = reader.nextLine();
				System.out.println("Enter the Departure Date and Time (dd/mm/yyyy hh:mm:ss): ");
				String pDate = reader.nextLine();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
                java.util.Date utildate = formatter.parse(pDate);
				Date departureTime = new Date(utildate.getTime());
				System.out.println("Enter the Destination: ");
				String destination = reader.nextLine();
				System.out.println("Enter the Arrival Date and Time (dd/mm/yyyy hh:mm:ss): ");
				pDate = reader.nextLine();
                utildate = formatter.parse(pDate);
				Date arrivalTime = new Date(utildate.getTime());
				flightmanager.create(flightNum,regNum, amount, takeOffPoint, departureTime, destination, arrivalTime);
			}
		}
        catch(Exception ex){
            System.out.println(ex.getMessage());      
		}
	}

	public static void showManageBookingMenu(){

	System.out.println("Enter 0 to return to Main Menu");
	System.out.println("Enter 1 to Create Booking");
	System.out.println("Enter 2 to List Booking");
	System.out.println("Enter 3 to Remove Booking");
	System.out.println("Enter 4 to Find a Booking");
	System.out.println("Enter 5 to Update a Booking");
	}

	public static void handleManageBookingsAction(String action){
		try{
			if(action.equals("0")){
				showMainMenu();
			}else if(action.equals("2")){
				bookingmanager.displayAll();
			}
			else if(action.equals("3")){
				System.out.println("Enter the Booking Number to remove?");
				int bookingNum = reader.nextInt();
				bookingmanager.remove(bookingNum);
			}
			else if(action.equals("4")){
				System.out.println("Enter the Id of Booking to find?");
				int bookingNum = reader.nextInt();
				bookingmanager.find(bookingNum);
			}
			else if(action.equals("5")){
				System.out.println("Enter the Booking Number to update your Booking: ");
				int bookingNum = reader.nextInt();

				System.out.println("Enter the passenger ID to update: ");
				String passengerID = reader.nextLine();
				System.out.println("Enter the Flight Number: ");
				String flightNo = reader.nextLine();
				System.out.println("Enter The Booking Date and Time (dd/mm/yyyy hh:mm:ss): ");
				String pDate = reader.nextLine();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
                java.util.Date utildate = formatter.parse(pDate);
				Date bookingDate = new Date(utildate.getTime());
				System.out.println("Enter the Seat Number: ");
				int seatNum = reader.nextInt();
				reader.nextLine();
				bookingmanager.update(bookingNum, passengerID , flightNo, bookingDate, seatNum);
			}
			else if (action.equals("1")){
				System.out.println("Create Your Booking Here (N.B: Enter the correct details!)");

				System.out.println("Enter the Booking Number:");
				int bookingNum = reader.nextInt();
				System.out.println("Enter the passenger ID to update: ");
				String passengerID = reader.nextLine();
				System.out.println("Enter the Flight Number: ");
				String flightNo = reader.nextLine();
				System.out.println("Enter The Booking Date and Time (dd/mm/yyyy hh:mm:ss): ");
				String pDate = reader.nextLine();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
                java.util.Date utildate = formatter.parse(pDate);
				Date bookingDate = new Date(utildate.getTime());
				System.out.println("Enter the Seat Number: ");
				int seatNum = reader.nextInt();
				reader.nextLine();
	
				bookingmanager.create(bookingNum, passengerID, flightNo, bookingDate, seatNum);
			}
		}
        catch(Exception ex){
            System.out.println(ex.getMessage());      
		}
	}

	public static void showManagePassengerMenu() {

    	System.out.println("Enter 0 to return to Main Menu");
        System.out.println("Enter 1 to List Passengers");
        System.out.println("Enter 2 to Create Passengers");
        System.out.println("Enter 3 to Remove Passengers");
        System.out.println("Enter 4 to Find an Passengers");
        System.out.println("Enter 5 to Update an Passengers");
    }

	public static void handleManagePassengerAction(String action) {

        if (action.equals("0")) {
            showMainMenu();
        }
        else if (action.equals("1")) {
           passengermanager.displayAll();
        }
        else if(action.equals("3")){
            System.out.println("Enter the Passenger ID of Passenger to remove Existing Passenger: ");
            String passengerID = reader.nextLine();
            passengermanager.remove(passengerID);
        }
        else if(action.equals("4")){
            System.out.println("Enter the Passenger ID of Passenger to check if it Exist: ");
            String passengerID = reader.nextLine();
            passengermanager.find(passengerID);
        }
        else if(action.equals("5")){
            System.out.println("Enter the Passenger ID of Passenger to update Paasenger's Details: ");
			String passengerID = reader.nextLine();

            System.out.println("Enter the Passenger Name: ");
            String name = reader.nextLine();
            System.out.println("Enter the Passenger E-Mail Address: ");
            String email = reader.nextLine();
            System.out.println("Enter the Passenger Address: ");
            String address = reader.nextLine();
            System.out.println("Enter the Passenger Phone: ");
            String phone = reader.nextLine();
            reader.nextLine();
            passengermanager.update(passengerID, name, email, address, phone);
        } 
        else if (action.equals("2")) {
            System.out.println("Fill the form bellow to generate A passenger ID (N.B: Enter the correct details!)");

            System.out.println("Enter the Passenger Passenger_ID: ");
            String passengerID = reader.nextLine();
            System.out.println("Enter the Passenger Name: ");
            String name = reader.nextLine();
            System.out.println("Enter the Passenger E-Mail Address: ");
            String email = reader.nextLine();
            System.out.println("Enter the Passenger Address: ");
            String address = reader.nextLine();
            System.out.println("Enter the Passenger Phone Number: ");
            String phone = reader.nextLine();
            reader.nextLine();
            passengermanager.create(passengerID, name, email, address, phone);
        }
    }

	

	/*aircraftManager.create("Learjet", "CHM101", 300);
	aircraftManager.create("Cirrus", "CHM232", 450);  
	aircraftManager.create("Blackbird", "CHM304", 500);  
	aircraftManager.create("Lockheed", "CHM362", 650);  
	aircraftManager.create("Boeing", "CHM332", 800);    
	// aircraftManager.update(2, "Cirrus", "CHM232", 450); 
	// aircraftManager.remove("CHM362"); 
	aircraftManager.displayAll();

	/*Aircraft aircraft =  aircraftManager.find("CHM345");
	if(aircraft !=null){
		System.out.println("Record found!");
		System.out.println(aircraft.toString());
	}	*/
	
}