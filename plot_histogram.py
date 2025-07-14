import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

# Chargement du fichier CSV
filename = "co_gt_values.csv"
data = pd.read_csv(filename)

values = data["CO_GT"].dropna()

plt.figure(figsize=(15, 10))

# 1) Histogramme
plt.subplot(2, 2, 1)
plt.hist(values, bins=10, color='skyblue', edgecolor='black')
plt.title("Histogramme de CO_GT")
plt.xlabel("CO_GT")
plt.ylabel("Fréquence")

# 2) Boxplot
plt.subplot(2, 2, 2)
sns.boxplot(x=values, color='lightgreen')
plt.title("Boxplot de CO_GT")

# 3) Densité (Kernel Density Estimate)
plt.subplot(2, 2, 3)
sns.kdeplot(values, shade=True, color='orange')
plt.title("Densité estimée de CO_GT")
plt.xlabel("CO_GT")

# 4) Scatter plot des valeurs dans l'ordre
plt.subplot(2, 2, 4)
plt.scatter(range(len(values)), values, alpha=0.5, color='purple', s=10)
plt.title("Dispersion des valeurs CO_GT")
plt.xlabel("Index")
plt.ylabel("CO_GT")

plt.tight_layout()
plt.show()
