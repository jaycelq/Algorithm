import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private int N;
    private Node<Item> first;
    private Node<Item> last;
    private class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;
    }
    
    // construct an empty deque
    public Deque() {
        N = 0;
        first = null;
        last = null;
    }
    
    // is the deque empty?
    public boolean isEmpty() {
        return N == 0;
    }
    
    // return the number of items on the deque
    public int size() {
        return N;
    }
    
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.prev = null;
        if (isEmpty()) {
            first.next = null;
            last = first;
        }
        else {
            first.next = oldfirst;
            oldfirst.prev = first;
        }
        N++;
    }
    
    public void addLast(Item item) {          // insert the item at the end
        if (item == null) throw new NullPointerException();
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            last.prev = null;
            first = last;
        }
        else {
            last.prev = oldlast;
            oldlast.next = last;
        }
        N++;
    }
    
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty()) 
            last = null;
        else first.prev = null;
        return item;
    }
    
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        N--;
        if (isEmpty()) 
            first = null;
        else 
            last.next = null;
        return item;
    }
    
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new ListedIterator();
    }
    
    private class ListedIterator implements Iterator<Item> {
        private Node<Item> current = first;
        public boolean hasNext() {
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    // unit testing
    public static void main(String[] args) {
        Deque<String> q = new Deque<String>();
        
        q.addFirst("abc");
        q.addLast("cde");
        q.addLast("def");
        q.addFirst("fqw");
        q.addLast("fwg");
        
        StdOut.println(q.removeLast());
        StdOut.println(q.removeFirst());
        StdOut.println(q.removeLast());
        StdOut.println(q.removeFirst());
        
        for (String s : q)
            StdOut.println(s);
        
        StdOut.println("(" + q.size() + " left on queue)");
    }
}