import {Departement} from "./departement.model";

export class Personne {
    id?: number;
    nom?: string;
    prenoms?: string;
    age?: number;
    login?: string;
    departement?:Departement;

    deserialize(input: any): Personne {
        Object.assign(this, input)

        return this;
    }

}
