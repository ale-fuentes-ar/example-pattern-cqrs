package ale.fuentes.cqrs.pattern.dto;

import ale.fuentes.cqrs.pattern.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactEvent {

    private String eventType;
    private Contact contact;

}
