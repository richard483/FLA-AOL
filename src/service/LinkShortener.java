package service;

import java.util.ArrayList;

import models.ShortLink;
import models.interfaces.ILinkShortener;
import repository.DBConnection;

public class LinkShortener implements ILinkShortener {

    DBConnection dbConnection;

    public LinkShortener(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public ShortLink getLongUrl(String shortUrl) {
        return dbConnection.getLink(shortUrl);
    }

    @Override
    public boolean createShortUrl(String longUrl, String shortUrl) {
        return dbConnection.addLink(shortUrl, longUrl);

    }

    @Override
    public boolean deleteShortUrl(String shortUrl) {
        return dbConnection.removeLink(shortUrl);
    }

    @Override
    public ArrayList<ShortLink> getAllLinks() {
        return dbConnection.getAllLinks();
    }

}
