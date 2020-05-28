
public class PizzaGuy {
	private String name;
	private double salary;
	private int deliveryCapacity;
	private int tips;

	public PizzaGuy(String name) {
		this.name = name;
		deliveryCapacity = (int) (Math.random()*2)+2;
		this.tips = 0;
		this.salary = 0;
	}
	
	private int addTip() {
		int tip;
		tip = (int) (Math.random()*15);
		return tip;
		}
	

	
	
}
