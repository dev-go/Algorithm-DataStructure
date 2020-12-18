// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.binary_search_tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {
    private class Node {
        E elem;
        Node left;
        Node right;

        Node(E elem, Node left, Node right) {
            this.elem = elem;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("%s", this.elem);
        }
    }

    private Node root;
    private int size;

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void add(E elem) {
        this.root = this._add(this.root, elem);
        return;
    }

    private Node _add(Node node, E elem) {
        if (node == null) {
            this.size++;
            return new Node(elem, null, null);
        }
        if (elem.compareTo(node.elem) < 0) {
            node.left = this._add(node.left, elem);
        } else if (elem.compareTo(node.elem) > 0) {
            node.right = this._add(node.right, elem);
        }
        return node;
    }

    public boolean contains(E elem) {
        return this._contains(this.root, elem);
    }

    private boolean _contains(Node node, E elem) {
        if (node == null) {
            return false;
        }
        if (elem.compareTo(node.elem) < 0) {
            return this._contains(node.left, elem);
        } else if (elem.compareTo(node.elem) > 0) {
            return this._contains(node.right, elem);
        }
        return true;
    }

    public E findMin() {
        if (this.size <= 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return this._findMin(this.root).elem;
    }

    private Node _findMin(Node node) {
        if (node.left == null) {
            return node;
        }
        return this._findMin(node.left);
    }

    public void removeMin() {
        if (this.size <= 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        this.root = this._removeMin(this.root);
        return;
    }

    private Node _removeMin(Node node) {
        if (node.left == null) {
            this.size--;
            return node.right;
        }
        node.left = this._removeMin(node.left);
        return node;
    }

    public void remove(E elem) {
        this.root = this._remove(this.root, elem);
        return;
    }

    private Node _remove(Node node, E elem) {
        if (node == null) {
            return null;
        }
        if (elem.compareTo(node.elem) < 0) {
            node.left = this._remove(node.left, elem);
            return node;
        } else if (elem.compareTo(node.elem) > 0) {
            node.right = this._remove(node.right, elem);
            return node;
        }
        if (node.left == null) {
            this.size--;
            return node.right;
        }
        if (node.right == null) {
            this.size--;
            return node.left;
        }
        Node min = this._findMin(node.right);
        min.right = this._removeMin(node.right);
        min.left = node.left;
        return min;
    }

    public List<E> preIter() {
        List<E> list = new ArrayList<>();
        this._iter(1, this.root, list);
        return list;
    }

    public List<E> inIter() {
        List<E> list = new ArrayList<>();
        this._iter(2, this.root, list);
        return list;
    }

    public List<E> postIter() {
        List<E> list = new ArrayList<>();
        this._iter(3, this.root, list);
        return list;
    }

    private void _iter(int order, Node node, List<E> list) {
        if (node == null) {
            return;
        }
        if (order == 1) {
            list.add(node.elem);
        }
        this._iter(order, node.left, list);
        if (order == 2) {
            list.add(node.elem);
        }
        this._iter(order, node.right, list);
        if (order == 3) {
            list.add(node.elem);
        }
        return;
    }

    public List<E> levelIter() {
        List<E> list = new ArrayList<>();
        if (this.root == null) {
            return list;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        Node node = null;
        for (; !queue.isEmpty();) {
            node = queue.remove();
            list.add(node.elem);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return list;
    }

    public List<E> preIterNR() {
        List<E> list = new ArrayList<>();
        if (this.root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(this.root);
        Node node = null;
        for (; !stack.isEmpty();) {
            node = stack.pop();
            list.add(node.elem);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    public List<E> inIterNR() {
        List<E> list = new ArrayList<>();
        if (this.root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        Node node = this.root;
        for (; node != null || !stack.isEmpty();) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.elem);
                node = node.right;
            }
        }
        return list;
    }

    public List<E> postIterNR() {
        List<E> list = new ArrayList<>();
        if (this.root == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        Node node = this.root;
        Node visited = null;
        for (; node != null || !stack.isEmpty();) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.peek();
                if (node.right != null && node.right != visited) {
                    node = node.right;
                    visited = null;
                } else {
                    list.add(node.elem);
                    visited = node;
                    node = null;
                    stack.pop();
                }
            }
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("BST(size=%d): \n", this.size));
        sb.append(String.format("    preIter: %s\n", this.preIter()));
        sb.append(String.format("    inIter: %s\n", this.inIter()));
        sb.append(String.format("    postIter: %s\n", this.postIter()));
        return sb.toString();
    }
}