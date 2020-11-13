package com.example.demo;

import com.example.demo.domain.Contact;
import com.example.demo.domain.ContactCreateRequest;
import com.example.demo.domain.ContactCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/contacts")
public class MainController {

    @Autowired
    private ContactService contactService;

    @GetMapping()
    public List<Contact> personList() {
        return contactService.getContacts();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseStatus(HttpStatus.CREATED)
    ContactCreateResponse add(@RequestBody() ContactCreateRequest contact) {
        ContactCreateResponse response = new ContactCreateResponse(contact.getName(), contact.getPhone(), contact.getDateOfBirth());
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setPhone(contact.getPhone());
        newContact.setDateOfBirth(contact.getDateOfBirth());
        contactService.postContact(newContact);
        return response;
    }

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseStatus()
//    Contact getById(@RequestBody() Integer id) {
//        return contactService.getContactByID(id);
//    }

    @PutMapping("/{id}")
    public void editContact(@RequestBody() Contact contact, @PathVariable Integer id) {
        contactService.updateContact(contact, id);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(Model model,
                                @PathVariable("id") Integer id) {
        contactService.deleteContact(id);
    }

}
