package models.interfaces;

import java.util.ArrayList;

import models.ShortLink;

public interface ILinkShortener {
    public ShortLink getLongUrl(String shortUrl);

    public boolean createShortUrl(String longUrl, String shortUrl);

    public boolean deleteShortUrl(String shortUrl);

    public ArrayList<ShortLink> getAllLinks();

}
