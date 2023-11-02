import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PersonneComponent } from './personne.component';
import {PersonneService} from "../service/personne.service";
import {of} from "rxjs";

describe('PersonneComponent', () => {
  let component: PersonneComponent;
  let fixture: ComponentFixture<PersonneComponent>;
  // let personneService : jasmine.SpyObj<PersonneService>;

  beforeEach(() => {
    // personneService = jasmine.createSpyObj('PersonneService',['listerPersonne'])
    TestBed.configureTestingModule({
      declarations: [PersonneComponent],
       providers:[
           {
             // provide: PersonneService, useValue: PersonneService
           }
       ]
    });
    fixture = TestBed.createComponent(PersonneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
    // const personnes = [];
    // personneService.listerPersones.and.returnValue(of(personnes));
    // component.listerPeronnes();
    // expect(component.personnes).toEqual(personnes);
  });
});
