
public class Call extends Thread {
	private int numOfPizzas;
	private String address;
	private long creditCardNum;
	private int arrivalTime;
	private double callDuration;
	
	public Call (int pizzas,String address,long creditNum,int arrivalTime,double callDuration) {
		this.numOfPizzas = pizzas;
		this.address = address;
		this.creditCardNum = creditNum;
		this.arrivalTime = arrivalTime;
		this.callDuration = callDuration;
	}
	
	public void run() {
		try {
			this.sleep(arrivalTime*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
