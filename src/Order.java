
public class Order  {
	private int numOfPizzas;
	private String address;
	public long creditCardNum;
	private double price;
	private int arrivalTime;
	private double distance;
	private static int countedSerial;
	private int serialNum;

	
	public Order(int numOfPizzas, String address, long creditCard,int arrivalTime, double price) {
		this.numOfPizzas = numOfPizzas;
		this.address = address;
		this.creditCardNum = creditCard;
		this.price = price;
		this.arrivalTime = arrivalTime;
		countedSerial++;
		serialNum = countedSerial;
		
	}

	public double getPrice() {
		return price;
	}

	public int getSerialNum() {
		return countedSerial;
	}
	
	@Override
	public String toString() {
		return "Order [numOfPizzas=" + numOfPizzas + ", address=" + address + ", creditCardNum=" + creditCardNum
				+ ", price=" + price + ", arrivalTime=" + arrivalTime + ", distance=" + distance + ", serialNum="
				+ serialNum + "]";
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public double getDistance() {
		return distance;
	}

	public int getNumOfPizzas() {
		return numOfPizzas;
	}

	public void setNumOfPizzas(int numOfPizzas) {
		this.numOfPizzas = numOfPizzas;
	}

	public String getAddress() {
		return  new String(address);
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getCreditCardNum() {
		return creditCardNum;
	}

	public void setCreditCardNum(long creditCardNum) {
		this.creditCardNum = creditCardNum;
	}

	public int getOrderTimeArrival() {
		return arrivalTime;
	}

	public void setOrderTimeArrival(int orderTimeArrival) {
		this.arrivalTime = orderTimeArrival;
	}

	

}
