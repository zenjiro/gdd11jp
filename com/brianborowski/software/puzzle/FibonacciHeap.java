package com.brianborowski.software.puzzle;
/**
 * File: FibonacciHeap.java
 * Author: Brian Borowski
 * Date created: March 2000
 * Date last modified: December 30, 2010
 */
public final class FibonacciHeap {
    private AStarNode min;
    private int n;

    public FibonacciHeap() { }

    public AStarNode min() {
        return min;
    }

    public AStarNode insert(final AStarNode toInsert) {
        if (min != null) {
            toInsert.left = min;
            toInsert.right = min.right;
            min.right = toInsert;
            toInsert.right.left = toInsert;
            if (toInsert.cost < min.cost) {
                min = toInsert;
            }
        } else {
            min = toInsert;
        }
        ++n;
        return toInsert;
    }

    public void delete(final AStarNode node) {
        decreaseKey(node, Integer.MIN_VALUE);
        removeMin();
    }

    public boolean isEmpty() {
        return min == null;
    }

    public AStarNode removeMin() {
        final AStarNode z = min;
        if (z != null) {
            int i = z.degree;
            AStarNode x = z.child;
            while (i > 0) {
                final AStarNode nextChild = x.right;
                x.left.right = x.right;
                x.right.left = x.left;
                x.left = min;
                x.right = min.right;
                min.right = x;
                x.right.left = x;
                x.parent = null;
                x = nextChild;
                --i;
            }
            z.left.right = z.right;
            z.right.left = z.left;
            if (z == z.right) {
                min = null;
            } else {
                min = z.right;
                consolidate();
            }
            --n;
        }
        return z;
    }

    public void decreaseKey(final AStarNode x, final int c) {
        if (c > x.cost) {
            System.err.println("Error: new key is greater than current key.");
            return;
        }
        x.cost = c;
        final AStarNode y = x.parent;
        if ((y != null) && (x.cost < y.cost)) {
            cut(x, y);
            cascadingCut(y);
        }
        if (x.cost < min.cost) {
            min = x;
        }
    }

    public int size() {
        return n;
    }

    public FibonacciHeap union(final FibonacciHeap heap1, final FibonacciHeap heap2) {
        final FibonacciHeap heap = new FibonacciHeap();
        if ((heap1 != null) && (heap2 != null)) {
            heap.min = heap1.min;
            if (heap.min != null) {
                if (heap2.min != null) {
                    heap.min.right.left = heap2.min.left;
                    heap2.min.left.right = heap.min.right;
                    heap.min.right = heap2.min;
                    heap2.min.left = heap.min;
                    if (heap2.min.cost < heap1.min.cost) {
                        heap.min = heap2.min;
                    }
                }
            } else {
                heap.min = heap2.min;
            }
            heap.n = heap1.n + heap2.n;
        }
        return heap;
    }

    private void cascadingCut(final AStarNode y) {
        final AStarNode z = y.parent;
        if (z != null) {
            if (!y.mark) {
                y.mark = true;
            } else {
                cut(y, z);
                cascadingCut(z);
            }
        }
    }

    private void consolidate() {
        final int D = n + 1;
        final AStarNode A[] = new AStarNode[D];
        for (int i = 0; i < D; ++i) {
            A[i] = null;
        }
        int k = 0;
        AStarNode x = min;
        if (x != null) {
            ++k;
            for (x = x.right; x != min; x = x.right) {
                ++k;
            }
        }
        while (k > 0) {
            int d = x.degree;
            final AStarNode rightAStarNode = x.right;
            while (A[d] != null) {
                AStarNode y = A[d];
                if (x.cost > y.cost) {
                    final AStarNode temp = y;
                    y = x;
                    x = temp;
                }
                link(y, x);
                A[d] = null;
                ++d;
            }
            A[d] = x;
            x = rightAStarNode;
            --k;
        }
        min = null;
        for (int i = 0; i < D; ++i) {
            final AStarNode ai = A[i];
            if (ai != null) {
                if (min != null) {
                    ai.left.right = ai.right;
                    ai.right.left = ai.left;
                    ai.left = min;
                    ai.right = min.right;
                    min.right = ai;
                    ai.right.left = ai;
                    if (ai.cost < min.cost) {
                        min = ai;
                    }
                } else {
                    min = ai;
                }
            }
        }
    }

    private void cut(final AStarNode x, final AStarNode y) {
        x.left.right = x.right;
        x.right.left = x.left;
        y.degree--;
        if (y.child == x) {
            y.child = x.right;
        }
        if (y.degree == 0) {
            y.child = null;
        }
        x.left = min;
        x.right = min.right;
        min.right = x;
        x.right.left = x;
        x.parent = null;
        x.mark = false;
    }

    private void link(final AStarNode node1, final AStarNode node2) {
        node1.left.right = node1.right;
        node1.right.left = node1.left;
        node1.parent = node2;
        if (node2.child == null) {
            node2.child = node1;
            node1.right = node1;
            node1.left = node1;
        } else {
            node1.left = node2.child;
            node1.right = node2.child.right;
            node2.child.right = node1;
            node1.right.left = node1;
        }
        node2.degree++;
        node1.mark = false;
    }
}
