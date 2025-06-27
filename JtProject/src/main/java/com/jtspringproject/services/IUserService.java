package com.jtspringproject.services;

import com.jtspringproject.models.User;
import java.util.List;

public interface IUserService {
    List<User> getUsers();
    User addUser(User user);
    User checkLogin(String username, String password);
    boolean checkUserExists(String username);
    User getUserByUsername(String username);
    User updateUser(User user);
} 