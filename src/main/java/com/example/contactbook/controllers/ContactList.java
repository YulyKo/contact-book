package com.example.contactbook.controllers;

import com.example.contactbook.model.Contact;
import com.example.contactbook.services.ContactService;
import com.example.contactbook.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactList {
    @Autowired
    private ContactService contactService;

    @GetMapping("/showContacts")
    public String findContacts(Model model) {
        var contacts = (List<Contact>) contactService.findAll();
        model.addAttribute("contacts", contacts);
        return "showContacts";
    }
}
