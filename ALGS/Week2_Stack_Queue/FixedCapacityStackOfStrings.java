import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStackOfStrings implements Iterable<String> {
    private String [] s;
    private int N;
    
    public FixedCapacityStackOfStrings(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException();
        s = new String [capacity];
        N = 0;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void push(String item) {
        if (N >= s.length) throw new ArrayIndexOutOfBoundsException();
        s[N++] = item;
    }
    
    public String pop() {
        if (N <= 0) throw new ArrayIndexOutOfBoundsException();
        String item = s[--N];
        s[N] = null;
        return item;
    }
    
    public Iterator<String> iterator() {return new ReverseArrayIterator();}
    
    public class ReverseArrayIterator implements Iterator<String> {
        private int i;
        
        public ReverseArrayIterator() {
            i = N;
        }
        
        public boolean hasNext() {
            return i > 0;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
        public String next() {
            if(!hasNext()) throw new NoSuchElementException();
            return s[--i];
        }
    }
    
    static public void main(String [] args) {
        FixedCapacityStackOfStrings strStack = new FixedCapacityStackOfStrings(10);
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if(!item.equals("-")) strStack.push(item);
            else if (!strStack.isEmpty()) StdOut.print(strStack.pop() + " ");
        }
        
        for(String s : strStack)
            StdOut.println(s);
        
        StdOut.println("(" + strStack.size() + " left on stack)");
    }
}