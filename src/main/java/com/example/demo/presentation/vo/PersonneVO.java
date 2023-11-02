package com.example.demo.presentation.vo;

import com.example.demo.model.Departement;
import com.example.demo.model.Personne;

public class PersonneVO {
    private Long id;
    private String nom;
    private String prenoms;
    private Integer age;
    private String login;

    private DepartementVO departement;

    public PersonneVO(){
    }

    public PersonneVO(Personne personne){
        this.id = personne.getId();
        this.nom = personne.getNom();
        this.prenoms = personne.getPrenoms();
        this.age = personne.getAge();
        this.login= personne.getLogin();
        this.departement= new DepartementVO(personne.getDepartement());

    }

    public PersonneVO(String nom, String prenoms, Integer age, String login){
        this.nom=nom;
        this.prenoms=prenoms;
        this.age=age;
        this.login=login;


    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public Integer getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public DepartementVO getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementVO departement) {
        this.departement = departement;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

