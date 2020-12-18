// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.linked_list;

public class LinkedList<E> {
    private class Node {
        E elem;
        Node prev;
        Node next;

        Node(E elem, Node prev, Node next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("%s", this.elem);
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        this.dummyHead = new Node(null, null, null);
        this.dummyHead.prev = this.dummyHead;
        this.dummyHead.next = this.dummyHead;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void checkIndex(boolean allowEnd, int index) {
        if (allowEnd) {
            if (index < 0 || index > this.size) {
                throw new IllegalArgumentException("index is invalid");
            }
        } else {
            if (index < 0 || index >= this.size) {
                throw new IllegalArgumentException("index is invalid");
            }
        }
        return;
    }

    private Node getNode(int index) {
        this.checkIndex(true, index);
        Node node = this.dummyHead;
        if (index < this.size >> 1) {
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
        } else {
            for (int i = this.size; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    public void add(int index, E elem) {
        Node prevNode = this.getNode(index).prev;
        Node newNode = new Node(elem, prevNode, prevNode.next);
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
        this.size++;
        return;
    }

    public void addFirst(E elem) {
        this.add(0, elem);
        return;
    }

    public void addLast(E elem) {
        this.add(this.size, elem);
        return;
    }

    public E get(int index) {
        this.checkIndex(false, index);
        return this.getNode(index).elem;
    }

    public E getFirst() {
        return this.get(0);
    }

    public E getLast() {
        return this.get(this.size - 1);
    }

    public int find(E elem) {
        Node node = this.dummyHead;
        for (int i = 0; i < this.size; i++) {
            node = node.next;
            if (elem.equals(node.elem)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E elem) {
        return this.find(elem) != -1;
    }

    public E set(int index, E elem) {
        this.checkIndex(false, index);
        Node node = this.getNode(index);
        E ret = node.elem;
        node.elem = elem;
        return ret;
    }

    public E remove(int index) {
        this.checkIndex(false, index);
        Node prevNode = this.getNode(index).prev;
        E ret = prevNode.next.elem;
        prevNode.next.next.prev = prevNode;
        prevNode.next = prevNode.next.next;
        this.size--;
        return ret;
    }

    public E removeFirst() {
        return this.remove(0);
    }

    public E removeLast() {
        return this.remove(this.size - 1);
    }

    public void removeElements(E elem) {
        Node prevNode = this.dummyHead;
        for (; prevNode.next != this.dummyHead;) {
            if (elem.equals(prevNode.next.elem)) {
                prevNode.next = prevNode.next.next;
                prevNode.next.prev = prevNode;
                this.size--;
            } else {
                prevNode = prevNode.next;
            }
        }
        return;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LinkedList(size=%d): [", this.size));
        Node node = this.dummyHead;
        for (int i = 0; i < this.size; i++) {
            node = node.next;
            sb.append(node);
            if (i != this.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}