package models;

public class ProMember extends User {

    public ProMember(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "pro-member";
    }

}
