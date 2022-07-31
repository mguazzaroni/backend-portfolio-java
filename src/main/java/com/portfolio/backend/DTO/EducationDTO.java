package com.portfolio.backend.DTO;

import javax.validation.constraints.NotBlank;

public class EducationDTO {
    @NotBlank
    private String Title;
    @NotBlank
    private String Institution;
    @NotBlank
    private String StartDate;
    @NotBlank
    private String EndDate;

    public EducationDTO() {}

    public EducationDTO(String title, String institution, String startDate, String endDate) {
        Title = title;
        Institution = institution;
        StartDate = startDate;
        EndDate = endDate;
    }

    //Getters
    public String getTitle() {
        return Title;
    }

    public String getInstitution() {
        return Institution;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    //Setters
    public void setTitle(String title) {
        Title = title;
    }

    public void setInstitution(String institution) {
        Institution = institution;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }
}
