package com.portfolio.backend.Controller;

import com.portfolio.backend.Entity.Project;
import com.portfolio.backend.Interface.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")

/*Este controlador no se utiliza ya que los proyectos los muestro
con un servicio en Angular usando la API de Github */

public class ProjectController{
    @Autowired
    private IProjectService _service;

    @GetMapping("/projects/all")
    public List<Project> GetAll(){
        return _service.getProjectList();
    }
    @GetMapping("/projects/get/{id}")
    public Project GetProject(@PathVariable Long id){
        return _service.findProjectById(id);
    }
    @PostMapping("/projects/new")
    public String Post(Project project){
        try {
            _service.saveProject(project);

            return "Saved";
        }
        catch (Exception ex){
            return "An error has occurred " + ex.getMessage();
        }
    }
    @PutMapping("/projects/update/{id}")
    public String Put(@PathVariable Long id,
                      @RequestParam("name") String newName,
                      @RequestParam("description") String newDescription,
                      @RequestParam("url") String newUrl)
    {
        Project project = _service.findProjectById(id);

        project.setProjectName(newName);
        project.setDescription(newDescription);
        project.setProjectUrl(newUrl);

        return "Updated successfully";
    }

    @DeleteMapping("/projects/delete/{id}")
    public String Delete(Long id){
        _service.deleteProject(id);

        return "Deleted";
    }
}
