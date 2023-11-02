import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './modules/general/home/home.component';
import { PersonneComponent } from './modules/general/personne/personne.component';
import { BoutiqueComponent } from './modules/general/boutique/boutique.component';
import {TabMenuModule} from "primeng/tabmenu";
import {MenubarModule} from "primeng/menubar";
import {TableModule} from "primeng/table";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ApiUrlInterceptor} from "./interceptors/api-url-interceptor";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ToastModule} from "primeng/toast";
import {ToolbarModule} from "primeng/toolbar";
import { PersonneModalComponent } from './modules/general/personne/personne-modal/personne-modal.component';
import {DialogModule} from "primeng/dialog";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ButtonModule} from "primeng/button";
import {DividerModule} from "primeng/divider";
import {InputTextModule} from "primeng/inputtext";
import {NgOptimizedImage} from "@angular/common";
import {InputNumberModule} from "primeng/inputnumber";
import { DepartementComponent } from './modules/general/departement/departement.component';
import { DepartementModalComponent } from './modules/general/departement/departement-modal/departement-modal.component';
import {AutoCompleteModule} from "primeng/autocomplete";
import {TreeTableModule} from "primeng/treetable";
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PersonneComponent,
    BoutiqueComponent,
    PersonneModalComponent,
    DepartementComponent,
    DepartementModalComponent,
  ],
  imports: [
    TabMenuModule,
    BrowserModule,
    AppRoutingModule,
    MenubarModule,
    TableModule,
    HttpClientModule,
    FormsModule,
    ToastModule,
    ToolbarModule,
    DialogModule,
    BrowserAnimationsModule,
    ButtonModule,
    DividerModule,
    InputTextModule,
    NgOptimizedImage,
    InputNumberModule,
    ReactiveFormsModule,
    AutoCompleteModule,
    TreeTableModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: ApiUrlInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
