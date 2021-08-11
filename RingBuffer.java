/* *****************************************************************************
 *  Name: Hasit
 *  NetID:
 *  Precept:
 *
 *  Partner Name:
 *  Partner NetID:
 *  Partner Precept:
 *
 *  Description: This program defines the ring buffer variable class.
 * The ring buffer models the medium (a string tied down at both ends) in which
 * the energy travels back and forth. The length of the ring buffer determines
 * the fundamental frequency of the resulting sound.
 *
 **************************************************************************** */

public class RingBuffer {

    private double[] rb;          // items in the buffer
    private int first;            // index for the next dequeue or peek
    private int last;             // index for the next enqueue
    private int size;             // number of items in the buffer
    private int storage;          // capacity of buffer


    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        rb = new double[capacity];
        storage = capacity;
        size = 0;
        first = 0;
        last = 0;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        return storage;
    }

    // return number of items currently in this ring buffer
    public int size() {
        return size;
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        if (size == storage) {
            return true;
        }
        return false;
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        if (isFull()) {
            throw new RuntimeException("RuntimeException at line 64: Queue is full");
        }

        rb[last] = x;
        last = (last + 1) % storage;
        size = size + 1;
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("RuntimeException at line 76: Queue is empty");
        }
        double temp = rb[first];
        first = (first + 1) % storage;
        size = size - 1;
        return temp;
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        if (isEmpty()) {
            throw new RuntimeException("RuntimeException at line 87: Queue is empty");
        }

        return rb[first];
    }

    // tests and calls every instance method in this class
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.peek());
    }

}


