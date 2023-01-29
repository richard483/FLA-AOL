package builder;

import models.Admin;
import models.Member;
import models.ProMember;
import models.User;

public class UserBuilder {
    private User user;
    private String username;
    private String password;
    private String role;

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public User build() {
        if (this.role.equals("Admin")) {
            user = new Admin(this.username, this.password);
            return user;
        } else if (this.role.equals("Member")) {
            user = new Member(this.username, this.password);
            return user;
        } else if (this.role.equals("Pro-Member")) {
            user = new ProMember(this.username, this.password);
            return user;
        } else {
            return null;
        }
    }
}
