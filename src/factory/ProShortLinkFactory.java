package factory;

import models.User;

public class ProShortLinkFactory implements FactoryInterface {

    public ProShortLinkFactory() {

    }

    @Override
    public void createShortLink(User user, String longUrl, String shortUrl) {
        user.createShortUrl(null, null, user);
    }

}
