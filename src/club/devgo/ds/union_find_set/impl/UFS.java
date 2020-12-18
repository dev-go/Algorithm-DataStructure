// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.union_find_set.impl;

import java.util.Arrays;

import club.devgo.ds.union_find_set.UnionFindSet;

public class UFS implements UnionFindSet {
    private int[] ids;
    private int[] ranks;

    public UFS(int size) {
        this.ids = new int[size];
        this.ranks = new int[size];
        for (int i = 0; i < size; ++i) {
            this.ids[i] = i;
            this.ranks[i] = 1;
        }
        return;
    }

    @Override
    public int getSize() {
        return this.ids.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return this.getId(p) == this.getId(q);
    }

    private int getId(int index) {
        if (index < 0 || index >= this.ids.length) {
            throw new IllegalArgumentException("index is invalid");
        }
        for (; index != this.ids[index];) {
            this.ids[index] = this.ids[this.ids[index]];
            index = this.ids[index];
        }
        return index;
    }

    @Override
    public void unionElements(int p, int q) {
        int pId = this.getId(p);
        int qId = this.getId(q);
        if (this.ranks[pId] < this.ranks[qId]) {
            this.ids[pId] = qId;
        } else if (this.ranks[pId] > this.ranks[qId]) {
            this.ids[qId] = pId;
        } else {
            this.ids[pId] = qId;
            this.ranks[qId]++;
        }
        return;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("UFS: (size=%d) ids: %s ranks: %s", this.ids.length, Arrays.toString(this.ids),
                Arrays.toString(this.ranks)));
        return sb.toString();
    }
}