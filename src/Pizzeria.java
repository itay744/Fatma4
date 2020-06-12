import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Pizzeria {
	public static Vector<Clerk> clerks;
	public static InformationSystem pizzaSystem;
	public static Vector<PizzaGuy> pizzaGuys;
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

	public Pizzeria(String file,double WorkingTime, int numOfPizzaGuy) {
		callsLine = new Queue<Call>();
		managerLine = new Queue<Call>();
		orders = new Queue<Order>();
		pizzaSystem = new InformationSystem();
		deliveries = new BoundedQueue<PizzaDelivery>();
		employees = buildEmployeesV(numOfPizzaGuy);
		manager = new Manager(managerLine, orders, pizzaSystem, callsLine, deliveries,employees);
		insertCallsToLine(readCalls(file));
        Vector <Thread> threads = createThreads(employees);
        startThraed(threads);
        startManagerDay(manager);
		dailyExpenses = 0;
		dailyIncome = 0;
	}
	
	private Vector<Employee> buildEmployeesV(int numOfPizzaGuy) {
		Vector<Employee> v = new Vector<Employee>();
		Clerk clerk1 = new Clerk("yuli", callsLine, orders, managerLine); v.add(clerk1);
		Clerk clerk2 = new Clerk("itay", callsLine, orders, managerLine); v.add(clerk2);
		Clerk clerk3 = new Clerk("gili", callsLine, orders, managerLine); v.add(clerk3);
		KitchenWorker KitchenWorker1 = new KitchenWorker("dani", pizzaSystem, deliveries); v.add(KitchenWorker1);
		KitchenWorker KitchenWorker2 = new KitchenWorker("evya", pizzaSystem, deliveries); v.add(KitchenWorker2);
		KitchenWorker KitchenWorker3 = new KitchenWorker("jordan", pizzaSystem, deliveries); v.add(KitchenWorker3);
		Scheduler scheduler1 = new Scheduler("Alon", orders, pizzaSystem); v.add(scheduler1);
		Scheduler scheduler2 = new Scheduler("Hassid", orders, pizzaSystem); v.add(scheduler2);
		for(int i = 0; i< numOfPizzaGuy; i++) {
			PizzaGuy p = new PizzaGuy("Itai"+i,deliveries);
			v.add(p);
		}
		
		return v;
		
	}
	
	public Vector<Thread> createThreads(Vector<Employee>employees) {
		Vector<Thread> tc = new Vector<Thread>();
		for (Employee emp : employees) {
			Thread t = new Thread(emp);
			tc.add(t);
		}
		return tc;
	}
	
	public void startThraed(Vector<Thread> tc) {
		for (Thread t : tc) {
			t.start();
		}
	}
	
	

	public static void addSalaryToExpenses(double salary) {
		dailyExpenses += salary;
	}

	public static void addOrderToIncome(double deliveryPrice) {
		dailyIncome += deliveryPrice;
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

		for (int i = 0; i < 2; i++) {
			Thread t = new Thread(c.get(i));
			t.start();
		}

	}

	public static double getDailyIncome() {
		return dailyIncome;
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
			Thread t = new Thread(m);
			t.start();
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
		//Pizzeria pizzaPazza = new Pizzeria(CallsFile);
//		System.out.println("yuli");
//		System.out.println(Pizzeria.callsLine.extract().toString());

		// pizzaPazza.printCalls(callsLine);
	}

}
