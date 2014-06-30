public class DeleteSuccessor {
    private int [] id;
    
    public DeleteSuccessor (int N) {
        if(N < 0)
            throw new IllegalArgumentException();
        id = new int [N];
        
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }
    
    private int root (int x) {
        while (id[x] != x) {
            id[x] = id[id[x]];
            x = id[x];
        }
        return x;
    }
    
    public void delete(int x) {
        if(x < 0 || x >= id.length) 
            throw new java.lang.IndexOutOfBoundsException();
        
        // if x hasn't beed deleted, then id[x] == x, so if x has beed deleted,
        // no need to delete once more.
        if(id[x] != x) return;
        
        id[x] = root(x + 1);
    }
    
    public int successor(int x) {
        if(x < 0 || x >= id.length) 
            throw new java.lang.IndexOutOfBoundsException();
        
        return root(x);
    }
    
    public static void main(String [] args) {
        DeleteSuccessor ds = new DeleteSuccessor(10);
        
        ds.delete(3);
        ds.delete(5);
        ds.delete(4);
        
        System.out.println("successor of 2: " + ds.successor(2));
        System.out.println("successor of 2: " + ds.successor(4));
        System.out.println("successor of 2: " + ds.successor(6));
    }
}