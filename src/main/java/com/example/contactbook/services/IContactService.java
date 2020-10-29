package com.example.contactbook.services;

import com.example.contactbook.model.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> findAll();
}
