package com.example.demo;

import com.example.demo.model.Departement;
import com.example.demo.model.Personne;
import com.example.demo.presentation.vo.DepartementVO;
import com.example.demo.presentation.vo.PersonneVO;
import com.example.demo.repository.DepartementRepository;
import com.example.demo.repository.PersonneRepository;
import com.example.demo.service.DepartementService;
import com.example.demo.service.PersonneService;
import org.aspectj.lang.annotation.Before;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestServiceDepartement {
    @Mock
    private DepartementRepository departementRepository;

    @InjectMocks
    private DepartementService departementService;

    @Mock
    private PersonneRepository personneRepository;

    @Before("")
    public void setUp() {
        departementRepository = Mockito.mock(DepartementRepository.class);
        departementService = new DepartementService(departementRepository);
    }

    @Test
    public void getDepartements(){
        //GIVEN

        Departement departement = new Departement();
        departement.setId(1L);
        departement.setCode("code");
        departement.setDesignation("cocody");

        when(departementRepository.findAll()).thenReturn(List.of(departement));

        //WHEN
        List<DepartementVO> resultat = departementService.getDepartements();

        // THEN
        assertThat(resultat)
                .isNotEmpty()
                .extracting(
                        DepartementVO::getId,
                        DepartementVO::getCode,
                        DepartementVO::getDesignation
                )
                .containsOnly(
                        tuple(1L, "code", "cocody")
                );


    }
    @Test
    public void getDepartement() {
        //GIVEN
        long id = 1L;
        Departement departement = new Departement();
        departement.setId(1L);
        departement.setCode("code");
        departement.setDesignation("palmeraie");

        when(departementRepository.findById(id)).thenReturn(Optional.of(departement));

        //WHEN
        DepartementVO resultat = departementService.getDepartement(id);

        //THEN
        assertThat(resultat).isNotNull();
        assertThat(resultat.getId()).isEqualTo(1L);
        assertThat(resultat.getCode()).isEqualTo("code");
        assertThat(resultat.getDesignation()).isEqualTo("palmeraie");
    }
    @Test
    public void getDepartementInvalidId(){
        //GIVEN
        long id = 2L;
       when(departementRepository.findById(id)).thenReturn(Optional.empty());
       //WHEN
        DepartementVO resultat = departementService.getDepartement(id);
        //THEN
        assertThat(resultat).isNull();

    }

    @Test
    public void getDepartementByCode(){
        //GIVEN
        String code = "code";
        Departement departement = new Departement();
        departement.setId(1L);
        departement.setCode("code");
        departement.setDesignation("faya");

        when(departementRepository.findByCode(code)).thenReturn(departement);

        //WHEN
        DepartementVO resultat = departementService.getDepartementByCode("code");

        //THEN

        assertThat(resultat).isNotNull();
        assertThat(resultat.getId()).isEqualTo(1L);
        assertThat(resultat.getCode()).isEqualTo("code");
        assertThat(resultat.getDesignation()).isEqualTo("faya");

    }

    @Test
    public void getDepartementByCodeInvald(){
        //GIVEN
        String code = "code";
        when(departementRepository.findByCode(code)).thenReturn(null);
        //WHEN
        DepartementVO resultat = departementService.getDepartementByCode("code");
        //THEN
        assertThat(resultat).isNull();
    }

    @Test
    public void createDepartement(){
        //GIVEN
        DepartementVO departementVO = new DepartementVO();
        departementVO.setId(2L);
        departementVO.setCode("code5");
        departementVO.setDesignation("adjame");

        Mockito.when(departementRepository.save(Mockito.any(Departement.class))).thenReturn(new Departement());

        //WHEN

        departementVO = departementService.createDepartement(departementVO);

        //THEN

        assertEquals(departementVO.getCode(), departementVO.getCode());
        assertEquals(departementVO.getDesignation(),departementVO.getDesignation());

    }

    @Test
    public void updateDepartement(){
        //GIVEN
        long id = (1L);
        DepartementVO departementVO = new DepartementVO();
        departementVO.setId(1L);
        departementVO.setCode("code");
        departementVO.setDesignation("riviera");

        when(departementRepository.findById(id)).thenReturn(Optional.of(new Departement()));

        //WHEN
        DepartementVO resultat = departementService.updateDepartement(id,departementVO);
        //THEN
        assertEquals(resultat.getCode(),departementVO.getCode());
        assertEquals(resultat.getDesignation(),departementVO.getDesignation());
    }

    @Test
    public void updateDepartementInvalid(){
        //GIVEN
        long id = 1L;
        when(departementRepository.findById(id)).thenReturn(Optional.ofNullable(null));
        //WHEN
        DepartementVO resultat = departementService.updateDepartement(1L,null);
        //THEN
        assertThat(resultat).isNull();

    }

    @Test
    public void rechercherParCodeOuDesignation(){
        //GIVEN
       String filtre = "filtre";
        Departement departement = new Departement();
        departement.setId(1L);
        departement.setCode("code");
        departement.setDesignation("designation");

        when(departementRepository.findByCodeIsContainingOrDesignationIsContaining(filtre,filtre)).thenReturn(List.of(departement));
        //WHEN
        List<DepartementVO> resultat = departementService.rechercherParCodeOuDesignation(filtre);


        //THEN
        assertThat(resultat)
                .isNotEmpty()
                .extracting(
                        DepartementVO::getId,
                        DepartementVO::getCode,
                        DepartementVO::getDesignation

                )
                .containsOnly(
                        tuple(1L, "code", "designation")
                );

    }

    @Test
    public void deleteDepartement(){
        //GIVEN
        long id = 1L;
        //WHEN
        departementService.deleteDepartement(id);
        //THEN
        verify(departementRepository,Mockito.times(1)).deleteById(id);

    }


}
