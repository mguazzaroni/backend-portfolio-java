package com.portfolio.backend.DTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
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
}
