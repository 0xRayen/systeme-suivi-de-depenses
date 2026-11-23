import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { HealthResponse, HealthService } from './service/health.service';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  apiState: 'checking' | 'up' | 'down' = 'checking';
  healthInfo: HealthResponse | null = null;

  constructor(private healthService: HealthService) {}

  ngOnInit(): void {
    this.verifierSanteApi();
  }

  verifierSanteApi(): void {
    this.apiState = 'checking';
    this.healthService.checkHealth().subscribe({
      next: (res) => {
        this.healthInfo = res;
        this.apiState = res.status === 'UP' ? 'up' : 'down';
      },
      error: () => {
        this.healthInfo = null;
        this.apiState = 'down';
      },
    });
  }
}
