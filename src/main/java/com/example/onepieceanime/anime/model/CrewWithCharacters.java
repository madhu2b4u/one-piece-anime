package com.example.onepieceanime.anime.model;

import lombok.Data;

import java.util.List;
@Data
public class CrewWithCharacters {
    private String id;
    private String crewName;
    private String flagImage;
    private String shipName;
    private List<CharacterInfo> characters;
}

@Data
class CharacterInfo {
    private String firstName;
    private String lastName;
    private String image;
}
