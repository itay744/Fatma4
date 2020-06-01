import java.util.Vector;

public class UnboundedBuffer<T> {
	private Vector<T> buffer;

	public UnboundedBuffer() {
		buffer = new Vector<T>();
	}

	public synchronized void insert(T item) {
		buffer.add(item);
		this.notifyAll();
	}

	public synchronized T extract() {
		while (buffer.isEmpty())
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		T t = buffer.elementAt(0);
		buffer.remove(t);
		return t;
	}
	
	public boolean isEmpty() {
		if(buffer.isEmpty()) {
			return false;
		}
		return true;
	}
}
