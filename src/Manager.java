import java.util.Vector;

public class Manager extends Thread {
	private Queue<Call> managerLine;
	private Queue<Order> orders;
	private Queue<Call> calls;
	private Vector<Employee> employees;
	private BoundedQueue<PizzaDelivery> deliveries;
	private InformationSystem system;
	private int callsForTheDay;
	private int deliveredOrders;

	public Manager(Queue<Call> managerLine, Queue<Order> orders, InformationSystem system,Queue<Call> Calls,BoundedQueue<PizzaDelivery> deliveries, Vector<Employee> employees ) {
		this.managerLine = managerLine;
		this.orders = orders;
		this.system = system;
		this.calls = Calls;
		this.deliveries = deliveries;
		this.callsForTheDay = 0;
		this.deliveredOrders = 0;
		this.employees = employees;
		
	}

	private Order createOrder(Call c) {
		int numOfPizzas = c.getNumOfPizzas();
		double totalPrice = 15 * numOfPizzas;
		if (numOfPizzas > 20) {
			totalPrice *= 0.9;
		}
		String address = c.getAddress();
		long creditCard = c.getCreditCardNum();
		int arrivalTime = c.getArrivalTime();
		Order o = new Order(numOfPizzas, address, creditCard, arrivalTime, totalPrice);

		return o;
	}

	public  void run() {
		Call c = managerLine.extract();
		Order o = createOrder(c);
		Pizzeria.addOrderToIncome(o.getPrice());
		double distance = convertAddress(o);
		o.setDistance(distance);
		System.out.println(o);
		//system.insertOrder(o);
//		try {
//			sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public void addToDeliveredOrder() {
		this.deliveredOrders++;
	}

	public void checkDeliveries() {
		int deliveriesLeft = callsForTheDay - deliveredOrders;
		if (deliveriesLeft <= 10) {
			PizzaGuy.onlyOneDeliveryPermited = true;
		}
	}
	
	

	private void printWorkingDayData() {
		System.out.println(" Orders Delivered " + deliveredOrders);
		System.out.println("Total Income: " + Pizzeria.getDailyIncome());
		System.out.println("Total Employees Salary: " + Pizzeria.getDailyExpenses());
	}

	public void checkIfDayIsOver() {
		if (deliveredOrders == calls.Size()) {
			stopWorkingDay();
		}
	}
	
	private int checkCallsForDay() {
		return calls.Size();
	}

	public void addCallToManagerList() {
		this.callsForTheDay++;
	}


	private double convertAddress(Order o) {
		String address = o.getAddress();
		double distance = calculateDistance(address);
		return distance;

	}

	private double calculateDistance(String s) {
		int distance = countWordsUsingSplit(s);
		char c = s.charAt(0);
		distance += addDistanceByFirstLetter(c);
		return distance;
	}

	private double addDistanceByFirstLetter(char c) {
		if (c >= 'a' || c <= 'h') {
			return 0.5;
		}
		if (c >= 'i' || c <= 'p') {
			return 2;
		}
		if (c >= 'q' || c <= 'z') {
			return 7;
		}
		if (c >= '0' || c <= '9') {
			return c - '0';
		}
		return 0;
	}

	public static int countWordsUsingSplit(String input) {
		if (input == null || input.isEmpty()) {
			return 0;
		}
		String[] words = input.split("\\s+");
		return words.length;
	}

	public void stopSchedulersWork() {
		Pizzeria.dayIsOver = true;
	}

	public void stopPizzaGuyWork() {
		Pizzeria.dayIsOver = true;
	}

	public void stopWorkingDay() {
		stopPizzaGuyWork();
		stopSchedulersWork();
		stopKitchenWorkersWork();
	}

	public void stopKitchenWorkersWork() {
		Pizzeria.ordersIsEmpty = true;
	}

}
