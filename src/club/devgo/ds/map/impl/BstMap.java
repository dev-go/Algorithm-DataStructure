// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.map.impl;

import java.util.Stack;

import club.devgo.ds.map.Map;

public class BstMap<K extends Comparable<K>, V> implements Map<K, V> {
    private class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            return;
        }

        @Override
        public String toString() {
            return String.format("%s: %s", this.key, this.value);
        }
    }

    private Node root;
    private int size;

    public BstMap() {
        this.root = null;
        this.size = 0;
        return;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void add(K key, V value) {
        this.root = this._add(this.root, key, value);
        return;
    }

    private Node _add(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value, null, null);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = this._add(node.left, key, value);
        } else {
            node.right = this._add(node.right, key, value);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = this._getNode(this.root, key);
        if (node != null) {
            this.root = this._remove(this.root, key);
        }
        return node == null ? null : node.value;
    }

    private Node _getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return this._getNode(node.left, key);
        } else {
            return this._getNode(node.right, key);
        }
    }

    private Node _remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) == 0) {
            this.size--;
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node min = this._findMin(node.right);
            min.right = this._removeMin(node.right);
            min.left = node.left;
            return min;
        } else if (key.compareTo(node.key) < 0) {
            node.left = this._remove(node.left, key);
            return node;
        } else {
            node.right = this._remove(node.right, key);
            return node;
        }
    }

    private Node _findMin(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return this._findMin(node.left);
        }
    }

    private Node _removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = this._removeMin(node.left);
        return node;
    }

    @Override
    public void set(K key, V value) {
        this.root = this._set(this.root, key, value);
        return;
    }

    private Node _set(Node node, K key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value, null, null);
        }
        if (key.compareTo(node.key) == 0) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = this._set(node.left, key, value);
        } else {
            node.right = this._set(node.right, key, value);
        }
        return node;
    }

    @Override
    public V get(K key) {
        Node node = this._getNode(this.root, key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean contains(K key) {
        return this._getNode(this.root, key) != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("BstMap(size=%d): {", this.size));
        Stack<Node> stack = new Stack<>();
        Node node = this.root;
        for (; node != null || !stack.isEmpty();) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                sb.append(String.format("%s, ", node));
                node = node.right;
            }
        }
        if (sb.charAt(sb.length() - 1) != '{') {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");

        return sb.toString();
    }
}