# Phase 2 — Product Backlog

**Projet** : Système de suivi de dépenses  
**Date** : 23 novembre 2026  
**Échéance d'évaluation** : 24 novembre 2026  
**Référentiels appliqués** : *Scrum Guide 2020*, *Le Product Backlog (`scrum-PBL.pdf`)* (format User Story, critères `SI / LORSQUE / ALORS`, priorisation MoSCoW, principes INVEST & DEEP), *TP Chaîne DevOps §3* (décomposition en tâches techniques pour Jira).

---

## 1. En-tête et repères

### Rappel du Product Goal
> Livrer une application web FullStack fonctionnelle (*Angular + Spring Boot + MySQL/JPA*) permettant la gestion complète des catégories et des dépenses quotidiennes, la recherche multi-critères et la consultation de rapports visuels (totaux, répartition par catégorie et évolution mensuelle), tout en maintenant une traçabilité réelle dans Jira pour l'évaluation du 24 novembre 2026.

### Légende MoSCoW (source : `scrum-PBL.pdf`, p. 15)
- **Must** : indispensable pour que l'application soit fonctionnelle et évaluable (cœur du Sprint 1 MVP).
- **Should** : très utile pour le confort d'utilisation ; réalisé dans Sprint 1 uniquement si tous les *Must* sont terminés et validés.
- **Could** : optionnel / confort avancé ; planifié pour une itération ultérieure (Release 2).
- **Won't** : exclu du périmètre académique actuel (hors budget temps).

### Échelle d'estimation (Story Points — suite de Fibonacci)
- **1–2 pts** : tâche simple et rapide, peu d'incertitude technique.
- **3 pts** : fonctionnalité standard (backend + frontend + validation/tests).
- **5 pts** : fonctionnalité complète impliquant plusieurs composants, relations JPA ou agrégations graphiques.
- **8 pts** : fonctionnalité lourde ou complexe (évitée en Sprint 1 par découpage).

### Definition of Done (DoD) commune
Une User Story est considérée **Terminée (*Done*)** uniquement lorsque :
1. Le code backend et/ou frontend compile sans erreur.
2. Les données sont réellement persistées et relues dans **MySQL** (pour les stories avec base de données).
3. Le comportement est vérifié manuellement dans le navigateur (**Angular**) selon les critères `SI / LORSQUE / ALORS`.
4. Les tests unitaires **JUnit** associés à la logique métier s'exécutent avec succès.
5. Le ticket Jira correspondant (et ses sous-tâches) est passé à *Done*.

---

## 2. Liste des Epics

| ID Epic | Nom de l'Epic | Priorité | Cible | Objectif |
|---|---|---|---|---|
| **EP-01** | Initialisation et architecture FullStack | Must | Sprint 1 | Mettre en place le socle Spring Boot + MySQL + Angular opérationnel. |
| **EP-02** | Gestion des catégories et des dépenses (CRUD) | Must | Sprint 1 | Gérer les catégories, les dépenses liées en JPA et leur validation. |
| **EP-03** | Recherche, filtres et rapports visuels | Must | Sprint 1 | Retrouver facilement les dépenses et afficher les tableaux et graphiques de suivi. |
| **EP-04** | Évolutions planifiées — Release 2 | Could / Won't | Hors Sprint 1 | Conserver les évolutions futures (export CSV, authentification, budgets, multi-devises) dans le backlog. |

---

## 3. Vue d'ensemble priorisée du Product Backlog

| ID | Epic | Titre | MoSCoW | Priorité Jira | Story Points | Valeur métier | Cible |
|---|---|---|---|---|---:|---|---|
| **US-01** | EP-01 | Initialiser l'architecture Spring Boot, MySQL et Angular | Must | Highest | 3 | Élevée (socle) | Sprint 1 |
| **US-02** | EP-02 | Gérer les catégories de dépenses (CRUD) | Must | High | 3 | Élevée | Sprint 1 |
| **US-03** | EP-02 | Ajouter et consulter mes dépenses quotidiennes | Must | High | 5 | Critique | Sprint 1 |
| **US-04** | EP-02 | Modifier et supprimer une dépense | Must | High | 3 | Élevée | Sprint 1 |
| **US-05** | EP-03 | Filtrer et rechercher mes dépenses | Must | Medium | 3 | Élevée | Sprint 1 |
| **US-06** | EP-03 | Consulter le tableau de bord et les rapports graphiques | Must | High | 5 | Critique | Sprint 1 |
| **US-07** | EP-04 | Filtrer le tableau de bord par mois et exporter en CSV | Could | Low | 2 | Moyenne | Release 2 |
| **US-08** | EP-04 | Authentification utilisateur (Login / JWT) | Could | Low | 5 | Moyenne | Release 2 |
| **US-09** | EP-04 | Définir un budget mensuel par catégorie avec alerte | Could | Low | 3 | Moyenne | Release 2 |
| **US-10** | EP-04 | Gestion multi-devises et synchronisation bancaire | Won't | Lowest | 8 | Faible (hors délai) | Hors périmètre |

**Bilan de charge :**
- **Sprint 1 obligatoire (`Must` : `US-01` à `US-06`)** : **22 Story Points** (19 sous-tâches techniques).
- **Hors Sprint 1 — Release 2 (`Could` : `US-07` à `US-09`)** : **10 Story Points** (aligné avec la Vision Produit : export CSV, authentification JWT et budgets mensuels différés en Release 2 ; 2 sous-tâches préparatoires `T-07.1`–`T-07.2` documentées pour `US-07`).
- **Exclu du périmètre (`Won't` : `US-10`)** : **8 Story Points** (conservé au Product Backlog selon le principe *DEEP* sans alourdir le sprint actuel).

---

## 4. Partie A — Fonctionnalités essentielles (Sprint 1)

### US-01 — Initialiser l'architecture Spring Boot, MySQL et Angular
- **Epic** : EP-01 — Initialisation et architecture FullStack
- **User Story** : *En tant que* développeur, *je veux* disposer d'un backend Spring Boot connecté à MySQL et d'un frontend Angular relié à l'API REST, *afin de* bâtir toutes les fonctionnalités sur une base stable.
- **Valeur métier** : Socle technique indispensable permettant d'intégrer et de tester chaque incrément dès le premier jour.
- **Priorité** : **Must** (Jira : `Highest`) | **Estimation** : **3 Story Points** | **Dépendances** : Aucune

**Critères d'acceptation :**

| SI | LORSQUE | ALORS |
|---|---|---|
| Le serveur MySQL est démarré et la base de données est configurée | L'application Spring Boot démarre sur le port `8080` et on appelle `GET /api/health` | La connexion MySQL s'établit sans erreur et l'endpoint répond `200 OK` avec un statut JSON valide |
| Le backend Spring Boot est actif sur `8080` | L'utilisateur ouvre l'application Angular sur `http://localhost:4200` | L'interface affiche la barre de navigation principale et vérifie l'accès à `/api/health` sans erreur CORS |

**Tâches techniques :**
- `T-01.1` : Créer le projet Spring Boot (Spring Web, JPA, Validation, MySQL Driver), configurer `application.properties` et le filtre CORS — **0,5h**
- `T-01.2` : Implémenter le contrôleur de santé `GET /api/health` — **0,25h**
- `T-01.3` : Initialiser le projet Angular avec Bootstrap 5, la barre de navigation et le routage de base — **0,75h**

**Points DoD spécifiques** : Démarrage simultané Spring Boot (`8080`) + Angular (`4200`) vérifié ; base MySQL créée automatiquement.

---

### US-02 — Gérer les catégories de dépenses (CRUD)
- **Epic** : EP-02 — Gestion des catégories et des dépenses (CRUD)
- **User Story** : *En tant que* utilisateur, *je veux* créer, consulter, modifier et supprimer des catégories de dépenses, *afin de* classer mes achats quotidiens par thème.
- **Valeur métier** : Structure l'ensemble des dépenses et rend possible la relation JPA `Categorie (1) — (*) Depense` ainsi que le reporting par thème.
- **Priorité** : **Must** (Jira : `High`) | **Estimation** : **3 Story Points** | **Dépendances** : `US-01`

**Critères d'acceptation :**

| SI | LORSQUE | ALORS |
|---|---|---|
| La table des catégories est vide au démarrage | L'application démarre pour la première fois | 6 catégories de départ (*Alimentation, Transport, Logement, Loisirs, Études, Autre*) sont créées et affichées |
| L'utilisateur saisit un nom de catégorie valide et non existant | Il soumet le formulaire d'ajout ou de modification | La catégorie est enregistrée en base MySQL et la liste est mise à jour immédiatement |
| Le nom saisi est vide ou existe déjà | L'utilisateur tente d'enregistrer la catégorie | L'API retourne une erreur explicite (`400` ou `409`) et un message clair s'affiche sans enregistrer |
| Une catégorie est associée à au moins une dépense | L'utilisateur demande sa suppression | La suppression est refusée proprement (`409 Conflict`) avec un message explicatif pour préserver l'intégrité des données |

**Tâches techniques :**
- `T-02.1` : Créer l'entité JPA `Categorie`, `CategorieRepository` et le composant `DataInitializer` — **0,5h**
- `T-02.2` : Développer `CategorieService` et `CategorieController` (`/api/categories`) avec validations — **0,75h**
- `T-02.3` : Créer le service Angular `CategorieService` et l'écran CRUD des catégories — **1h**
- `T-02.4` : Écrire et exécuter les tests unitaires JUnit sur `CategorieService` — **0,5h**

**Points DoD spécifiques** : CRUD complet vérifié en navigateur + MySQL ; tests JUnit verts sur unicité et refus de suppression si liée.

---

### US-03 — Ajouter et consulter mes dépenses quotidiennes
- **Epic** : EP-02 — Gestion des catégories et des dépenses (CRUD)
- **User Story** : *En tant que* utilisateur, *je veux* enregistrer une dépense (titre, montant, date, catégorie, description optionnelle) et consulter ma liste de dépenses, *afin de* suivre mes achats au jour le jour.
- **Valeur métier** : Fonctionnalité centrale du produit permettant la saisie et la consultation quotidiennes.
- **Priorité** : **Must** (Jira : `High`) | **Estimation** : **5 Story Points** | **Dépendances** : `US-02`

**Critères d'acceptation :**

| SI | LORSQUE | ALORS |
|---|---|---|
| Tous les champs obligatoires sont valides (`titre` non vide, `montant > 0`, `dateDepense`, `categorieId` existant) | L'utilisateur soumet le formulaire d'ajout | `POST /api/depenses` renvoie `201 Created`, la dépense est sauvegardée dans MySQL avec sa catégorie et apparaît dans la liste |
| Un champ obligatoire manque ou `montant <= 0` | L'utilisateur soumet le formulaire | L'API rejette la requête (`400 Bad Request`) et les erreurs de validation s'affichent clairement |
| Des dépenses existent en base | L'utilisateur ouvre la page « Dépenses » | Le tableau affiche toutes les dépenses triées par date décroissante avec le nom de leur catégorie |

**Tâches techniques :**
- `T-03.1` : Créer l'entité JPA `Depense` (relation `@ManyToOne` vers `Categorie`) et `DepenseRepository` — **0,5h**
- `T-03.2` : Développer `DepenseService`, `DepenseController` (`GET`, `POST`) et la gestion globale des erreurs REST — **1h**
- `T-03.3` : Créer `DepenseService` côté Angular, la liste des dépenses et le formulaire d'ajout — **1,25h**
- `T-03.4` : Écrire et exécuter les tests JUnit sur la création valide et les validations de `DepenseService` — **0,5h**

**Points DoD spécifiques** : Persistance vérifiée après rechargement de la page ; clé étrangère `categorie_id` vérifiée en base MySQL.

---

### US-04 — Modifier et supprimer une dépense
- **Epic** : EP-02 — Gestion des catégories et des dépenses (CRUD)
- **User Story** : *En tant que* utilisateur, *je veux* modifier une dépense existante ou la supprimer, *afin de* corriger une erreur de saisie et garder mon suivi exact.
- **Valeur métier** : Finalise le cycle CRUD complet exigé pour l'entité principale `Depense`.
- **Priorité** : **Must** (Jira : `High`) | **Estimation** : **3 Story Points** | **Dépendances** : `US-03`

**Critères d'acceptation :**

| SI | LORSQUE | ALORS |
|---|---|---|
| Une dépense existe dans la liste | L'utilisateur clique sur « Modifier », change ses champs valides et enregistre | `PUT /api/depenses/{id}` renvoie `200 OK`, la modification est persistée dans MySQL et le tableau est actualisé |
| Une dépense existe dans la liste | L'utilisateur clique sur « Supprimer » et confirme l'action | `DELETE /api/depenses/{id}` supprime l'enregistrement en base et retire la ligne du tableau |
| L'identifiant demandé n'existe pas | Une requête `GET`, `PUT` ou `DELETE` est envoyée avec cet ID | L'API retourne proprement le statut `404 Not Found` avec un message explicite |

**Tâches techniques :**
- `T-04.1` : Ajouter les endpoints backend `GET /{id}`, `PUT /{id}`, `DELETE /{id}` et la gestion `404` dans `DepenseService` / `DepenseController` — **0,5h**
- `T-04.2` : Implémenter le mode édition et la confirmation de suppression dans l'interface Angular — **0,75h**
- `T-04.3` : Écrire et exécuter les tests JUnit sur la mise à jour, la suppression et le cas `404` — **0,25h**

**Points DoD spécifiques** : Modification et suppression persistées en base et reflétées immédiatement à l'écran.

---

### US-05 — Filtrer et rechercher mes dépenses
- **Epic** : EP-03 — Recherche, filtres et rapports visuels
- **User Story** : *En tant que* utilisateur, *je veux* filtrer mes dépenses par catégorie, par mois et par mot-clé, *afin de* retrouver rapidement un achat ou analyser une période précise.
- **Valeur métier** : Transforme la liste brute en un outil de suivi pratique au quotidien.
- **Priorité** : **Must** (Jira : `Medium`) | **Estimation** : **3 Story Points** | **Dépendances** : `US-03`

**Critères d'acceptation :**

| SI | LORSQUE | ALORS |
|---|---|---|
| Plusieurs dépenses de catégories et mois différents existent | L'utilisateur sélectionne une catégorie et/ou un mois (`AAAA-MM`) | Seules les dépenses correspondantes et leur montant total filtré s'affichent |
| Des dépenses existent en base | L'utilisateur saisit un mot-clé dans le champ de recherche | La liste est filtrée de manière insensible à la casse sur le titre de la dépense |
| Un ou plusieurs filtres sont actifs | L'utilisateur clique sur « Réinitialiser » | Tous les filtres sont vidés et l'ensemble des dépenses réapparaît |

**Tâches techniques :**
- `T-05.1` : Implémenter les requêtes de filtrage dans `DepenseRepository`, `DepenseService` et `GET /api/depenses?categorieId=&mois=&q=` — **0,75h**
- `T-05.2` : Ajouter la barre de filtres (catégorie, mois, mot-clé, réinitialisation) et le total filtré dans Angular — **0,75h**

**Points DoD spécifiques** : Combinaison de filtres vérifiée côté API et dans l'interface Angular sans erreur sur résultat vide.

---

### US-06 — Consulter le tableau de bord et les rapports graphiques
- **Epic** : EP-03 — Recherche, filtres et rapports visuels
- **User Story** : *En tant que* utilisateur, *je veux* visualiser un tableau de bord contenant les indicateurs clés, un tableau récapitulatif et des graphiques (par catégorie et par mois), *afin de* comprendre clairement la répartition de mes dépenses.
- **Valeur métier** : Répond directement à l'exigence centrale du projet : *visualisation des données par graphiques/tableaux et rapports de suivi*.
- **Priorité** : **Must** (Jira : `High`) | **Estimation** : **5 Story Points** | **Dépendances** : `US-03`

**Critères d'acceptation :**

| SI | LORSQUE | ALORS |
|---|---|---|
| Des dépenses sont enregistrées en base | L'utilisateur accède au Tableau de bord | Les cartes KPI affichent le total global, le total du mois courant, le nombre de dépenses et la dépense moyenne |
| Des dépenses sont réparties sur plusieurs catégories | La page du tableau de bord est chargée | Un tableau récapitulatif (montant et %) et un graphique de répartition par catégorie s'affichent avec des valeurs exactes |
| Des dépenses existent sur un ou plusieurs mois | La page du tableau de bord est chargée | Un graphique en barres présente l'évolution mensuelle des dépenses |
| Aucune dépense n'existe en base | L'utilisateur ouvre le Tableau de bord | Les indicateurs affichent `0` et un message informatif propre apparaît sans erreur console ni plantage |

**Tâches techniques :**
- `T-06.1` : Créer les DTOs de synthèse, `RapportService` et `RapportController` (`GET /api/rapports/resume`) — **1h**
- `T-06.2` : Créer `RapportService` côté Angular et le composant Tableau de bord (cartes KPI, tableau récapitulatif, graphiques par catégorie et par mois) — **1,5h**
- `T-06.3` : Écrire et exécuter les tests JUnit sur les calculs d'agrégation et le cas base vide — **0,5h**

**Points DoD spécifiques** : Cohérence exacte vérifiée entre les totaux calculés en base MySQL, le tableau et les graphiques affichés dans Angular.

---

## 5. Partie B — Fonctionnalités optionnelles (Release 2) et hors périmètre Sprint 1 (`Could` / `Won't`)

Conformément au document de **Vision Produit** et au principe **DEEP** (*Detailed Appropriately* — `scrum-PBL.pdf`, p. 14), ces items ne font pas partie du Sprint 1 :

| ID | Epic | User Story | MoSCoW | Estimation | Statut / Décision |
|---|---|---|---|---:|---|
| **US-07** | EP-04 | *En tant qu'* utilisateur, *je veux* filtrer le tableau de bord sur un mois donné et télécharger mes dépenses au format CSV, *afin de* conserver une copie mensuelle de mon suivi. | Could | 2 pts | **Planifiée pour Release 2 / Sprint 2** (alignée avec le document de Vision ; hors Sprint 1). |
| **US-08** | EP-04 | *En tant qu'* utilisateur, *je veux* me connecter avec un compte sécurisé (JWT), *afin de* protéger l'accès à mes dépenses personnelles. | Could | 5 pts | Planifiée pour **Release 2 / Sprint 2** (hors MVP Sprint 1 pour garantir la livraison dans les délais). |
| **US-09** | EP-04 | *En tant qu'* utilisateur, *je veux* fixer un plafond budgétaire mensuel par catégorie et voir une alerte en cas de dépassement, *afin de* mieux maîtriser mon budget. | Could | 3 pts | Planifiée pour **Release 2 / Sprint 2**. |
| **US-10** | EP-04 | *En tant qu'* utilisateur, *je veux* gérer plusieurs devises et synchroniser mon compte bancaire, *afin de* automatiser le suivi international. | Won't | 8 pts | **Exclue** du périmètre académique (complexité injustifiée par rapport au cours et à l'échéance). |

### US-07 — Filtrer le tableau de bord par mois et exporter en CSV *(Release 2 — hors Sprint 1)*
- **Epic** : EP-04 — Évolutions planifiées — Release 2
- **User Story** : *En tant que* utilisateur, *je veux* filtrer le tableau de bord sur un mois donné et télécharger mes dépenses au format CSV, *afin de* conserver une copie mensuelle de mon suivi.
- **Valeur métier** : Améliore l'exploitation des rapports après livraison du MVP.
- **Priorité** : **Could** (Jira : `Low`) | **Estimation** : **2 Story Points** | **Dépendances** : `US-06`

**Critères d'acceptation :**

| SI | LORSQUE | ALORS |
|---|---|---|
| Le tableau de bord est affiché | L'utilisateur choisit un mois spécifique | Les cartes KPI, le tableau et le graphique par catégorie se recalculent pour ce mois |
| Une liste de dépenses est affichée | L'utilisateur clique sur « Exporter CSV » | Un fichier `.csv` valide contenant les dépenses affichées est téléchargé |

**Tâches techniques préparées pour Release 2 (hors Sprint 1) :**
- `T-07.1` : Ajouter le paramètre optionnel `mois` sur `GET /api/rapports/resume` — **0,25h**
- `T-07.2` : Ajouter le sélecteur de mois sur le tableau de bord et le bouton d'export CSV dans Angular — **0,5h**

---

## 6. Ordre exact d'exécution recommandé (Sprint 1 — MVP)

Pour avancer de manière ordonnée sans se disperser :

1. **Étape 1 (`US-01`)** : Mettre en place le socle Spring Boot + MySQL + Angular + test de santé `/api/health`.
2. **Étape 2 (`US-02`)** : Implémenter l'entité `Categorie`, son API REST, les catégories de démarrage, les tests JUnit et l'écran Angular.
3. **Étape 3 (`US-03`)** : Implémenter l'entité `Depense` (liée à `Categorie`), l'ajout, la liste, les validations et les tests JUnit.
4. **Étape 4 (`US-04`)** : Ajouter la modification et la suppression d'une dépense (backend + frontend + tests).
5. **Étape 5 (`US-05`)** : Ajouter la recherche et les filtres (catégorie, mois, mot-clé) sur la liste des dépenses.
6. **Étape 6 (`US-06`)** : Construire le Tableau de bord et les rapports graphiques (KPI, tableau récapitulatif, graphique par catégorie, graphique mensuel) + tests JUnit, puis finaliser la campagne de tests (Phase 6) et la documentation finale (Phases 7–9).

*Note : Conformément au document de Vision, le filtre mensuel du tableau de bord et l'export CSV (`US-07`) sont reportés en **Release 2** afin de sécuriser la livraison du MVP (`US-01` à `US-06`) pour le 24 novembre 2026.*
