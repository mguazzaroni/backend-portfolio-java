package com.portfolio.backend.Interface;

import com.portfolio.backend.Entity.Education;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IEducationService{
    public List<Education> getEducationList();
    public Education getEducationById(Long id);
    public Boolean existsEducationById(Long id);
    public Education findEducationById(Long id);
    public void saveEducation(Education education);
    public void deleteEducation(Long id);
}
