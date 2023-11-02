package com.example.demo.service;

import com.example.demo.model.Departement;
import com.example.demo.model.Personne;
import com.example.demo.presentation.vo.PersonneVO;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.repository.PersonneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonneService {

    private final PersonneRepository personneRepository;
    private final DepartementRepository departementRepository;

    public PersonneService(PersonneRepository personneRepository, DepartementRepository departementRepository) {
        this.personneRepository = personneRepository;
        this.departementRepository = departementRepository;
    }

    public List<PersonneVO> getPersonnes(){
        List<Personne> personnes = personneRepository.findAll();
        return personnes.stream().map(PersonneVO::new).collect(Collectors.toList());
    }

    public PersonneVO getPersonne(long id){
        Optional<Personne> personnesOptional = personneRepository.findById(id);
        if (personnesOptional.isPresent()){
            System.out.println(personnesOptional);
            Personne personne = personnesOptional.get();
            return new PersonneVO(personne);
        }
        else
            return null;
    }

    public PersonneVO getPersonneByLogin(String login){
        Personne personne = personneRepository.findByLogin(login);
        if(personne != null){
            return new PersonneVO(personne);
        }
        else
            return null;

    }

    public PersonneVO createPersonne(PersonneVO personneVO ) {
        Departement departement = departementRepository.findByCode(personneVO.getDepartement().getCode());
        Personne personne1 = new Personne(personneVO.getNom(),personneVO.getPrenoms(),personneVO.getAge(),personneVO.getLogin(), departement);
        personneRepository.save(personne1);
        return new PersonneVO(personne1);
    }


    public PersonneVO updatePersonne(Long id, PersonneVO personneVO){
        Departement departement = departementRepository.findByCode(personneVO.getDepartement().getCode());
        Optional <Personne> personneOptional =personneRepository.findById(id);
        if (personneOptional.isPresent()){
            Personne personne1 = personneOptional.get();
            personne1.mettreAJour(personneVO.getNom(),personneVO.getPrenoms(),personneVO.getAge(),personneVO.getLogin(),departement);
            personneRepository.save(personne1);
            return new PersonneVO(personne1);
        }
        else
            return null;


        }

    public void deletePersonne(Long id){
        personneRepository.deleteById(id);
    }
  }

