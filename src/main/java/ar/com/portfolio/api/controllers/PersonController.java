package ar.com.portfolio.api.controllers;

import ar.com.portfolio.api.models.Person;
import ar.com.portfolio.api.services.PersonService;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/person")

public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/all")
    public ArrayList<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @PostMapping("/crear")
    public Person savePerson(@RequestBody Person person) {
        return personService.savePerson(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updateEducation(@PathVariable("id") long id, @RequestBody Person personRequest) {

        Person person = personService.findById(id);
        personRequest.setId(person.getId());
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));

        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        return new ResponseEntity<>(personService.save(personRequest), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public Person getPersonByID(@PathVariable("id") Long id) {
        return personService.getPersonByID(id);
    }

    @GetMapping("/query")
    public ArrayList<Person> getPersonByApellido(@RequestParam("apellido") String apellido) {
        return personService.getPersonByApellido(apellido);
    }

    @DeleteMapping("/{id}")
    public String removePerson(@PathVariable("id") Long id) {
        if (personService.removePerson(id)) {
            return "Se eliminó a la persona de di" + id + "correctamente";
        } else {
            return "La pesona no existe o no pudo ser eliminada";
        }

    }
}
