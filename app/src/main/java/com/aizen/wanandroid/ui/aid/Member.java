package com.aizen.wanandroid.ui.aid;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ld on 2019/1/4.
 *
 * @author ld
 * @date 2019/1/4
 * 描    述：
 */
public class Member implements Serializable {
//    @SerializedName("Python") private ArrayList<String> python_1 = new ArrayList<>();
//    @SerializedName("Android") private ArrayList<String> android = new ArrayList<>();
//    @SerializedName("Speak") private ArrayList<String> speak = new ArrayList<>();
//    @SerializedName("Python2") private ArrayList<String> python_2 = new ArrayList<>();
    @SerializedName("Guy") private ArrayList<String> guy = new ArrayList<>();

//    public ArrayList<String> getPython_1() {
//        return python_1;
//    }
//
//    public void setPython_1(ArrayList<String> python_1) {
//        this.python_1 = python_1;
//    }
//
//    public ArrayList<String> getAndroid() {
//        return android;
//    }
//
//    public void setAndroid(ArrayList<String> android) {
//        this.android = android;
//    }
//
//    public ArrayList<String> getSpeak() {
//        return speak;
//    }
//
//    public void setSpeak(ArrayList<String> speak) {
//        this.speak = speak;
//    }
//
//    public ArrayList<String> getPython_2() {
//        return python_2;
//    }
//
//    public void setPython_2(ArrayList<String> python_2) {
//        this.python_2 = python_2;
//    }

    public ArrayList<String> getGuy() {
        return guy;
    }

    public void setGuy(ArrayList<String> guy) {
        this.guy = guy;
    }
}
