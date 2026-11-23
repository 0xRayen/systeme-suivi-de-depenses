import { Categorie } from './categorie.model';

export interface Depense {
  id: number;
  titre: string;
  montant: number;
  date: string;
  description?: string | null;
  categorie: Categorie;
}

export interface DepensePayload {
  titre: string;
  montant: number;
  date: string;
  categorieId: number;
  description?: string;
}
