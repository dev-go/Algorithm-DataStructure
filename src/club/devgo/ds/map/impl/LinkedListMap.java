// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.map.impl;

import club.devgo.ds.map.Map;

public class LinkedListMap<K, V> implements Map<K, V> {
    private class Node {
        K key;
        V value;
        Node next;

        Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
            return;
        }

        @Override
        public String toString() {
            return String.format("%s: %s", this.key, this.value);
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Node(null, null, null);
        this.dummyHead.next = this.dummyHead;
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

    private Node findPrev(K key) {
        Node prevNode = this.dummyHead;
        for (; prevNode.next != this.dummyHead;) {
            if (key.equals(prevNode.next.key)) {
                return prevNode;
            } else {
                prevNode = prevNode.next;
            }
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node prevNode = this.findPrev(key);
        if (prevNode != null) {
            prevNode.next.value = value;
        } else {
            Node node = new Node(key, value, this.dummyHead.next);
            this.dummyHead.next = node;
            this.size++;
        }
        return;
    }

    @Override
    public V remove(K key) {
        Node prevNode = this.findPrev(key);
        V value = null;
        if (prevNode != null) {
            value = prevNode.next.value;
            prevNode.next = prevNode.next.next;
            this.size--;
        }
        return value;
    }

    @Override
    public void set(K key, V value) {
        Node prevNode = this.findPrev(key);
        if (prevNode != null) {
            prevNode.next.value = value;
        }
        return;
    }

    @Override
    public V get(K key) {
        Node prevNode = this.findPrev(key);
        return prevNode == null ? null : prevNode.next.value;
    }

    @Override
    public boolean contains(K key) {
        return this.findPrev(key) != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LinkedListMap(size=%d): {", this.size));
        Node node = this.dummyHead.next;
        for (; node != this.dummyHead;) {
            sb.append(node);
            node = node.next;
            if (node != this.dummyHead) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}