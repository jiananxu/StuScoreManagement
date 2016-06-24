package com.java.user;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joe on 2016/5/31.
 */
public class User {

    private String name;
    private int id;
    private int score1;
    private int score2;

    public User() {
    }

    public User(int id, String name, int score1, int score2) {
        this.id = id;
        this.name = name;
        this.score1 = score1;
        this.score2 = score2;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getScore2() {
        return score2;
    }

    @Override
    public String toString() {
        return "User[" + "id=" + id + "name=" + name + "score1=" + score1 + "score2=" + score2 + "]";
    }
}
