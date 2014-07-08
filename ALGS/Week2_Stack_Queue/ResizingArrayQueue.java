import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<Item> implements Iterable<Item> {
    private int N = 0;
    private int first = 0;
    private int last = 0;
    private Item [] q;
    
    public ResizingArrayQueue() {
        q = (Item []) new Object [2];
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private void resize(int capacity) {
        assert capacity >= N;
        Item [] temp = (Item []) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = q[(first + i) % q.length];
        }
        q = temp;
        first = 0;
        last = N;
    }
    
    public void enqueue(Item item) {
        if (N >= q.length) resize(2 * q.length);
        q[last] = item;
        last = (last + 1) % q.length;
        N++;
    }
    
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = q[first];
        q[first] = null;
        first = (first + 1) % q.length;
        N--;
        if (N <= q.length/4 && N > 0) resize(q.length/2);
        return item;
    }
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return q[first];
    }
    
    public Iterator<Item> iterator() {return new ArrayIterator();}
    
    private class ArrayIterator implements Iterator<Item> {
        private int i;
        public ArrayIterator() {i = 0;}
        public boolean hasNext() {return i < N;}
        public void remove() {throw new UnsupportedOperationException();}
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }
    
    public static void main(String[] args) {
        ResizingArrayQueue<String> q = new ResizingArrayQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        
        for(String s : q)
            StdOut.println(s);
        
        StdOut.println("(" + q.size() + " left on queue)");
    }
}