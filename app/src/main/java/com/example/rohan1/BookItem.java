package com.example.rohan1;

public class BookItem {
    private String name;
    private int imageResource;

    public BookItem(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
