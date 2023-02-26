package com.clevertec.receipt.services.user;

import com.clevertec.receipt.models.entities.User;
import com.clevertec.receipt.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User addUser(User user) {

        if (user.getStatus().matches("\\^\\D{3}")){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void delete(int id) {

        userRepository.deleteById(id);
    }

    @Override
    public User getById(int id) {

        return userRepository.getById(id);
    }

    @Override
    public User editUser(User user) {

        return userRepository.save(user);
    }
}
