package com.example.onepieceanime.anime.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document("character")
public class Character {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;

    private long crewId;

    public long getCrewId() {
        return crewId;
    }

    public void setCrewId(long crewId) {
        this.crewId = crewId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Character(){

    }

    public Character(String firstName, String lastName, String image, long crewId) {
        this.firstName = firstName;
        this.image = image;
        this.lastName = lastName;
        this.crewId = crewId;
    }

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String firstName;

    private String image;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String lastName;

    @Override
    public String toString() {
        return "Character [id=" + id + ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", image=" + image +
                ", crewId=" + crewId +
                "]";
    }
}
