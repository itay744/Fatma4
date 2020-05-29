
public class Order {
	private int serialNum;
	private int numOfPizzas;
	private String address;
	public long creditCardNum;
	private double price;
	private String orderTimeArrival;

	public Order(int serialNum, int numOfPizzas, String address, long creditCard, double price,
			String orderTimeArrival) {
		this.serialNum = serialNum;
		this.numOfPizzas = numOfPizzas;
		this.address = address;
		this.creditCardNum = creditCard;
		this.price = price;
		this.orderTimeArrival = orderTimeArrival;
	}

}
