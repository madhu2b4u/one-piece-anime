package com.example.onepieceanime.anime.repository;

import com.example.onepieceanime.anime.model.Crew;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CrewRepository extends MongoRepository<Crew, Integer> {

    Crew findCrewById(Integer id);

}
