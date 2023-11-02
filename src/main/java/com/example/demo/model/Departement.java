package com.example.demo.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = Departement.TABLE_NAME)
public class Departement {
    public static final String TABLE_NAME = "departement";
    private  static final String TABLE_SEQ = "departement_id_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_SEQ)
    @SequenceGenerator(name = TABLE_SEQ, sequenceName = TABLE_SEQ)
    private Long id;
    private String code;
    private  String designation;


    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    @OneToMany(mappedBy = "departement")
    private List<Personne> personnes;

    public Departement(){

    }
    public  Departement(String code, String designation){
        this.code=code;
        this.designation = designation;
    }

    public void mettreAjour(String code, String designation){
        this.code=code;
        this.designation=designation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
