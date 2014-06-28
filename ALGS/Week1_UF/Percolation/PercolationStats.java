public class PercolationStats {
    private double [] x;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        x = new double[T];
        for (int k = 0; k < T; k++) {
            int openNum = 0;
            Percolation p = new Percolation(N);
            while (true) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                if (p.isOpen(i, j)) 
                    continue;
                else {
                    openNum++;
                    p.open(i, j);
                    if (p.percolates()) {
                        x[k] = ((double) openNum) / (N * N);
                        break;
                    }
                }
            }
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(x.length);
    }
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(x.length);
    }
    // test client, described below
    public static void main(String[] args) {
        int N, T;
        N = Integer.parseInt(args[0]);
        T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " 
                           + ps.confidenceHi());
    }
}