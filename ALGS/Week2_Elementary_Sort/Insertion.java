public class Insertion {
    //This class should not be instantiated
    private Insertion() {}
    
    public static void sort(Comparable [] a) {
        int N = a.length;
        
        for (int i = N-1; i > 0; i--)
            if(less(a[i], a[i-1])) exch(a, i, i-1);
        
        for (int i = 2; i < N; i++) {
            Comparable v = a[i];
            int j = i-1;
            for (; j >= 0; j--) {
                if(less(v, a[j]))
                    a[j+1] = a[j];
                else
                    break;
            }
            a[j+1] = v;
        }
        
        assert isSorted(a);
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void exch(Comparable [] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = a[i];
    }
    
    public static boolean isSorted(Comparable [] a) {
        for (int i = 1; i < a.length - 1; i++) 
            if(less(a[i], a[i-1])) return false;
        return true;
    }
    
    public static void show(Comparable [] a) {
        for (int i = 0; i < a.length; i++)
            StdOut.println(a[i]);
    }
    
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        InsertionX.sort(a);
        show(a);
    }
}