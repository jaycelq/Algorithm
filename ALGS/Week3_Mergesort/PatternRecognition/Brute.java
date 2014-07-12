import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        Point [] p = new Point[N];
        Point [] out = new Point[4];
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger

        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            p[i] = new Point(x, y);
            p[i].draw();
        }
        
        for (int i = 0; i < N - 3; i++) {
            for (int j = i + 1; j < N - 2; j++) {
                for (int k = j + 1; k < N - 1; k++) {
                    for (int z = k + 1; z < N; z++) {
                        if (p[i].slopeTo(p[j]) == p[i].slopeTo(p[k]) 
                            && p[i].slopeTo(p[j]) == p[i].slopeTo(p[z])) {
                            out[0] = p[i];
                            out[1] = p[j];
                            out[2] = p[k];
                            out[3] = p[z];
                            Arrays.sort(out);
                            for (int n = 0; n < 3; n++) {
                                StdOut.print(out[n].toString() + " -> ");
                            }
                            StdOut.println(out[3].toString());
                            out[0].drawTo(out[3]);
                        }
                    }
                }
            }
        }
        // display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}