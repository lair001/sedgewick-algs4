import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
/*
 * We will solve the backwash problem within memory requirements by using an
 * array of bytes. The first three bits of each byte will be used to encode
 * three booleans describing the state of the corresponding site:
 *
 * The first bit will indicate whether the site is open.
 *
 * If a tree of open sites is rooted at the site, the second bit
 * will indicate if the tree is connected to the top row.
 *
 * If a tree of open sites is rooted at the site, the third bit
 * will indicate if the tree is connected to the bottom row.
 */
public class Percolation {

    private static final byte OPEN_TRUE = 1;
    private static final byte TOP_TRUE = 2;
    private static final byte FULL_TRUE = 3;
    private static final byte BOTTOM_TRUE = 4;
    private static final byte PERC_TRUE = 7;

    private WeightedQuickUnionUF uf;
    private byte[] siteData;
    private int n;
    private int numOpenSites;
    private boolean percolates;


    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be at least 1!");
        }

        this.n = n;
        int numSites = n * n;

        this.siteData = new byte[numSites];
        this.uf = new WeightedQuickUnionUF(numSites);

        for (int i = 0; i < n; i++) {
            this.siteData[i] |= TOP_TRUE;
        }

        for (int i = numSites - n; i < numSites; i++) {
            this.siteData[i] |= BOTTOM_TRUE;
        }
    }

    private int unionWithOpenAdjacentSite(int i, int root, int adjRow, int adjCol) {
        int adj = this.coordsToIndex(adjRow, adjCol);
        if (!this.isOpen(adj)) {
            return root;
        }
        int adjRoot = uf.find(adj);
        if (root == adjRoot) {
            return root;
        }
        
        uf.union(i, adj);

        /* My best shot at passing the nondeterministic
         * find() tests.
         */
        int newRoot = uf.find(i);
        this.siteData[newRoot] |= this.siteData[adjRoot];
        this.siteData[newRoot] |= this.siteData[adj];
        this.siteData[newRoot] |= this.siteData[root];
        this.siteData[root] = this.siteData[newRoot];
        this.siteData[adj] = this.siteData[newRoot];
        this.siteData[adjRoot] = this.siteData[newRoot];

        return newRoot;
    }

    private void throwExceptionIfNotInRange(int row, int col) {
        if (row < 1
                || col < 1
                || row > this.n
                || col > this.n) {
            throw new IllegalArgumentException(
                    "rows and cols must be in [1, n]"
            );
        }
    }

    public void open(int row, int col) {
        throwExceptionIfNotInRange(row, col);
        int i = this.coordsToIndex(row, col);
        if (!this.isOpen(i)) {
            this.siteData[i] |= OPEN_TRUE;
            int root = i;

            if (col > 1) {
                root = this.unionWithOpenAdjacentSite(i, root, row, col - 1);
            }

            if (col < this.n) {
                root = this.unionWithOpenAdjacentSite(i, root, row, col + 1);
            }

            if (row > 1) {
                root = this.unionWithOpenAdjacentSite(i, root, row - 1, col);
            }

            if (row < this.n) {
                root = this.unionWithOpenAdjacentSite(i, root, row + 1, col);
            }

            if (!this.percolates()
                    && (this.siteData[root] & PERC_TRUE) == PERC_TRUE) {
                this.percolates = true;
            }

            this.numOpenSites++;
        }
    }

    public boolean isOpen(int row, int col) {
        throwExceptionIfNotInRange(row, col);
        return this.isOpen(this.coordsToIndex(row, col));
    }

    private boolean isOpen(int i) {
        return (this.siteData[i] & OPEN_TRUE) == OPEN_TRUE;
    }

    public boolean isFull(int row, int col) {
        throwExceptionIfNotInRange(row, col);
        return this.isFull(this.coordsToIndex(row, col));
    }

    private boolean isFull(int i) {
        return (this.siteData[uf.find(i)] & FULL_TRUE) == FULL_TRUE;
    }

    public int numberOfOpenSites() {
        return this.numOpenSites;
    }

    public boolean percolates() {
        return this.percolates;
    }

    private int coordsToIndex(int r, int c) {
        return this.n * (r - 1) + c - 1;
    }

    public static void main(String[] args) {
    }

}
