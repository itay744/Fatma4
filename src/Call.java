

public class Call extends Thread {
	private int numOfPizzas;
	private String address;
	private long creditCardNum;
	private int arrivalTime;
	private long callDuration;
	private UnboundedBuffer<Call> callLine;
	public static int siryalNum;
	
	public Call (int pizzas,String address,long creditNum,int arrivalTime,long callDuration,UnboundedBuffer<Call> callLine) {
		this.numOfPizzas = pizzas;
		this.address = address;
		this.creditCardNum = creditNum;
		this.arrivalTime = arrivalTime;
		this.callDuration = callDuration;
		this.callLine = callLine;
		Call.siryalNum++;
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

	public int getNumOfPizzas() {
		return numOfPizzas;
	}

	public String getAddress() {
		return address;
	}

	public long getCreditCardNum() {
		return creditCardNum;
	}

	public long getCallDuration() {
		return callDuration;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}

}
