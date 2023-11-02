import {Component, OnInit} from '@angular/core';
import {BoutiqueComponent} from "./modules/general/boutique/boutique.component";
import {MenuItem} from "primeng/api";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  items: MenuItem[] | undefined;
  title: BoutiqueComponent | undefined;
  activeItem: any;

  ngOnInit() {
    this.items = [
      { label: 'Home', icon: 'pi pi-fw pi-home', routerLink:'/home' },
      { label: 'Personne', icon: 'pi pi-fw pi-user', routerLink:'/personne' },
      { label: 'Departement', icon: 'pi pi-home', routerLink:'/departement' },
      { label: 'Boutique', icon: '', routerLink:'/boutique' },
    ];
  }

}

