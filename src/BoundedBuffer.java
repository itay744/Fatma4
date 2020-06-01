import java.util.Vector;

class BoundedBuffer<T> {
	private Vector<T> buffer;
	private int maxSize;

	public BoundedBuffer() {
		buffer = new Vector<T>();
		this.maxSize = 12;
	}

	public synchronized void insert(T item) {
		while (buffer.size() >= maxSize)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
}