import java.util.Vector;

class BoundedBuffer<T> extends UnboundedBuffer<T> {

	private Vector<T> buffer;
	private int maxSize;

	public BoundedBuffer(int maxSize) {
		super();
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

	
	
}