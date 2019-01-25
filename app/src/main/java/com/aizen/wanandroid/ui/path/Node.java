package com.aizen.wanandroid.ui.path;


import androidx.annotation.NonNull;

/**
 * Created by ld on 2018/12/11.
 *
 * @author ld
 * @date 2018/12/11
 * 描    述：
 */
public class Node implements Comparable<Node>{
    Coordinate mCoordinate;
    int g;// G：是个准确的值，是起点到当前结点的代价
    int h;// H：是个估值，当前结点到目的结点的估计代价
    Node parent;

    public Node(int x ,int y){
        this.mCoordinate = new Coordinate(x,y);
    }

    public Node(Coordinate coordinate,Node parent, int g, int h) {
        mCoordinate = coordinate;
        this.parent = parent;
        this.g = g;
        this.h = h;
    }


    @Override
    public int compareTo(@NonNull Node o) {
        if (g + h > o.g + o.h) return 1;
        else if (g + h < o.g + o.h) return -1;
        return 0;
    }
}
