package com.aizen.wanandroid.ui.path;

/**
 * Created by ld on 2018/12/11.
 *
 * @author ld
 * @date 2018/12/11
 * 描    述：
 */
public class Coordinate {
     int x ;
     int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj){
            return false;
        }

        if(obj instanceof  Coordinate){
            Coordinate coordinate = (Coordinate) obj;
            return coordinate.x == x && coordinate.y == y;
        }
        return false;
    }
}
