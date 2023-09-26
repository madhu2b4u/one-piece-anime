package com.example.onepieceanime.anime.controller;


import com.example.onepieceanime.anime.model.Crew;
import com.example.onepieceanime.anime.model.Crew;
import com.example.onepieceanime.anime.model.CrewWithCharacters;
import com.example.onepieceanime.anime.repository.CrewRepository;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@RestController
@RequestMapping("/api/anime/crew")
public class CrewController {

    @Autowired
    private CrewRepository crewRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    private MongoTemplate mongoTemplate;


    @PostMapping("/add")
    public Crew addCrew(@RequestBody Crew crew) {
        crew.setId(sequenceGenerator.generateSequence(Crew.SEQUENCE_NAME));
        return crewRepository.save(crew);
    }

    @GetMapping()
    public Iterable<Crew> getCrew() {
        return crewRepository.findAll();
    }

    @GetMapping("/{id}")
    public Crew findCrewById(@PathVariable Integer id) {
        return crewRepository.findCrewById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Crew> updateCrew(@PathVariable Integer id, @RequestParam String image) {
        if (!crewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Crew updatedCrew = new Crew();
        updatedCrew.setId(id);
        updatedCrew.setFlagImage(image);
        return ResponseEntity.ok(crewRepository.save(updatedCrew));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrew(@PathVariable Integer id) {
        if (!crewRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        crewRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/with-crewMates")
    public List<CrewWithCharacters> getCrewsWithCrewMates() {
        return getCrewWithCharacters();
    }

    public List<CrewWithCharacters> getCrewWithCharacters() {
        LookupOperation lookup = LookupOperation.newLookup()
                .from("character")
                .localField("_id")
                .foreignField("crewId")
                .as("characters");


        Aggregation aggregation = Aggregation.newAggregation(lookup);

        return mongoTemplate.aggregate(aggregation, "crew", CrewWithCharacters.class).getMappedResults();
    }

}
