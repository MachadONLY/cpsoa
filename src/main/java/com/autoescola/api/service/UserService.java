package com.autoescola.api.service;

import com.autoescola.api.entity.User;
import java.util.List;

public interface UserService {
    User registerNewUser(User user);
    List<User> getAllUsers();
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    User findByUsername(String username);
    User changePassword(Long id, String oldPassword, String newPassword);
}

