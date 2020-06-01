
public class Clerk extends Thread {
	private String name;
	private double salary;
	private static int pizzaPrice=25;
	UnboundedBuffer<Call> callLine;
	UnboundedBuffer<Order> orders;

	public Clerk(String name, UnboundedBuffer<Call> callLine) {
		this.name = name;
		this.callLine = callLine;
	}

	public void run() {
		Call c = callLine.extract();
		salary+=2;
		try {
			Thread.sleep((c.getCallDuration() * 1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c.getNumOfPizzas() < 10) {
			Order o = createOrder(c);
			orders.insert(o);
			notifyAll();
		} 
		else  {
			try {
				sleep(500);// need to transfer to manager call line
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	private void addCallPayment() {
		this.salary += 2;
	}

	private Order createOrder(Call c) {
		int numOfPizzas = c.getNumOfPizzas();
		double totalPrice = pizzaPrice * numOfPizzas;
		int serial = Call.serialNum;
		String address = c.getAddress();
		long creditCard = c.getCreditCardNum();
		int arrivalTime = c.getArrivalTime();
		Order o = new Order(serial, numOfPizzas,address ,creditCard, totalPrice,arrivalTime);

		return o;
	}

}
