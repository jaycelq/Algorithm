public class Quick {
    private Quick() {}
    
    public static void sort(Comparable [] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }
    
    private static void sort(Comparable [] a, int lo, int hi) {
        if (lo < hi) {
            int mid = partition(a, lo, hi);
            sort(a, lo, mid - 1);
            sort(a, mid+1, hi);
        }
        assert isSorted(a, lo, hi);
    }
    
    private static int partition(Comparable [] a, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        
        while (i < j) {
            while (less(a[++i], a[lo]))
                if(i == hi) break;
        
            while (less(a[lo], a[--j]))
                if (j == lo) break;
            
            if (i >= j) break;
            else exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    
    private static Comparable select(Comparable [] a, int k) {
        int lo = 0, hi = a.length - 1;
        
        while (hi > lo) {
            int mid = partition(a, lo, hi);
            if(k == mid) return a[k];
            else if (k > mid) 
                lo = mid + 1;
            else
                hi = mid - 1;
        }
 
        return a[lo];
    }
    
    
   /***********************************************************************
    *  Helper sorting functions
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
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
     * Reads in a sequence of strings from standard input; quicksorts them; 
     * and prints them to standard output in ascending order. 
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        show(a);

        // shuffle
        StdRandom.shuffle(a);

        // display results again using select
        StdOut.println();
        for (int i = 0; i < a.length; i++) {
            String ith = (String) Quick.select(a, i);
            StdOut.println(ith);
        }
    }
}