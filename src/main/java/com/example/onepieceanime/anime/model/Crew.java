package com.example.onepieceanime.anime.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document("crew")
public class Crew {

    @Transient
    public static final String SEQUENCE_NAME = "crew_sequence";


    @Id
    private long id;

    @NotBlank
    @Size(max = 100)
    @Indexed(unique = true)
    private String crewName;

    private String flagImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCrewName() {
        return crewName;
    }

    public void setCrewName(String crewName) {
        this.crewName = crewName;
    }

    public String getFlagImage() {
        return flagImage;
    }

    public void setFlagImage(String flagImage) {
        this.flagImage = flagImage;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    private String shipName;

    public Crew(){

    }

    public Crew(String crewName, String flagImage, String shipName) {
        this.crewName = crewName;
        this.flagImage = flagImage;
        this.shipName = shipName;
    }

    @Override
    public String toString() {
        return "Crew [id=" + id + ", crewName=" + crewName + ", shipName=" + shipName + ", flagImage=" + flagImage +
                "]";
    }
}
