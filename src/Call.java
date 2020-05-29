

public class Call extends Thread {
	private int numOfPizzas;
	private String address;
	private long creditCardNum;
	private int arrivalTime;
	private double callDuration;
	private UnboundedBuffer<Call> callLine;
	
	public Call (int pizzas,String address,long creditNum,int arrivalTime,double callDuration,UnboundedBuffer<Call> callLine) {
		this.numOfPizzas = pizzas;
		this.address = address;
		this.creditCardNum = creditNum;
		this.arrivalTime = arrivalTime;
		this.callDuration = callDuration;
		this.callLine = callLine;
	}
	
	public void run() {
		try {
			Thread.sleep(arrivalTime*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		callLine.insert(this);
	}

}
