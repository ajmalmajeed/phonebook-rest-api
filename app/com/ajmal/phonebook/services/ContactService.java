package com.ajmal.phonebook.services;

import com.ajmal.phonebook.models.Contact;

public interface ContactService {

    Contact updateContact(Contact contactToUpdate);

    Contact deleteContact(long idToDelete);
}
