# Phase 1 — Parties Prenantes, Cadre Scrum et Adaptation des Rôles

**Projet** : Système de suivi de dépenses  
**Date** : 23 novembre 2026  
**Référentiel méthodologique** : *The Scrum Guide (2020)*, *Le Product Backlog (`scrum-PBL.pdf`)*, *Méthodologie Agile Scrum*, *TP Chaîne DevOps (§2–3)*, *L'avenir de l'Agilité et de Scrum à l'ère de l'IA*.

---

## 1. Parties prenantes (*Stakeholders*)

| Partie prenante | Rôle dans le projet | Attentes principales | Mode d'interaction |
|---|---|---|---|
| **Enseignant évaluateur** | Client académique et jury d'évaluation (représente le donneur d'ordre). | Application FullStack fonctionnelle et cohérente ; maîtrise de la démarche Scrum ; traçabilité réelle dans Jira ; documentation honnête et claire le 24 nov. 2026. | Évaluation finale, démonstration en direct, revue des livrables et réponses aux questions. |
| **Étudiant réalisateur** | Porteur unique du projet (assume les responsabilités Scrum et le développement). | Livrer un MVP complet et testé dans les temps tout en comprenant et appliquant concrètement Scrum et Jira. | Exécution quotidienne, suivi Jira, validation des incréments et des tests. |
| **Utilisateurs finaux cibles** | Étudiants et particuliers gérant leurs dépenses personnelles. | Interface simple pour saisir une dépense en quelques secondes, filtrer ses achats et comprendre ses dépenses via des graphiques. | Besoins modélisés via les *User Stories* et validés par des critères d'acceptation. |

---

## 2. Cadre Scrum retenu pour le projet

Conformément aux supports de cours, le projet s'appuie sur les fondements suivants :

### Piliers et Valeurs (`Scrum Guide 2020`)
- **3 Piliers empiriques** : **Transparence** (backlog, tableau Jira et code visibles à tout moment), **Inspection** (vérification continue du progrès, des tests et de l'incrément), **Adaptation** (ajustement rapide des priorités et correctifs en cas de blocage).
- **5 Valeurs Scrum** : **Engagement** (*Commitment*), **Focus**, **Ouverture** (*Openness*), **Respect**, **Courage**.

### Artefacts Scrum et Engagements associés
| Artefact | Engagement associé | Outil / Support dans le projet |
|---|---|---|
| **Product Backlog** | **Product Goal** | Document `Scrum/Product-Backlog/` + Backlog Jira (hiérarchie *Epic → User Story → Tâches*). |
| **Sprint Backlog** | **Sprint Goal** | Document `Scrum/Sprint-Planning/` + Sprint 1 actif et tableau Scrum dans Jira. |
| **Incrément** | **Definition of Done (DoD)** | Code Spring Boot + Angular compilé, testé, intégré et vérifié en local. |
| **Burndown Chart** *(mentionné dans `méthodologie agile scrum.pdf`)* | Suivi de l'avancement du Sprint | Généré et capturé depuis le tableau Scrum Jira au cours du Sprint 1. |

### Événements Scrum
1. **Le Sprint** : conteneur itératif (ici **Sprint 1 compressé**, planifié du 23 au 24 novembre 2026).
2. **Sprint Planning** : définition du *Sprint Goal*, sélection des User Stories prioritaires (`Must`) et décomposition en tâches techniques.
3. **Daily Scrum (Stand-up quotidien)** : point court d'inspection et d'adaptation (ce qui a été fait, ce qui suit, les blocages).
4. **Sprint Review** : inspection de l'incrément fonctionnel réellement livré face au *Sprint Goal* et mise à jour du Product Backlog.
5. **Sprint Retrospective** : bilan sur le processus, les outils (Jira, Spring Boot, Angular, IA) et pistes d'amélioration.

---

## 3. Répartition transparente des rôles Scrum (travail individuel)

Dans un contexte professionnel ou dans le TP collaboratif (4 à 5 personnes), chaque responsabilité est portée par une personne distincte. Comme ce projet est réalisé **par un seul étudiant**, les trois responsabilités du *Scrum Guide 2020* sont assumées **par l'étudiant en alternant explicitement les casquettes**, sans inventer d'équipiers fictifs :

| Responsabilité Scrum | Assumée par | Missions concrètes dans ce projet |
|---|---|---|
| **Product Owner (PO)** | **Étudiant** | Formule la vision et le *Product Goal* ; rédige, ordonne et priorise les User Stories (MoSCoW) ; définit les critères d'acceptation (`SI / LORSQUE / ALORS`) ; arbitre le périmètre MVP face à l'échéance du 24 nov. 2026 ; valide fonctionnellement les incréments. |
| **Scrum Master (SM)** | **Étudiant** *(accompagné méthodologiquement par Antigravity)* | Garantit le respect du cadre Scrum enseigné et de la *Definition of Done* ; structure les artefacts ; configure et suit le tableau Jira ; identifie et lève les blocages techniques ou organisationnels ; anime les points d'inspection/rétrospective. |
| **Développeur (Équipe de réalisation & QA)** | **Étudiant** *(assisté techniquement par Antigravity)* | Estime la complexité ; décompose les User Stories en tâches techniques (`Modèle, Repository, Service, Contrôleur, Angular, Tests`) ; développe le backend Spring Boot/MySQL et le frontend Angular ; exécute les tests unitaires, d'intégration et fonctionnels. |

### Rôle de l'assistant IA (Antigravity) — aligné avec `Avenir_de_l_Agilite_et_de_Scrum_a_l_ere_de_l_IA.txt`
Conformément au document de cours sur l'Agilité à l'ère de l'IA :
- **Antigravity** agit comme un **orchestrateur / assistant d'exécution et de coaching** (aide à la structuration des artefacts Scrum, à l'architecture, à l'écriture du code, aux tests et à la documentation).
- **L'étudiant** conserve l'entière **responsabilité humaine** : décisions de valeur produit, priorisation du backlog, manipulation directe de Jira, vérification des tests, compréhension de chaque choix technique et soutenance orale devant le professeur.

---

## 4. Transparence sur le calendrier compressé (23–24 novembre 2026)

Afin de respecter la rigueur académique et de ne jamais travestir la réalité du projet (état constaté le **23 novembre 2026**), nous distinguons strictement ce qui est **terminé**, ce qui est **en cours** et ce qui reste **planifié** :

| Statut réel (au 23 nov. 2026) | Nature | Éléments concernés dans ce projet |
|---|---|---|
| **Activités terminées (faites)** | Réalisées le **23 novembre 2026** | • **Phase 0** : Analyse des supports de cours et cadrage initial<br>• **Phase 1** : Rédaction de la Vision, du cadrage, des parties prenantes et de l'adaptation des rôles Scrum<br>• **Phase 2** : Élaboration et priorisation du Product Backlog (Markdown, Excel et CSV pour Jira) |
| **Activité en cours** | Exécutée le **23 novembre 2026** | • **Phase 3** : Inspection et configuration du projet Scrum dans Jira et préparation/import du backlog |
| **Activités planifiées — Sprint 1 (à venir, 23–24 nov. 2026)** | Planifiées pour le Sprint 1 compressé (**non encore exécutées**) | • **Phase 4** : Sprint Planning (définition du *Sprint Goal*, Sprint Backlog et *Definition of Done*)<br>• **Phase 5** : Développement de l'application FullStack (Spring Boot / MySQL + Angular)<br>• **Phase 6** : Campagne de tests réels et enregistrement des résultats observés<br>• **Phase 7** : Suivi Scrum en format académique compressé et solo (*Daily Scrum*, *Sprint Review* et *Sprint Retrospective*, documentés uniquement une fois réalisés)<br>• **Phases 8–9** : Rédaction du rapport final et préparation de l'évaluation du 24 novembre 2026 |
| **Éléments planifiés — Release 2 / Hors Sprint 1** | Conservés au Product Backlog (**non exécutés dans ce délai**) | • `US-07` (filtre mensuel du tableau de bord et export CSV), `US-08` (authentification JWT) et `US-09` (budgets mensuels et alertes) : planifiés en **Release 2 / Sprint 2** (`Could`)<br>• `US-10` (multi-devises et synchronisation bancaire) : exclue (`Won't`) |
