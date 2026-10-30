/**
 * This RingBuffer object . . .
 * 
 * @author  
 * @version 
 */
public class RingBuffer 
{

    private double[] queue;
    private int size;
    private int headInd;
    private int tailInd;

    public RingBuffer(int capacity)
    {
        this.queue = new double[capacity];
        size = 0;
        headInd = 0;
        tailInd = 0;
    }

    public int size()
    {
        return size;
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public boolean isFull()
    {
        return (size == queue.length);
    }
    
    public void add(double value) throws OverflowException
    {
        if(isEmpty()){
            headInd = 0;
            tailInd = 0;
        }
        else{
            if(isFull()){
                throw new OverflowException("Queue is full");
            }
            if(tailInd == queue.length - 1){
                tailInd = 0;
            }
            else{
                tailInd++;
            }
        }
        queue[tailInd] = value;
        size++;
    }
    
    public double peek()
    {
        if(isEmpty()){
            throw new QueueUnderflowException("No elements in the queue");
        }
        else
        return queue[headInd];
    }
    
    public double remove()
    {
        if(isEmpty()){
            throw new QueueUnderflowException("No elements in the queue");
        }
        else{
            double current = queue[headInd];
            queue[headInd] = 0;
            size--;
            if(size == 0){
                headInd = 0;
                tailInd = 0;
            }
            else{
                if(headInd == queue.length - 1){
                    headInd = 0;
                }
                else{
                    headInd++;
                }
            }
            return current;
        }
    }
    
    // a simple test of the constructor and methods in RingBuffer
    public static void main(String[] args) 
    {
        int capacity = 100;
        RingBuffer buffer = new RingBuffer(capacity);  
        for (int i = 1; i <= capacity; i++) 
            buffer.add(i);
      
        double t = buffer.remove();
        buffer.add(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) 
        {
            double x = buffer.remove();
            double y = buffer.remove();
            buffer.add(x + y);
        }
        System.out.println(buffer.peek());
    }

}
