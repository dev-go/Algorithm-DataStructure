// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.avl_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class AvlTree<K extends Comparable<K>, V> {
    private class Node {
        K key;
        V value;
        int height;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            return;
        }

        @Override
        public String toString() {
            return String.format("%s: %s", this.key, this.value);
        }
    }

    private Node root;
    private int size;

    public AvlTree() {
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
        return this.ensureBalanced(node);
    }

    private Node ensureBalanced(Node node) {
        if (node == null) {
            return null;
        }
        node.height = Math.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
        int bf = this.getBalanceFactor(node);
        if (bf > 1 && this.getBalanceFactor(node.left) >= 0) {
            node = this.rightRotate(node);
        } else if (bf < -1 && this.getBalanceFactor(node.right) <= 0) {
            node = this.leftRotate(node);
        } else if (bf > 1 && this.getBalanceFactor(node.left) < 0) {
            node.left = this.leftRotate(node.left);
            node = this.rightRotate(node);
        } else if (bf < -1 && this.getBalanceFactor(node.right) > 0) {
            node.right = this.rightRotate(node.right);
            node = this.leftRotate(node);
        }
        return node;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private Node rightRotate(Node node) {
        Node ret = node.left;
        node.left = ret.right;
        ret.right = node;
        node.height = Math.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
        ret.height = Math.max(this.getHeight(ret.left), this.getHeight(ret.right)) + 1;
        return ret;
    }

    private Node leftRotate(Node node) {
        Node ret = node.right;
        node.right = ret.left;
        ret.left = node;
        node.height = Math.max(this.getHeight(node.left), this.getHeight(node.right)) + 1;
        ret.height = Math.max(this.getHeight(ret.left), this.getHeight(ret.right)) + 1;
        return ret;
    }

    private int getBalanceFactor(Node node) {
        return this.getHeight(node.left) - this.getHeight(node.right);
    }

    public boolean contains(K key) {
        return this._get(this.root, key) != null;
    }

    private Node _get(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return this._get(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return this._get(node.right, key);
        } else {
            return node;
        }
    }

    public V get(K key) {
        Node node = this._get(this.root, key);
        return node == null ? null : node.value;
    }

    public boolean set(K key, V value) {
        Node node = this._get(this.root, key);
        if (node == null) {
            return false;
        }
        node.value = value;
        return true;
    }

    public V remove(K key) {
        Node node = this._get(this.root, key);
        if (node != null) {
            this.root = this._remove(this.root, key);
        }
        return node == null ? null : node.value;
    }

    private Node _remove(Node node, K key) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) < 0) {
            node.left = this._remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = this._remove(node.right, key);
        } else {
            if (node.left == null) {
                this.size--;
                node = node.right;
            } else if (node.right == null) {
                this.size--;
                node = node.left;
            } else {
                Node min = this.findMin(node.right);
                node.right = this._remove(node.right, min.key);
                min.left = node.left;
                min.right = node.right;
                node = min;
            }
        }
        return this.ensureBalanced(node);
    }

    private Node findMin(Node node) {
        for (; node.left != null;) {
            node = node.left;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("AvlTree (size=%d):", this.size));
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
        for (int i = 0; i < list.size() - 1; ++i) {
            if (list.get(i).key.compareTo(list.get(i + 1).key) >= 0) {
                return false;
            }
            if (Math.abs(this.getBalanceFactor(list.get(i))) > 1) {
                return false;
            }
        }
        if (list.size() > 0 && Math.abs(this.getBalanceFactor(list.get(list.size() - 1))) > 1) {
            return false;
        }
        return true;
    }

}