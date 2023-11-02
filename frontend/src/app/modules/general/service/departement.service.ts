import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Departement} from "../model/departement.model";

@Injectable({
  providedIn: 'root'
})
export class DepartementService {

  private url ='/ws/departements'

  constructor(private http: HttpClient) {
  }


  listerDepartement(): Observable<Departement[]> {
    return this.http.get<Departement[]>(this.url);
  }

  updateDepartement(id: number, personne: Departement): Observable<Departement> {
    const updateUrl = `${this.url}/${id}`;
    return this.http.put<Departement>(updateUrl, personne);
  }
  getDepartementById(id:number ) :Observable<Departement>{
    return this.http.get<Departement>(`${this.url}/${id}`);
  }

  enregistreDepartement(personne: Departement) : Observable<Departement>{
    return this.http.post<Departement>(`${this.url}`, personne)
  }

  modifieDepartement(id:number,personne: Departement): Observable<Departement>{
    return this.http.put<Departement>( `${this.url}/${id}`,personne)
  }
  departementSpprime(id:number): Observable<Departement>{
    return this.http.delete<Departement>(`${this.url}/${id}`) }

  rechercherParCodeOuDesignation(filtre: string): Observable<Departement[]> {
    return this.http.get<Departement[]>(`${this.url}/auto-compete/${filtre}`);
  }

}
