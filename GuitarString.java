/**
 * Program that can be used to simulate the pluck of a guitar string
 * @author Justin Andrea
 *
 */


public class GuitarString {

	private RingBuffer ringBuffer;
	private int size;
	private int ticCount;
	
	/**
	 * Creates a guitar string of the given frequency, using a sampling rate of 44,100
	 * @param frequency the fundamental frequency 
	 * @throws Exception if the ringBuffer is full
	 */
	GuitarString(double frequency) throws Exception{
		 size = (int) Math.ceil(44100.0/frequency);
		ringBuffer = new RingBuffer(size);
		for(int i = 0; i < size; i++) {
			ringBuffer.enqueue(0.0);
		}
	}
	
	/**
	 * Creates a guitar string whose size and initial values are given by the array
	 * @param init the array of doubles used to determine the size and initial values of the guitar string
	 * @throws Exception if the ringBuffer is full
	 */
	GuitarString(double[] init) throws Exception{
		ringBuffer = new RingBuffer(init.length);
		for(int i = 0; i < init.length; i++) {
			ringBuffer.enqueue(init[i]);
		}
	}
	
	/**
	 * Sets the ringBuffer to white noise
	 * @throws Exception if the ringBuffer is either full or empty
	 */
	public void pluck() throws Exception{

		for(int i = 0; i < size; i++) {
			ringBuffer.dequeue();
			double answer = Math.random()+(-.5);
			ringBuffer.enqueue(answer);
			
		}
		
	}
	/**
	 * Advances the simulation one time step
	 * @throws Exception if the ringBuffer is either full or empty 
	 */
	public void tic() throws Exception {
	
		ringBuffer.enqueue(.994 * ((ringBuffer.dequeue()+ringBuffer.peek()) *.5));
		ticCount++;
	}
	
	/**
	 * Gets the value of the item at the front of the ringBuffer.
	 * @return the current sample (the value of item at the front of the ringBuffer)
	 */
	public double sample() {
		
		return ringBuffer.peek();
		
	}
	
	/**
	 * Gets the total number of times tic() has been called
	 * @return ticCount the total number of times tic() has been called
	 */
	public int time() {
		return ticCount;
	}
	
	
}
