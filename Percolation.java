import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    public static final int SITE_BLOCKED = 0;
    public static final int SITE_OPEN = 0;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){ 
        grid = new int[n][n];
        for (int row = 0; row < n; row++) {
            for(int col = 0; col < n; col++){
                grid[row][col] = SITE_BLOCKED;
            }
        }
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col){
    }
    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        return true;
    }
    // is site (row, col) full?
    public boolean isFull(int row, int col){
        return true;
    }

    // number of open sites
    public int numberOfOpenSites(){
        return 1;
    }

    // does the system percolate?
    public boolean percolates(){
        return true;
    }

    public static void main(String[] args) { 
        System.out.println("Hello, World");
    }
}
