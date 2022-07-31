package ar.com.portfolio.api.repositories;

import ar.com.portfolio.api.models.Project;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository <Project, Long> {
    public List<Project> findAllByPersonId(Long personId);
}
