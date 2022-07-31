
package ar.com.portfolio.api.repositories;

import ar.com.portfolio.api.models.Person;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    public abstract ArrayList<Person> findByApellido(String apellido);
}