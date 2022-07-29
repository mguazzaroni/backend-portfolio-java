package com.portfolio.backend.Interface;

import com.portfolio.backend.Entity.Experience;
import java.util.List;

public interface IExperienceService{
    public List<Experience> getExperienceList();
    public Experience getExperienceById(Long id);
    public Boolean existsExperienceById(Long id);
    public Experience findExperienceById(Long id);
    public void saveExperience(Experience experience);
    public void deleteExperience(Long id);
}
