import java.util.Vector;

public class PizzaGuy extends Thread {
	private String name;
	private double salary;
	private int deliveryCapacity;
	private int totalDeliveries;
	public static boolean onlyOneDeliveryPermited = false;
	BoundedQueue<PizzaDelivery> deliveries;
	PizzaDelivery[] delivery;

	public PizzaGuy(String name, BoundedQueue<PizzaDelivery> deliveries) {
		this.name = name;
		deliveryCapacity = (int) (Math.random() * 2) + 2;
		this.salary = 0;
		this.deliveries = deliveries;
		delivery = new PizzaDelivery[deliveryCapacity];
		totalDeliveries=0;
	}

	private int addTip() {
		int tip;
		tip = (int) (Math.random() * 15);
		return tip;
	}

	private void addDeliveryToSalary(int numOfDeliveries, double distance, int tips) {
		salary += 3 * numOfDeliveries + 4 * distance + tips;
		Pizzeria.addSalaryToExpenses(3 * numOfDeliveries + 4 * distance);
	}
	
	public int getTotalDeliveries() {
		return totalDeliveries;
	}

	public void run() {
		
		if(onlyOneDeliveryPermited) {
			deliveryCapacity = 1;
		}
		
		for (int i = 0; i < deliveryCapacity; i++) {
			PizzaDelivery d = deliveries.extract();
			totalDeliveries++;
			delivery[i] = d;

		}
		for (int i = 0; i < deliveryCapacity; i++) {
			int currentDriving = (int) delivery[i].getDistance();

			try {
				sleep(currentDriving*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			addDeliveryToSalary(1, currentDriving, addTip());
			Pizzeria.manager.addToDeliveredOrder();
			Pizzeria.manager.checkIfDayIsOver();
			
		}
	}
	
	

}
