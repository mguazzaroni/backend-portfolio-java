package com.portfolio.backend.DTO;

import javax.validation.constraints.NotBlank;

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

    //Getters

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getDescription() {
        return Description;
    }

    public String getProfession() {
        return Profession;
    }

    public String getImage() {
        return Image;
    }

    //Setters

    public void setName(String name) {
        Name = name;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public void setImage(String image) {
        Image = image;
    }
}
