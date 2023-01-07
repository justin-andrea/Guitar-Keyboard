/**
 * Class that creates a RingBuffer using a circular array of doubles
 * @author Justin Andrea
 *
 */

public class RingBuffer  {
	private int capacity;
	private int size;
	private int tail;
	private int front;
	private double[] samples;
	
	
	
	/**
	 * creates an empty RingBuffer, with given max capacity
	 * @param capacity
	 */
	public RingBuffer(int capacity) {
		this.capacity = capacity;
		size = 0;
		front = 0;
		tail =  0;
		samples = new double[capacity]; 
	}


	
	/**
	 * returns the number of items currently in the RingBuffer
	 * @return size, the number of items currently in the RingBuffer
	 */
	public int size() {
		return size;
	}
	
	
	/**
	 * Tests if the RingBuffer is empty (size equals zero)
	 * @return true if the RingBuffer is empty, false otherwise
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Tests if the RingBuffer is full (size equals capacity)
	 * @return true of the RingBuffer is full, false otherwise
	 */
	public boolean isFull() {
		if(size == capacity ) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Adds the given item to the end of the RingBuffer 
	 * @param x the double to be added to the end of the RingBuffer
	 * @throws Exception if the RingBuffer is full
	 */
	public void enqueue(double x) throws Exception {
		if(isFull()) {
			throw new Exception(" the RingBuffer is full");
		}
		
		if(isEmpty()) {
			
			samples[0] = x;
			size++;
			
		}else if(tail == capacity -1) {
			
			tail = 0;
			samples[tail] = x;
			size++;
			
		}else {
			
			tail++;
			samples[tail] = x;
			size++;
			
		}
		
	}
	
	
	/**
	 * Removes the item from the front of the RingBuffer
	 * @return the item that was removed
	 * @throws Exception if the RingBuffer is empty
	 */
	public double dequeue() throws Exception {
		double store = 0.0;
		
	if(isEmpty()) {
		throw new Exception("the RingBuffer is already empty");
		
	  } else if(front == capacity -1) {
		  
		store = samples[capacity-1];
		samples[capacity-1] = 0.0;
		size--;
		front = 0;
		
	  } else {
		  
        store = samples[front];	
        samples[front] = 0.0;
		front++;
		size--;
		
	  }
	
	return store;
	
	}
	
	
	/**
	 * Looks at the value in the front of the RingBuffer without removal
	 * @return the item at the front of the RingBuffer 
	 */
	public double peek() {
		return samples[front];
	}
	

}
