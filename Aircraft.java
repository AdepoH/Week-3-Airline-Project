public class Aircraft{

	private int id;
	private String name;
	private String regNum;
	private int capacity;

	public Aircraft(int id, String name, String regNum, int capacity){
		this.id = id;
		this.name = name;
		this.regNum = regNum;
		this.capacity = capacity;
	}

    public Aircraft(String name, String regNum, int capacity){
        this.name = name;
        this.regNum = regNum;
        this.capacity = capacity;
    }
	
	public void setId(int id){
		this.id = id;
    }
    public int getId(){
    	return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setRegNum(String regNum){
    	this.regNum = regNum;
    }
    public String getRegNum(){
    	return regNum;
    }
    public void setCapacity(int capacity){
		this.capacity = capacity;
    }
    public int getCapacity(){
    	return capacity;
    }
    @Override
    public String toString(){
    
    return String.format("%d \t %s \t %s \t %d", id, name, regNum, capacity);
	}
}