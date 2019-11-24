package com.example.u_book;

public class Game {
    private String name;
    private int imageSource;
    private String description;

    public Game (int imageSource, String name, String description) {
        this.name = name;
        this.imageSource = imageSource;
        this.description= description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getImageSource() {
        return imageSource;
    }
    public String getDescription(){
        return description;
    }
}