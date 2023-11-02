import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonneModalComponent } from './personne-modal.component';
import { PersonneService } from "../../service/personne.service";
import {FormBuilder} from "@angular/forms";
import {DepartementService} from "../../service/departement.service";

describe('PersonneModalComponent', () => {
  let component: PersonneModalComponent;
  let fixture: ComponentFixture<PersonneModalComponent>;
  let personneService: PersonneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PersonneModalComponent],
      providers:[PersonneService, FormBuilder,DepartementService]
    });
    fixture = TestBed.createComponent(PersonneModalComponent);
    component = fixture.componentInstance;
    personneService= TestBed.get(personneService);
  });

  it('should set from values when- idPersonne is provided', () => {
    const personneData = {
      nom : 'zoh',
      prenoms: 'sara',
      age : 30,
      login: 'sara' ,
      departement : 'jj'
    };
    component.idPersonne = 1;
    component.onOuverture();

    expect(component.formPersonne.get('nom').value).toEqual(personneData.nom);
    expect(component.formPersonne.get('prenoms').value).toEqual(personneData.prenoms);
    expect(component.formPersonne.get('age').value).toEqual(personneData.age);
    expect(component.formPersonne.get('login').value).toEqual(personneData.login);
    expect(component.formPersonne.get('departement').value).toEqual(personneData.departement);
  });

  it('should patch null when idPersonne is not provided',()=>{
    component.idPersonne=null;
    component.onOuverture();
    expect(component.formPersonne.value).toEqual(null);
  });
});
