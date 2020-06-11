import java.util.Vector;

public class InformationSystem extends Thread {
	private Vector <Order> farD;
	private Vector <Order> shortD;
	private Vector <Order> meduimD;
	
	

	public InformationSystem() {
		farD = new Vector<Order>();
		meduimD = new Vector<Order>();
		shortD = new Vector<Order>();
		
	}

	public synchronized void insertOrder(Order o) {
		if(o.getDistance()> 8) {
		farD.add(o);
		
		}
		if(o.getDistance() <=3) {
			shortD.add(o);
			
		}
		if(o.getDistance()<=8 && o.getDistance()>3) {
			meduimD.add(o);
			
		}
		System.out.println("New Order Arriverd:");
		System.out.println("Serial number: "+ o.getSerialNum());
		this.notifyAll();
	}

	public synchronized Order extractOrder() {
		while (shortD.isEmpty()&&meduimD.isEmpty()&&farD.isEmpty())
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(!shortD.isEmpty()) {
		Order o1 = shortD.elementAt(0);
		shortD.remove(o1);
		return o1;
		}
		if(!meduimD.isEmpty()) {
		Order o2 = meduimD.elementAt(0);
		meduimD.remove(o2);
		return o2;
		}
		if(!farD.isEmpty()) {
		Order o3 = farD.elementAt(0);
		farD.remove(o3);
		return o3;
		}
		else {
			return null;
		}
	}

}
