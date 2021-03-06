public class MergeBU {
    private MergeBU() {}
    
    private static void merge(Comparable [] a, Comparable [] aux, int lo, int mid, int hi) {
        //StdOut.println("lo " + lo + "mid " + mid + "hi " + hi);
        for (int i = lo; i <= hi; i++)
            aux[i] = a[i];
        
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        
    }
    
    public static void sort(Comparable [] a) {
        int N = a.length;
        Comparable [] aux = new Comparable[N];
        
        for(int n = 1; n < N; n = n+n) {
            for(int i = 0; i < N - n; i += 2*n) {
                merge(a, aux, i, i+n-1, Math.min(i + 2*n-1, N-1));
            }
        }
        assert isSorted(a);
    }
    
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
        for (int i = 1; i < a.length; i++)
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
     * Reads in a sequence of strings from standard input; bottom-up
     * mergesorts them; and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MergeBU.sort(a);
        show(a);
    }
}