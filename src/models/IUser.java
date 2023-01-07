package models;

public interface IUser {
    public String getLongUrl(String shortUrl);

    public String createShortUrl(String longUrl, String shortUrl, User user);
}
