package org.example.dao;
import org.example.model.User;
import java.util.ArrayList;
import java.util.List;
public class UserDAOImpl implements UserDAO {
    private List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}