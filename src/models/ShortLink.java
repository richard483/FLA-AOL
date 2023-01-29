package models;

public class ShortLink {
    int id;
    String link_name;
    String long_link;

    public ShortLink(int id, String link_name, String long_link) {
        this.id = id;
        this.link_name = link_name;
        this.long_link = long_link;
    }

    public int getId() {
        return this.id;
    }

    public String getLink_name() {
        return this.link_name;
    }

    public String getLong_link() {
        return this.long_link;
    }
}
