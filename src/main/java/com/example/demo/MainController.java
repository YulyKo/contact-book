package com.example.demo;

import com.example.demo.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = { "/contacts" }, method = RequestMethod.GET)
    public String personList(Model model) {
        List<Contact> contacts = contactService.getContacts();
        model.addAttribute("contacts", contacts);
        return "contactList";
    }

    @RequestMapping(value = { "/addContact" }, method = RequestMethod.GET)
    public String showAddPersonPage(Model model) {
        Contact contact = new Contact();
        model.addAttribute("contact", contact);
        return "addContact";
    }

    @RequestMapping(value = { "/addContact" }, method = RequestMethod.POST)
    public String savePerson(Model model,
                             @ModelAttribute("contact") Contact contact) {

        String name = contact.getName();
        String phone = contact.getPhone();
        Date dateOfBirth = contact.getDateOfBirth();
// 38 093 183 12 12
        if (name != null && name.length() > 0 && phone.length() >= 10) {
            Contact newContact = new Contact();
            newContact.setPhone(phone);
            newContact.setName(name);
            newContact.setDateOfBirth(dateOfBirth);
            contactService.postContact(newContact);

            return "redirect:/contacts";
        }
//        else {
//            String errorMessage = "Something wrong";
//            model.addAttribute("errorMessage", errorMessage);
//        }

        return "addContact";
    }

    @RequestMapping( value = { "/editContact/{id}" }, method = RequestMethod.GET)
    public String editContact(Model model, @PathVariable("id") Integer id) {
        Optional<Contact> contact = contactService.getContactByID(id);
        model.addAttribute("contact", contact);
//        Contacts newContact = new Contacts();
//        newContact.setId(contact.get().getId());
        return "/editContact";
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
