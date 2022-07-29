package com.portfolio.backend.Controller;

import com.portfolio.backend.DTO.PersonDTO;
import com.portfolio.backend.Entity.Person;
import com.portfolio.backend.Interface.IPersonService;
import com.portfolio.backend.Response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    @Autowired
    private IPersonService _service;

    @GetMapping("/person/all")
    public ResponseEntity<List<Person>> GetAll(){
        List<Person> personList = _service.getPersonList();

        return new ResponseEntity<>(personList, HttpStatus.OK);
    }
    @GetMapping("/person/get/profile")
    public ResponseEntity<Person> GetProfile(){
        //Se pasa el 1 hardcodeado porque siempre muestro el mismo perfil
        try {
            Person profile = _service.findPersonById((long) 1);

            return new ResponseEntity<Person>(profile, HttpStatus.OK);

        } catch (Exception ex){
            //Si se elimina de la base de datos, se lanza una excepcion y se devuelve un not found
            return new ResponseEntity(new Response("the default profile is missing or was deleted"), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/person/new")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Post(@RequestBody PersonDTO model){
        if(model.getName().isBlank()){
            return new ResponseEntity<>(new Response("The name field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getSurname().isBlank()){
            return new ResponseEntity<>(new Response("The surname field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getDescription().isBlank()){
            return new ResponseEntity<>(new Response("The description field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getProfession().isBlank()){
            return new ResponseEntity<>(new Response("The profession field is required"), HttpStatus.BAD_REQUEST);
        }
        //La imagen de perfil se muestra hardcodeada desde el front, asi que no se valida por ahora en el back
        //if(model.getImage().isBlank()){
        //    return new ResponseEntity<>(new Response("The image field is required"), HttpStatus.BAD_REQUEST);
        //}

        try{
            Person person = new Person( model.getName(),
                                        model.getSurname(),
                                        model.getDescription(),
                                        model.getProfession(),
                                        model.getImage());
            _service.savePerson(person);

            return new ResponseEntity<>(new Response("Created successfully"), HttpStatus.CREATED);

        } catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/person/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Put(@PathVariable Long id, @RequestBody PersonDTO model)
    {
        if(!_service.existsPersonById(id)){
            return new ResponseEntity<>(new Response("The id does not exist"), HttpStatus.NOT_FOUND);
        }
        if(model.getName().isBlank()){
            return new ResponseEntity<>(new Response("The name field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getSurname().isBlank()){
            return new ResponseEntity<>(new Response("The surname field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getDescription().isBlank()){
            return new ResponseEntity<>(new Response("The description field is required"), HttpStatus.BAD_REQUEST);
        }
        if(model.getProfession().isBlank()){
            return new ResponseEntity<>(new Response("The profession field is required"), HttpStatus.BAD_REQUEST);
        }

        try{
            Person person = _service.getPersonById(id);

            //Si encuentra la persona, le actualizo los campos con lo que trae el modelo

            person.setName(model.getName());
            person.setSurname(model.getSurname());
            person.setDescription(model.getDescription());
            person.setProfession(model.getProfession());

            //Guardo los cambios
            _service.savePerson(person);

            return new ResponseEntity<>(new Response("Updated successfully"), HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("person/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> Delete(@PathVariable Long id){
        if(!_service.existsPersonById(id)){
            return new ResponseEntity<>(new Response("The id does not exists"), HttpStatus.NOT_FOUND);
        }

        try {
            _service.deletePerson(id);

            return new ResponseEntity<>(new Response("Deleted successfully"), HttpStatus.OK);

        } catch (Exception ex){
            return new ResponseEntity<>(new Response(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
