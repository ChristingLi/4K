package com.aizen.wanandroid.ui.path;

import android.graphics.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by ld on 2018/12/11.
 *
 * @author ld
 * @date 2018/12/11
 * 描    述：
 */
public class StartPath {
    public final static int DIRECT_VALUE = 10;
    public final static int OBLIQUE_VALUE = 14;

    private Queue<Node> openList = new PriorityQueue <>();

    private List<Node> closeList = new ArrayList <>();

    /**
     * 计算H 值
     *
     * @param end
     * @param start
     * @return
     */
    private int caculateH(Coordinate end ,Coordinate start){
        return Math.abs(end.x - start.x) + Math.abs(end.y - start.y);
    }

    /**
     * 是否是最终节点
     * @param input
     * @param end
     * @return
     */
    private boolean isEndCoordine(Coordinate input,Coordinate end){
        if(input != null){
           return input.equals(end);
        }
        return false;
    }

    private Node findInOpen(Coordinate current){
        if(current == null || openList.isEmpty()){
            return null;
        }
        for (Node node : openList) {
            if(current.equals(node.mCoordinate)){
                return node;
            }
        }
        return null;
    }

    private boolean isInClose(Coordinate current){
        if(null == current || closeList.isEmpty()){
            return false;
        }
        return isCoordInClose(current.x,current.y);
    }

    private boolean isCoordInClose(int x, int y) {
        if (closeList.isEmpty()) return false;
        for (Node node : closeList) {
            if (node.mCoordinate.x == x && node.mCoordinate.y == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个位置是否可以选择
     */
    private boolean canAddNodeToOpen(MapInfo mapInfo, int x, int y) {
        //是否在地图中
        if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
        //判断是否能过
        if (mapInfo.map[y][x] == MapUtils.WALL) return false;
        //判断是否已经被 选择过的
        if (isCoordInClose(x, y)) return false;
        return true;
    }

    public void start(MapInfo info){
        if(null == info){
            return;
        }

        openList.clear();
        closeList.clear();

        openList.add(info.start);

        moveToNext(info);
    }

    private void moveToNext(MapInfo info) {
        while (!openList.isEmpty()){
            if(isInClose(info.end.mCoordinate)){
                MapUtils.path = new Path();
                if (info.end != null) {
                    MapUtils.path.moveTo(info.end.mCoordinate.x * 80 + 40, info.end.mCoordinate.y * 80 + 40);
                }
                while (info.end != null) {//把结果入栈
                    MapUtils.path.lineTo(info.end.mCoordinate.x * 80 + 40, info.end.mCoordinate.y * 80 + 40);
                    MapUtils.result.push(info.end);
                    info.end = info.end.parent;
                }
            }
            Node current = openList.poll();
            closeList.add(current);

            expose(info,current);
        }
    }

    private void expose(MapInfo info,Node current) {
        int x = current.mCoordinate.x;
        int y = current.mCoordinate.y;

        expose(info, current, x - 1, y, DIRECT_VALUE);
        expose(info, current, x, y - 1, DIRECT_VALUE);
        expose(info, current, x + 1, y, DIRECT_VALUE);
        expose(info, current, x, y + 1, DIRECT_VALUE);

        expose(info, current, x + 1, y + 1, OBLIQUE_VALUE);
        expose(info, current, x - 1, y - 1, OBLIQUE_VALUE);
        expose(info, current, x + 1, y - 1, OBLIQUE_VALUE);
        expose(info, current, x - 1, y + 1, OBLIQUE_VALUE);

    }
    private void expose(MapInfo info,Node current,int x ,int y ,int direction) {
        if(canAddNodeToOpen(info,x,y)){
            Node end = info.end;
            Coordinate coordinate = new Coordinate(x,y);
            int g = current.g + direction;
            Node child = findInOpen(coordinate);
            if(null == child){
                int h = caculateH(end.mCoordinate,coordinate);
                if(isEndCoordine(coordinate,end.mCoordinate)){
                    child = end;
                    child.parent = current;
                    child.g = g;
                    child.h = h;
                }else {
                    child = new Node(coordinate,current,g,h);
                }
                openList.add(child);
            }
        }


    }
}
