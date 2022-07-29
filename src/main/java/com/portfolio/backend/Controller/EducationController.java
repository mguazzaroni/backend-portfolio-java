package com.portfolio.backend.Controller;

import com.portfolio.backend.DTO.EducationDTO;
import com.portfolio.backend.Entity.Education;
import com.portfolio.backend.Interface.IEducationService;
import com.portfolio.backend.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EducationController {

    @Autowired
    private IEducationService _service;

    @GetMapping("/education/all")
    public ResponseEntity<List<Education>> GetAll(){

        List<Education> educationList = _service.getEducationList();
        return new ResponseEntity<>(educationList, HttpStatus.OK);
    }
    @GetMapping("/education/get/{id}")
    public ResponseEntity<Education> getEducation(@PathVariable Long id){

        if(_service.findEducationById(id) == null){
            return new ResponseEntity(new Response("The id does not exist"), HttpStatus.NOT_FOUND);
        }
        Education education = _service.findEducationById(id);

        return new ResponseEntity<Education>(education, HttpStatus.OK);
    }
    @PostMapping("/education/new")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Post(@RequestBody EducationDTO model){
        if(model.getTitle().isBlank()){
            return new ResponseEntity<>(new Response("The title field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getInstitution().isBlank()){
            return new ResponseEntity<>(new Response("The institution field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getStartDate().isBlank()){
            return new ResponseEntity<>(new Response("The startDate field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getEndDate().isBlank()){
            return new ResponseEntity<>(new Response("The endDate field is required"), HttpStatus.BAD_REQUEST);
        }
        try{
            Education education = new Education(model.getTitle(),
                                                model.getInstitution(),
                                                model.getStartDate(),
                                                model.getEndDate());
            _service.saveEducation(education);

            return new ResponseEntity<>(new Response("Created successfully"), HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/education/update/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Put(@PathVariable Long id, @RequestBody EducationDTO model){
        if(model.getTitle().isBlank()){
            return new ResponseEntity<>(new Response("The title field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getInstitution().isBlank()){
            return new ResponseEntity<>(new Response("The institution field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getStartDate().isBlank()){
            return new ResponseEntity<>(new Response("The startDate field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getEndDate().isBlank()){
            return new ResponseEntity<>(new Response("The endDate field is required"), HttpStatus.BAD_REQUEST);
        }
        try {
            Education education = _service.getEducationById(id);

            education.setTitle(model.getTitle());
            education.setInstitution(model.getInstitution());
            education.setStartDate(model.getStartDate());
            education.setEndDate(model.getEndDate());

            _service.saveEducation(education);

            return new ResponseEntity<>(new Response("Updated successfully"), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/education/delete/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Delete(@PathVariable Long id) {
        if(!_service.existsEducationById(id)){
            return new ResponseEntity<>(new Response("The id does not exists"), HttpStatus.NOT_FOUND);
        }
        try{
            _service.deleteEducation(id);

            return new ResponseEntity<>(new Response("Deleted successfully"), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
