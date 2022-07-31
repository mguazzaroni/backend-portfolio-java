package com.portfolio.backend.DTO;

import javax.validation.constraints.NotBlank;

public class ExperienceDTO {
    @NotBlank
    private String CompanyName;
    @NotBlank
    private String Description;
    @NotBlank
    private String StartDate;

    private String EndDate;

    public ExperienceDTO() {
    }

    public ExperienceDTO(String companyName, String description, String startDate, String endDate) {
        CompanyName = companyName;
        Description = description;
        StartDate = startDate;
        EndDate = endDate;
    }

    //Getters

    public String getCompanyName() {
        return CompanyName;
    }

    public String getDescription() {
        return Description;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    //Setters

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
