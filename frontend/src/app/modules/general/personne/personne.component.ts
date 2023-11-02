import {Component, OnInit} from '@angular/core';
import {Personne} from "../model/personne.model";
import {PersonneService} from "../service/personne.service";
import {MessageService} from "primeng/api";
@Component({
    selector: 'app-personne',
    templateUrl: './personne.component.html',
    styleUrls: ['./personne.component.scss']
})
export class PersonneComponent implements OnInit {

  personnes: Personne[] = [];
  personneSelectionne: Personne;



  editionPersonne: boolean = false;
  idPersonne?: number;



  constructor(private personneService: PersonneService) {
      this.personneSelectionne = new Personne();
  }

  ngOnInit() {
    this.listerPeronnes();
  }


  listerPeronnes(): void {
    this.personneService.listerPersones().subscribe(personnes => {
      this.personnes = personnes;
    })
  }

  openWindow(personne?: Personne) {
    this.editionPersonne = true;
    this.idPersonne = personne?.id;

  }

  onPersonneModifiee(): void {
    this.listerPeronnes();

  }

  personneSpprime(personne: Personne) {
    if (personne.id != null) {
      this.personneService.personneSpprime(personne.id).subscribe((personne) => {
        console.log("Personne supprimée avec succès");
        this.listerPeronnes();
      });
    }
  }


}


