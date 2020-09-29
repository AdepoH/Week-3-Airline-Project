import java.util.*;

public interface IAircraftManager{

	public List<Aircraft> getAll();

	public boolean create(String name, String regNum, int capacity);

	public boolean update(String name, String regNum, int capacity);

	public boolean remove(String regNum);

    public Aircraft find(String regNum); 

    public void displayAll();
}