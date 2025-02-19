package com.saloon.service;

import java.util.List;

import com.saloon.exception.UserException;
import com.saloon.modal.User;

public interface userService {
    User createUser(User user);
    User getUserById(Long id) throws UserException;
    List<User> getAllUser();
    void deleteUser(Long id) throws UserException;
    User updateUser(Long id, User user) throws UserException;
}
