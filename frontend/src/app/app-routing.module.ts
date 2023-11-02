import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './modules/general/home/home.component';
import { PersonneComponent } from './modules/general/personne/personne.component';
import { BoutiqueComponent } from './modules/general/boutique/boutique.component';
import {DepartementComponent} from "./modules/general/departement/departement.component";

const routes: Routes = [
  { path: 'home', component: HomeComponent, },
  { path: 'personne', component: PersonneComponent },
  { path: 'boutique', component: BoutiqueComponent },
  { path: 'departement', component: DepartementComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
