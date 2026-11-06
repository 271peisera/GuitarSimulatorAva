/**
 * This GuitarString object . . .
 * 
 * @author  
 * @version 
 */
public class GuitarString 
{
    private RingBuffer ring;
    private int ticTime = 0;

    public GuitarString(double frequency) 
    {
        if (frequency <= 0 || Double.isNaN(frequency) || Double.isInfinite(frequency)) {
            throw new IllegalArgumentException("Frequency must be a finite positive value");
        }
        int capacity = (int) (44100 / frequency);
        if (capacity < 2) {
            throw new IllegalArgumentException("Frequency is too high for the sample rate");
        }
        ring = new RingBuffer(capacity);
        for (int i = 0; i < capacity; i++) {
            ring.add(0.0);
        }
    }

    public GuitarString(double[] array) 
    {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("At least two samples are required");
        }
        ring = new RingBuffer(array.length);
        for(int i = 0; i < array.length; i++){
            ring.add(array[i]);
        }
    }

    public void pluck() 
    {
        int samples = ring.size();
        for (int i = 0; i < samples; i++) {
            ring.remove();
            ring.add(Math.random() - 0.5);
        }
    }

    // advance the simulation one time step
    public void tic() 
    {
        double first = ring.remove();
        ring.add(((first + ring.peek()) / 2) * 0.994);
        ticTime++;
    }

    // return the current sample
    public double sample() 
    {
        return ring.peek();
    }

    // return number of times tic was called
    public int time() 
    {
        return ticTime;
    }

    public static void main(String[] args) 
    {
        double[] samples = { .2, .4, .5, .3, -.2, .4, .3, .0, -.1, -.3 };  
        GuitarString testString = new GuitarString(samples);
        for (int i = 0; i < 25; i++) 
        {
            int t = testString.time();
            double sample = testString.sample();
            System.out.printf("%6d %8.4f\n", t, sample);
            testString.tic();
        }
    }
}
