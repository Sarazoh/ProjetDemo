package com.example.demo.service;

import com.example.demo.model.Departement;
import com.example.demo.model.Personne;
import com.example.demo.presentation.vo.DepartementVO;
import com.example.demo.presentation.vo.PersonneVO;
import com.example.demo.repository.DepartementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartementService {
    private final DepartementRepository departementRepository;
    public DepartementService (DepartementRepository departementRepository) {this.departementRepository = departementRepository;}

    public List<DepartementVO> getDepartements(){
        List<Departement> departements = departementRepository.findAll();
        return departements.stream().map(departement -> new DepartementVO(departement)).collect(Collectors.toList());
    }
    public DepartementVO getDepartement(long id){
        Optional<Departement> departementOptional=departementRepository.findById(id);
        if(departementOptional.isPresent()){
            Departement departement= departementOptional.get();
            return new DepartementVO(departement);
        }
        else
            return null ;
    }
    public DepartementVO getDepartementByCode(String code){
        Departement departement= departementRepository.findByCode(code);
        if (departement!=null){
            return new DepartementVO(departement);
        }
        else
            return null;
    }

    public DepartementVO createDepartement(DepartementVO departementVO){
        Departement departement1 = new Departement(departementVO.getCode(), departementVO.getDesignation());
        departementRepository.save(departement1);
        return new DepartementVO(departement1);
    }
    public DepartementVO updateDepartement(Long id,DepartementVO departementVO){
        Optional<Departement> departementOptional= departementRepository.findById(id);
        if (departementOptional.isPresent()){
            Departement departement1 = departementOptional.get();
            departement1.mettreAjour(departementVO.getCode(), departementVO.getDesignation());
            departementRepository.save(departement1);
            return new DepartementVO(departement1);
        }
        else
            return null;
    }

    public List<DepartementVO> rechercherParCodeOuDesignation(String filtre){
        List<Departement> departements = departementRepository.findByCodeIsContainingOrDesignationIsContaining(filtre, filtre);
        return departements.stream().map(DepartementVO::new).collect(Collectors.toList());
    }

    public void deleteDepartement(Long id){departementRepository.deleteById(id);}

}
