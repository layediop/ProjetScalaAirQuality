
# Projet AirQualityFunctional

Ce projet Scala analyse les données de qualité de l’air (fichier `AirQualityUCI.csv`) pour extraire des statistiques sur la concentration de CO (CO_GT), puis génère un fichier CSV pour visualisation. Un script Python utilise ces données pour créer plusieurs graphiques (histogramme, scatter plot, boxplot).


## Contenu du projet

airqualityfunctional/
├── AirQualityUCI.csv       # Données qualité de l’air
├── build.sbt               # Configuration SBT Scala
├── co\_gt\_values.csv        # Fichier CSV généré pour visualisation
├── launch.sh               # Script bash pour lancer Scala + Python
├── plot\_histogram.py       # Script Python pour tracer les graphiques
├── project/                # Configuration SBT interne
├── src/                    # Code source Scala
├── target/                 # Fichiers compilés Scala
├── requirements.txt        # Librairies Python nécessaires

````

---

## Prérequis

- Java 11+  
- SBT (Scala Build Tool)  
- Python 3.8+  
- Modules Python (installés dans un environnement virtuel) :
  - pandas  
  - matplotlib  
  - seaborn

---

## Installation

1. Crée un environnement virtuel Python et active-le :

```bash
python3 -m venv venv
source venv/bin/activate
````

2. Installe les dépendances Python :

```bash
pip install --upgrade pip
pip install -r requirements.txt
```

---

## Utilisation

Rends le script `launch.sh` exécutable puis lance-le :

```bash
chmod +x launch.sh
./launch.sh
```

Le script effectue :

* Compilation et exécution du programme Scala (analyse et création de `co_gt_values.csv`)
* Exécution du script Python `plot_histogram.py` pour générer et afficher les graphiques

---

## Graphiques générés

* Histogramme des valeurs CO\_GT
* Scatter plot CO\_GT vs index
* Boxplot de la distribution CO\_GT

---

## Structure du code Scala

* `Main.scala` : Point d'entrée, lecture et analyse des données, export CSV
* `DataProcessing.scala` : Fonctions utilitaires pour traitement des données
* `Visualization.scala` : (optionnel) visualisation via XChart (non utilisé ici)

---

## Notes

* Le fichier `AirQualityUCI.csv` doit être à la racine du projet
* Le script Python nécessite un environnement avec les dépendances installées
* Utiliser l’environnement virtuel évite d’installer globalement les paquets Python
* Le script `launch.sh` automatise tout le processus

