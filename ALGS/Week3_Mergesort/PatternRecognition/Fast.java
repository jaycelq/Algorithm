import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Point [] p = new Point[N];
        Point [] out = new Point[N];
        int num = 0;
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
            out[i] = p[i];
            p[i].draw();
        }
        
        for (int i = 0; i < N; i++) {
            for (int z = 0; z < N; z++) 
                out[z] = p[z];

            Arrays.sort(out, p[i].SLOPE_ORDER);
            int j = 1, k = 2;
            while (k < N) {
                while (out[0].slopeTo(out[j]) == out[0].slopeTo(out[k])) {
                    k++;
                    if (k >= N)
                        break;
                }
                if (k - j >= 3) {
                    out[j-1] = p[i];
                    Arrays.sort(out, j-1, k);
                    if (out[j-1] == p[i]) {
                        for (int n = j-1; n < k-1; n++) {
                                StdOut.print(out[n].toString() + " -> ");
                            }
                        StdOut.println(out[k-1].toString());
                        out[j-1].drawTo(out[k-1]);
                        num++;
                    }
                    else 
                        out[0] = p[i];
                }
                j = k;
                k++;
            }
        }
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}