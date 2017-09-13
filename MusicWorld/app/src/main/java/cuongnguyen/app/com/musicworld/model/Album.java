package cuongnguyen.app.com.musicworld.model;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class Album {
    private String key;
    private String title;
    private String path;

    public Album(String key, String title, String path) {
        this.key = key;
        this.title = title;
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
}
