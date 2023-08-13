package ale.fuentes.cqrs.pattern.controller;

import ale.fuentes.cqrs.pattern.entity.Contact;
import ale.fuentes.cqrs.pattern.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public List<Contact> fetchAllContacts(){
        return contactService.getContacts();
    }
}
