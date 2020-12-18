// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.union_find_set.impl;

import java.util.Arrays;

import club.devgo.ds.union_find_set.UnionFindSet;

public class UFSQuickFind implements UnionFindSet {
    private int[] setIdArray;

    public UFSQuickFind(int size) {
        this.setIdArray = new int[size];
        for (int i = 0; i < size; ++i) {
            this.setIdArray[i] = i;
        }
        return;
    }

    @Override
    public int getSize() {
        return this.setIdArray.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return this.getSetId(p) == this.getSetId(q);
    }

    private int getSetId(int index) {
        if (index < 0 || index >= this.setIdArray.length) {
            throw new IllegalArgumentException("index is invalid");
        }
        return this.setIdArray[index];
    }

    @Override
    public void unionElements(int p, int q) {
        int pSetId = this.getSetId(p);
        int qSetId = this.getSetId(q);
        if (pSetId == qSetId) {
            return;
        }
        for (int i = 0; i < this.setIdArray.length; ++i) {
            if (this.setIdArray[i] == pSetId) {
                this.setIdArray[i] = qSetId;
            }
        }
        return;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("UFSQuickFind: (size=%d) ", this.setIdArray.length));
        sb.append(Arrays.toString(this.setIdArray));
        return sb.toString();
    }
}