package models;

public abstract class User {
    // untuk member biasa, minimum shortlink namenya adalah 10 karakter
    // untuk member pro, minimum shortlink namenya adalah 1 karakter
    int id;
    String username;
    String password;
    String role;

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
