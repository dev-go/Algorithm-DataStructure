// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.hash_table;

import java.util.TreeMap;
import java.util.Map.Entry;

public class HashTable<K extends Comparable<K>, V> {

    private final int[] capArray = { 53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593, 49157, 98317, 196613,
            393241, 786433, 1572869, 3145739, 6291469, 12582917, 25165843, 50331653, 100663319, 201326611, 402653189,
            805306457, 1610612741 };
    private final int upperTol = 10;
    private final int lowerTol = 2;

    private TreeMap<K, V>[] table;
    private int capIndex;
    private int size;

    public HashTable() {
        this.size = 0;
        this.capIndex = 0;
        this.table = this.buildTable(this.capArray[this.capIndex]);
        return;
    }

    @SuppressWarnings({ "unchecked" })
    private TreeMap<K, V>[] buildTable(int len) {
        TreeMap<K, V>[] table = new TreeMap[len];
        for (int i = 0; i < table.length; ++i) {
            table[i] = new TreeMap<>();
        }
        return table;
    }

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void put(K key, V value) {
        int tableIndex = this.hash(key, this.table.length);
        TreeMap<K, V> treeMap = this.table[tableIndex];
        if (treeMap.containsKey(key)) {
            treeMap.put(key, value);
        } else {
            treeMap.put(key, value);
            this.size++;
            this.resize(true);
        }
        return;
    }

    private int hash(K key, int len) {
        return (key.hashCode() & 0x7fffffff) % len;
    }

    private void resize(boolean increase) {
        boolean isResize = false;
        TreeMap<K, V>[] oldTable = null;
        if (increase) {
            if (this.size >= this.upperTol * this.table.length && this.capIndex < this.capArray.length - 1) {
                isResize = true;
                oldTable = this.table;
                this.capIndex++;
                System.out.println("resize: increase ...");

            }
        } else {
            if (this.size <= this.lowerTol * this.table.length && this.capIndex > 0) {
                isResize = true;
                oldTable = this.table;
                this.capIndex--;
                System.out.println("resize: decrease ...");
            }
        }
        if (isResize) {
            TreeMap<K, V>[] newTable = this.buildTable(this.capArray[this.capIndex]);
            for (int i = 0; i < oldTable.length; ++i) {
                TreeMap<K, V> treeMap = oldTable[i];
                for (Entry<K, V> entry : treeMap.entrySet()) {
                    int index = this.hash(entry.getKey(), newTable.length);
                    newTable[index].put(entry.getKey(), entry.getValue());
                }
            }
            this.table = newTable;
        }
        return;
    }

    public V get(K key) {
        int tableIndex = this.hash(key, this.table.length);
        return this.table[tableIndex].get(key);
    }

    public boolean contains(K key) {
        int tableIndex = this.hash(key, this.table.length);
        return this.table[tableIndex].containsKey(key);
    }

    public V remove(K key) {
        int tableIndex = this.hash(key, this.table.length);
        if (!this.table[tableIndex].containsKey(key)) {
            return null;
        }
        V ret = this.table[tableIndex].remove(key);
        this.size--;
        this.resize(false);
        return ret;
    }

}