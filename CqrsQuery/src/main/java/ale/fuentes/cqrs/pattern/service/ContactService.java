package ale.fuentes.cqrs.pattern.service;

import ale.fuentes.cqrs.pattern.dto.ContactEvent;
import ale.fuentes.cqrs.pattern.entity.Contact;
import ale.fuentes.cqrs.pattern.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Value("${cqrspattern.topic.event-name}")
    private final String EVENT_NAME_TOPIC_PORPERTY = "contact-event-topic";
    @Value("${cqrspattern.topic.event-group}")
    private final String EVENT_GROUP_TOPIC_PORPERTY = "contact-event-group";
    private final String CREATE_CONTACT = "CreateContact";
    private final String UPADATE_CONTACT = "UpdateContact";

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    @KafkaListener(topics = EVENT_NAME_TOPIC_PORPERTY, groupId = EVENT_GROUP_TOPIC_PORPERTY)
    public void processContactEvents(ContactEvent contactEvent){
        Contact contact = contactEvent.getContact();

        if(CREATE_CONTACT.equals(contactEvent.getEventType())){
            contactRepository.save(contact);
        }
        if(UPADATE_CONTACT.equals(contactEvent.getEventType())){
            Contact existingContact = contactRepository.findById(contact.getId()).get();

            existingContact.setName(contact.getName());
            existingContact.setPhone(contact.getPhone());

            contactRepository.save(existingContact);
        }
    }
}
