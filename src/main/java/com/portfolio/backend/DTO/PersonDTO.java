package com.portfolio.backend.DTO;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PersonDTO {
    @NotBlank
    private String Name;
    @NotBlank
    private String Surname;
    @NotBlank
    private String Description;
    @NotBlank
    private String Profession;
    @NotBlank
    private String Image;

    public PersonDTO() {
    }

    public PersonDTO(String name, String surname, String description, String profession, String image) {
        Name = name;
        Surname = surname;
        Description = description;
        Profession = profession;
        Image = image;
    }
}
