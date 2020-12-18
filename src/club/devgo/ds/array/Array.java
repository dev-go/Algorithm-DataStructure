// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.array;

public class Array<E> {
    private int size;
    private E[] data;

    @SuppressWarnings({ "unchecked" })
    public Array(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity is invalid");
        }
        this.data = (E[]) new Object[capacity];
        this.size = 0;
        return;
    }

    public Array() {
        this(1 << 4);
        return;
    }

    public int getSize() {
        return this.size;
    }

    public int getCapacity() {
        return this.data.length;
    }

    public boolean isEmpty() {
        return this.getSize() == 0;
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

    @SuppressWarnings({ "unchecked" })
    private void resize(int newCapacity) {
        if (newCapacity < this.size) {
            throw new IllegalArgumentException("newCapacity is invalid");
        }
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
        return;
    }

    public void add(int index, E e) {
        this.checkIndex(true, index);
        if (this.size == this.data.length) {
            this.resize(this.data.length * 2);
        }
        for (int i = this.size - 1; i >= index; i--) {
            this.data[i + 1] = this.data[i];
        }
        this.data[index] = e;
        this.size++;
        return;
    }

    public void addFirst(E e) {
        this.add(0, e);
        return;
    }

    public void addLast(E e) {
        this.add(this.size, e);
        return;
    }

    public E get(int index) {
        this.checkIndex(false, index);
        return this.data[index];
    }

    public void set(int index, E e) {
        this.checkIndex(false, index);
        this.data[index] = e;
        return;
    }

    public int find(E e) {
        for (int i = 0; i < this.size; i++) {
            if (e.equals(this.data[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(E e) {
        return this.find(e) != -1;
    }

    public E remove(int index) {
        this.checkIndex(false, index);
        E ret = this.data[index];
        for (int i = index + 1; i < this.size; i++) {
            this.data[i - 1] = this.data[i];
        }
        this.data[this.size - 1] = null;
        this.size--;
        if (this.size == this.data.length / 4) {
            this.resize(this.data.length / 2);
        }
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(this.size - 1);
    }

    public void removeElement(E e) {
        int index = this.find(e);
        if (index != -1) {
            remove(index);
        }
        return;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array(size:%d,capacity:%d): [", this.size, this.data.length));
        for (int i = 0; i < this.size; i++) {
            sb.append(this.data[i]);
            if (i != this.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}