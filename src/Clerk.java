import javax.sql.rowset.Joinable;

public class Clerk implements Runnable {
	private String name;
	private double salary;
	private static int pizzaBasePrice=25;
	Queue<Call> callLine;
	Queue<Order> orders;
	Queue<Call> managerLine;

	public Clerk(String name, Queue<Call> callLine,Queue<Order> orders,Queue<Call> managerLine) {
		this.name = name;
		this.callLine = callLine;
		this.orders = orders;
		this.managerLine = managerLine;
		this.salary=0;
	}

	public synchronized void run() {
		Call c = callLine.extract();
		addCallToClerkSalary();
		Thread t = new Thread(c);
		try {
			Thread.sleep((long) (c.getCallDuration() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c.getNumOfPizzas() < 10) {
			Order o = createOrder(c);
			Pizzeria.addOrderToIncome(o.getPrice());
			orders.insert(o);
		} 
		else  {
			try {
				Thread.sleep(500);// need to transfer to manager call line
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			managerLine.insert(c);	
		}
	}
	
	private void addCallToClerkSalary() {
		this.salary+=2;
		Pizzeria.addSalaryToExpenses(2);
	}
	
	private Order createOrder(Call c) {
		int numOfPizzas = c.getNumOfPizzas();
		double totalPrice = pizzaBasePrice * numOfPizzas;
		String address = c.getAddress();
		long creditCard = c.getCreditCardNum();
		int arrivalTime = c.getArrivalTime();
		Order o = new Order(numOfPizzas,address,creditCard,arrivalTime,totalPrice);

		return o;
	}

}
