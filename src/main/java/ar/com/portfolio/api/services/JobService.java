package ar.com.portfolio.api.services;

import ar.com.portfolio.api.models.Job;
import ar.com.portfolio.api.repositories.JobRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public ArrayList<Job> getAllJob() {
        return (ArrayList<Job>) jobRepository.findAll();
    }

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public Job getJobByID(Long id) {
        return jobRepository.findById(id).get();
    }

    public boolean deleteById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Job> getJobByPersonId(Long personId) {
        return jobRepository.findAllByPersonId(personId);
    }


}