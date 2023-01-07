package factory;

import models.User;

public class ShortLinkFactory implements FactoryInterface {

    public ShortLinkFactory() {

    }

    @Override
    public void createShortLink(User user, String longUrl, String shortUrl) {
        user.createShortUrl(null, null, user);
    }

}
