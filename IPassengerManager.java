import java.util.*;

public interface IPassengerManager{

	public List<Passenger> getAll();

	public boolean create(String passengerID, String name, String email, String address, String phone);

	public boolean update(String passengerID, String name, String email, String address, String phone);

	public  boolean remove(String passengerID);

	public Passenger find(String passengerID);

	public void displayAll();

}