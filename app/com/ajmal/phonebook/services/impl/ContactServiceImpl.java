package com.ajmal.phonebook.services.impl;

import com.ajmal.phonebook.dao.ContactDao;
import com.ajmal.phonebook.dao.impl.ContactDaoImpl;
import com.ajmal.phonebook.models.Contact;
import com.ajmal.phonebook.services.ContactService;

import javax.inject.Inject;
import javax.inject.Named;

public class ContactServiceImpl implements ContactService {

    @Inject
    @Named("contactDao")
    private ContactDao contactDao;


    @Override
    public Contact updateContact(Contact contactToUpdate) {
        ContactDao contactDao = new ContactDaoImpl();

        //get contactId from database
        Contact contactInDb = contactDao.searchContact(contactToUpdate.getId());

        /*if conName to be updated is not wmpty,
        update the contact in db with new conName
         */
        if(contactToUpdate.getConName().isEmpty())
            contactInDb.setConName(contactToUpdate.getConName());

        //return update contact
        return contactDao.updateContact(contactInDb);
    }

    @Override
    public Contact deleteUser(long idToDelete) {

        //get contact from database
        Contact contactInDb = contactDao.searchContact(idToDelete);

        //check if contact in db is null
        if(contactInDb == null)
            return null;

        //delete the contact we got and return it
        return contactDao.deleteContact(contactInDb);

    }
}
