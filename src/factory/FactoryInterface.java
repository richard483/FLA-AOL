package factory;

import models.User;

public interface FactoryInterface {
    public void createShortLink(User user, String longUrl, String shortUrl);
}
