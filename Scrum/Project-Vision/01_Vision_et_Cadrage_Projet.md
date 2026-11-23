# Phase 1 — Vision et Cadrage du Projet

**Projet** : Système de suivi de dépenses  
**Cadre académique** : ISET — 3ème année (Conception Orientée Objet et Méthodes Agiles / Développement FullStack)  
**Date d'initiation** : 23 novembre 2026  
**Échéance d'évaluation** : 24 novembre 2026  
**Réalisation** : Travail individuel (étudiant unique, assisté par Antigravity)

---

## 1. Vue d'ensemble du projet

Le **Système de suivi de dépenses** est une application web FullStack permettant à un utilisateur d'enregistrer ses dépenses quotidiennes, de les classer par catégories, de consulter et filtrer son historique et de visualiser la répartition et l'évolution de ses dépenses grâce à des tableaux et graphiques de suivi.

Le projet applique conjointement :
- **La démarche Agile Scrum** (artefacts, rôles, événements, gestion du backlog dans Jira) selon les supports du cours ;
- **L'architecture FullStack enseignée en cours** :
  - **Backend** : Spring Boot (API REST, architecture `Model / Repository / Service / Controller`, validation des données) ;
  - **Persistance** : MySQL avec Spring Data JPA et relation entre entités (`Categorie` 1 — * `Depense`) ;
  - **Frontend** : Angular (composants standalone, Bootstrap 5, services HTTP, CRUD complet et visualisation par tableaux et graphiques).

---

## 2. Problématique (méthode QQOQCCP — source : `chII_Mreference.pdf`)

Conformément à l'étape d'identification du problème du cours (*poser les questions : quoi, qui, depuis quand, combien, pourquoi, comment, où*), la problématique est formalisée ci-dessous :

| Question | Analyse appliquée au suivi des dépenses |
|---|---|
| **Quoi ?** | Absence d'un outil centralisé, simple et visuel pour enregistrer les dépenses quotidiennes et suivre la répartition du budget par catégorie et par mois. |
| **Qui ?** | Étudiants et particuliers souhaitant maîtriser leurs dépenses courantes sans utiliser de logiciels comptables complexes. |
| **Depuis quand / Fréquence ?** | Problème quotidien et récurrent à chaque achat (transport, alimentation, logement, loisirs, études) et lors du bilan de fin de mois. |
| **Où ?** | En usage personnel sur poste web (navigateur), face à des notes papier, reçus dispersés ou fichiers tableurs non structurés. |
| **Combien ?** | Risque de perte de visibilité sur plusieurs dizaines de petites transactions mensuelles et dépassement involontaire du budget mensuel. |
| **Comment ?** | En remplaçant la saisie éparse par une application web FullStack reliée à une base MySQL, offrant saisie rapide, filtrage et graphiques de synthèse. |
| **Pourquoi ?** | Donner à l'utilisateur une vision claire, immédiate et fiable de ses dépenses afin de mieux contrôler son budget et de prendre des décisions éclairées. |

---

## 3. Product Vision Board (modèle Roman Pichler — source : `scrum-PBL.pdf`, p. 18)

### Vision Statement (Énoncé de vision)
> Permettre à tout étudiant ou particulier de suivre facilement ses dépenses quotidiennes par catégorie et d'analyser ses habitudes financières en un coup d'œil grâce à une application web claire, fiable et structurée.

### Tableau de Vision Produit

| Groupe cible (*Target group*) | Besoins (*Needs*) | Produit (*Product* — 4 fonctionnalités clés) | Valeur (*Value*) |
|---|---|---|---|
| **Étudiants et particuliers** gérant un budget personnel au quotidien (ainsi que l'évaluateur académique validant le produit et la démarche). | Enregistrer rapidement chaque dépense avec sa date et sa catégorie ; corriger ou supprimer une saisie ; retrouver une dépense ; visualiser où part l'argent chaque mois. | 1. **Gestion des catégories** (CRUD + catégories initiales).<br>2. **Gestion des dépenses quotidiennes** (CRUD relié à une catégorie, validation des champs).<br>3. **Recherche et filtrage** (par catégorie, mois et mot-clé).<br>4. **Tableau de bord et rapports** (indicateurs clés, tableau récapitulatif, graphiques par catégorie et par mois). | Gain de temps sur le suivi quotidien, réduction des oublis, meilleure visibilité financière et démonstration concrète d'une chaîne Scrum + FullStack cohérente. |

---

## 4. Objectif Produit (*Product Goal* — source : `Scrum Guide 2020`)

> **Product Goal** : Livrer une application web FullStack fonctionnelle (*Angular + Spring Boot + MySQL/JPA*) permettant la gestion complète des catégories et des dépenses quotidiennes, la recherche multi-critères et la consultation de rapports visuels (totaux, répartition par catégorie et évolution mensuelle), tout en produisant l'ensemble des artefacts Scrum et du suivi Jira exigés pour l'évaluation du 24 novembre 2026.

---

## 5. Objectifs du projet

### Objectifs fonctionnels
1. Permettre la création, consultation, modification et suppression (CRUD) des **catégories** de dépenses.
2. Permettre la création, consultation, modification et suppression (CRUD) des **dépenses** associées à une catégorie.
3. Offrir le **filtrage et la recherche** des dépenses (par catégorie, mois/période et texte).
4. Fournir un **tableau de bord et des rapports visuels** (total global, total du mois courant, nombre de dépenses, moyenne, répartition par catégorie et tendance mensuelle).

### Objectifs techniques (alignés avec le cours FullStack)
1. Structurer le backend Spring Boot selon les 4 couches enseignées : `Model (Entity)`, `Repository (Spring Data JPA)`, `Service`, `Controller (API REST)`.
2. Modéliser et persister dans **MySQL** une relation JPA claire entre deux entités : `Categorie` (`@OneToMany`) et `Depense` (`@ManyToOne`).
3. Implémenter la validation des entrées côté serveur et côté client ainsi que la gestion propre des erreurs HTTP.
4. Développer un frontend **Angular** modulaire et ergonomique intégrant des appels REST, des tableaux et des graphiques de visualisation.

### Objectifs méthodologiques (alignés avec le cours Scrum)
1. Appliquer le cadre Scrum de bout en bout (Vision, Product Backlog, Jira, Sprint Planning, Daily Scrum, Sprint Review, Sprint Retrospective, Definition of Done).
2. Maintenir une traçabilité réelle entre le Product Backlog, le tableau Jira, les tâches techniques, le code et les tests.

---

## 6. Périmètre du produit (*Product Scope*)

Le périmètre est volontairement maîtrisé pour garantir un **MVP (Minimum Viable Product) fonctionnel et testé** dans le délai imparti (23–24 novembre 2026).

### Inclus dans le MVP — Release 1 / Sprint 1 (Obligatoire)
- **Gestion des catégories (`Categorie`)** : liste, ajout, modification, suppression (avec protection/gestion propre si des dépenses y sont rattachées), validation du nom unique et non vide, données initiales de démarrage.
- **Gestion des dépenses (`Depense`)** : liste, ajout, modification, suppression ; champs : `titre` (libellé), `montant` (> 0), `dateDepense`, `categorie` (obligatoire), `description` (optionnelle).
- **Filtrage et recherche** : filtrer par catégorie, par mois/année ou période, et rechercher par mot-clé sur le libellé.
- **Rapports et visualisation** :
  - Cartes KPI : total des dépenses filtrées / du mois, nombre de transactions, dépense moyenne, catégorie principale ;
  - Tableau récapitulatif des totaux et pourcentages par catégorie ;
  - Graphiques dans Angular : répartition par catégorie (camembert / anneau) et évolution mensuelle (histogramme / barres).

### Hors périmètre Sprint 1 — Planifié pour Release 2 / Sprint 2 (`Could`)
- Authentification et gestion multi-utilisateurs (Login / JWT) — *décidé hors MVP lors de la Phase 0 afin de concentrer Sprint 1 sur la valeur métier et le reporting*.
- Définition de plafonds budgétaires mensuels par catégorie avec alertes visuelles.
- Export des rapports mensuels au format CSV ou PDF.

### Hors périmètre du projet (`Won't`)
- Synchronisation bancaire automatique (Open Banking).
- Gestion multi-devises avec taux de change temps réel.
- Application mobile native ou pipeline DevOps CI/CD Docker (exclu par décision de périmètre projet validée en Phase 0 afin de concentrer le délai sur Scrum, Jira et l'application FullStack — et non comme une dispense confirmée par l'enseignant).

---

## 7. Feuille de route Produit (*Agile Product Roadmap* — source : `scrum-PBL.pdf`, pp. 19–20)

Conformément au cours, la *Product Roadmap* décrit l'évolution haut niveau du produit par **Releases**, tandis que le *Product Backlog* détaille les User Stories à implémenter dans les Sprints.

| Release 1 — MVP (En cours : 23–24 nov. 2026) | Release 2 — Enrichissement (Planifiée) | Release 3 — Perspectives (Future) |
|---|---|---|
| • Architecture Spring Boot + MySQL + Angular<br>• CRUD Catégories & relation JPA `Categorie` / `Depense`<br>• CRUD Dépenses + validation<br>• Recherche et filtres multi-critères<br>• Tableau de bord & graphiques de suivi | • Authentification sécurisée (JWT / rôles)<br>• Budgets mensuels par catégorie et alertes de dépassement<br>• Export des rapports (CSV / PDF) | • Dépenses récurrentes automatiques<br>• Support multi-devises<br>• Notifications et statistiques annuelles avancées |

---

## 8. Contraintes et gestion des risques

### Contraintes du projet
1. **Contrainte temporelle critique** : évaluation fixée au **24 novembre 2026** (fenêtre d'exécution réelle du 23 au 24 novembre 2026).
2. **Ressource humaine unique** : réalisation assurée par un seul étudiant, obligeant à calibrer le Sprint 1 sur une capacité réaliste.
3. **Conformité stricte au cours** : respect de la terminologie Scrum enseignée, du formalisme `En tant que... / Je veux... / Afin de...` et `SI / LORSQUE / ALORS`, ainsi que de la pile FullStack étudiée en TP.
4. **Honnêteté académique** : aucune donnée, date, réunion d'équipe ou preuve de test ne doit être inventée ; toute compression ou simulation académique est explicitement signalée.

### Registre des risques

| ID | Risque identifié | Probabilité / Impact | Mesure d'atténuation |
|---|---|---|---|
| **R-01** | **Dérive du périmètre (*Scope Creep*)** : tenter d'ajouter l'authentification ou des options secondaires avant de finir le cœur métier. | Élevée / Critique | Priorisation stricte MoSCoW ; exclusion explicite du Login/JWT de Sprint 1 ; focalisation sur les items `Must` du MVP. |
| **R-02** | **Problème de configuration technique** (démarrage MySQL, versions Java/Spring Boot, CORS entre Angular et Spring Boot, librairie de graphiques). | Moyenne / Élevée | Vérifier la configuration de l'environnement et les versions déjà fonctionnelles sur le poste avant de coder ; configurer CORS dès l'initialisation backend. |
| **R-03** | **Désynchronisation entre Jira, le Backlog et le code réel**. | Moyenne / Élevée | Importer les User Stories validées dans Jira dès la Phase 3 et mettre à jour le statut de chaque ticket à chaque étape de la Phase 5. |
| **R-04** | **Manque de temps pour les tests, les captures et le rapport final**. | Élevée / Élevée | Réserver des créneaux dédiés aux tests réels (Phase 6), aux captures d'écran dans `Evidence/` au fil de l'eau et à la préparation de soutenance (Phases 8–9). |
