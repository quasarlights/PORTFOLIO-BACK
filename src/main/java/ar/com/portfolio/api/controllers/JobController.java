package ar.com.portfolio.api.controllers;

import ar.com.portfolio.api.models.Job;
import ar.com.portfolio.api.models.Person;
import ar.com.portfolio.api.services.JobService;
import ar.com.portfolio.api.services.PersonService;
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
@RequestMapping("/api/job")
public class JobController {
    
   @Autowired
    PersonService personService;

    @Autowired
    JobService jobService;

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJob() {
        List<Job> jobList = jobService.getAllJob();
        return new ResponseEntity<>(jobList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable(value = "id") Long id) {
        Job job = jobService.getJobByID(id);
        return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/person/{person_id}")
    public ResponseEntity<List<Job>> getAllJobByPersonId(@PathVariable(value = "person_id") Long personId) {
        List<Job> jobList = new ArrayList<>();
        if (personService.getPersonByID(personId) != null) {
            jobList = jobService.getJobByPersonId(personId);
            return new ResponseEntity<>(jobList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(jobList, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/person/{person_id}")
    public ResponseEntity<Job> createJob(@PathVariable(value = "person_id") Long personId, @RequestBody Job jobRequest) {
        Person p = personService.findById(personId);
        jobRequest.setPerson(p);
        Job newJob = jobService.save(jobRequest);
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable("id") long id, @RequestBody Job jobRequest) {
        Job job = jobService.getJobByID(id);
        // .orElseThrow(() -> new ResourceNotFoundException("EducationId " + id + "not found"));
        jobRequest.setPerson(job.getPerson());
        jobRequest.setId(id);
        return new ResponseEntity<>(jobService.save(jobRequest), HttpStatus.OK);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable("id") long id) {
        jobService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
