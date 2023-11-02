package com.example.demo.controleur;

import com.example.demo.presentation.vo.PersonneVO;
import com.example.demo.service.PersonneService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("ws/personnes")
public class PersonneControleur {

    private final PersonneService personneService;

    public PersonneControleur(PersonneService personneService){
        this.personneService = personneService;
    }

    @GetMapping()
    public List<PersonneVO> getPersonnes(){
        return personneService.getPersonnes();
    }

    @GetMapping("/login/{login}")
    public PersonneVO getPersonneByLogin(@PathVariable String login){
        return personneService.getPersonneByLogin(login);
    }

    @GetMapping("/{id}")
    public PersonneVO getPersonne(@PathVariable long id) {
        return personneService.getPersonne(id);
    }


    @PostMapping
    public PersonneVO createPersonne(@RequestBody PersonneVO personne){
        return  personneService.createPersonne(personne);
    }

    @PutMapping("/{id}")
    public PersonneVO updatePersonne(@PathVariable Long id, @RequestBody PersonneVO personne){
            return personneService.updatePersonne(id,personne);
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable Long id){
        personneService.deletePersonne(id);
    }

   }
