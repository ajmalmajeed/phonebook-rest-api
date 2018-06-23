package com.ajmal.phonebook.controllers;

import com.ajmal.phonebook.dao.UserDao;
import com.ajmal.phonebook.dao.impl.UserDaoImpl;
import com.ajmal.phonebook.dto.LoginCredentials;
import com.ajmal.phonebook.models.User;
import com.ajmal.phonebook.services.UserService;
import com.ajmal.phonebook.services.impl.UserServiceImpl;
import com.ajmal.phonebook.util.JsonServiceUtil;
import com.ajmal.phonebook.util.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;

public class UserController extends Controller {



    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private UserService userService;

    /**
     * Save user to the database from the request (Use hard coded user)
     * @return user saved in the database
     */

    public Result addUser(){
        User userToSave =  new User();
        userToSave.setFirstName("Amila");
        userToSave.setLastName("Nuwan");
        userToSave.setEmail("asd@asd.com");
        userToSave.setPassword("123");

        UserDao  userDao = new UserDaoImpl();

        User addedUser = userDao.addUser(userToSave);

        return ok("User added Successfuly");
    }


    /**
     * This is to update user in database
     * @return updated user in the database
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateUser(){
        JsonNode jsonNode = request().body().asJson();

        User userToUpdate = null;

        try {
            userToUpdate = objectMapper.treeToValue(jsonNode, User.class);


            UserService userService = new UserServiceImpl();

            User updatedUser = userService.updateUser(userToUpdate);

            return ok("user Updated");


        } catch (JsonProcessingException e) {
            Logger.error(e.getMessage());
            return badRequest("Not a json");
        }
    }

    /**
     *  This is to delete user from database
     * @return Deleted user
     */
    public Result deleteUser(long idToDelete){

        User deletedUser = userService.deleteUser(idToDelete);

        if (deletedUser == null){
            return badRequest(JsonServiceUtil.toJsonNode(new ResponseWrapper<>("No user for the id", null)));
        }

        return ok(JsonServiceUtil.toJsonNode(new ResponseWrapper<>("User deleted", deletedUser)));

    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result login(){
        JsonNode jsonNode = request().body().asJson();

        try {
            LoginCredentials loginCridentials = objectMapper.treeToValue(jsonNode, LoginCredentials.class);

            User loggedInUser = userService.login(loginCridentials);
            if (loggedInUser == null){
                return badRequest(JsonServiceUtil.toJsonNode(new ResponseWrapper<>("No user for the provided email/password", null)));
            }
            return ok(JsonServiceUtil.toJsonNode(new ResponseWrapper<>("User loggedIn", loggedInUser)));
        } catch (JsonProcessingException e) {
            Logger.error(e.getMessage());

            return badRequest(JsonServiceUtil.toJsonNode(new ResponseWrapper<>("Can not get data", null)));
        }
    }
}