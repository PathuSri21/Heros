package com.example.heros;

public class ExampleItem {

    private String mImageUrl;
    private String mCreator;
    public ExampleItem(String imageUrl, String creator) {
        mImageUrl = imageUrl;
        mCreator = creator;
    }
    public String getImageUrl() {
        return mImageUrl;
    }
    public String getCreator() {
        return mCreator;
    }
}
