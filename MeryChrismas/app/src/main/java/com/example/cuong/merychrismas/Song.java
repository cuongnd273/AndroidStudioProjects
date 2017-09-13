package com.example.cuong.merychrismas;

/**
 * Created by Cuong on 21-12-2015.
 */
public class Song {
    private String name;
    private String link;

    public Song(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public Song() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
