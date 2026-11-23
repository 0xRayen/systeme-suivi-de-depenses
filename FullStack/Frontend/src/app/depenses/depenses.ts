import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Categorie } from '../models/categorie.model';
import { Depense, DepensePayload } from '../models/depense.model';
import { CategorieService } from '../service/categorie.service';
import { DepenseService } from '../service/depense.service';

@Component({
  selector: 'app-depenses',
  imports: [CommonModule, FormsModule],
  templateUrl: './depenses.html',
})
export class Depenses implements OnInit {
  depenses: Depense[] = [];
  categories: Categorie[] = [];

  titre = '';
  montant: number | null = null;
  date = this.dateAujourdhui();
  categorieId: number | null = null;
  description = '';

  loading = false;
  saving = false;
  errorMessage = '';
  successMessage = '';

  constructor(
    private depenseService: DepenseService,
    private categorieService: CategorieService
  ) {}

  ngOnInit(): void {
    this.chargerDonneesInitiales();
  }

  chargerDonneesInitiales(): void {
    this.categorieService.getAll().subscribe({
      next: (cats) => {
        this.categories = cats;
        if (this.categorieId === null && cats.length > 0 && cats[0].id) {
          this.categorieId = cats[0].id;
        }
      },
      error: () => {
        this.errorMessage = 'Impossible de charger les catégories.';
      },
    });
    this.chargerDepenses();
  }

  chargerDepenses(): void {
    this.loading = true;
    this.depenseService.getAll().subscribe({
      next: (data) => {
        this.depenses = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = this.extraireErreur(err, 'Impossible de charger les dépenses.');
        this.loading = false;
      },
    });
  }

  enregistrer(): void {
    this.errorMessage = '';
    this.successMessage = '';

    const titreNettoye = this.titre.trim();
    if (!titreNettoye) {
      this.errorMessage = 'Le titre de la dépense est obligatoire.';
      return;
    }
    if (this.montant === null || this.montant <= 0) {
      this.errorMessage = 'Le montant doit être strictement positif.';
      return;
    }
    if (!this.date) {
      this.errorMessage = 'La date est obligatoire.';
      return;
    }
    if (!this.categorieId) {
      this.errorMessage = 'Veuillez choisir une catégorie.';
      return;
    }

    const payload: DepensePayload = {
      titre: titreNettoye,
      montant: Number(this.montant),
      date: this.date,
      categorieId: Number(this.categorieId),
      description: this.description.trim() || undefined,
    };

    this.saving = true;
    this.depenseService.create(payload).subscribe({
      next: () => {
        this.successMessage = 'Dépense enregistrée avec succès.';
        this.reinitialiserFormulaire();
        this.saving = false;
        this.chargerDepenses();
      },
      error: (err) => {
        this.errorMessage = this.extraireErreur(err, "Erreur lors de l'enregistrement.");
        this.saving = false;
      },
    });
  }

  get totalAffiche(): number {
    return this.depenses.reduce((acc, item) => acc + Number(item.montant), 0);
  }

  private reinitialiserFormulaire(): void {
    this.titre = '';
    this.montant = null;
    this.date = this.dateAujourdhui();
    this.description = '';
  }

  private dateAujourdhui(): string {
    return new Date().toISOString().slice(0, 10);
  }

  private extraireErreur(err: unknown, fallback: string): string {
    const httpErr = err as { error?: { message?: string } };
    return httpErr?.error?.message || fallback;
  }
}
