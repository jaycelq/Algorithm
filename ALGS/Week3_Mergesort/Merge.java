public class Merge {
    //This class should not be instantiated.
    private Merge() {}
    
    private static void merge(Comparable [] a, Comparable [] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);
        
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) 
                a[k] = aux[j++];
            else if (j > hi) 
                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);
    }
    
    private static void sort(Comparable [] a, Comparable [] aux, int lo, int hi) {
        if (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            merge(a, aux, lo, mid, hi);
        }
    }
    
    public static void sort(Comparable [] a) {
        Comparable [] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
        assert isSorted(a);
    }
    
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w)) < 0;
    }
    
    private static void exch(Comparable [] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private static boolean isSorted(Comparable [] a) {
        return isSorted(a, 0, a.length - 1);
    }
    
    private static boolean isSorted(Comparable [] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }
    
    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Merge.sort(a);
        show(a);
    }
}