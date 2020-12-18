// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.union_find_set;

public interface UnionFindSet {
    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}