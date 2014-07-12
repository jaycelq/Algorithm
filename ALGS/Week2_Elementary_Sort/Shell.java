public class Shell {
    //This class should not be instantiated
    private Shell() {}
    
    public static void sort(Comparable [] a) {
        int N = a.length;
        
        int h = 1;
        while (h < N/3) h = 3*h + 1;
        
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                Comparable v = a[i];
                int j = i - h;
                for (; j >= 0; j -= h) {
                    if(less(v, a[j]))
                        a[j+h] = a[j];
                    else
                        break;
                }
                a[j+h] = v;
            }
            assert isHsorted(a, h);
            h = h/3;
        }
        assert isSorted(a);    
    }
    
    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    
    public static void exch(Comparable [] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    public static boolean isSorted(Comparable [] a) {
        int N = a.length;
        
        for (int i = 1; i < N; i++) 
            if(less(a[i], a[i-1])) return false;
        
        return true;
    }
    
    public static boolean isHsorted(Comparable [] a, int h) {
        int N = a.length;
        
        for (int i = h; i < N; i++)
            if(less(a[i], a[i-h])) return false;
        
        return true;
    }
    
    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; Shellsorts them; 
     * and prints them to standard output in ascending order. 
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Shell.sort(a);
        show(a);
    }
}