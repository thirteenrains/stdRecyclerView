package com.thirteenbrains.unodir.stdrecyclerview;

import android.graphics.Bitmap;

public class Book {
    private String title;
    private String author;
    private String publisher;
    private Bitmap bitmap;

    public Book(String title, String author, String publisher, Bitmap bitmap) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
