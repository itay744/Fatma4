
public class Order {
	private long serialNum;
	private int numOfPizzas;
	private String adress;
	public long creditCard;
	private double price;
	private String orderTimeArrival;

	public Order(long serialNum, int numOfPizzas, String adress, long creditCard, double price,
			String orderTimeArrival) {
		this.serialNum = serialNum;
		this.numOfPizzas = numOfPizzas;
		this.adress = adress;
		this.creditCard = creditCard;
		this.price = price;
		this.orderTimeArrival = orderTimeArrival;
	}

}
