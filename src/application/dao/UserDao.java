package application.dao;

import application.entity.User;

import java.util.List;

public interface UserDao {
    User createUser(User user);
    List<User> getAll();
}
