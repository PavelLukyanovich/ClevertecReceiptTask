package com.clevertec.receipt.repositories;

import com.clevertec.receipt.models.entities.User;

public interface UserRepository {

    User save(User user);

    void deleteById(int id);

    User getById(int id);

}
