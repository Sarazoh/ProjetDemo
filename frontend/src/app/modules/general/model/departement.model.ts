
export class Departement {
  id?: number;
  code?: string;
  designation?: string;


  deserialize(input: any): Departement {
    Object.assign(this, input)

    return this;
  }

}
