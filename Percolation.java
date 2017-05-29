import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[] grid;
    private int size;
    private int virtual_top = 0;
    private int virtual_bot = 0;
    private WeightedQuickUnionUF uf;
    public static final int SITE_BLOCKED = 0;
    public static final int SITE_OPEN = 1;
    // create n-by-n grid, with all sites blocked
    public Percolation(int n){ 
        grid = new int[n*n];
        virtual_top  = n*n -1 + 1;
        virtual_bot  = n*n -1 + 2;
        uf = new WeightedQuickUnionUF(n*n + 2);
        size = n;
        for (int i = 0;i < n*n; i++) {
            grid[i] = SITE_BLOCKED;
        }

        for(int i = 0; i < n; i++){
            uf.union(i, virtual_top); 
        }

        for(int i = n*(n -1) ; i < n*n; i++){
            uf.union(i, virtual_bot); 
        }
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col){
        System.out.println("*********");
        int current_index = row* size + col;
         System.out.println(current_index);
        // Mark new site as open
        if(grid[current_index] != SITE_OPEN){
            grid[current_index] = SITE_OPEN;
        }

        // connect it to all of its adjacent open sites
        for(int i = row -1; i < row + 2; i++){
            for (int j = col - 1; j < col +2; j++) {
                int temp_index = size*i + j;
                // check if the site is adjacent or not?
                if( ((i == row) || (j == col)) ){
                    // If the site is open then connect to it
                    if((isOpen(i,j) == true)){
                        System.out.println(temp_index);
                        System.out.println(current_index);
                        uf.union(temp_index, current_index);
                    }
                }
            }
        }
    }
    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        int index = row* size + col;
        if(row < 0 || col < 0 || row > 4 || col > 4)
            return false;

        if(grid[index] == SITE_OPEN){
            return true;
        }
        else{
            return false;
        }
    }
    // is site (row, col) full?
    public boolean isFull(int row, int col){
        // A full site is an open site that can 
        // be connected to an open site in the top row via a 
        // chain of neighboring (left, right, up, down) open sites
        int current_index = size*row + col;
        // check if the site is a open site
        if(isOpen(row, col) == true){
            // check if there is any open site in the top row
            for(int i = 0; i < size; i++){
                if(isOpen(0, i) == true){
                    if(uf.connected(i, current_index) == true){
                        return true;
                    }
                }
            }
        }

        return false;
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
        Percolation object = new Percolation(5);
        // object.open(0,0);
        // object.open(0,1);
        // object.open(1,1);
        // object.open(1,2);
        // object.open(2,2);
        // object.open(2,3);
        // object.open(3,3);
        // object.open(3,4);
        // object.open(4,4);
        // if(object.isOpen(0,1) == true){
        //     System.out.println("site 0,0 open");
        // }

        // if(object.uf.connected(0, 2) == true)
        // {
        //     System.out.println("site 1,2 and 2,2 are connected to each other");
        // }

        // start test open method
        System.out.println("test open method");
        object.open(0,0);
        object.open(0,4);
        object.open(4,0);
        object.open(4,4);
        object.open(2,2);

        if(object.isOpen(0,0) != true){
            System.out.println("test open fail");
        }
        else{
            System.out.println("test open1 pass");
        }

        if(object.isOpen(0,4) != true){
            System.out.println("test open fail");
        }
        else{
            System.out.println("test open2 pass");
        }

        if(object.isOpen(4,0) != true){
            System.out.println("test open fail");
        }
        else{
            System.out.println("test open3 pass");
        }

        if(object.isOpen(4,4) != true){
            System.out.println("test open fail");
        }
        else{
            System.out.println("test open4 pass");
        }

        if(object.isOpen(2,2) != true){
            System.out.println("test open fail");
        }
        else{
            System.out.println("test open5 pass");
        }

        if(object.isOpen(2,1) == true){
            System.out.println("test open fail");
        }
        else{
            System.out.println("test open6 pass");
        }

        Percolation object1 = new Percolation(5);
        object1.open(1,2);

        if(object1.uf.connected(7, 6) == true)
        {
            System.out.println("test open 7 failed");
        }
        else{
            System.out.println("test open 7 passed");
        }

        if(object1.uf.connected(7, 2) == true)
        {
            System.out.println("test open 7 failed");
        }
        else{
            System.out.println("test open 7 passed");
        }

        if(object1.uf.connected(7, 8) == true)
        {
            System.out.println("test open 7 failed");
        }
        else{
            System.out.println("test open 7 passed");
        }

        if(object1.uf.connected(7, 12) == true)
        {
            System.out.println("test open 7 failed");
        }
        else{
            System.out.println("test open 7 passed");
        }

        object1.open(1,1);

        if(object1.uf.connected(7, 6) == true)
        {
            System.out.println("test open 8 passed");
        }
        else{
            System.out.println("test open 8 failed");
        }

        if(object1.uf.connected(7, 2) == true)
        {
            System.out.println("test open 8 failed");
        }
        else{
            System.out.println("test open 8 passed");
        }

        if(object1.uf.connected(7, 8) == true)
        {
            System.out.println("test open 8 failed");
        }
        else{
            System.out.println("test open 8 passed");
        }

        if(object1.uf.connected(7, 12) == true)
        {
            System.out.println("test open 8 failed");
        }
        else{
            System.out.println("test open 8 passed");
        }

        object1.open(0,2);

        if(object1.uf.connected(7, 2) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 6) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 8) == true)
        {
            System.out.println("test open 9 failed");
        }
        else{
            System.out.println("test open 9 passed");
        }

        if(object1.uf.connected(7, 12) == true)
        {
            System.out.println("test open 9 failed");
        }
        else{
            System.out.println("test open 9 passed");
        }

        object1.open(1,3);

        if(object1.uf.connected(7, 2) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 6) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 8) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 12) == true)
        {
            System.out.println("test open 9 failed");
        }
        else{
            System.out.println("test open 9 passed");
        }


        object1.open(2,2);
        if(object1.uf.connected(7, 2) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 6) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 8) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }

        if(object1.uf.connected(7, 12) == true)
        {
            System.out.println("test open 9 passed");
        }
        else{
            System.out.println("test open 9 failed");
        }
    }
}
