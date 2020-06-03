import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Pizzeria {
	public static Clerk clerk1;
	public static Clerk clerk2;
	public static Clerk clerk3;
	public static InformationSystem system;
	public static PizzaGuy pizzaGuy1;
	public static PizzaGuy pizzaGuy2;
	public static PizzaGuy pizzaGuy3;
	public static PizzaDelivery pizzaDelivery1;
	public static PizzaDelivery pizzaDelivery2;
	public static Manager manager;
	public static Scheduler scheduler1;
	public static Scheduler scheduler2;
	public static UnboundedBuffer<Call> callsLine;
	public static UnboundedBuffer<Call> managerLine;
	public static UnboundedBuffer<Order> orders;
	public static BoundedBuffer<PizzaDelivery> delivery;
	

	public Pizzeria(String file) {
		callsLine = new UnboundedBuffer<Call>();
		managerLine = new UnboundedBuffer<Call>();
		orders = new UnboundedBuffer<Order>();
		delivery = new BoundedBuffer<PizzaDelivery>();	
		readCalls(file);
		Clerk clerk1 = new Clerk("yuli",callsLine,orders,managerLine);
		Clerk clerk2 = new Clerk("itai",callsLine,orders,managerLine);
		Clerk clerk3 = new Clerk("gili",callsLine,orders,managerLine);
		Clerk(clerk1);
		Clerk(clerk2);
		Clerk(clerk3);
		system = new InformationSystem();
		scheduler1 = new Scheduler("Roi",orders,system);
		scheduler2 = new Scheduler("Sofia",orders,system);
		pizzaGuy1 = new PizzaGuy("Guy",delivery);
		pizzaGuy2 = new PizzaGuy("Jon",delivery);
		pizzaGuy3 = new PizzaGuy("Juli",delivery);
		manager = new Manager("Gabi",managerLine,orders,system);	
		
	}
	
	
	
	public static void main(String [] args)
    {
        String CallsFile = new String ("assignment4_callsData.txt");
        Pizzeria pizzaPazza = new Pizzeria(CallsFile);
        System.out.println("yuli");
        System.out.println(Pizzeria.callsLine.extract().toString());
 
       // pizzaPazza.printCalls(callsLine);
    }
	
	public void readCalls(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));// create new buffer reader and file reader
			String line;
			line = br.readLine();// reading lines from file
			Vector<Thread>threads = new Vector<Thread>();
			while ((line = br.readLine()) != null) {// runs until line is null
				String temp[] = line.split("\\t");// splitting string by tab
				int creditCardNum = Integer.parseInt(temp[0]);
				int numOfPizzas = Integer.parseInt(temp[1]);
				int arrivalTime = Integer.parseInt(temp[2]);
				double callDuration = Double.parseDouble(temp[3]);
				String address = new String(temp[4]);
				Call c = new Call(creditCardNum,numOfPizzas,arrivalTime,callDuration,address, callsLine);
				Thread t = new Thread(c);
				threads.add(t);
			}
			startThreads(threads);
			

		} catch (IOException e) {// catching io exception3
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
	
	public void startThreads(Vector<Thread>threads) {
		for(Thread t: threads){
			t.start();
		}
	}
	
	public void printCalls(UnboundedBuffer<Call> call) {
		for(int i = 0; i<150; i++) {
			System.out.println(call.extract().toString());
		}
	}
	
	public static void Clerk(Clerk c) {
		Thread t = new Thread(c);	
		t.start();
	}
	
	
	

}
