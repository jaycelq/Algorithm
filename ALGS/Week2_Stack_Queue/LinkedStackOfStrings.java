import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStackOfStrings implements Iterable<String>{
    private class Node { // 16 bytes (object overhead)
        String item;     // 8 bytes (inner class extra overhead)
        Node next;       // 8 bytes (reference to String)
    }                    // 8 bytes (reference to Node)
    
    private Node first = null;
    private int N;

    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return N;
    }
    
    public void push(String item) {
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
        assert check();
    }
    
    public String pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        String item = first.item;
        first = first.next;
        N--;
        assert check();
        return item;
    }
    
    public String peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item;
    }
    
    public Iterator<String> iterator() { return new ListIterator();}

    private class ListIterator implements Iterator<String> {
        private Node current = first;
        public boolean hasNext() { return current != null; }
        public void remove() { throw new UnsupportedOperationException(); }
        
        public String next() {
            if(!hasNext()) throw new NoSuchElementException();
            String item = current.item;
            current = current.next;
            return item;
        }
    }
    
    private boolean check() {
        if(N == 0) {
            if (first != null) return false;
        }
        else if (N == 1) {
            if (first == null) return false;
            if (first.next != null) return false;
        }
        else {
            if(first.next == null) return false;
        }
        
        int numberOfNodes = 0;
        for (Node x = first; x != null; x = x.next) {
            numberOfNodes++;
        }
        if(numberOfNodes != N) return false;
        
        return true;
    }
    
    static public void main(String [] args) {
        LinkedStackOfStrings strStack = new LinkedStackOfStrings();
        
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