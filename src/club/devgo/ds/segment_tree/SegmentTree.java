// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.segment_tree;

public class SegmentTree<E> {
    private E[] array;
    private E[] tree;
    private Merger<E> merger;

    @SuppressWarnings({ "unchecked" })
    public SegmentTree(E[] array, Merger<E> merger) {
        this.array = (E[]) new Object[array.length];
        for (int i = 0; i < array.length; ++i) {
            this.array[i] = array[i];
        }
        this.tree = (E[]) new Object[4 * array.length];
        this.merger = merger;
        this.buildTree(0, 0, this.array.length - 1);
        return;
    }

    private void buildTree(int treeIndex, int leftEdge, int rightEdge) {
        if (leftEdge == rightEdge) {
            this.tree[treeIndex] = this.array[leftEdge];
            return;
        }
        int leftChildIndex = this.getLeftChildIndex(treeIndex);
        int rightChildIndex = this.getRightChildIndex(treeIndex);
        int middleEdge = leftEdge + (rightEdge - leftEdge) / 2;
        this.buildTree(leftChildIndex, leftEdge, middleEdge);
        this.buildTree(rightChildIndex, middleEdge + 1, rightEdge);
        this.tree[treeIndex] = this.merger.merge(this.tree[leftChildIndex], this.tree[rightChildIndex]);
        return;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public E query(int left, int right) {
        if (left < 0 || right < 0 || left >= this.array.length || right >= this.array.length || left > right) {
            throw new IllegalArgumentException("left or right is invalid");
        }
        return this._query(0, 0, this.array.length - 1, left, right);
    }

    private E _query(int treeIndex, int leftEdge, int rightEdge, int left, int right) {
        if (left == leftEdge && right == rightEdge) {
            return this.tree[treeIndex];
        }
        int leftChildIndex = this.getLeftChildIndex(treeIndex);
        int rightChildIndex = this.getRightChildIndex(treeIndex);
        int middleEdge = leftEdge + (rightEdge - leftEdge) / 2;
        if (left > middleEdge) {
            return this._query(rightChildIndex, middleEdge + 1, rightEdge, left, right);
        } else if (right <= middleEdge) {
            return this._query(leftChildIndex, leftEdge, middleEdge, left, right);
        } else {
            E leftResult = this._query(leftChildIndex, leftEdge, middleEdge, left, middleEdge);
            E rightResult = this._query(rightChildIndex, middleEdge + 1, rightEdge, middleEdge + 1, right);
            return this.merger.merge(leftResult, rightResult);
        }
    }

    public void set(int index, E value) {
        if (index < 0 || index >= this.array.length) {
            throw new IllegalArgumentException("index is invalid");
        }
        this._set(0, 0, this.array.length - 1, index, value);
        return;
    }

    private void _set(int treeIndex, int leftEdge, int rightEdge, int index, E value) {
        if (leftEdge == rightEdge) {
            this.tree[treeIndex] = value;
            this.array[index] = value;
            return;
        }
        int leftChildIndex = this.getLeftChildIndex(treeIndex);
        int rightChildIndex = this.getRightChildIndex(treeIndex);
        int middleEdge = leftEdge + (rightEdge - leftEdge) / 2;
        if (index <= middleEdge) {
            this._set(leftChildIndex, leftEdge, middleEdge, index, value);
        } else {
            this._set(rightChildIndex, middleEdge + 1, rightEdge, index, value);
        }
        this.tree[treeIndex] = this.merger.merge(this.tree[leftChildIndex], this.tree[rightChildIndex]);
        return;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SegmentTree: \n\tarray: [");
        for (int i = 0; i < this.array.length; ++i) {
            sb.append(this.array[i]);
            if (i != this.array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\n\ttree: [");
        for (int i = 0; i < this.tree.length; ++i) {
            sb.append(this.tree[i]);
            if (i != this.tree.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]\n");
        return sb.toString();
    }
}