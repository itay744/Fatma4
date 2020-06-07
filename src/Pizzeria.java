import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Pizzeria {
	public static Vector<Clerk> clerks;
	public static InformationSystem system;
	public static Vector<PizzaGuy> pizzaGuys;
	public static Vector<PizzaDelivery> pizzaDeliverys;
	public static Vector<Scheduler> schedulers;
	public static Manager manager;
	public static Queue<Call> callsLine;
	public static Queue<Call> managerLine;
	public static Queue<Order> orders;
	public static BoundedQueue<PizzaDelivery> deliverys;
	public static boolean dayIsOver = false;
	public  boolean callsIsEmpty = false;
	public static boolean ordersIsEmpty = false;
	private static double dailyExpenses;
	private static double dailyIncome;

	public Pizzeria(String file) {
		system = new InformationSystem();
		manager = new Manager(managerLine, orders, system);
		clerks = buildClercks();
		pizzaGuys = buildPizzaGuy();
		schedulers = buildSchedulers();
		callsLine = new Queue<Call>();
		managerLine = new Queue<Call>();
		orders = new Queue<Order>();
		deliverys = new BoundedQueue<PizzaDelivery>();
		readCalls(file);
		startClerksDay(clerks);
		startSchedulersDay(schedulers);
		startPizzaGuysDay(pizzaGuys);
		dailyExpenses = 0;
		dailyIncome = 0;
	}

	public static void addSalaryToExpenses(double salary) {
		dailyExpenses += salary;
	}

	public static void addOrderToIncome(double deliveryPrice) {
		dailyIncome += deliveryPrice;
	}

	private Vector<Clerk> buildClercks() {
		Vector<Clerk> v = new Vector<Clerk>();
		Clerk clerk1 = new Clerk("yuli", callsLine, orders, managerLine);
		Clerk clerk2 = new Clerk("itay", callsLine, orders, managerLine);
		Clerk clerk3 = new Clerk("gili", callsLine, orders, managerLine);
		v.add(clerk1);
		v.add(clerk2);
		v.add(clerk3);
		return v;
	}

	private Vector<Scheduler> buildSchedulers() {
		Vector<Scheduler> v = new Vector<Scheduler>();
		Scheduler scheduler1 = new Scheduler("Alon", orders, system);
		Scheduler scheduler2 = new Scheduler("Hassid", orders, system);
		v.add(scheduler1);
		v.add(scheduler2);
		return v;
	}

	private Vector<PizzaGuy> buildPizzaGuy() {
		Vector<PizzaGuy> v = new Vector<PizzaGuy>();
		PizzaGuy pizzaGuy1 = new PizzaGuy("Sapir", deliverys);
		PizzaGuy pizzaGuy2 = new PizzaGuy("Ido", deliverys);
		PizzaGuy pizzaGuy3 = new PizzaGuy("Shay", deliverys);
		v.add(pizzaGuy1);
		v.add(pizzaGuy2);
		v.add(pizzaGuy3);
		return v;
	}

	public void readCalls(String file) { // reads from the Customers file.
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));// create new buffer reader and file reader
			String line;
			line = br.readLine();// reading lines from file
			Vector<Thread> threads = new Vector<Thread>();
			while ((line = br.readLine()) != null) {// runs until line is null
				String temp[] = line.split("\\t");// splitting string by tab
				int creditCardNum = Integer.parseInt(temp[0]);
				int numOfPizzas = Integer.parseInt(temp[1]);
				int arrivalTime = Integer.parseInt(temp[2]);
				double callDuration = Double.parseDouble(temp[3]);
				String address = new String(temp[4]);
				Call c = new Call(creditCardNum, numOfPizzas, arrivalTime, callDuration, address, callsLine);
				manager.addCallToManagerList();
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

	public static double getDailyExpenses() {
		return dailyExpenses;
	}

	public static double getDailyIncome() {
		return dailyIncome;
	}

	public void startThreads(Vector<Thread> threads) {
		for (Thread t : threads) {
			t.start();
		}
	}

	public void printCalls(Queue<Call> call) {
		for (int i = 0; i < 150; i++) {
			System.out.println(call.extract().toString());
		}
	}

	public  void startClerksDay(Vector<Clerk> v) {
		while (!callsIsEmpty) {
			for (Clerk c : v) {
				Thread t = new Thread(c);
				t.start();
				checkCallsLine();
			}
		}
	}

	private  void checkCallsLine() {
		if (callsLine.isEmpty()) {
			callsIsEmpty = true;
		}
	}

	public static void startPizzaGuysDay(Vector<PizzaGuy> v) {
		while (!dayIsOver) {
			for (PizzaGuy p : v) {
				Thread t = new Thread(p);
				t.start();
			}
		}
	}

	public static void startSchedulersDay(Vector<Scheduler> v) {
		while (!dayIsOver) {
			for (Scheduler s : v) {
				Thread t = new Thread(s);
				t.start();
			}
		}
	}

	public static void startKitchenWorkersDay(Vector<KitchenWorker> v) {
		while (!dayIsOver) {
			for (KitchenWorker k : v) {
				Thread t = new Thread(k);
				t.start();
			}
		}
	}

	public static void main(String[] args) {
		String CallsFile = new String("assignment4_callsData.txt");
		Pizzeria pizzaPazza = new Pizzeria(CallsFile);
		System.out.println("yuli");
		System.out.println(Pizzeria.callsLine.extract().toString());

		// pizzaPazza.printCalls(callsLine);
	}

}
