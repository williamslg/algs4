public class Subset
{
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        //System.out.println(k);
        /* RandomizedQueue version */
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            queue.enqueue(s);
        }
        
        for (int i = 0; i < k; i++)
        {
            System.out.println(queue.dequeue());
        }
       
        
        // Deque
        /*
        Deque<String> queue = new Deque<String>();
        int n = 0;
        
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (n < k) {
                queue.addLast(s);
            }
            int index = StdRandom.uniform(n+1);
            n++;
            if (index < 3) {
                if (index == 0) {
                    queue.removeFirst();
                    queue.addFirst(s);
                } else if (index == 1) {
                    String first = queue.removeFirst();
                    queue.removeFirst();
                    queue.addFirst(s);
                    queue.addFirst(first);
                } else {
                    queue.removeLast();
                    queue.addLast(s);
                }
            }
        }
        
        for (String s: queue) {
            System.out.println(s);
        }
        */
        
    }
}
