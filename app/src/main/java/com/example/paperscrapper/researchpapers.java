package com.example.paperscrapper;

public class researchpapers {
    String Title, link;
    public researchpapers(String Title,  String link){
        this.Title = Title;
        this.link = link;
    }

    public String getTitle() {
        return Title;
    }

    public String getLink() {
        return link;
    }
}
