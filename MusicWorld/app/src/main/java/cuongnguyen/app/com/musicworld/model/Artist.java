package cuongnguyen.app.com.musicworld.model;

/**
 * Created by CuongNguyen on 4/6/2017.
 */

public class Artist {
    private String key;
    private String title;

    public Artist(String key, String title) {
        this.key = key;
        this.title = title;
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
}
