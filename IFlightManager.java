import java.util.*;
import java.util.Date;

public interface IFlightManager{

	public List<Flight> getAll();

	public boolean create(String flightNum, String regNum, double amount, String takeOffPoint, Date departureTime, String destination, Date arrivalTime);

	public boolean update(String flightNum, String regNum, double amount, String takeOffPoint, Date departureTime, String destination, Date arrivalTime);

	public boolean remove(String flightNum);

    public Flight find(String flightNum); 

    public void displayAll();
}