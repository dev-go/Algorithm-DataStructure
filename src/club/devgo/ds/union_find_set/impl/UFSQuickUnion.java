// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.union_find_set.impl;

import java.util.Arrays;

import club.devgo.ds.union_find_set.UnionFindSet;

public class UFSQuickUnion implements UnionFindSet {
    private int[] parentSetIdArray;

    public UFSQuickUnion(int size) {
        this.parentSetIdArray = new int[size];
        for (int i = 0; i < size; ++i) {
            this.parentSetIdArray[i] = i;
        }
        return;
    }

    @Override
    public int getSize() {
        return this.parentSetIdArray.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return this.getSetId(p) == this.getSetId(q);
    }

    private int getSetId(int index) {
        if (index < 0 || index >= this.parentSetIdArray.length) {
            throw new IllegalArgumentException("index is invalid");
        }
        for (; index != this.parentSetIdArray[index];) {
            index = this.parentSetIdArray[index];
        }
        return index;
    }

    @Override
    public void unionElements(int p, int q) {
        int pSetId = this.getSetId(p);
        int qSetId = this.getSetId(q);
        if (pSetId == qSetId) {
            return;
        }
        this.parentSetIdArray[pSetId] = qSetId;
        return;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("UFSQuickUnion: (size=%d) ", this.parentSetIdArray.length));
        sb.append(Arrays.toString(this.parentSetIdArray));
        return sb.toString();
    }
}