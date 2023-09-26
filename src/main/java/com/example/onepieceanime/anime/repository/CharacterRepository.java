package com.example.onepieceanime.anime.repository;

import com.example.onepieceanime.anime.model.Character;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CharacterRepository extends MongoRepository<Character, Integer> {

    Character findCharacterById(Integer id);

}
