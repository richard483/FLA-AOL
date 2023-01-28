package models.interfaces;

import models.User;

public interface IAuth {
    public User login(String username, String password);

    public boolean register(User user);
}
