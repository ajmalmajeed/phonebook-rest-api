package com.ajmal.phonebook.dao.impl;

import com.ajmal.phonebook.dao.UserDao;
import com.ajmal.phonebook.models.User;
import com.avaje.ebean.Model;

public class UserDaoImpl implements UserDao {

    public static final Model.Finder<Long, User> find = new Model.Finder<>(User.class);


    @Override
    public User addUser(User userToAdd) {
        userToAdd.save();
        return userToAdd;
    }

    @Override
    public User updateUser(User userToUpdate) {
        userToUpdate.update();
        return userToUpdate;
    }

    @Override
    public User deleteUser(User userToDelete) {
        userToDelete.delete();
        return userToDelete;
    }

    @Override
    public User searchUser(long idToSearch) {
        return find.byId(idToSearch);
    }

    @Override
    public User findUserByToken(String authToken) {
        return find.where().eq("authToken", authToken).findUnique();

    }

    @Override
    public User findUserByUsername(String username) {
        return find.where().eq("username", username).findUnique();
    }

}