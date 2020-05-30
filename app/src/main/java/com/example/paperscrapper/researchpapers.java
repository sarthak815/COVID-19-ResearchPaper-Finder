package com.example.paperscrapper;

public class researchpapers {
    String title, link;
    public researchpapers(String title,  String link){
        this.title = title;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }
}
