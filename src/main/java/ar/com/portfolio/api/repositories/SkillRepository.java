package ar.com.portfolio.api.repositories;

import ar.com.portfolio.api.models.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    public List<Skill> findAllByPersonId(Long personId);

}

