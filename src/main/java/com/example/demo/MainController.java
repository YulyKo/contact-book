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
import java.util.Optional;

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

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseStatus(HttpStatus.CREATED)
    Contact editContact(@RequestBody() Integer id) {
        return contactService.getContactByID(id);
    }

    @RequestMapping( value = { "/saveEditContact/{id}" }, method = RequestMethod.POST)
    public String editContact(Model model,
                              @ModelAttribute("contact") Contact contact,
                              @PathVariable("id") Integer id) {
        String name = contact.getName();
        String phone = contact.getPhone();
//        Date dateOfBirth = contact.getDateOfBirth();
        if (name != null && name.length() > 0 && phone.length() >= 10) {
            contactService.updateContact(contact, id);
            return "redirect:/contacts";
        }
//        else {
//            String errorMessage = "Something wrong";
//            model.addAttribute("errorMessage", errorMessage);
//        }

        return "/editContact/{id}";
    }

    @RequestMapping( value = { "/contacts/delete/{id}" }, method = { RequestMethod.DELETE, RequestMethod.GET})
    public String deleteContact(Model model,
                                @PathVariable("id") Integer id) {
//        Contact contact = contactService.getContactByID(id).get();
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }

}
