
public class Clerk extends Thread {
	private String name;
	private double salary;
	private static int pizzaPrice=25;
	UnboundedBuffer<Call> callLine;
	UnboundedBuffer<Order> orders;
	UnboundedBuffer<Call> managerLine;

	public Clerk(String name, UnboundedBuffer<Call> callLine) {
		this.name = name;
		this.callLine = callLine;
	}

	public synchronized void run() {
		while(callLine.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		notifyAll();
		Call c = callLine.extract();
		System.out.println("yuli");
		salary+=2;
//		try {
//			Thread.sleep((long) (c.getCallDuration() * 1000));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (c.getNumOfPizzas() < 10) {
//			Order o = createOrder(c);
//			orders.insert(o);
//			try {
//				join();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} 
//		else  {
//			try {
//				sleep(500);// need to transfer to manager call line
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			managerLine.insert(c);	
//		}
		
		

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
