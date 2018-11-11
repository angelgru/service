package com.angel.service.service;

import com.angel.service.domain.User;
import com.angel.service.domain.Vehicle;
import com.angel.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findOne(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);
    }

    public User addUser(User userRequest) {
        return userRepository.save(userRequest);
    }

    public User addVehicle(Long userId, Vehicle vehicleRequest) {
        User user = findOne(userId);
        if(user != null) {
            user.addVehicle(vehicleRequest);
            userRepository.save(user);
            return user;
        } else
            return null;
    }
}
