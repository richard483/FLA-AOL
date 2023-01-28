package service;

import models.User;
import models.interfaces.IAuth;
import repository.DBConnection;

public class Auth implements IAuth {

    DBConnection dbConnection;

    public Auth(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public User login(String username, String password) {
        return dbConnection.login(username, password);
    }

    @Override
    public boolean register(User user) {
        return dbConnection.register(user);
    }

}
