# Système de Gestion d'une Bibliothèque Municipale
**Projet Java — Fin de module | L2 GLSI — ESP / UCAD Dakar**

---

## 📋 Description

Application Java orientée objet simulant la gestion complète d'une bibliothèque municipale :
catalogue de documents, membres, emprunts, caisse, stock, véhicule de livraison, et capteurs de conservation.

---

## 🏗️ Architecture

```
bibliotheque-app/
├── src/main/java/
│   ├── app/
│   │   └── Main.java                    ← Point d'entrée + scénario complet
│   ├── model/
│   │   ├── Document.java                ← Classe abstraite (polymorphisme)
│   │   ├── Livre.java                   ← extends Document
│   │   ├── Revue.java                   ← extends Document
│   │   ├── Membre.java
│   │   ├── Emprunt.java                 ← Enum Statut, calcul automatique amende
│   │   ├── Stock.java
│   │   ├── Caisse.java                  ← Historique des transactions
│   │   ├── Vehicule.java                ← Enum Statut, livraisons inter-annexes
│   │   ├── CapteurConservation.java     ← Enum Alerte, seuils de conservation
│   │   └── Bibliotheque.java            ← Classe centrale (agrégation)
│   ├── dao/
│   │   └── DatabaseManager.java         ← Singleton SQLite, CRUD complet
│   └── service/
│       └── BibliothequeService.java     ← Logique métier, orchestration
├── pom.xml
└── README.md
```

---

## 🎯 Concepts OOP appliqués

| Concept          | Où dans le code                                          |
|------------------|----------------------------------------------------------|
| **Héritage**     | `Livre` et `Revue` héritent de `Document` (abstract)     |
| **Polymorphisme**| `getType()` méthode abstraite, overridée dans les sous-classes |
| **Encapsulation**| Attributs `private` partout, getters/setters validants   |
| **Abstraction**  | Classe `Document` abstraite                              |
| **Enum**         | `Emprunt.Statut`, `Vehicule.Statut`, `CapteurConservation.Alerte` |
| **Singleton**    | `DatabaseManager.getInstance()`                          |
| **Collections**  | `List<>`, `Optional<>`, Stream API                       |
| **Exceptions**   | Validation systématique + exceptions métier              |

---

## 🗄️ Base de données (SQLite)

La base `bibliotheque.db` est créée automatiquement au premier lancement.

**Tables :**
- `membres` — informations + amende courante
- `documents` — table polymorphique Livre/Revue
- `stocks` — exemplaires par document
- `emprunts` — avec date retour, statut, amende
- `caisse` + `caisse_transactions`
- `vehicules`
- `capteurs`

---

## 🚀 Compilation et exécution

### Avec Maven
```bash
cd bibliotheque-app
mvn clean package
java -jar target/bibliotheque-municipale-1.0.0.jar
```

### Avec IntelliJ IDEA
1. Ouvrir le dossier `bibliotheque-app` comme projet Maven
2. Laisser IntelliJ télécharger les dépendances
3. Exécuter `app.Main`

---

## 📋 Scénario simulé (Main.java)

1. ✅ Initialisation de la bibliothèque avec caisse de départ
2. 👥 Inscription de 3 membres + test email dupliqué
3. 📚 Ajout de 3 livres + 1 revue avec stocks
4. 📖 5 emprunts + test document indisponible
5. 📗 Retours dont un **en retard → calcul automatique d'amende**
6. 💳 Paiement d'amende + test emprunt refusé (amende impayée)
7. 💰 Historique de caisse + décaissement
8. 🚐 Livraison inter-annexes + maintenance véhicule
9. 📡 Capteurs : conditions normales → anomalies → alertes
10. 📊 État général final complet

---

## 📐 Diagramme UML

Voir le diagramme UML fourni séparément (`diagramme_classes_uml.png`).

---

*Auteur : L2 GLSI — ESP / UCAD — 2024-2025*
