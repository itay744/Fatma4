import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Pizzeria {
	public static Clerk clerk1;
	private static Clerk clerk2;
	private static Clerk clerk3;
	private InformationSystem system;
	private PizzaGuy pizzaGuy1;
	private PizzaGuy pizzaGuy2;
	private PizzaGuy pizzaGuy3;
	private PizzaDelivery pizzaDelivery1;
	private PizzaDelivery pizzaDelivery2;
	private Manager manager;
	private Scheduler scheduler1;
	private Scheduler scheduler2;
	public static UnboundedBuffer<Call> callsLine;
	private UnboundedBuffer<Call> managerLine;
	private UnboundedBuffer<Order> orders;
	private BoundedBuffer<PizzaDelivery> delivery;
	

	public Pizzeria(String file) {
		callsLine = new UnboundedBuffer<Call>();
		managerLine = new UnboundedBuffer<Call>();
		orders = new UnboundedBuffer<Order>();
		delivery = new BoundedBuffer<PizzaDelivery>();	
		readCalls(file);
		Clerk clerk1 = new Clerk("yuli",callsLine);
		Clerk clerk2 = new Clerk("itai",callsLine);
		Clerk clerk3 = new Clerk("gili",callsLine);
		
		
		
		
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
			Clerk(clerk1);
			Clerk(clerk2);
			Clerk(clerk3);

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
