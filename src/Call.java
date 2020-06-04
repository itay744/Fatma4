import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Call implements Runnable {
	private int numOfPizzas;
	private String address;
	private long creditCardNum;
	private int arrivalTime;
	private double callDuration;
	private Queue<Call> callLine;
	
	
	public Call (long creditNum,int pizzas,int arrivalTime,double callDuration,String address,Queue<Call> callLine) {
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

	public int getNumOfPizzas() {
		return numOfPizzas;
	}

	public String getAddress() {
		return address;
	}

	public long getCreditCardNum() {
		return creditCardNum;
	}

	public double getCallDuration() {
		return callDuration;
	}
	
	public int getArrivalTime() {
		return arrivalTime;
	}
	
	public String toString()
    {//todo check if needed
        return this.creditCardNum + " " + this.numOfPizzas + " " + this.arrivalTime + " " + this.callDuration + " "+ this.address;
    }

}
