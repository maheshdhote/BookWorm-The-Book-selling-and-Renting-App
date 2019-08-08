package com.example.mahesh.BookRent;

public class ReturnRecord {
    private String Id, Title, Author, Category, Price, Contact, URLofImage;

    public ReturnRecord() {

    }

    public ReturnRecord(String Id, String Title, String Author, String Category, String Price, String Contact, String URLofImage) {
        this.Id = Id;
        this.Title = Title;
        this.Author = Author;
        this.Category = Category;
        this.Price = Price;
        this.Contact = Contact;
        this.URLofImage = URLofImage;

    }

    public String getId() {
        return Id;
    }

    public void setId(String ID) {
        this.Id = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        this.Price = Price;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String Contact) {
        this.Contact = Contact;
    }

    public String getUrlofimage() {
        return URLofImage;
    }

    public void setUrlofimage(String URLofImage) {
        URLofImage = URLofImage;
    }
}
