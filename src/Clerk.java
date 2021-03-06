import javax.sql.rowset.Joinable;

public class Clerk extends Employee {
	private static int pizzaBasePrice = 25;
	Queue<Call> callLine;
	Queue<Order> orders;
	Queue<Call> managerLine;

	public Clerk(String name,Queue<Call> callLine, Queue<Order> orders, Queue<Call> managerLine) {
        super(name);
		this.callLine = callLine;
		this.orders = orders;
		this.managerLine = managerLine;

	}

	public synchronized void run() {
		while (!isDayFinished()) {
			Call c = callLine.extract();
			calculateSalary(0);
			System.out.println(c);
//		try {
//			Thread.sleep((long) (c.getCallDuration())*1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
			if (c.getNumOfPizzas() < 10) {
				Order o = createOrder(c);
				// Pizzeria.addOrderToIncome(o.getPrice());
				System.out.println(o);
				orders.insert(o);
			}

			else {
//			try {
//				Thread.sleep(500);// need to transfer to manager call line
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
				// managerLine.insert(c);
			}
		}
		this.notifyAll();
	}


	private Order createOrder(Call c) {
		int numOfPizzas = c.getNumOfPizzas();
		double totalPrice = pizzaBasePrice * numOfPizzas;
		String address = c.getAddress();
		long creditCard = c.getCreditCardNum();
		int arrivalTime = c.getArrivalTime();
		Order o = new Order(numOfPizzas, address, creditCard, arrivalTime, totalPrice);

		return o;
	}

	private boolean isDayFinished() {
		return callLine.isEmpty();

	}

	@Override
	public void calculateSalary(double time) {
		this.salary += 2;
		Pizzeria.addSalaryToExpenses(2);
		
	}

}

