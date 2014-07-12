public class Quick3way {
    
    // This class should not be instantiated
    private Quick3way() {}
    
    public static void sort(Comparable [] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }
    
    private static void sort(Comparable [] a, int lo, int hi) {
        if(lo < hi) {
            Comparable v = a[lo];
            int lt = lo, gt = hi;
            int i = lo + 1;
            while (i <= gt) {
                if (less(a[i],v)) 
                    exch(a, lt++, i++);
                else if (less(v, a[i]))
                    exch(a, i, gt--);
                else
                    i++;
            }
            sort(a, lo, lt-1);
            sort(a, gt+1, hi);
            assert isSorted(a, lo, hi);
        }
    }
    
    
   /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // does v == w ?
    private static boolean eq(Comparable v, Comparable w) {
        return (v.compareTo(w) == 0);
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }



    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; 3-way
     * quicksorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick3way.sort(a);
        show(a);
    }
}