package ar.com.portfolio.api.controllers;

import ar.com.portfolio.api.models.Person;
import ar.com.portfolio.api.models.Skill;
import ar.com.portfolio.api.services.PersonService;
import ar.com.portfolio.api.services.SkillService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/skill")

public class SkillController {

    @Autowired
    PersonService personService;

    @Autowired
    SkillService skillService;

    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAllSkill() {
        List<Skill> skillList = skillService.getAllSkill();
        return new ResponseEntity<>(skillList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable(value = "id") Long id) {
        Skill skill = skillService.getSkillByID(id);
        return new ResponseEntity<>(skill, HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Skill>> getAllSkillByPersonId(@PathVariable(value = "person_id") Long personId) {
        List<Skill> skillList = new ArrayList<>();
        if (personService.getPersonByID(personId) != null) {
            skillList = skillService.getSkillByPersonId(personId);
            return new ResponseEntity<>(skillList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(skillList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person/{person_id}")
    public ResponseEntity<Skill> createSkill(@PathVariable(value = "person_id") Long personId, @RequestBody Skill skillRequest) {
        Person p = personService.findById(personId);
        skillRequest.setPerson(p);
        Skill newSkill = skillService.save(skillRequest);
        return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> updateJob(@PathVariable("id") long id, @RequestBody Skill skillRequest) {
        Skill skill = skillService.getSkillByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        skillRequest.setPerson(skill.getPerson());
        skillRequest.setId(id);
        return new ResponseEntity<>(skillService.save(skillRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable("id") long id) {
        skillService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
