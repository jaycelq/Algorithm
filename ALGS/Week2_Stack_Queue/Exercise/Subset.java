public class Subset {
    public static void main(String [] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int K;
        int i = 0;
        K = Integer.parseInt(args[0]);
        
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (i < K)
                q.enqueue(item);
            else {
                if (StdRandom.uniform(i+1) < K) {
                    q.dequeue();
                    q.enqueue(item);
                }
            }
            i++;
        }
        
        for (int j = 0; j < K; j++) {
            StdOut.println(q.dequeue());
        }
    }
}