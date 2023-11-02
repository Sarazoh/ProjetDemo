import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {PersonneService} from "../../service/personne.service";
import {Personne} from "../../model/personne.model";
import {AutoCompleteCompleteEvent} from "primeng/autocomplete";
import {DepartementService} from "../../service/departement.service";
import {Departement} from "../../model/departement.model";

@Component({
  selector: 'app-personne-modal',
  templateUrl: './personne-modal.component.html',
  styleUrls: ['./personne-modal.component.scss']
})
export class PersonneModalComponent implements OnInit{

  @Input()
  visible: boolean = false;

  @Output()
  visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  @Input()
  idPersonne?: number

  @Output()
  personneModifie: EventEmitter<void> = new EventEmitter<void>();


  formPersonne?: FormGroup;

  value?: Text;

  items: any[] | undefined;

  selectedItem: any;

  suggestions: Departement[]= [];

  constructor(private formBuilder: FormBuilder, private personneService: PersonneService,
              private departementService: DepartementService) {
    this.formPersonne = this.formBuilder.group({
      nom: [null],
      prenoms: [null],
      age: [null],
      login: [null],
      departement:[null]
    })

  }

  ngOnInit() {

  }

  search(event: AutoCompleteCompleteEvent) {
    const filtre = event?.query;
    this.departementService.rechercherParCodeOuDesignation(filtre).subscribe(departements => {
      this.suggestions = departements;
    })
  }

  onOuverture(): void {
    if (this.idPersonne) {
      this.personneService.getPersonneById(this.idPersonne).subscribe(personne => {
        this.formPersonne.patchValue({
          nom: personne.nom,
          prenoms: personne.prenoms,
          age: personne.age,
          login: personne.login,
          departement: personne.departement
        });
      })
    }else {
      this.formPersonne.patchValue(null);
    }
  }


  fermerModale(): void {
    this.visibleChange.emit(false);
    this.formPersonne.reset();
  }

  enregisterPersonne(){
    const personneAEnregistrer = new Personne().deserialize(this.formPersonne.getRawValue())

    if (this.idPersonne != null) {
      this.personneService.modifiePersonne(this.idPersonne, personneAEnregistrer).subscribe(personneModifie => {
        this.personneModifie.emit();
        this.fermerModale();
      })
    }
    else {
      this.personneService.enregistrePersonne(personneAEnregistrer).subscribe(personne => {
        this.personneModifie.emit();
        this.fermerModale();
      })
    }

  }



@Component({
  selector: 'button-loading-demo',
  templateUrl: './button-loading-demo.html'
})
  loading: boolean = false;

  load() {
    this.loading = true;

    setTimeout(() => {
      this.loading = false
    }, 2000);
  }

  protected readonly Personne = Personne;
  colData: any;


}
