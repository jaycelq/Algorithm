import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private int N;
    private Node<Item> first;
    
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }
    
    public Stack() {
        N = 0;
        first = null;
    }
    
    public boolean isEmpty() {
        return N == 0;
    }
    
    public int size() {
        return N;
    }
    
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }
    
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return first.item;
    }
    
    public Iterator<Item> iterator() {return new ListIterator();}
    
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;
        public ListIterator() {current = first;}
        public boolean hasNext() {return current != null;}
        public void remove() {throw new UnsupportedOperationException();}
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args) {
        Stack<String> strStack = new Stack<String>();
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