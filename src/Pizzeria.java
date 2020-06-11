import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Pizzeria {
	public static Vector<Clerk> clerks;
	public static InformationSystem pizzaSystem;
	public static Vector<PizzaGuy> pizzaGuys;
	public static Vector<PizzaDelivery> pizzaDeliverys;
	public static Vector<Scheduler> schedulers;
	public static Vector<KitchenWorker> kitchenWorkers;
	public static Vector<Employee> employees;
	public static Manager manager;
	public static Queue<Call> callsLine;
	public static Queue<Call> managerLine;
	public static Queue<Order> orders;
	public static BoundedQueue<PizzaDelivery> deliveries;
	public static boolean dayIsOver = false;
	public boolean callsIsEmpty = false;
	public static boolean ordersIsEmpty = false;
	private static double dailyExpenses;
	private static double dailyIncome;

	public Pizzeria(String file) {
		callsLine = new Queue<Call>();
		managerLine = new Queue<Call>();
		orders = new Queue<Order>();
		pizzaSystem = new InformationSystem();
		deliveries = new BoundedQueue<PizzaDelivery>();
		employees = 
		manager = new Manager(managerLine, orders, pizzaSystem, callsLine, deliveries, pizzaGuys);
		kitchenWorkers = buildKitchenWorkers();
		insertCallsToLine(readCalls(file));
		startClerksDay(clerks);
		// startManagerDay(manager);
		startSchedulersDay(schedulers);
		startKitchenWorkersDay(kitchenWorkers);
		// startPizzaGuysDay(pizzaGuys);
		dailyExpenses = 0;
		dailyIncome = 0;
	}
	
	private void buildEmployeesV() {
		Vector<Clerk> v = new Vector<Clerk>();
		Clerk clerk1 = new Clerk("yuli", callsLine, orders, managerLine);
		Clerk clerk2 = new Clerk("itay", callsLine, orders, managerLine);
		Clerk clerk3 = new Clerk("gili", callsLine, orders, managerLine);
		employees.add(clerk1);

	}

	public static void addSalaryToExpenses(double salary) {
		dailyExpenses += salary;
	}

	public static void addOrderToIncome(double deliveryPrice) {
		dailyIncome += deliveryPrice;
	}

	private Vector<Clerk> buildClerks() {
		Vector<Clerk> v = new Vector<Clerk>();
		Clerk clerk1 = new Clerk("yuli", callsLine, orders, managerLine);
		Clerk clerk2 = new Clerk("itay", callsLine, orders, managerLine);
		Clerk clerk3 = new Clerk("gili", callsLine, orders, managerLine);
		v.add(clerk1);
		v.add(clerk2);
		v.add(clerk3);
		return v;
	}

	private Vector<KitchenWorker> buildKitchenWorkers() {
		Vector<KitchenWorker> v = new Vector<KitchenWorker>();
		KitchenWorker KitchenWorker1 = new KitchenWorker("dani", pizzaSystem, deliveries);
		KitchenWorker KitchenWorker2 = new KitchenWorker("evya", pizzaSystem, deliveries);
		KitchenWorker KitchenWorker3 = new KitchenWorker("jordan", pizzaSystem, deliveries);
		v.add(KitchenWorker1);
		v.add(KitchenWorker2);
		v.add(KitchenWorker3);
		return v;
	}

	private Vector<Scheduler> buildSchedulers() {
		Vector<Scheduler> v = new Vector<Scheduler>();
		Scheduler scheduler1 = new Scheduler("Alon", orders, pizzaSystem);
		Scheduler scheduler2 = new Scheduler("Hassid", orders, pizzaSystem);
		v.add(scheduler1);
		v.add(scheduler2);
		return v;
	}

	private Vector<PizzaGuy> buildPizzaGuys() {
		Vector<PizzaGuy> v = new Vector<PizzaGuy>();
		PizzaGuy pizzaGuy1 = new PizzaGuy("Sapir", deliveries);
		PizzaGuy pizzaGuy2 = new PizzaGuy("Ido", deliveries);
		PizzaGuy pizzaGuy3 = new PizzaGuy("Shay", deliveries);
		v.add(pizzaGuy1);
		v.add(pizzaGuy2);
		v.add(pizzaGuy3);
		return v;
	}

	public Vector<Call> readCalls(String file) { // reads from the Customers file.
		BufferedReader br = null;
		Vector<Call> callsV = new Vector<Call>();
		try {
			br = new BufferedReader(new FileReader(file));// create new buffer reader and file reader
			String line;
			line = br.readLine();// reading lines from file
			while ((line = br.readLine()) != null) {// runs until line is null
				String temp[] = line.split("\\t");// splitting string by tab
				int creditCardNum = Integer.parseInt(temp[0]);
				int numOfPizzas = Integer.parseInt(temp[1]);
				int arrivalTime = Integer.parseInt(temp[2]);
				double callDuration = Double.parseDouble(temp[3]);
				String address = new String(temp[4]);
				Call c = new Call(creditCardNum, numOfPizzas, arrivalTime, callDuration, address, callsLine);
				callsV.add(c);
			}

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
		return callsV;
	}

	public static double getDailyExpenses() {
		return dailyExpenses;
	}

	private void insertCallsToLine(Vector<Call> c) {
//		Vector<Thread> tc = new Vector<Thread>();
//		for (Call call : c) {
//			Thread t = new Thread(call);
//			tc.add(t);
//		}
//		startThreads(tc);
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(c.get(i));
			t.start();
		}

	}

	public static double getDailyIncome() {
		return dailyIncome;
	}

	public void startThreads(Vector<Thread> threads) {
		for (Thread t : threads) {
			t.start();
		}
	}

	public void startClerksDay(Vector<Clerk> v) {
//		while (!callsIsEmpty) {
//			for (Clerk c : v) {
//				Thread t = new Thread(c);
//				t.start();
//				checkCallsLine();
//			}
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(v.get(i));
			t.start();
			checkCallsLine();
		}
//		Thread t = new Thread(v.get(0));
//		t.start();
		// }
	}

	public void startManagerDay(Manager m) {
		while (!dayIsOver) {
			Thread t = new Thread(m);
			t.start();

		}

	}

	private void checkCallsLine() {
		if (callsLine.isEmpty()) {
			callsIsEmpty = true;
		}
	}

	public static void startPizzaGuysDay(Vector<PizzaGuy> v) {
//		while (!dayIsOver) {
//			for (PizzaGuy p : v) {
//				Thread t = new Thread(p);
//				t.start();
//			}
//		}
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(v.get(i));
			t.start();

		}
	}

	public static void startSchedulersDay(Vector<Scheduler> v) {
//		while (!dayIsOver) {
//			for (Scheduler s : v) {
//				Thread t = new Thread(s);
//				t.start();
//			}
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(v.get(i));
			t.start();

		}
//		}
//		Thread t = new Thread(v.get(0));
//		t.start();
	}

	public static void startKitchenWorkersDay(Vector<KitchenWorker> v) {
//		while (!dayIsOver) {
//			for (KitchenWorker k : v) {
//				Thread t = new Thread(k);
//				t.start();
//			}
		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(v.get(i));
			t.start();

		}
//		}
//		Thread t = new Thread(v.get(0));
//		t.start();
	}

	public static void main(String[] args) {
		String CallsFile = new String("assignment4_callsData.txt");
		Pizzeria pizzaPazza = new Pizzeria(CallsFile);
//		System.out.println("yuli");
//		System.out.println(Pizzeria.callsLine.extract().toString());

		// pizzaPazza.printCalls(callsLine);
	}

}
