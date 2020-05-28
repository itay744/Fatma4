import java.util.Vector;

public class PizzaDelivery {
	private int numOfPizzas;
	private String address;
	private double distance;
	private Vector<Integer> pizzasInDelivery;

	public PizzaDelivery(int numOfPizzas, String address, double distance, Vector<Integer> pizzasInDelivery) {

		this.numOfPizzas = numOfPizzas;
		this.address = address;
		this.distance = distance;
		this.pizzasInDelivery = pizzasInDelivery;
	}
}
