import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item [] a;
    private int N;
    
    public ResizingArrayStack() {
        a = (Item []) new Object[2];
        N = 0;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    private void resize(int capacity) {
        assert capacity >= N;
        Item [] temp = (Item []) new Object [capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }
    
    public void push(Item item) {
        if (N >= a.length) resize(2 * a.length);
        a[N++] = item;
    }
    
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        
        Item item = a[--N];
        a[N] = null;
        
        if (N > 0 && N <= a.length/4) resize(a.length/2);
        
        return item;
    }
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        
        return a[N-1];
    }
    
    public Iterator<Item> iterator() {return new ReverseArrayIterator();}
    
    public class ReverseArrayIterator implements Iterator<Item> {
        private int i;
        public ReverseArrayIterator() {
            i = N;
        }
        public boolean hasNext() {
            return i != 0;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if(!hasNext()) throw new NoSuchElementException();
            return a[--i];
        }
    }
    
    public static void main(String[] args) {
        ResizingArrayStack<String> strStack = new ResizingArrayStack<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) strStack.push(item);
            else if (!strStack.isEmpty()) StdOut.print(strStack.pop() + " ");
        }
        
        for(String s : strStack)
            StdOut.println(s);
        
        StdOut.println("(" + strStack.size() + " left on stack)");
    }
}