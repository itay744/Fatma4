import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Pizzeria {
	private Clerk clerk1;
	private Clerk clerk2;
	private Clerk clerk3;
	private InformationSystem system;
	private PizzaGuy pizzaGuy1;
	private PizzaGuy pizzaGuy2;
	private PizzaGuy pizzaGuy3;
	private PizzaDelivery pizzaDelivery1;
	private PizzaDelivery pizzaDelivery2;
	private Manager manager;
	private Scheduler scheduler1;
	private Scheduler scheduler2;
	private UnboundedBuffer<Call> callsLine;
	private UnboundedBuffer<Call> managerLine;
	private UnboundedBuffer<Order> orders;
	private BoundedBuffer<PizzaDelivery> delivery;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private void readCalls(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));// create new buffer reader and file reader
			String line;
			line = br.readLine();// reading lines from file
			while ((line = br.readLine()) != null) {// runs until line is null
				String temp[] = line.split("\\t");// splitting string by tab
				 long creditCardNum = Long.parseLong(temp[0]);
				 int numOfPizzas = Integer.parseInt(temp[1]);
				 int arrivalTime = Integer.parseInt(temp[2]);
				 double callDuration = Double.parseDouble(temp[3]);
				 String address = new String(temp[4]);
				Call c = new Call(creditCardNum,numOfPizzas,arrivalTime,callDuration,address, callsLine);
			}

		} catch (IOException e) {// catching io exception
			e.printStackTrace();
		} finally {
			try {
				br.close();// closing buffer reader
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
