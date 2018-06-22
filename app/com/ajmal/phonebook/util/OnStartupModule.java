package com.ajmal.phonebook.util;

import com.ajmal.phonebook.dao.UserDao;
import com.ajmal.phonebook.dao.impl.UserDaoImpl;
import com.ajmal.phonebook.services.UserService;
import com.ajmal.phonebook.services.impl.UserServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class OnStartupModule extends AbstractModule {


    @Override
    protected void configure() {

        //Bind dao
        bind(UserDao.class).annotatedWith(Names.named("userDao")).to(UserDaoImpl.class);

        //bind Service
        bind(UserService.class).to(UserServiceImpl.class);
    }
}