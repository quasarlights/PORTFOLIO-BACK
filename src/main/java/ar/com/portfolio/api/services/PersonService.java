package ar.com.portfolio.api.services;

import ar.com.portfolio.api.models.Person;
import ar.com.portfolio.api.repositories.PersonRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public ArrayList<Person> getAllPerson() {
        return (ArrayList<Person>) personRepository.findAll();
    }
    
    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person savePerson(Person persona) {
        return personRepository.save(persona);
    }

    public Person getPersonByID(Long id) {
        return personRepository.findById(id).get();
    }

    public ArrayList<Person> getPersonByApellido(String apellido) {
        return personRepository.findByApellido(apellido);
    }
    
    public boolean removePerson(Long id) {
        try {
            personRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Person findById(Long personId) {
        return personRepository.findById(personId).get();
    }

}