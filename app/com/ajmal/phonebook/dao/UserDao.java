package com.ajmal.phonebook.dao;

import com.ajmal.phonebook.models.User;

public interface UserDao {

    User addUser(User userToAdd);

    User updateUser(User userToUpdate);

    User deleteUser(User userToDelete);

    User searchUser(long idToSearch);
}