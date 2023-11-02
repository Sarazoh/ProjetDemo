import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Personne} from "../model/personne.model";

@Injectable({
    providedIn: 'root'
})
export class PersonneService {

    private url ='/ws/personnes'

    constructor(private http: HttpClient) {
    }


    listerPersones(): Observable<Personne[]> {
        return this.http.get<Personne[]>(this.url);
    }

    updatePersonne(id: number, personne: Personne): Observable<Personne> {
        const updateUrl = `${this.url}/${id}`;
        return this.http.put<Personne>(updateUrl, personne);
    }
    getPersonneById(id:number ) :Observable<Personne>{
        return this.http.get<Personne>(`${this.url}/${id}`);
    }

    enregistrePersonne(personne: Personne) : Observable<Personne>{
        return this.http.post<Personne>(`${this.url}`, personne)
    }

    modifiePersonne(id:number,personne: Personne): Observable<Personne>{
        return this.http.put<Personne>( `${this.url}/${id}`,personne)
    }
  personneSpprime(id:number): Observable<Personne>{
      return this.http.delete<Personne>(`${this.url}/${id}`) }

}
