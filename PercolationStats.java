import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import java.lang.Math;
import java.lang.IllegalArgumentException;

public class PercolationStats {
 private double fraction[];
 private int t = 0;
 private double meanVal = 0;
 private double stddevVal = 0;
 private double confidenceLoVal = 0;
 private double confidenceHiVal = 0;
    // perform trials independent experiments on an n-by-n grid
 public PercolationStats(int n, int trials){
  // The constructor should throw a java.lang.IllegalArgumentException if either n ≤ 0 or trials ≤ 0.
  if((n <= 0) || (trials <= 0))
   throw new IllegalArgumentException();
  fraction = new double[trials];
  t = trials;
  boolean gridIsPecolation = false;
  int iter = 0;
  int randomSite = 0;
  // do trials time experiment
  for (int i = 0; i < trials; i++) {
   // Initialize all sites to be blocked
   Percolation pec = new Percolation(n);
   gridIsPecolation = false;
   while(gridIsPecolation != true){
    // Choose a site uniformly at random among all blocked sites.
    randomSite = StdRandom.uniform(n*n);
    int col =  randomSite % n;
    int row =  (randomSite - col)/n;
    pec.open(row, col);
    if(pec.percolates() == true)
    {
     fraction[i] = (double)pec.numberOfOpenSites()/((double)(n*n));
//     System.out.println(fraction[i]);
     gridIsPecolation = true;
    }
   } 
  }

 }
 // sample mean of percolation threshold
 public double mean(){
  for (int i = 0; i < t; i++) {
   meanVal += fraction[i];
  }

  // meanVal = StdStats.mean(fraction);
  meanVal = meanVal / t;
  return meanVal;

 }
 // sample standard deviation of percolation threshold
 public double stddev(){
  // for (int i = 0; i < t; i++) {
  //  stddevVal += (fraction[i] - meanVal)*(fraction[i] - meanVal);
  // }

  // stddevVal = stddevVal /(t - 1);
  stddevVal = StdStats.stddev(fraction);
  return stddevVal;
 }
 
 // low  endpoint of 95% confidence interval
 public double confidenceLo(){
  confidenceLoVal = meanVal - 1.96*stddevVal/Math.sqrt(t);
  return confidenceLoVal;
 }
 
 // high endpoint of 95% confidence interval
 public double confidenceHi(){
  confidenceHiVal = meanVal + 1.96*stddevVal/Math.sqrt(t);
  return confidenceHiVal;
 }

 public static void main(String[] args){
  PercolationStats pecostats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
  System.out.println("mean = " + pecostats.mean());
  System.out.println("stddev = " + pecostats.stddev());
 }
}