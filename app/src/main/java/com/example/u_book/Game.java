package com.example.u_book;

public class Game {
    private String name;
    private int imageSource;
    private String description;
    private int availability;
    private String categories;

    public Game (int imageSource, String name, String description, int availability, String categories) {
        this.name = name;
        this.imageSource = imageSource;
        this.description= description;
        this.availability= availability;
        this.categories = categories;
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
    public void setAvailability(int availability){
        this.availability = availability;
    }
    public void setCategories(String categories){
        this.categories = categories;
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
    public int getAvailability(){return availability;}
    public String getCategories(){return categories;}
}