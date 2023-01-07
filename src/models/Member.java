package models;

public class Member extends User {

    public Member(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = "member";
    }

    @Override
    public String getLongUrl(String shortUrl) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String createShortUrl(String longUrl, String shortUrl, User user) {
        // TODO Auto-generated method stub
        return null;
    }

}
