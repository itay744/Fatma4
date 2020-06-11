import java.util.Random;
import java.util.Vector;

public class PizzaGuy extends Employee implements Runnable{
	private String name;
	private double salary;
	private int deliveryCapacity;
	private int totalDeliveries;
	public static boolean onlyOneDeliveryPermited = false;
	BoundedQueue<PizzaDelivery> deliveries;
	PizzaDelivery[] delivery;

	public PizzaGuy(String name, BoundedQueue<PizzaDelivery> deliveries) {
		Random r = new Random();
		this.name = name;
		deliveryCapacity = r.nextInt(3) + 2;
		this.salary = 0;
		this.deliveries = deliveries;
		delivery = new PizzaDelivery[deliveryCapacity];
		totalDeliveries = 0;
	}

	private int addTip() {
		int tip;
		tip = (int) (Math.random() * 15);
		return tip;
	}

	public void setDeliveryCapacity(int deliveryCapacity) {
		this.deliveryCapacity = deliveryCapacity;
	}

	private void addDeliveryToSalary(int numOfDeliveries, double distance, int tips) {
		salary += 3 * numOfDeliveries + 4 * distance + tips;
		Pizzeria.addSalaryToExpenses(3 * numOfDeliveries + 4 * distance);
	}

	public int getTotalDeliveries() {
		return totalDeliveries;
	}

	public synchronized void run() {

		if (onlyOneDeliveryPermited) {
			this.setDeliveryCapacity(1);
		}

		for (int i = 0; i < 2; i++) {
			PizzaDelivery d = deliveries.extract();
			totalDeliveries++;
			delivery[i] = d;
		//	System.out.println(d);
			System.out.println("capacity "+ deliveryCapacity);
			System.out.println("tip "+ addTip());
		}
		for (int i = 0; i < 2; i++) {
			int currentDriving = (int) delivery[i].getDistance();

			try {
				Thread.sleep(currentDriving*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("current driving " +currentDriving);
			addDeliveryToSalary(1, currentDriving, addTip());
			Pizzeria.manager.addToDeliveredOrder();
			Pizzeria.manager.checkIfDayIsOver();
			delivery[i] = null;
			
		}
	}

}
