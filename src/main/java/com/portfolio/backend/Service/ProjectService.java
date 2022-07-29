package com.portfolio.backend.Service;

import com.portfolio.backend.Entity.Project;
import com.portfolio.backend.Interface.IProjectService;
import com.portfolio.backend.Repository.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    private IProjectRepository _repository;

    @Override
    public List<Project> getProjectList() {
        return _repository.findAll();
    }

    @Override
    public void saveProject(Project project) {
        _repository.save(project);
    }

    @Override
    public void deleteProject(Long id) {
        _repository.deleteById(id);
    }

    @Override
    public Project findProjectById(Long id) {
        return _repository.findById(id).orElse(null);
    }
}
