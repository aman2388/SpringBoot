package com.neueda.payments.service;

import com.neueda.payments.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User addUser(User user);
}
