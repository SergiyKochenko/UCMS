package Interfaces;

public interface Authenticatable {
    public boolean login(String username, String password);
    public boolean logout();
}
