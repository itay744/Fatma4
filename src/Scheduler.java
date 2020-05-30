
public class Scheduler extends Thread{
	private String name;
	private double salary;
	UnboundedBuffer<Call> calls = new UnboundedBuffer<Call>();


	public Scheduler(String name, UnboundedBuffer<Call> calls) {
		super();
		this.name = name;
		this.calls = calls;
		this.salary = 0;
	}

	@Override
	public void run() {
		Call temp = calls.extract();
		try {
			Thread.sleep((temp.getCallDuration()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(temp.getNumOfPizzas() < 10) {
			Order newOrder = newOrder(temp);
		}
		else
		{
			
		}
		
		
	}
	
	
	private Order newOrder(Call temp) {	
		int numOfPizzas = temp.getNumOfPizzas();
		double totalPrice = 25*numOfPizzas;
		Order newOrder = new Order(Call.siryalNum,numOfPizzas,temp.getAddress(),
				temp.getCreditCardNum(),totalPrice,temp.getArrivalTime());
		return newOrder;
	}
	
	
	
	
}
