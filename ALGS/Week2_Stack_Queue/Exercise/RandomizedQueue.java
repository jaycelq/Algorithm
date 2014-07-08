import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int N;
    private Item [] q;
    
    // construct an empty randomized queue
    public RandomizedQueue() {
        N = 0;
        q = (Item []) new Object [2];
    }
    
    // is the queue empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the queue
    public int size() {
        return N;
    }
    
    private void resize(int capacity) {
        assert capacity >= N;
        Item [] temp = (Item []) new Object [capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = q[i];
        }
        q = temp;
    }
    
    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        q[N] = item;
        N++;
        if (N >= q.length) resize(2 * q.length);
    }
    
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int i = StdRandom.uniform(N);
        Item item = q[i];
        q[i] = q[N-1];
        q[N-1] = null;
        N--;
        if (N <= q.length/4 && N > 0) resize(q.length/2);
        return item;
    }
    
    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return q[StdRandom.uniform(N)];
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedArrayIterator();
    }
    
    private class RandomizedArrayIterator implements Iterator<Item> {
        private int iteratorN;
        private Item [] iteratorQ;
        public RandomizedArrayIterator() {
            iteratorQ = (Item []) new Object [N];
            for (int i = 0; i < N; i++) {
                iteratorQ[i] = q[i];
            }
            iteratorN = N;
        }
        public boolean hasNext() {
            return iteratorN > 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int i = StdRandom.uniform(iteratorN);
            Item item = iteratorQ[i];
            iteratorQ[i] = iteratorQ[iteratorN-1];
            iteratorQ[iteratorN-1] = null;
            iteratorN--;
            return item;
        }
    }
    
    public static void main(String[] args) {  // unit testing
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        
        for (String s : q)
            StdOut.println(s);
        
        StdOut.println("(" + q.size() + " left on queue)");
    }
}