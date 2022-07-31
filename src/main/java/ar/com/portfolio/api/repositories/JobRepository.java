
package ar.com.portfolio.api.repositories;

import ar.com.portfolio.api.models.Job;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JobRepository extends JpaRepository <Job, Long>{
    
    public List<Job> findAllByPersonId(Long personID);
    
}
