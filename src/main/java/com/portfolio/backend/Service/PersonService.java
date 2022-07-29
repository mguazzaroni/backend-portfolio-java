package com.portfolio.backend.Service;

import com.portfolio.backend.Entity.Person;
import com.portfolio.backend.Interface.IPersonService;
import com.portfolio.backend.Repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService implements IPersonService {
    @Autowired
    private IPersonRepository _repository;

    @Override
    public List<Person> getPersonList() {
        return _repository.findAll();
    }
    @Override
    public Person getPersonById(Long id){
        return _repository.getById(id);
    }
    @Override
    public void savePerson(Person person) {
        _repository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        _repository.deleteById(id);
    }

    @Override
    public Person findPersonById(Long id) {
        return _repository.findById(id).orElse(null);
    }

    @Override
    public Boolean existsPersonById(Long id){
        return _repository.existsById(id);
    }
}
