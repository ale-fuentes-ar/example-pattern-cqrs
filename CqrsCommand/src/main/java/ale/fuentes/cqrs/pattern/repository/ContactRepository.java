package ale.fuentes.cqrs.pattern.repository;

import ale.fuentes.cqrs.pattern.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
