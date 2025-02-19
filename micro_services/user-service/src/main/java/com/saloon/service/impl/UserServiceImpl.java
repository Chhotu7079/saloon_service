package com.saloon.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.saloon.exception.UserException;
import com.saloon.modal.User;
import com.saloon.repository.UserRepository;
import com.saloon.service.userService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements userService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new UserException("user not found");
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isEmpty()) {
            throw new UserException("user not exist with this id " + id);
        }
        userRepository.deleteById(opt.get().getId());
    }

    @Override
    public User updateUser(Long id, User user) throws UserException {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isEmpty()) {
            throw new UserException("user not exist with this id " + id);
        }
        User existingUser = opt.get();
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        existingUser.setPhone(user.getPhone());
        existingUser.setUserName(user.getUserName());

        return userRepository.save(existingUser);
    }

}
