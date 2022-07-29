package com.portfolio.backend.DTO;

import lombok.Getter;
import javax.validation.constraints.NotBlank;

@Getter
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
}
