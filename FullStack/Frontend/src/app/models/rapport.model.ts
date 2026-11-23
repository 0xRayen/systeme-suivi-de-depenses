export interface CategorieStat {
  categorieId: number;
  categorieNom: string;
  total: number;
  nombreDepenses: number;
  pourcentage: number;
}

export interface MensuelStat {
  mois: string;
  total: number;
  nombreDepenses: number;
}

export interface RapportResume {
  totalGlobal: number;
  totalMoisCourant: number;
  nombreDepenses: number;
  moyenneDepense: number;
  moisCourant: string;
  parCategorie: CategorieStat[];
  parMois: MensuelStat[];
}
