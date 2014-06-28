public class Percolation {
    private int N;
    private WeightedQuickUnionUF percolationUF;
    private WeightedQuickUnionUF fullUF;
    private boolean [][] open;
    
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException();
        this.N = N;
        percolationUF = new WeightedQuickUnionUF(N*N + 2);
        fullUF = new WeightedQuickUnionUF(N*N + 1);
        open = new boolean [N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                open[i][j] = false;
            }
        }
    }
    
    private int position(int row, int column) {
        return row * N + column + 1;
    }
    
    public void open(int i, int j) {
        int row = i - 1, column = j - 1;
        
        if (i < 1 || i > N || j < 1 || j > N)
            throw new java.lang.IndexOutOfBoundsException();
    
        if (open[row][column]) return;
        
        open[row][column] = true;
        
        if (row == 0) {
            percolationUF.union(0, position(row, column));
            fullUF.union(0, position(row, column));
        }
        
        if (row == N - 1) {
            percolationUF.union(N * N + 1, position(row, column));
        }
        
        if (row > 0) {
            if (open[row-1][column]) {
                percolationUF.union(position(row-1, column), position(row, column));
                fullUF.union(position(row-1, column), position(row, column));
            }
        }
        if (row < N-1) {
            if (open[row+1][column]) {
                percolationUF.union(position(row+1, column), position(row, column));
                fullUF.union(position(row+1, column), position(row, column));
            }
        }
        if (column > 0) {
            if (open[row][column-1]) {
                percolationUF.union(position(row, column-1), position(row, column));
                fullUF.union(position(row, column-1), position(row, column));
            }
        }
        if (column < N-1) {
            if (open[row][column+1]) {
                percolationUF.union(position(row, column+1), position(row, column));
                fullUF.union(position(row, column+1), position(row, column));
            }
        }
    }
    
    public boolean isOpen(int i, int j) {
        int row = i - 1, column = j - 1;
        
        if (i < 1 || i > N || j < 1 || j > N)
            throw new java.lang.IndexOutOfBoundsException();

        return open[row][column];
    }
    
    public boolean isFull(int i, int j) {
        int row = i - 1, column = j - 1;
        
        if (i < 1 || i > N || j < 1 || j > N)
            throw new java.lang.IndexOutOfBoundsException();

        return fullUF.connected(0, position(row, column));
    }
    
    public boolean percolates() {
        return percolationUF.connected(0, N*N+1);
    }
}