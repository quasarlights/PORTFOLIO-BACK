
package ar.com.portfolio.api.controllers;

import ar.com.portfolio.api.models.Person;
import ar.com.portfolio.api.models.Project;
import ar.com.portfolio.api.services.PersonService;
import ar.com.portfolio.api.services.ProjectService;
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
@RequestMapping("/api/project")

public class ProjectController {
    @Autowired
    PersonService personService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProject() {
        List<Project> projectList = projectService.getAllProject();
        return new ResponseEntity<>(projectList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long id) {
        Project project = projectService.getProjectByID(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Project>> getAllProjectByPersonId(@PathVariable(value = "person_id") Long personId) {
        List<Project> projectList = new ArrayList<>();
        if (personService.getPersonByID(personId) != null) {
            projectList = projectService.getProjectByPersonId(personId);
            return new ResponseEntity<>(projectList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(projectList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person/{person_id}")
    public ResponseEntity<Project> createProject(@PathVariable(value = "person_id") Long personId, @RequestBody Project projectRequest) {
        Person p = personService.findById(personId);
        projectRequest.setPerson(p);
        Project newProject = projectService.save(projectRequest);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project projectRequest) {
        Project project = projectService.getProjectByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
         projectRequest.setPerson(project.getPerson());
         projectRequest.setId(id);
        return new ResponseEntity<>(projectService.save(projectRequest), HttpStatus.OK);
    }
    
    
      
  
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") long id) {
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

