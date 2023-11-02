import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {DepartementService} from "../../service/departement.service";
import {Departement} from "../../model/departement.model";

@Component({
  selector: 'app-departement-modal',
  templateUrl: './departement-modal.component.html',
  styleUrls: ['./departement-modal.component.scss']
})
export class DepartementModalComponent implements OnInit{

  @Input()
  visible: boolean = false;

  @Output()
  visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  @Input()
  idDepartement: number

  @Output()
  departementModifie: EventEmitter<void> = new EventEmitter<void>();


  formDepartement: FormGroup;

  value?: Text;

  constructor(private formBuilder: FormBuilder, private departementService: DepartementService) {
  }

  ngOnInit() {
    this.formDepartement = this.formBuilder.group({
      code: [null],
      designation: [null],
    })
  }

  onOuverture(): void {
    if (this.idDepartement) {
      this.departementService.getDepartementById(this.idDepartement).subscribe(departement => {
        this.formDepartement.patchValue(departement);
      })
    }else {
      this.formDepartement.patchValue(null);
    }
  }


  fermerModale(): void {
    this.visibleChange.emit(false);
    this.formDepartement.reset();
  }

  enregisterDepartement(){
    const departementAEnregistrer = new Departement().deserialize(this.formDepartement.getRawValue())
    if (this.idDepartement != null) {
      this.departementService.modifieDepartement(this.idDepartement, departementAEnregistrer).subscribe(personneModifie => {
        this.departementModifie.emit();
        this.fermerModale();
      })
    }
    else {
      this.departementService.enregistreDepartement(departementAEnregistrer).subscribe(departement => {
        this.departementModifie.emit();
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

  protected readonly Departement = Departement;
}
