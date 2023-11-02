package com.example.demo;

import com.example.demo.model.Calcul;
import com.example.demo.model.Departement;
import com.example.demo.model.Personne;
import com.example.demo.presentation.vo.DepartementVO;
import com.example.demo.presentation.vo.PersonneVO;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.repository.PersonneRepository;
import com.example.demo.service.PersonneService;
import org.aspectj.lang.annotation.Before;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestServices {
    @Mock
    private PersonneRepository personneRepository;

    @InjectMocks
    private PersonneService personneService;

    @Mock
    private  DepartementRepository departementRepository;

    @Before("")
    public void setUp() {
        personneRepository = Mockito.mock(PersonneRepository.class);
        departementRepository = Mockito.mock(DepartementRepository.class);
        personneService = new PersonneService(personneRepository, departementRepository);
    }



    @Test
    public void getPersonnes() {
        // GIVEN
        Personne personne1 = new Personne();
        personne1.setId(1L);
        personne1.setNom("nom");
        personne1.setPrenoms("prenom");
        personne1.setAge(1);
        personne1.setLogin("login1");

        Departement departement1 = new Departement();
        departement1.setId(101L);
        departement1.setCode("code1");
        personne1.setDepartement(departement1);

        Personne personne2 = new Personne();
        personne2.setId(2L);
        personne2.setNom("nom1");
        personne2.setPrenoms("prenom1");
        personne2.setAge(2);
        personne2.setLogin("login2");

        Departement departement2 = new Departement();
        departement2.setId(102L);
        departement2.setCode("code2");
        personne2.setDepartement(departement2);

        when(personneRepository.findAll()).thenReturn(List.of(personne1, personne2));

        // WHEN
        List<PersonneVO> resultat = personneService.getPersonnes();

        // THEN
        assertThat(resultat)
                .isNotEmpty()
                .extracting(
                        PersonneVO::getId,
                        PersonneVO::getNom,
                        PersonneVO::getPrenoms,
                        PersonneVO::getAge,
                        PersonneVO::getLogin
                )
                .containsOnly(
                        tuple(1L, "nom", "prenom", 1, "login1"),
                        tuple(2L, "nom1", "prenom1", 2, "login2")

                );

        List<DepartementVO> departementVOS = resultat.stream().map(PersonneVO::getDepartement).toList();
        assertThat(departementVOS)
                .isNotEmpty()
                .extracting(DepartementVO::getId, DepartementVO::getCode)
                .containsOnly(
                        tuple(101L, "code1"),
                        tuple(102L, "code2")
                );
    }

    @Test
    public void getPersonne(){
        // GIVEN
        long id = 1L;
        Personne personne = new Personne();
        personne.setId(1L);
        personne.setNom("nom");
        personne.setPrenoms("prenom");
        personne.setAge(1);
        personne.setLogin("login");

        Departement departement = new Departement();
        departement.setId(101L);
        departement.setCode("code1");
        personne.setDepartement(departement);

        when(personneRepository.findById(id)).thenReturn(Optional.of(personne));

        // WHEN
        PersonneVO resultat = personneService.getPersonne(id);

        // THEN
        assertThat(resultat).isNotNull();
        assertThat(resultat.getId()).isEqualTo(id);
        assertThat(resultat.getNom()).isEqualTo("nom");
        assertThat(resultat.getPrenoms()).isEqualTo("prenom");
        assertThat(resultat.getAge()).isEqualTo(1);
        assertThat(resultat.getLogin()).isEqualTo("login");

        assertThat(resultat.getDepartement()).isNotNull();
        assertThat(resultat.getDepartement().getId()).isEqualTo(101L);
        assertThat(resultat.getDepartement().getCode()).isEqualTo("code1");
    }
    @Test
    public void getPersonneInvalidId() {
        // GIVEN
        long id = 1L;
        when(personneRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        PersonneVO resultat = personneService.getPersonne(id);

        // THEN
        assertThat(resultat).isNull();
    }

    @Test
    public void getPersonneByLogin(){

        //GIVEN
        Personne personne = new Personne();
        personne.setId(1L);
        personne.setNom("nom");
        personne.setPrenoms("prenom");
        personne.setAge(1);
        personne.setLogin("sarajulie");

        Departement departement = new Departement();
        departement.setId(2L);
        departement.setCode("code2");
        personne.setDepartement(departement);

        Mockito.when(personneRepository.findByLogin("sarajulie")).thenReturn(personne);

        // WHEN

        PersonneVO personneVO = personneService.getPersonneByLogin("sarajulie");

        // THEN
        assertEquals("sarajulie",personneVO.getLogin());
    }

    @Test
    public void getPersonneByInvalidLogin(){
        //GIVEN
        Mockito.when(personneRepository.findByLogin("sarajulie")).thenReturn(null);

        // WHEN
        PersonneVO personneVO = personneService.getPersonneByLogin("sarajulie");

        // THEN
        assertNull(personneVO);

    }

    @Test
    public  void createPersonne(){

        //GIVEN
        PersonneVO personneVO1 = new PersonneVO();
        personneVO1.setId(1L);
        personneVO1.setNom("nom");
        personneVO1.setPrenoms("pernom");
        personneVO1.setAge(1);
        personneVO1.setLogin("login");

        DepartementVO departement = new DepartementVO();
        departement.setId(12L);
        departement.setCode("code3");
        personneVO1.setDepartement(departement);

        Departement departement1 = new Departement();

        when(departementRepository.findByCode("code3")).thenReturn(departement1);
        Mockito.when(personneRepository.save(Mockito.any(Personne.class))).thenReturn(new Personne());

        // WHEN
        PersonneVO personneVO;
        personneVO = personneService.createPersonne(personneVO1);

        // THEN
        assertEquals(personneVO1.getNom(),personneVO.getNom());
        assertEquals(personneVO1.getPrenoms(),personneVO.getPrenoms());
        assertEquals(personneVO1.getAge(),personneVO.getAge());
        assertEquals(personneVO1.getLogin(),personneVO.getLogin());

    }
    @Test
    public void updatePersonne(){
        //GIVEN

        long id = 1L;
        PersonneVO personneVO = new PersonneVO();
        personneVO.setId(1L);
        personneVO.setNom("nom");
        personneVO.setPrenoms("prenom");
        personneVO.setAge(1);
        personneVO.setLogin("login");

        DepartementVO departementVO = new DepartementVO();
        departementVO.setId(1L);
        departementVO.setCode("code4");
        departementVO.setDesignation("abidjan");
        personneVO.setDepartement(departementVO);

        Departement departement = new Departement();

        when(departementRepository.findByCode("code4")).thenReturn(departement);
        when(personneRepository.findById(id)).thenReturn(Optional.of(new Personne()));

        //WHEN
        PersonneVO resultat = personneService.updatePersonne(id,personneVO);

        //THEN
        assertEquals(personneVO.getNom(), resultat.getNom());
        assertEquals(personneVO.getPrenoms(),resultat.getPrenoms());
        assertEquals(personneVO.getLogin(),resultat.getLogin());
    }

    @Test
    public void deletePersonne(){
        //GIVEN
        long id = 1L;
        //WHEN
       personneService.deletePersonne(id);
       //THEN
        verify(personneRepository,Mockito.times(1)).deleteById(id);

    }



}
