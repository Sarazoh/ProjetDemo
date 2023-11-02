package com.example.demo.model;

import jakarta.persistence.*;

import java.lang.reflect.Type;

@Entity
@Table(name = Personne.TABLE_NAME)
public class Personne {

    public static final String TABLE_NAME = "personne";
    private static final String TABLE_SEQ = "personne_id_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_SEQ)
    @SequenceGenerator(name = TABLE_SEQ, sequenceName = TABLE_SEQ)
    private Long id;

    private String nom;
    private String prenoms;
    private Integer age;
    private String login;

    @ManyToOne()
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;
    
    public Personne() {

    }

    public Personne(String nom, String prenoms, Integer age, String login, Departement departement){
        this.nom = nom;
        this.prenoms = prenoms;
        this.age=age;
        this.login=login;
        this.departement = departement;
    }

    public void mettreAJour(String nom, String prenoms, Integer age, String login, Departement departement){
        this.nom = nom;
        this.prenoms = prenoms;
        this.age=age;
        this.login=login;
        this.departement=departement;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
