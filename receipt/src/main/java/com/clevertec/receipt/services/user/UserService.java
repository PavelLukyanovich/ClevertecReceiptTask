package com.clevertec.receipt.services.user;

import com.clevertec.receipt.models.entities.User;

public interface UserService {
    User addUser(User user);

    void delete(int id);

    User getById(int id);

    User editUser(User user);
}
