package com.example.paperscrapper;

public class researchpapers {
    String Title, link,authors,journal,citations,abstract1;
    public researchpapers(String Title,  String link, String authors,String journal, String citations, String abstract1){
        this.Title = Title;
        this.link = link;
        this.authors = authors;
        this.journal = journal;
        this.citations = citations;
        this.abstract1 = abstract1;
    }

    public String getTitle() {
        return Title;
    }

    public String getLink() {
        return link;
    }

    public String getAuthors() {
        return authors;
    }
    public String getJournal() {
        return journal;
    }
    public String getCitations() {
        return citations;
    }
    public String getAbstract1() {
        return abstract1;
    }


}
