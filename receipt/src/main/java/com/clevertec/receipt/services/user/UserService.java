package com.clevertec.receipt.services.user;

import com.clevertec.receipt.models.entities.User;

public interface UserService {
   public User addUser(User user);

    public void delete(int id);

   public User getById(int id);

   public User editUser(User user);
}
