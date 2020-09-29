public class Calculator{

	public static void main(String[] args) {
		
		int x = Integer.parseInt(args[0]);
		int y = Integer.parseInt(args[1]);

		com.adepo.Math math = new com.adepo.Math();
		int sum = math.add(x,y);
		System.out.printlnf("sum of %d and %d",x,y,sum);
	}
}