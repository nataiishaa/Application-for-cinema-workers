package org.example.dao;
import org.example.model.User;
import java.util.ArrayList;
import java.util.List;
public interface UserDAO {
    void addUser(User user);
    List<User> getAllUsers();
}
