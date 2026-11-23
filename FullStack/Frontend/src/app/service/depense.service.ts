import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Depense, DepensePayload } from '../models/depense.model';

export interface DepenseFiltres {
  categorieId?: number | null;
  mois?: string;
  q?: string;
}

@Injectable({
  providedIn: 'root',
})
export class DepenseService {
  private readonly apiUrl = 'http://localhost:8080/api/depenses';

  constructor(private http: HttpClient) {}

  getAll(filtres?: DepenseFiltres): Observable<Depense[]> {
    let params = new HttpParams();
    if (filtres?.categorieId !== null && filtres?.categorieId !== undefined) {
      params = params.set('categorieId', String(filtres.categorieId));
    }
    if (filtres?.mois && filtres.mois.trim()) {
      params = params.set('mois', filtres.mois.trim());
    }
    if (filtres?.q && filtres.q.trim()) {
      params = params.set('q', filtres.q.trim());
    }
    return this.http.get<Depense[]>(this.apiUrl, { params });
  }

  getById(id: number): Observable<Depense> {
    return this.http.get<Depense>(`${this.apiUrl}/${id}`);
  }

  create(payload: DepensePayload): Observable<Depense> {
    return this.http.post<Depense>(this.apiUrl, payload);
  }

  update(id: number, payload: DepensePayload): Observable<Depense> {
    return this.http.put<Depense>(`${this.apiUrl}/${id}`, payload);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
