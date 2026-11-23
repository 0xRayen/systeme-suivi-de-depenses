import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface HealthResponse {
  application: string;
  timestamp: string;
  database: string;
  status: string;
}

@Injectable({
  providedIn: 'root',
})
export class HealthService {
  private readonly apiUrl = 'http://localhost:8080/api/health';

  constructor(private http: HttpClient) {}

  checkHealth(): Observable<HealthResponse> {
    return this.http.get<HealthResponse>(this.apiUrl);
  }
}
