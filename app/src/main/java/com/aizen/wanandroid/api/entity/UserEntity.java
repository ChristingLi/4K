package com.aizen.wanandroid.api.entity;

import java.util.List;

/**
 * Created by ld on 2018/12/29.
 *
 * @author ld
 * @date 2018/12/29
 * 描    述：
 */
public class UserEntity {

    /**
     * chapterTops : []
     * collectIds : []
     * email :
     * icon :
     * id : 15408
     * password :
     * token :
     * type : 0
     * username : 15615510800
     */

    private String email;
    private String icon;
    private int id;
    private String password;
    private String token;
    private int type;
    private String username;
    private List <?> chapterTops;
    private List <Integer> collectIds;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List <?> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List <?> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List <Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List <Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
