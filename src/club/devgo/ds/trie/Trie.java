// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.trie;

import java.util.TreeMap;

public class Trie {
    private class Node {
        boolean isWord;
        TreeMap<Character, Node> children;

        Node(boolean isWord) {
            this.isWord = isWord;
            this.children = new TreeMap<>();
            return;
        }
    }

    private Node root;
    private int size;

    public Trie() {
        this.root = new Node(false);
        this.size = 0;
        return;
    }

    public int getSize() {
        return this.size;
    }

    public void add(String word) {
        Node currNode = this.root;
        for (int i = 0; i < word.length(); ++i) {
            Character c = word.charAt(i);
            if (currNode.children.get(c) == null) {
                currNode.children.put(c, new Node(false));
            }
            currNode = currNode.children.get(c);
        }
        if (!currNode.isWord) {
            currNode.isWord = true;
            this.size++;
        }
        return;
    }

    public boolean contains(String word) {
        Node currNode = this.root;
        for (int i = 0; i < word.length(); ++i) {
            Character c = word.charAt(i);
            if (currNode.children.get(c) == null) {
                return false;
            }
            currNode = currNode.children.get(c);
        }
        return currNode.isWord;
    }

    public boolean isPrefix(String word) {
        Node currNode = this.root;
        for (int i = 0; i < word.length(); ++i) {
            Character c = word.charAt(i);
            if (currNode.children.get(c) == null) {
                return false;
            }
            currNode = currNode.children.get(c);
        }
        return true;
    }

    public void remove(String word) {
        if (word.length() == 0 || this.size == 0) {
            return;
        }
        this.root = this._remove(this.root, word, 0);
        return;
    }

    private Node _remove(Node node, String word, int index) {
        if (node == null) {
            return null;
        }
        if (index == word.length()) {
            if (node.isWord) {
                node.isWord = false;
                this.size--;
            }
        } else {
            Node childNode = this._remove(node.children.get(word.charAt(index)), word, index + 1);
            if (childNode == null) {
                node.children.remove(word.charAt(index));
            } else {
                node.children.put(word.charAt(index), childNode);
            }
        }
        if (node.children.size() == 0 && node != this.root) {
            return null;
        }
        return node;
    }
}