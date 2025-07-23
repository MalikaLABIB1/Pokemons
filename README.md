# 🔥 Projet Java 2024–2025 : Pokémon Battle Engine

Bienvenue dans **Pokémon Battle Engine**, un mini-jeu en Java développé dans le cadre des TP Java de LPDIM. Ce projet, réparti en deux parties, propose d’abord un moteur de combat entre Pokémon, puis une version avancée inspirée des échecs sur un plateau 9x9.

---

## 📌 Objectifs pédagogiques

- Créer et structurer des classes Java.
- Manipuler des objets et appliquer les principes de l'encapsulation.
- Implémenter des combats avec logique de jeu et interactions.
- Développer un jeu en tour par tour avec affichage console (ou graphique selon amélioration).
- Charger des données depuis un fichier CSV (pokedex).

---

## 🧱 Structure du projet

### Partie 1 – Moteur de combat Pokémon
- Création de la classe `Type` : gestion des types, noms, et efficacité.
- Création de la classe `Pokemon` : attributs (numéro, nom, types, stats...), méthodes (get/set, attaque, etc.).
- Implémentation de la méthode `attaque` avec prise en compte de la vitesse et des types.
- Classe `Main` : création de deux Pokémon et exécution d’un combat jusqu’à la mort d’un des deux.

#### 🔁 Améliorations :
- Lecture automatique des statistiques depuis un fichier `pokedex.csv`.
- Simplification de l’instanciation.
- Prise en compte des types dans le calcul des dégâts.

---

### Partie 2 – Plateau de jeu Pokémon (style échecs)
- Plateau 9x9 avec positionnement initial des Pokémon.
- Jeu en tour par tour :
  - Déplacement sur une case adjacente.
  - Attaque si un ennemi est sur une case voisine.
- Condition de victoire : tuer le **Mewtwo** adverse.

#### 🧩 Bonus :
- Chargement du plateau depuis un fichier de configuration.
- Placement personnalisé des Pokémon en début de partie.

---

## 🖥️ Lancement du projet

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/votre-compte/pokemon-.git
   cd projet-java-pokemon-

