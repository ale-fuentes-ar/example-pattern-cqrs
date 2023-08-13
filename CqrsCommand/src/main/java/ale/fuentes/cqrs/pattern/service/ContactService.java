package ale.fuentes.cqrs.pattern.service;

import ale.fuentes.cqrs.pattern.dto.ContactEvent;
import ale.fuentes.cqrs.pattern.entity.Contact;
import ale.fuentes.cqrs.pattern.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${cqrspattern.topic.event-name}")
    private String EVENT_NAME_TOPIC_PORPERTY = "contact-event-topic";;
    private final String CREATE_CONTACT = "CreateContact";
    private final String UPADATE_CONTACT = "UpdateContact";

    public Contact createContact(ContactEvent contactEvent) {

        Contact contactDto = contactRepository.save(contactEvent.getContact());
        ContactEvent event = new ContactEvent(CREATE_CONTACT, contactDto);

        kafkaTemplate.send(EVENT_NAME_TOPIC_PORPERTY, event);
        return contactDto;

    }

    public Contact updateContact(long id, ContactEvent contactEvent) {
        Contact existContact = contactRepository.findById(id).get();

        Contact newContact = contactEvent.getContact();
        existContact.setName(newContact.getName());
        existContact.setPhone(newContact.getPhone());

        Contact contactDto = contactRepository.save(existContact);
        ContactEvent event = new ContactEvent(UPADATE_CONTACT, contactDto);

        kafkaTemplate.send(EVENT_NAME_TOPIC_PORPERTY, event);
        return contactDto;
    }
}
