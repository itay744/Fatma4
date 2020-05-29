import java.util.Vector;

class BoundedBuffer<T> {

	private Vector<T> buffer;
	private int maxSize;

	public BoundedBuffer(int maxSize) {
		buffer = new Vector<T>();
		this.maxSize = maxSize;
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
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		T item = buffer.elementAt(0);
		buffer.remove(item);
		notifyAll();
		return item;
	}
}