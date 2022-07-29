package com.portfolio.backend.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "CompanyName")
    @NotNull
    private String CompanyName;

    @Column(name = "Description")
    @NotNull
    private String Description;

    @Column(name = "StartDate")
    @NotNull
    private String StartDate;

    @Column(name = "EndDate")
    @NotNull
    private String EndDate;

    public Experience() {
    }

    public Experience(String companyName, String description, String startDate, String endDate) {
        CompanyName = companyName;
        Description = description;
        StartDate = startDate;
        EndDate = endDate;
    }
}
