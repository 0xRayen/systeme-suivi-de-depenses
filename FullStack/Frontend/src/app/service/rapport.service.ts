import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RapportResume } from '../models/rapport.model';

@Injectable({
  providedIn: 'root',
})
export class RapportService {
  private readonly apiUrl = 'http://localhost:8080/api/rapports/resume';

  constructor(private http: HttpClient) {}

  getResume(): Observable<RapportResume> {
    return this.http.get<RapportResume>(this.apiUrl);
  }
}
