package com.clevertec.receipt.repositories;

import com.clevertec.receipt.models.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<Integer, User> userRepo;

    @Override
    public User save(User user) {

        userRepo.put(user.getId(), user);
        return user;
    }

    @Override
    public void deleteById(int id) {

        userRepo.remove(id);
    }

    @Override
    public User getById(int id) {

        return userRepo.get(id);
    }
}
