import { Component, OnInit } from '@angular/core';
import { Departement } from "../model/departement.model";
import { DepartementService } from "../service/departement.service";
import {Personne} from "../model/personne.model";

@Component({
  selector: 'app-departement',
  templateUrl: './departement.component.html',
  styleUrls: ['./departement.component.scss']
})
export class DepartementComponent implements OnInit {
  departements: Departement[] = [];
  departementSelectionne: Departement =  new Departement() ;

    editionDepartement: boolean=false;
    idDepartement: number;



  constructor(private departementService: DepartementService) {
  }

  ngOnInit() {
    this.listeDepartement();
  }

  listeDepartement(): void {
    this.departementService.listerDepartement().subscribe((departements: Departement[]) => {
      this.departements = departements;
    })
  }
  ouvreFenetre(departement?: Departement): void{
    this.editionDepartement = true;
    this.idDepartement = departement.id;

  }
  departementModifie(): void {
    this.listeDepartement();

  }

  departementSpprime(departement: Departement) {
    if (departement.id != null) {
      this.departementService.departementSpprime(departement.id).subscribe((departement) => {
        console.log("Personne supprimée avec succès");
        this.listeDepartement();
      });
    }
  }


}
