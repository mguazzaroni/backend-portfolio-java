package com.portfolio.backend.Interface;

import com.portfolio.backend.Entity.Project;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IProjectService {
    public List<Project> getProjectList();
    public void saveProject(Project project);
    public void deleteProject(Long id);
    public Project findProjectById(Long id);
}
