package com.example.listviewdemo;

/**
 * Created by xuzj on 2016/3/24.
 */
public class Fruit {
    private String name;
    private int imageId;

    public Fruit( String name,int imageId) {
        this.imageId = imageId;
        this.name = name;
    }

    public Fruit() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {

        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
