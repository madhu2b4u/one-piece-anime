package com.example.onepieceanime.anime.controller;


import com.example.onepieceanime.anime.model.Character;
import com.example.onepieceanime.anime.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/anime/character")
public class CharacterController {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;


    @PostMapping("/add")
    public Character addCharacter(@RequestBody Character character) {
        character.setId(sequenceGenerator.generateSequence(Character.SEQUENCE_NAME));
        return characterRepository.save(character);
    }

    @GetMapping()
    public Iterable<Character> getCustomers() {
        return characterRepository.findAll();
    }

    @GetMapping("/{id}")
    public Character findCustomerById(@PathVariable Integer id) {
        return characterRepository.findCharacterById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Integer id, @RequestParam String image) {
        if (!characterRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Character updatedCharacter = new Character();
        updatedCharacter.setId(id);
        updatedCharacter.setImage(image);
        return ResponseEntity.ok(characterRepository.save(updatedCharacter));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Integer id) {
        if (!characterRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        characterRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
