# Système de suivi de dépenses

Application web FullStack de suivi des dépenses quotidiennes, réalisée dans le cadre du projet académique ISET (3ème année) avec application de la démarche **Agile Scrum**, suivi dans **Jira** (`SSD`) et intégration continue sous **GitHub Actions**.

## Architecture & Technologies
- **Frontend (`FullStack/Frontend/`)** : Angular 20 (composants standalone), Bootstrap 5, graphiques SVG (répartition par catégorie et évolution mensuelle)
- **Backend (`FullStack/Backend/`)** : Spring Boot 4.1.1 (Java 23), Spring WebMVC, Spring Data JPA, Bean Validation, architecture `Entity / Repository / Service / Controller / DTO`
- **Base de données** : MySQL (`suivi_depenses_db`) — relation `Categorie` (1) — `Depense` (*)
- **Tests & CI** : JUnit 5 + MockMvc (base H2 en mémoire en test) et workflow `.github/workflows/backend-ci.yml`

## Prérequis
- JDK 23 (`JAVA_HOME` pointant vers Java 23)
- Node.js & npm (Angular CLI 20)
- Serveur MySQL / MariaDB actif sur `localhost:3306`

## Configuration locale de la base de données (sans secrets dans Git)
Ne jamais commiter de mot de passe dans `application.properties`. Créez un fichier local ignoré par Git `FullStack/Backend/src/main/resources/application-local.properties` (ou définissez `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`) :

```properties
spring.datasource.username=root
spring.datasource.password=VOTRE_MOT_DE_PASSE
```

Au démarrage, la base `suivi_depenses_db` est créée si nécessaire et les 6 catégories initiales sont insérées si la table est vide : `Alimentation`, `Transport`, `Logement`, `Loisirs`, `Études`, `Autre`.

## Exécution des tests et du build

### Backend (JUnit 5 + Spring Boot Test)
```powershell
cd FullStack/Backend
$env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
.\mvnw.cmd test
```

### Frontend (Build Angular)
```powershell
cd FullStack/Frontend
npm install
npm run build
```

## Lancement de l'application complète
1. **Backend Spring Boot (`http://localhost:8080`)** :
   ```powershell
   cd FullStack/Backend
   $env:JAVA_HOME = 'C:\Program Files\Java\jdk-23'
   .\mvnw.cmd spring-boot:run
   ```
2. **Frontend Angular (`http://localhost:4200`)** :
   ```powershell
   cd FullStack/Frontend
   npm start
   ```

## Endpoints REST principaux
- `GET /api/health` — État de l'application et de la connexion MySQL
- `GET / POST / PUT / DELETE /api/categories` — Gestion CRUD des catégories (protection `409 Conflict` si doublon ou catégorie liée à des dépenses)
- `GET / POST / GET/{id} / PUT/{id} / DELETE/{id} /api/depenses` — Gestion CRUD des dépenses et filtrage (`?categorieId=&mois=AAAA-MM&q=`)
- `GET /api/rapports/resume` — Indicateurs KPI, répartition par catégorie et totaux mensuels

## Organisation du dépôt & Workflow Git
- `Scrum/` : Vision produit, parties prenantes, Product Backlog (`.docx`, `.pdf`, `.xlsx`, `.csv`) et suivi d'avancement
- `Evidence/` : Captures Jira (`SSD`), Pull Requests / CI GitHub et application
- Branches : `main` (stable), `develop` (intégration), `feature/<cle-jira>-<sujet>` fusionnées par Pull Request après passage de la CI
