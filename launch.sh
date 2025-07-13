# #!/bin/bash

# echo "Lancement du programme Scala..."
# sbt run

# echo "Exécution du script Python pour les graphiques..."
# source venv/bin/activate
# python plot_histogram.py

#!/bin/bash

echo "Création/activation de l'environnement virtuel Python..."
if [ ! -d "venv" ]; then
  python3 -m venv venv
fi
source venv/bin/activate

echo "Installation des dépendances Python..."
pip install --upgrade pip
pip install -r requirements.txt

echo "Lancement du programme Scala..."
sbt run

echo "Exécution du script Python pour les graphiques..."
python plot_histogram.py

echo "Terminé."
