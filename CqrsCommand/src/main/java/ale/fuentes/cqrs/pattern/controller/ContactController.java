package ale.fuentes.cqrs.pattern.controller;

import ale.fuentes.cqrs.pattern.dto.ContactEvent;
import ale.fuentes.cqrs.pattern.entity.Contact;
import ale.fuentes.cqrs.pattern.repository.ContactRepository;
import ale.fuentes.cqrs.pattern.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public Contact createContact(@RequestBody ContactEvent contactEvent){
        return contactService.createContact(contactEvent);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable long id, @RequestBody ContactEvent contactEvent){
        return contactService.updateContact(id, contactEvent);
    }


}
