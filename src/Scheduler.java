
public class Scheduler extends Thread {
	private String name;
	private double salary;
	UnboundedBuffer<Order> orders;

	public Scheduler(String name, UnboundedBuffer<Order> orders) {
		super();
		this.name = name;
		this.salary = 0;
		this.orders = orders;
	}

	public void run() {
		Order o = orders.extract();
	}

	private double calculateWorkingTime(double distance) {
		return distance * 0.25;
	}

	private void addOrderToSalary(double workingTime) {
		salary += workingTime * 4;
	}

	private double convertAddress(Order o) {
		String address = o.getAddress();
		double distance = calculateDistance(address);
		return distance;

	}

	private double calculateDistance(String s) {
		int distance = countWordsUsingSplit(s);
		char c = s.charAt(0);
		distance += addDistanceByFirstLetter(c);
		return distance;
	}

	private double addDistanceByFirstLetter(char c) {
		if (c >= 'a' || c <= 'h') {
			return 0.5;
		}
		if (c >= 'i' || c <= 'p') {
			return 2;
		}
		if (c >= 'q' || c <= 'z') {
			return 7;
		}
		if (c >= '0' || c <= '9') {
			return c - '0';
		}
		return 0;
	}

	public static int countWordsUsingSplit(String input) {
		if (input == null || input.isEmpty()) {
			return 0;
		}
		String[] words = input.split("\\s+");
		return words.length;
	}

}
