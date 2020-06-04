import java.util.Vector;

public class Queue<T> {
	private Vector<T> buffer;

	public Queue() {
		buffer = new Vector<T>();
	}

	public synchronized void insert(T item) {
		buffer.add(item);
		this.notifyAll();
	}

	public synchronized T extract() {
		while (buffer.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		T t = buffer.elementAt(0);
		buffer.remove(t);
		return t;
	}
	
	public int Size() {
		return buffer.size();
	}
	
	public boolean isEmpty() {
		return buffer.isEmpty();
	}

}
