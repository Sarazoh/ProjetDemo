package com.example.demo.presentation.vo;

import com.example.demo.model.Departement;
import jakarta.persistence.Column;

public class DepartementVO{
    private  Long id;

    private  String designation;

    private String code;

    public DepartementVO(){

    }
    public DepartementVO(Departement departement){
        this.id = departement.getId();
        this.code = departement.getCode();
        this.designation = departement.getDesignation();
    }

  public DepartementVO(String code, String designation){
        this.code =code;
        this.designation=designation;
  }

    public Long getId() {
        return id;
    }

    public String getDesignation() {
        return designation;
    }

    public String getCode() {
        return code;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setCode(String code) {
        this.code = code;
    }
}



