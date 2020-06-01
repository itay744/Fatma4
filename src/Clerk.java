
public class Clerk implements Runnable {
	private String name;
	private double salary;
	private static int pizzaPrice=25;
	UnboundedBuffer<Call> callLine;
	UnboundedBuffer<Order> orders;
	UnboundedBuffer<Call> managerLine;

	public Clerk(String name, UnboundedBuffer<Call> callLine,UnboundedBuffer<Order> orders,UnboundedBuffer<Call> managerLine) {
		this.name = name;
		this.callLine = callLine;
		this.orders = orders;
		this.managerLine = managerLine;
	}

	public synchronized void run() {
		Call c = callLine.extract();
		salary+=2;
		try {
			Thread.sleep((long) (c.getCallDuration() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c.getNumOfPizzas() < 10) {
			Order o = createOrder(c);
			orders.insert(o);
			Thread t = new Thread(this);
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	private Order createOrder(Call c) {
		int numOfPizzas = c.getNumOfPizzas();
		double totalPrice = pizzaPrice * numOfPizzas;
		String address = c.getAddress();
		long creditCard = c.getCreditCardNum();
		int arrivalTime = c.getArrivalTime();
		Order o = new Order(numOfPizzas,address,creditCard,arrivalTime,totalPrice);

		return o;
	}

}
