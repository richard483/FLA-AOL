package proxy;

import java.util.HashMap;

import models.User;
import models.interfaces.IAuth;
import repository.DBConnection;
import service.Auth;

public class AuthProxy implements IAuth {

    Auth auth;
    HashMap<String, User> users = new HashMap<String, User>();

    public AuthProxy(DBConnection dbConnection) {
        auth = new Auth(dbConnection);
    }

    // cache proxy, akan mengecek apakah user sudah pernah login sebelumnya dan
    // datanya masih tersimpan di hashmap
    @Override
    public User login(String username, String password) {
        if (users.containsKey(username)) {
            return users.get(username);
        } else {
            User user = auth.login(username, password);
            if (user != null) {
                users.put(username, user);
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        return auth.register(user);
    }

}
