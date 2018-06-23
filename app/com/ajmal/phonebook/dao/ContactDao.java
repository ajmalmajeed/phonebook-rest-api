package com.ajmal.phonebook.dao;

import com.ajmal.phonebook.models.Contact;

public interface ContactDao {

    Contact addContact(Contact contactToAdd);

    Contact updateContact(Contact contactToUpdate);

    Contact deleteContact(Contact contactToDelete);

    Contact searchContact(long idToSearch);
}
