
public class Order {
	private int serialNum;
	private int numOfPizzas;
	private String address;
	public long creditCardNum;
	private double price;
	private int arrivalTime;
	private double distance;

	
	public Order(int serialNum, int numOfPizzas, String address, long creditCard, double price,int arrivalTime) {
		this.serialNum = serialNum;
		this.numOfPizzas = numOfPizzas;
		this.address = address;
		this.creditCardNum = creditCard;
		this.price = price;
		this.arrivalTime = arrivalTime;
	}

	public int getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
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
