import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Categorie } from '../models/categorie.model';
import { CategorieService } from '../service/categorie.service';

@Component({
  selector: 'app-categories',
  imports: [CommonModule, FormsModule],
  templateUrl: './categories.html',
})
export class Categories implements OnInit {
  categories: Categorie[] = [];
  formModel: Categorie = { nom: '', description: '' };
  editingId: number | null = null;
  loading = false;
  saving = false;
  errorMessage = '';
  successMessage = '';

  constructor(private categorieService: CategorieService) {}

  ngOnInit(): void {
    this.chargerCategories();
  }

  chargerCategories(): void {
    this.loading = true;
    this.errorMessage = '';
    this.categorieService.getAll().subscribe({
      next: (data) => {
        this.categories = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = this.extraireErreur(
          err,
          'Impossible de charger les catégories depuis le serveur.'
        );
        this.loading = false;
      },
    });
  }

  enregistrer(): void {
    this.errorMessage = '';
    this.successMessage = '';
    const nom = this.formModel.nom.trim();
    if (!nom) {
      this.errorMessage = 'Le nom de la catégorie est obligatoire.';
      return;
    }

    const payload: Categorie = {
      nom,
      description: this.formModel.description?.trim() || undefined,
    };

    this.saving = true;
    const action$ =
      this.editingId !== null
        ? this.categorieService.update(this.editingId, payload)
        : this.categorieService.create(payload);

    action$.subscribe({
      next: () => {
        this.successMessage =
          this.editingId !== null
            ? 'Catégorie mise à jour avec succès.'
            : 'Catégorie ajoutée avec succès.';
        this.reinitialiserFormulaire();
        this.saving = false;
        this.chargerCategories();
      },
      error: (err) => {
        this.errorMessage = this.extraireErreur(err, 'Erreur lors de la sauvegarde.');
        this.saving = false;
      },
    });
  }

  commencerEdition(cat: Categorie): void {
    this.errorMessage = '';
    this.successMessage = '';
    this.editingId = cat.id ?? null;
    this.formModel = {
      nom: cat.nom,
      description: cat.description ?? '',
    };
  }

  annulerEdition(): void {
    this.errorMessage = '';
    this.reinitialiserFormulaire();
  }

  supprimer(cat: Categorie): void {
    if (!cat.id) {
      return;
    }
    if (!confirm(`Voulez-vous vraiment supprimer la catégorie « ${cat.nom} » ?`)) {
      return;
    }

    this.errorMessage = '';
    this.successMessage = '';
    this.categorieService.delete(cat.id).subscribe({
      next: () => {
        if (this.editingId === cat.id) {
          this.reinitialiserFormulaire();
        }
        this.successMessage = 'Catégorie supprimée avec succès.';
        this.chargerCategories();
      },
      error: (err) => {
        this.errorMessage = this.extraireErreur(
          err,
          'Impossible de supprimer cette catégorie.'
        );
      },
    });
  }

  private reinitialiserFormulaire(): void {
    this.editingId = null;
    this.formModel = { nom: '', description: '' };
  }

  private extraireErreur(err: unknown, fallback: string): string {
    const httpErr = err as { error?: { message?: string } };
    return httpErr?.error?.message || fallback;
  }
}
