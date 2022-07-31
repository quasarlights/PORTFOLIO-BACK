package ar.com.portfolio.api.services;

import ar.com.portfolio.api.models.Project;
import ar.com.portfolio.api.repositories.ProjectRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public ArrayList<Project> getAllProject() {
        return (ArrayList<Project>) projectRepository.findAll();
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectByID(Long id) {
        return projectRepository.findById(id).get();
    }

    public boolean deleteById(Long id) {
        try {
            projectRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Project> getProjectByPersonId(Long personId) {
        return projectRepository.findAllByPersonId(personId);
    }

}
