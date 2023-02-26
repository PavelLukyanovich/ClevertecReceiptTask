package com.clevertec.receipt.repositories;

import com.clevertec.receipt.models.entities.User;

public interface UserRepository {

    public User save(User user);

    public void deleteById(int id);

    public User getById(int id);

}
