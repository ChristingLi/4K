package com.aizen.wanandroid.ui.path;

import android.util.Log;

import com.aizen.utils.LoggerUtils;

/**
 * Created by ld on 2018/12/11.
 *
 * @author ld
 * @date 2018/12/11
 * 描    述：
 */
public class MapInfo {
    public int[][] map; // 二维数组的地图
    public int width; // 地图的宽
    public int hight; // 地图的高
    public Node start; // 起始结点
    public Node end; // 最终结点

    public MapInfo(int[][] map, int width, int hight, Node start, Node end)
    {
        this.map = map;
        this.width = width;
        this.hight = hight;
        this.start = start;
        this.end = end;
        LoggerUtils.Logger("Map","初始化");
    }
}
