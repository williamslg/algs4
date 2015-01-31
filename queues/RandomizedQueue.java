import java.util.NoSuchElementException;
import java.util.Iterator;
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int N;
    
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        queue = (Item[]) new Object[2];
        N = 0;
    }
   
    public boolean isEmpty()                 // is the queue empty?
    {
        return N == 0;
    }
   
    public int size()                        // return the number of items on the queue
    {
        return N;
    }
   
    public void enqueue(Item item)           // add the item
    {
        if (item == null) throw new NullPointerException();
        if (N == queue.length) resize(2*queue.length);
        queue[N++] = item;
    }
    
    public Item dequeue()                    // delete and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(N); // 0- N-1
        Item item = queue[index];
        queue[index] = queue[N-1];
        queue[N-1] = null;
        N--;
        if (N > 0 && N == queue.length/4) resize(queue.length/2);
        return item;
    }
    
    public Item sample()                     // return (but do not delete) a random item
    {
        if (isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(N); // 0- N-1
        return queue[index];
    }
    
    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }
    
    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    {
        return new ListIterator();
    }
    
    private class ListIterator implements Iterator<Item>
    {
        private int[] copy;
        private int count;
        
        public ListIterator()
        {
            copy = new int[N];
            for (int i = 0; i < N; i++) {
                copy[i] = i;
            }
            shuffle(copy);
            count = 0;
        }
        
        private void shuffle(int[] a)
        {
            for (int i = 0; i < a.length; i++) {
                int r = StdRandom.uniform(i+1);
                int tmp = a[i];
                a[i] = a[r];
                a[r] = tmp;
            }
        }
        
        public boolean hasNext()
        {
            return count != N;
        }
        
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
        
        public Item next()
        {
            if (!hasNext()) throw new NoSuchElementException();
            return queue[copy[count++]];
        }
    }
    public static void main(String[] args)   // unit testing
    {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            //queue.dequeue();
        }
        
        //queue.dequeue();
        System.out.println("size(): " + queue.size());
        for (int value: queue) {
            System.out.println("Iterator1: " + value);
        }
        
        for (int value: queue) {
            System.out.println("Iterator2: " + value);
        }
    }
}