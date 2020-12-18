// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.red_black_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class RedBlackTree<K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        boolean isRed;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.isRed = true;
            this.left = null;
            this.right = null;
            return;
        }

        @Override
        public String toString() {
            return String.format("{%s: %s(%c)}", this.key, this.value, this.isRed ? 'r' : 'b');
        }
    }

    Node root;
    int size;

    public RedBlackTree() {
        this.root = null;
        this.size = 0;
        return;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void put(K key, V value) {
        this.root = this._put(this.root, key, value);
        this.root.isRed = false;
        return;
    }

    private Node _put(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = this._put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = this._put(node.right, key, value);
        } else {
            node.value = value;
        }
        return this.ensureBalance(node);
    }

    private Node ensureBalance(Node node) {
        if (this.isRed(node.right) && !this.isRed(node.left)) {
            node = this.leftRotate(node);
        }
        if (this.isRed(node.left) && this.isRed(node.left.left)) {
            node = this.rightRotate(node);
        }
        if (this.isRed(node.left) && this.isRed(node.right)) {
            node = this.flipColors(node);
        }
        return node;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.isRed;
    }

    private Node leftRotate(Node node) {
        Node ret = node.right;
        node.right = ret.left;
        ret.left = node;
        ret.isRed = node.isRed;
        node.isRed = true;
        return ret;
    }

    private Node rightRotate(Node node) {
        Node ret = node.left;
        node.left = ret.right;
        ret.right = node;
        ret.isRed = node.isRed;
        node.isRed = true;
        return ret;
    }

    private Node flipColors(Node node) {
        node.isRed = true;
        node.left.isRed = false;
        node.right.isRed = false;
        return node;
    }

    public V get(K key) {
        Node node = this.getNode(this.root, key);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return this.getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return this.getNode(node.right, key);
        } else {
            return node;
        }
    }

    public boolean contains(K key) {
        return this.getNode(this.root, key) != null;
    }

    public boolean set(K key, V value) {
        Node node = this.getNode(this.root, key);
        if (node == null) {
            return false;
        }
        node.value = value;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("RedBlackTree (size=%d): ", this.size));
        sb.append(Arrays.toString(this.inOrder().toArray()));
        return sb.toString();
    }

    private List<Node> inOrder() {
        List<Node> list = new ArrayList<>();
        Stack<Node> stk = new Stack<>();
        Node node = this.root;
        for (; node != null || !stk.isEmpty();) {
            if (node != null) {
                stk.push(node);
                node = node.left;
            } else {
                node = stk.pop();
                list.add(node);
                node = node.right;
            }
        }
        return list;
    }

    @SuppressWarnings({ "unused" })
    private boolean verify() {
        List<Node> list = this.inOrder();
        Node node = null;
        for (int i = 0; i < list.size() - 1; ++i) {
            node = list.get(i);
            if (node.key.compareTo(list.get(i + 1).key) >= 0) {
                return false;
            }
            if ((this.isRed(node) && this.isRed(node.left)) || this.isRed(node.right)) {
                return false;
            }
        }
        return true;
    }

}