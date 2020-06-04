
public class KitchenWorker extends Thread{
	private String name;
	private double salary;
	InformationSystem system;
	BoundedQueue<PizzaDelivery> deliveries;

	public KitchenWorker(String name,InformationSystem system,BoundedQueue<PizzaDelivery> deliveries) {
		this.name = name;
		this.system = system;
		this.deliveries = deliveries;
	}
	
	public void run() {
		Order o = system.extractOrder();
		addOrderToSalary(o);
		int workingTime = calculateWorkingTime(o);
		try {
			sleep(workingTime*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PizzaDelivery delivery = new PizzaDelivery(o.getAddress(), o.getDistance(), o.getNumOfPizzas());
		deliveries.insert(delivery);
	}
	
	private int calculateWorkingTime(Order o) {
		int timePerPizza = 0; // need to be changed by GUI
		return timePerPizza*o.getNumOfPizzas();
	}
	
	private void addOrderToSalary(Order o) {
		this.salary+= o.getNumOfPizzas()*2;
		Pizzeria.addSalaryToExpenses(o.getNumOfPizzas()*2);
	}
}
