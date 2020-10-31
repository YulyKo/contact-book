package com.example.demo;

import com.example.demo.domain.Contact;
import com.example.demo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public void postContact(Contact contact){
//        Contacts contact = new Contacts();
//        Optional<Contact> contact = contactRepository.findAllById(1);
//        contact.get().setPhone("2344242444");

//        contactRepository.save(contact);
        contactRepository.save(contact);
    }

    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactByID(Integer id) {
        return contactRepository.findById(id);
    }

    public void updateContact(Contact contact, Integer id) {
        String name = contact.getName();
        Date dateOfBirth = contact.getDateOfBirth();
        String phone = contact.getPhone();
        System.out.println(contact);
        Contact newContact = contactRepository.findById(id).get();
        newContact.setPhone(phone);
        newContact.setDateOfBirth(dateOfBirth);
        newContact.setName(name);
        System.out.println(newContact);
        contactRepository.saveAndFlush(newContact);
    }

    public void deleteContact(Integer id) {
//        Contact contact = this.getContactByID(id).get();
        contactRepository.deleteById(id);
    }
}
