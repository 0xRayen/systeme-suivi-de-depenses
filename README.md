# Système de suivi de dépenses

Application web FullStack de suivi des dépenses quotidiennes, réalisée dans le cadre du projet académique ISET (3ème année) avec application de la démarche **Agile Scrum** et suivi dans **Jira**.

## Technologies retenues
- **Frontend** : Angular 20, Bootstrap 5, visualisation par tableaux et graphiques
- **Backend** : Spring Boot, Spring Web, Spring Data JPA, Bean Validation
- **Base de données** : MySQL (relation `Categorie` 1 — * `Depense`)

## Organisation du dépôt
- `Scrum/` : Vision produit, parties prenantes & rôles Scrum, Product Backlog (Markdown, Word, PDF, Excel, CSV Jira), suivi d'avancement
- `FullStack/Backend/` : API REST Spring Boot *(initialisation prévue dans `US-01`)*
- `FullStack/Frontend/` : Application Angular *(initialisation prévue dans `US-01`)*
- `Course-Material/` : Index des supports de cours de référence
- `Evidence/` : Captures et preuves Jira, application et tests

## Stratégie de branches Git
- `main` : versions stables et validées
- `develop` : branche d'intégration des fonctionnalités
- `feature/<nom>` : branches de développement par User Story (fusionnées vers `develop` via Pull Request)
