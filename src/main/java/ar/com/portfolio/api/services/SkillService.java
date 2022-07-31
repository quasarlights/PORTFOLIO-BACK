package ar.com.portfolio.api.services;

import ar.com.portfolio.api.models.Skill;
import ar.com.portfolio.api.repositories.SkillRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public ArrayList< Skill> getAllSkill() {
        return (ArrayList< Skill>) skillRepository.findAll();
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill getSkillByID(Long id) {
        return skillRepository.findById(id).get();
    }

    public boolean deleteById(Long id) {
        try {
            skillRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Skill> getSkillByPersonId(Long personId) {
        return skillRepository.findAllByPersonId(personId);
    }

}
