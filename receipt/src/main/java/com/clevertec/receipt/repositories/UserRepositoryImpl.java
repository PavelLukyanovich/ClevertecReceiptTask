package com.clevertec.receipt.repositories;

import com.clevertec.receipt.cash.proxy.DeleteEnableCache;
import com.clevertec.receipt.cash.proxy.GetEnableCache;
import com.clevertec.receipt.cash.proxy.PostEnableCache;
import com.clevertec.receipt.models.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class UserRepositoryImpl implements UserRepository {

    private Map<Integer, User> userRepo;

    @Override
    @PostEnableCache
    public User save(User user) {

        userRepo.put(user.getId(), user);
        return user;
    }

    @Override
    @DeleteEnableCache
    public User deleteById(int id) {

        return userRepo.remove(id);
    }

    @Override
    @GetEnableCache
    public User getById(int id) {

        return userRepo.get(id);
    }
}
