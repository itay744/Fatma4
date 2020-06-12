public class KitchenWorker extends Employee  {
	InformationSystem pizzaSystem;
	BoundedQueue<PizzaDelivery> deliveries;

	public KitchenWorker(String name,InformationSystem pizzaSystem,BoundedQueue<PizzaDelivery> deliveries) {
		super(name);
		this.pizzaSystem = pizzaSystem;
		this.deliveries = deliveries;
	}
	
	public synchronized void run() {
		Order o = pizzaSystem.extractOrder();
		int numOfPizza = o.getNumOfPizzas();
		calculateSalary(numOfPizza);
		int workingTime = calculateWorkingTime(o);
		try {
			Thread.sleep(workingTime*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PizzaDelivery d = new PizzaDelivery(o.getAddress(), o.getDistance(), o.getNumOfPizzas());
		deliveries.insert(d);
		System.out.println(d);
	}
	
	private int calculateWorkingTime(Order o) {
		int timePerPizza = 0; // need to be changed by GUI
		return timePerPizza*o.getNumOfPizzas();
	}

	@Override
	public void calculateSalary(double numOfPizza) {
		this.salary+= numOfPizza*2;
		Pizzeria.addSalaryToExpenses(numOfPizza*2);
		
	}
}
