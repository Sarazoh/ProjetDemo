package com.example.demo.repository;

import com.example.demo.model.Departement;
import com.example.demo.presentation.vo.DepartementVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartementRepository extends JpaRepository<Departement,Long>{


    Departement findByCode(String code);
    Departement deleteByCode(String code);

    List<Departement> findByCodeIsContainingOrDesignationIsContaining(String filtreCode, String filtreDesignation);

}
