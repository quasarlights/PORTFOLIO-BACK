
package ar.com.portfolio.api.repositories;

import ar.com.portfolio.api.models.Education;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {

    public List<Education> findAllByPersonId(Long personId);

   // public abstract ArrayList<Person> findByApellido(String apellido);
}
