package com.ajmal.phonebook.services;

import com.ajmal.phonebook.dto.LoginCredentials;
import com.ajmal.phonebook.models.User;

public interface UserService {

    User updateUser(User userToUpdate);

    User deleteUser(long idToDelete);

    User login(LoginCredentials loginCredentials);
}