import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CategorieStat, MensuelStat, RapportResume } from '../models/rapport.model';
import { RapportService } from '../service/rapport.service';

export interface DonutSegment extends CategorieStat {
  color: string;
  dashArray: string;
  dashOffset: string;
}

export interface BarItem extends MensuelStat {
  x: number;
  y: number;
  width: number;
  height: number;
  labelX: number;
}

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard implements OnInit {
  private readonly palette = [
    '#0d6efd',
    '#198754',
    '#fd7e14',
    '#6f42c1',
    '#20c997',
    '#dc3545',
    '#0dcaf0',
    '#6c757d',
  ];

  readonly rayonDonut = 64;
  readonly circonference = 2 * Math.PI * this.rayonDonut;

  resume: RapportResume | null = null;
  segments: DonutSegment[] = [];
  barres: BarItem[] = [];
  loading = false;
  errorMessage = '';

  constructor(private rapportService: RapportService) {}

  ngOnInit(): void {
    this.chargerResume();
  }

  chargerResume(): void {
    this.loading = true;
    this.errorMessage = '';
    this.rapportService.getResume().subscribe({
      next: (data) => {
        this.resume = data;
        this.preparerGraphiques(data);
        this.loading = false;
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les données du tableau de bord.';
        this.loading = false;
      },
    });
  }

  couleurPourIndex(index: number): string {
    return this.palette[index % this.palette.length];
  }

  private preparerGraphiques(data: RapportResume): void {
    let cumul = 0;
    this.segments = data.parCategorie.map((cat, idx) => {
      const longueur = (cat.pourcentage / 100) * this.circonference;
      const segment: DonutSegment = {
        ...cat,
        color: this.couleurPourIndex(idx),
        dashArray: `${longueur.toFixed(2)} ${Math.max(0, this.circonference - longueur).toFixed(2)}`,
        dashOffset: (-cumul).toFixed(2),
      };
      cumul += longueur;
      return segment;
    });

    const moisList = data.parMois;
    if (moisList.length === 0) {
      this.barres = [];
      return;
    }

    const maxTotal = Math.max(...moisList.map((m) => Number(m.total)), 1);
    const svgWidth = 560;
    const baselineY = 185;
    const maxBarHeight = 140;
    const slotWidth = svgWidth / moisList.length;
    const barWidth = Math.min(54, Math.max(24, slotWidth * 0.55));

    this.barres = moisList.map((m, idx) => {
      const ratio = Number(m.total) / maxTotal;
      const height = Math.max(4, ratio * maxBarHeight);
      const center = idx * slotWidth + slotWidth / 2;
      return {
        ...m,
        x: center - barWidth / 2,
        y: baselineY - height,
        width: barWidth,
        height,
        labelX: center,
      };
    });
  }
}
