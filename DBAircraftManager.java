import java.util.*;
import java.sql.*;

public class DBAircraftManager implements IAircraftManager{

	Connection connection;

	public DBAircraftManager(Connection connection){
		this.connection=connection;
	}

	public List<Aircraft> getAll(){
		List<Aircraft>aircrafts = new ArrayList<Aircraft>();
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			ResultSet resultSet = statement.executeQuery("select id, name, regnum, capacity from aircraft");

			// Iterate through the result 
			while (resultSet.next()){
			int id=resultSet.getInt(1);
		    String name=resultSet.getString(2);
		    String regNum=resultSet.getString(3);
		    int capacity=resultSet.getInt(4);
		    Aircraft aircraft = new Aircraft(id, name, regNum, capacity);
		    aircrafts.add(aircraft);
			}
   		}
    	catch(SQLException ex){
       		ex.printStackTrace();
    	}
    	return aircrafts;
   	}

	public boolean create(String name, String regNum, int capacity){
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			int count= statement.executeUpdate("insert into aircraft (name, regnum, capacity)values ('"+name+"','"+regNum+"','"+capacity+"')");
			if(count >0)
			return true;
			}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}
	public boolean update(String name, String regNum, int capacity){
	    try{
		    // Create a statement
		    Statement statement = connection.createStatement();

		    // Execute a statement
		    int count= statement.executeUpdate("update aircraft set name='"+name+"',regnum='"+regNum+"' where capacity='"+capacity+"'");
		    if(count >0)
		       return true;
	    }
	    catch(SQLException ex){
	        ex.printStackTrace();
	    }
	    return false;
   }

	public boolean remove(String regNum){
		try{
			// Create a statement
			Statement statement = connection.createStatement();

			// Execute a statement
			int count= statement.executeUpdate("remove from aircraft where regnum = ('"+regNum+"'");
			if(count >0)
			return true;
			}
			catch(SQLException ex){
			ex.printStackTrace();
		}
		return false;
	}

	public Aircraft find(String regNum){

		try{
		    // Create a statement
		    Statement statement = connection.createStatement();

		    // Execute a statement
		    ResultSet resultSet = statement.executeQuery("select id, name, regnum, capacity from aircraft where regnum = '"+regNum+"'");

		    // Iterate through the result 
		    while (resultSet.next()){

		    int id=resultSet.getInt(1);
		    String name=resultSet.getString(2);
		    regNum=resultSet.getString(3);
		    int capacity=resultSet.getInt(4);
		    Aircraft aircraft = new Aircraft(id, name, regNum, capacity);
		    return aircraft;

	    	}
	    }
	    catch(SQLException ex){
	        ex.printStackTrace();
		}
    	return null;
	}

	public void displayAll(){
		List<Aircraft> aircrafts = getAll();
		for(Aircraft a: aircrafts)

			System.out.printf("%d \t %s \t %s \t %d \n", a.getId(), a.getName(), a.getRegNum(), a.getCapacity());
	}
}