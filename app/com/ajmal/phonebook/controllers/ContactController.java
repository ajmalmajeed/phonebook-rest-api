package com.ajmal.phonebook.controllers;


import com.ajmal.phonebook.dao.ContactDao;
import com.ajmal.phonebook.dao.impl.ContactDaoImpl;
import com.ajmal.phonebook.models.Contact;
import com.ajmal.phonebook.services.ContactService;
import com.ajmal.phonebook.services.impl.ContactServiceImpl;
import com.ajmal.phonebook.util.JsonServiceUtil;
import com.ajmal.phonebook.util.ResponseWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.Logger;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;

public class ContactController extends Controller {

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private ContactService contactService;

    /**
     * Save contact to the database from the request (hard coded contact)
     * @return contact saved in the database
     */

    public Result addContact(){
        Contact contactToSave =  new Contact();
        contactToSave.setConName("Ajmal");
        contactToSave.setConAddress("123, example road, city");
        contactToSave.setConEmail("123@example.com");
        contactToSave.setMobileNumber("0777771234");
        contactToSave.setHomeNumber("0112345678");
        contactToSave.setOfficeNumber("0116537287");

        ContactDao userDao = new ContactDaoImpl();

        Contact addedUser = userDao.addContact(contactToSave);

        return ok("Contact added Successfuly");
    }


    /**
     * This is to update contact in database
     * @return updated conatct in the database
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result updateContact(){
        JsonNode jsonNode = request().body().asJson();

        Contact contactToUpdate = null;

        try {
            contactToUpdate = objectMapper.treeToValue(jsonNode, Contact.class);


            ContactService contactService = new ContactServiceImpl();

            Contact updatedContact = contactService.updateContact(contactToUpdate);

            return ok("Contact Updated");


        } catch (JsonProcessingException e) {
            Logger.error(e.getMessage());
            return badRequest("Not a json");
        }
    }

    /**
     *  This is to delete contact from database
     * @return Deleted contact
     */
    public Result deleteContact(long idToDelete){

        Contact deletedContact = contactService.deleteContact(idToDelete);

        if (deletedContact == null){
            return badRequest(JsonServiceUtil.toJsonNode(new ResponseWrapper<>("No contact for the id", null)));
        }

        return ok(JsonServiceUtil.toJsonNode(new ResponseWrapper<>("Contact deleted", deletedContact)));

    }
}

