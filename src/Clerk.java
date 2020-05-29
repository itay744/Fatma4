
public class Clerk extends Thread {
	private String name;
	private double salary;
	private UnboundedBuffer<Call> callLine;

	public Clerk(String name, UnboundedBuffer<Call> callLine) {
		this.name = name;
		this.callLine = callLine;
	}

	public void run() {
		while (true) {
			Call c = callLine.extract();
		}
	}

}
