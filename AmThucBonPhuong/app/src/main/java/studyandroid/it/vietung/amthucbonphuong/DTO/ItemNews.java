package studyandroid.it.vietung.amthucbonphuong.DTO;

/**
 * Created by VietUng on 13/01/2016.
 */
public class ItemNews {

    private String title;
    private String description;
    private String pubdate;
    private String link;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}