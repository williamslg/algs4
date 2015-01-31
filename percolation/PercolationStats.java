public class PercolationStats
{
    private Percolation percolation; // Percolation object
    //private double mean;             // mean of the percolation probability 
    //private double dev;           // sample variance deviation 
    private double[] result;         // array to save percolation probability of each experiment 
    
    /*
     * Perform T independent experiments on a N-by-N grid
     * @param N: size of the grid
     * @param T: number of experiments
     * @throws java.lang.IllegalArgumentException() if N<=0 or T<=0
     */
    public PercolationStats(int N, int T)
    {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        result = new double[T];
        for (int i = 0; i < T; i++) {
            int count = 0;
            percolation = new Percolation(N);
            while (!percolation.percolates()) {
                int x = 1 + StdRandom.uniform(N);
                int y = 1 + StdRandom.uniform(N);
                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                    count++;
                }
            }
            result[i] = count * 1.0 / (N * N);
        }
    }
    
    public double mean()
    {
        return StdStats.mean(result);
    }
    
    public double stddev()
    {
        return StdStats.stddev(result);
    }
    
    public double confidenceLo()
    {
        return mean() - 1.96 * stddev()/Math.sqrt(result.length);
    }
    
    public double confidenceHi()
    {
        return mean() + 1.96 * stddev()/Math.sqrt(result.length);
    }
    
    public static void main(String[] args)
    {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean                    = " + ps.mean());
        StdOut.println("stddev                  = " + ps.stddev());
        StdOut.println("95% confidence interval = " + ps.confidenceLo() + ", " + ps.confidenceHi());
        
    }
}