package com.ajmal.phonebook.dao.impl;

import com.ajmal.phonebook.dao.ContactDao;
import com.ajmal.phonebook.models.Contact;
import com.avaje.ebean.Model;

public class ContactDaoImpl implements ContactDao {

    public static final Model.Finder<Long, Contact> find = new Model.Finder<>(Contact.class);

    @Override
    public Contact addContact(Contact contactToAdd) {
        contactToAdd.save();
        return contactToAdd;

    }

    @Override
    public Contact updateContact(Contact contactToUpdate) {
        contactToUpdate.update();
        return contactToUpdate;
    }

    @Override
    public Contact deleteContact(Contact contactToDelete) {
        contactToDelete.delete();
        return contactToDelete;
    }

    @Override
    public Contact searchContact(long idToSearch) {
        return find.byId(idToSearch);
    }
}
