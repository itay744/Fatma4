import java.util.Vector;

class BoundedQueue<T> {
	private Vector<T> buffer;
	private int maxSize;

	public BoundedQueue() {
		buffer = new Vector<T>();
		this.maxSize = 12;
	}
	
	public BoundedQueue(int size) {
		buffer = new Vector<T>();
		this.maxSize = size;
	}

	public synchronized void insert(T item) {
		while (buffer.size() >= maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		buffer.add(item);
		notifyAll();
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
	
	public int Size() {
		return buffer.size();
	}
}