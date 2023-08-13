package ale.fuentes.cqrs.pattern.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CONTACT_COMMAND")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue
    private Long Id;
    @Column(name = "Name")
    private String Name;
    @Column(name = "Phone")
    private String Phone;
}
