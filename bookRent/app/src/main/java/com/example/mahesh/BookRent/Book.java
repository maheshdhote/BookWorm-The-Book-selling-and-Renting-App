package com.example.mahesh.BookRent;

import android.net.Uri;

public class Book {
    private String Author;
    private String Category;
    private String Contact;
    private String Id;
    private String Price;
    private String Title;
    private String URLofImage;
    private Uri uri;


    public Book() {

    }

    public Book(String Author, String Category, String Contact, String Id, String Price, String Title, String URLofImage,Uri uri) {
        this.Author = Author;
        this.Category = Category;
        this.Contact = Contact;
        this.Id = Id;
        this.Price = Price;
        this.Title = Title;
        this.URLofImage =URLofImage;
        this.uri = uri;
    }



    public String getAuthor() {
        return Author;

    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setAuthor(String Author) {
        Author = Author;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        Category = Category;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        Contact = Contact;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        Id = Id;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String Price) {
        Price = Price;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = Price;
    }

    public String getURLofImage() {
        return URLofImage;
    }

    public void setURLofImage(String URLofImage) {
        this.URLofImage = URLofImage;
    }
}
