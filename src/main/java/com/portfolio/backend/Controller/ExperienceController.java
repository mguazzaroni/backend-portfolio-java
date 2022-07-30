package com.portfolio.backend.Controller;

import com.portfolio.backend.DTO.ExperienceDTO;
import com.portfolio.backend.Entity.Experience;
import com.portfolio.backend.Interface.IExperienceService;
import com.portfolio.backend.Response.Response;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200/")
@CrossOrigin(origins = "https://portfolio-guazzaroni.web.app/")

public class ExperienceController {
    @Autowired
    private IExperienceService _service;

    @GetMapping("/experience/all")
    public ResponseEntity<List<Experience>> getAll(){
        List<Experience> experienceList =  _service.getExperienceList();

        return new ResponseEntity<>(experienceList, HttpStatus.OK);
    }
    @GetMapping("/experience/get/{id}")
    public ResponseEntity<Experience> getExperienceById(@PathVariable Long id){
       if(_service.findExperienceById(id) == null){
           return new ResponseEntity(new Response("The id does not exist"), HttpStatus.NOT_FOUND);
       }

       Experience experience = _service.findExperienceById(id);

       return new ResponseEntity<Experience>(experience, HttpStatus.OK);
    }
    @PostMapping("/experience/new")
   //  @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Post(@RequestBody ExperienceDTO model){
        if(StringUtils.isBlank(model.getCompanyName())){
            return new ResponseEntity<>(new Response("the company name field is required"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(model.getDescription())){
            return new ResponseEntity<>(new Response("the description field is required"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(model.getStartDate())){
            return new ResponseEntity<>(new Response("the startDate field is required"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(model.getEndDate())){
            return new ResponseEntity<>(new Response("the endDate field is required"), HttpStatus.BAD_REQUEST);
        }
        try{
            Experience experience = new Experience( model.getCompanyName(),
                                                    model.getDescription(),
                                                    model.getStartDate(),
                                                    model.getEndDate());
            _service.saveExperience(experience);
            return new ResponseEntity<>(new Response("Created successfully"), HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/experience/update/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Put(@PathVariable Long id, @RequestBody ExperienceDTO model){

        if(StringUtils.isBlank(model.getCompanyName())){
            return new ResponseEntity<>(new Response("the company name field is required"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(model.getDescription())){
            return new ResponseEntity<>(new Response("the description field is required"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(model.getStartDate())){
            return new ResponseEntity<>(new Response("the startDate field is required"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(model.getEndDate())){
            return new ResponseEntity<>(new Response("the endDate field is required"), HttpStatus.BAD_REQUEST);
        }

        try {
            Experience experience = _service.getExperienceById(id);

            experience.setCompanyName(model.getCompanyName());
            experience.setDescription(model.getDescription());
            experience.setStartDate(model.getStartDate());
            experience.setEndDate(model.getEndDate());

            _service.saveExperience(experience);

            return new ResponseEntity<>(new Response("Updated successfully"), HttpStatus.OK);

        } catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/experience/delete/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        if(!_service.existsExperienceById(id)){
            return new ResponseEntity<>(new Response("The id does not exists"), HttpStatus.NOT_FOUND);
        }
        try {
            _service.deleteExperience(id);

            return new ResponseEntity<>(new Response("Deleted successfully"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
