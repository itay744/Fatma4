import java.util.Vector;

public class PizzaDelivery {
	private String address;
	private double distance;
	private Vector<Integer> pizzasInDelivery;

	public PizzaDelivery(String address, double distance, Vector<Integer> pizzasInDelivery) {
		this.address = address;
		this.distance = distance;
		this.pizzasInDelivery = pizzasInDelivery;
	}
}
