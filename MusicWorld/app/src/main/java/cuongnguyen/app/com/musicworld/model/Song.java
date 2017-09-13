package cuongnguyen.app.com.musicworld.model;

import java.io.Serializable;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class Song implements Serializable {
    private String key;
    private String title;
    private String path;
    private String album;
    private String artist;

    public Song(String key, String title, String path) {
        this.key = key;
        this.title = title;
        this.path = path;
    }

    public Song(String title, String album, String artist, String path) {
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
