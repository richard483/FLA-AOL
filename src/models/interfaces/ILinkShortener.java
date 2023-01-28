package models.interfaces;

import models.User;

public interface ILinkShortener {
    public String getLongUrl(String shortUrl);

    public void createShortUrl(String longUrl, String shortUrl, User user);

    public void deleteShortUrl(String shortUrl, User user);

}
