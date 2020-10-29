package com.example.contactbook.services;

import com.example.contactbook.model.Contact;
import com.example.contactbook.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService implements IContactService {

    private ContactRepository repository;

    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Contact> findAll() {
        var contacts = (List<Contact>) repository.findAll();
        return contacts;
    }
}
