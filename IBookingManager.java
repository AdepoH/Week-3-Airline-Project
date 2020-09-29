import java.util.*;
import java.util.Date;

public interface IBookingManager{

	public List<Booking> getAll();

	public boolean create(int bookingNum, String passengerID, String flightNo , Date bookingDate, int seatNum);

	public boolean update(int bookingNum, String passengerID, String flightNo , Date bookingDate, int seatNum);

	public boolean remove(int bookingNum);

    public Booking find(int bookingNum); 

    public void displayAll();
}