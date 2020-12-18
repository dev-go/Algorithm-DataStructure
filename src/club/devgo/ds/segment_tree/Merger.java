// Copyright (c) 2020, devgo.club
// All rights reserved.

package club.devgo.ds.segment_tree;

public interface Merger<E> {
    E merge(E a, E b);
}