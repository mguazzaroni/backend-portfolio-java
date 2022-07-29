package com.portfolio.backend.Entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter @Getter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Name")
    @NotNull
    private String ProjectName;

    @Column(name = "Description")
    @NotNull
    private String Description;

    @Column(name = "ProjectUrl")
    @NotNull
    private String ProjectUrl;
}
