package com.portfolio.backend.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long Id;

    @Column(name = "Title")
    @NotNull
    private String Title;

    @Column(name = "Institution")
    @NotNull
    private String Institution;

    @Column(name = "StartDate")
    @NotNull
    private String StartDate;

    @Column(name = "EndDate")
    @NotNull
    private String EndDate;

    public Education() {}
    public Education(String title, String institution, String startDate, String endDate) {
        Title = title;
        Institution = institution;
        StartDate = startDate;
        EndDate = endDate;
    }
}
