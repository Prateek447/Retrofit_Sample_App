package com.example.jokar.retrofitsampleapp;

import com.google.gson.annotations.SerializedName;

public class Post {

    private  int userId;
    //because Integer is nullable
    private  Integer id;
    private  String title;

    //property is different from the json response then we use this annotation
    @SerializedName("body")
    private  String text;


    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
