public class Percolation {
    
    private WeightedQuickUnionUF uf;
    private int[] status; // 000 = block, 001 = open, 010 = connected to top, 100 = connected to bottom
    private final int MASK_OPEN = 1;
    private final int MASK_TOP = 2;
    private final int MASK_BOTTOM = 4;
    private int N; // size of the grid;
    private boolean percolate = false;
    
    /*
     * Constructoer, initialize two UF
     * @param N: size of N-by-N grid
     */
    public Percolation(int N)
    {
        if (N <= 0) throw new IllegalArgumentException();
        this.N = N;
        uf = new WeightedQuickUnionUF((N+2)*(N+2));
        status = new int[(N+2)*(N+2)]; //extend the status array to (N+2)*(N+2) to avoid boundary exception
        
    }
    
    /*
     * Open a site
     * @param i: ith row
     * @param j: jth column
     * @throws java.lang.IndexOutOfBoundsExcpetion() unless both 1<=i<=N and 1<=j<=N
     */
    public void open(int i, int j)
    {
        if (i < 1 || i > N || j < 1 || j > N) throw new IndexOutOfBoundsException();
        int current = xyTo1D(i, j);
        status[current] = status[current] | MASK_OPEN;
        if (i == 1)
            status[current] = status[current] | MASK_TOP;
        if (i == N)
            status[current] = status[current] | MASK_BOTTOM;
        
        //union it with adjacent open site
        
        int roottop = 0;
        int rootbottom = 0;
        int rootleft = 0;
        int rootright = 0;
        int top = xyTo1D(i-1, j);
        int bottom = xyTo1D(i+1, j);
        int left = xyTo1D(i, j-1);
        int right = xyTo1D(i, j+1);
        if ((status[top] & MASK_OPEN) == MASK_OPEN) { //top
            roottop = status[uf.find(top)];
            uf.union(top, current);
        }
        
        if ((status[bottom] & MASK_OPEN) == MASK_OPEN) { //bottom
            rootbottom = status[uf.find(bottom)];
            uf.union(bottom, current);
            
        }
        
        if ((status[left] & MASK_OPEN) == MASK_OPEN) { //left
            rootleft = status[uf.find(left)];
            uf.union(left, current);
        }
        
        if ((status[right] & MASK_OPEN) == MASK_OPEN) { //right
            rootright = status[uf.find(right)];
            uf.union(right, current);
        }
        
        status[uf.find(current)] |= roottop | rootbottom | rootleft | rootright;
        
        if ((status[uf.find(current)] & (MASK_TOP | MASK_BOTTOM))== (MASK_TOP | MASK_BOTTOM))
            percolate = true;
        
    }
    
    /*
     * Return true if a site is open, otherwise false
     * @param i: ith row
     * @param j: jth column
     * @throws java.lang.IndexOutOfBoundsExcpetion() unless both 1 <= i <= N and 1 <= j <= N
     */
    public boolean isOpen(int i, int j)
    {
        if (i < 1 || i > N || j < 1 || j > N) throw new IndexOutOfBoundsException();
        return (status[xyTo1D(i, j)] & MASK_OPEN) == MASK_OPEN;
    }
    
    /*
     * Return true if a site is full, otherwise false
     * @param i: ith row
     * @param j: jth column
     * @throws java.lang.IndexOutOfBoundsExcpetion() unless both 1 <= i <= N and 1 <= j <= N
     */
    public boolean isFull(int i, int j)
    {
        if (i < 1 || i > N || j < 1 || j > N) throw new IndexOutOfBoundsException();
        return (status[uf.find(xyTo1D(i, j))] & MASK_TOP) == MASK_TOP;
    }
    
    public boolean percolates()
    {
        return percolate;
    }
    
    private int xyTo1D(int i, int j) {
        return i*(N+2) + j;
    }
    
    
    public static void main(String[] args)
    {
    }
    
}