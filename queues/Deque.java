import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;
    
    private class Node
    {
        private Item item;
        private Node previous;
        private Node next;
    }
    
    public Deque()                          // construct an empty deque
    {
        first = null;
        last = null;
        N = 0;
    }
    
    public boolean isEmpty()                 // is the deque empty?
    {
        return N == 0;
    }
    
    public int size()                        // return the number of items on the deque
    {
        return N;
    }
    
    public void addFirst(Item item)          // insert the item at the front
    {
        if (item == null) throw new NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.previous = null;
        if (isEmpty()) {
            last = first;
        } else {
            oldfirst.previous = first;
        }
        N++;
    }
    
    public void addLast(Item item)           // insert the item at the end
    {
        if (item == null) throw new NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
            last.previous = oldlast;
        }
        N++;
    }
    
    public Item removeFirst()                // delete and return the item at the front
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        //N--;
        if (first == null) {
            last = null;
        } else {
            first.previous = null;
        }
        N--;
        return item;
    }
    
    public Item removeLast()                 // delete and return the item at the end
    {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }
        N--;
        return item;
    }
    
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        
        public boolean hasNext()
        {
            return current != null;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    
    public static void main(String[] args)   // unit testing
    {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 5; i++) {
            deque.addFirst(i+1);
            //deque.addLast((i+1)*100);
            //System.out.println(deque.removeLast());
            //System.out.println(deque.removeFirst());
        }
        
        for (int a: deque) {
            System.out.println(a);
        }
        for (int i = 0; i < 5; i++) {
            //System.out.println(deque.removeFirst());
            System.out.println(deque.removeLast());
            System.out.println("Size: " + deque.size());
        }
        
        
        
    }
}