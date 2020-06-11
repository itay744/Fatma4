
public class PizzaDelivery {
	@Override
	public String toString() {
		return "PizzaDelivery [address=" + address + ", distance=" + distance + ", pizzasInDelivery=" + pizzasInDelivery
				+ "]";
	}

	private String address;
	private double distance;
	private int pizzasInDelivery;

	public PizzaDelivery(String address, double distance, int pizzasInDelivery) {
		this.address = address;
		this.distance = distance;
		this.pizzasInDelivery = pizzasInDelivery;
	}
	
	public double getDistance() {
		return this.distance;
	}
	
	public double getPizzasInDelivery() {
		return this.pizzasInDelivery;
	}
}
