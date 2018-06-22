package com.ajmal.phonebook.services;

import com.ajmal.phonebook.models.User;

public interface UserService {

    User updateUser(User userToUpdate);

    User deleteUser(long idToDelete);
}