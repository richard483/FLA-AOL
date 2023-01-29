package proxy;

import java.util.ArrayList;
import java.util.HashMap;

import models.ShortLink;
import models.User;
import models.interfaces.ILinkShortener;
import repository.DBConnection;
import service.LinkShortener;

public class LinkShortenerProxy implements ILinkShortener {
    LinkShortener linkShortener;
    User authenticatedUser;
    HashMap<String, ShortLink> shortLinks = new HashMap<String, ShortLink>();

    public LinkShortenerProxy(DBConnection dbConnection, User authenticatedUser) {
        linkShortener = new LinkShortener(dbConnection);
        this.authenticatedUser = authenticatedUser;
    }

    public ShortLink getLongUrl(String shortUrl) {
        if (shortLinks.containsKey(shortUrl)) {
            return shortLinks.get(shortUrl);
        } else {
            ShortLink shortLink = linkShortener.getLongUrl(shortUrl);
            if (shortLink != null) {
                shortLinks.put(shortUrl, shortLink);
                return shortLink;
            }
        }
        return null;
    }

    public boolean createShortUrl(String longUrl, String shortUrl) {

        if (authenticatedUser.getRole().equals("member")) {
            if (shortUrl.length() < 5 || shortUrl.length() > 10) {
                System.err.println("Short URL must be at least 5 & at most 10 characters long for member account!");
                return false;
            }
            return linkShortener.createShortUrl(longUrl, shortUrl);
        } else if (authenticatedUser.getRole().equals("pro-member")) {
            return linkShortener.createShortUrl(longUrl, shortUrl);
        }

        System.err.println("You are not authorized to create short URL!");

        return false;
    }

    public boolean deleteShortUrl(String shortUrl) {
        if (authenticatedUser.getRole().equals("admin")) {
            return linkShortener.deleteShortUrl(shortUrl);
        }
        System.err.println("You are not authorized to delete short URL!");
        return false;
    }

    @Override
    public ArrayList<ShortLink> getAllLinks() {
        return linkShortener.getAllLinks();
    }

}
