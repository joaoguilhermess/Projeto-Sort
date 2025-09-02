from matplotlib.ticker import FuncFormatter
import matplotlib.pyplot as plt
import numpy as np
import json
import tkinter as tk
from matplotlib.backends.backend_tkagg import FigureCanvasTkAgg

# ====================
# Carregar dados
# ====================
with open("./../data/results.json", "r") as file:
    data = json.load(file)

# ====================
# Preparar dados (comparações)
# ====================
methods = set(d["method"] for d in data)
method_data = {method: {"amount": [], "comparisons": []} for method in methods}
for d in data:
    method_data[d["method"]]["amount"].append(d["amount"])
    method_data[d["method"]]["comparisons"].append(d["comparisons"])  # número de comparações direto

colors = {
    "bubbleSort": "red",
    "selectSort": "blue",
    "insertSort": "green",
    "mergeSort": "orange",
    "heapSort": "purple",
    "quickSort": "brown",
    "sequentialSearch": "gray",
    "binarySearch": "cyan",
    "tree": "magenta",
    "balancedTree": "pink"
}

# ====================
# Criar figura
# ====================
fig, ax = plt.subplots(figsize=(14,6))

# ====================
# Eixo X
# ====================
ticks_x = [
    1_000, 10_000, 50_000, 100_000, 200_000,
    300_000, 400_000, 500_000, 1_000_000,
    2_000_000, 2_641_560
]
positions_x = range(len(ticks_x))
labels_x = [f"{t//1000}k" if t < 1_000_000 else f"{t/1_000_000:.2f}M" for t in ticks_x]
amount_to_pos = {amount: pos for amount, pos in zip(ticks_x, positions_x)}

# ====================
# Plotar algoritmos
# ====================
for method in methods:
    x_pos = [amount_to_pos[a] for a in method_data[method]["amount"]]
    ax.plot(
        x_pos,
        method_data[method]["comparisons"],
        marker="o",
        linestyle="-",
        color=colors.get(method, "black"),
        label=method
    )

ax.set_xticks(positions_x)
ax.set_xticklabels(labels_x)

# ====================
# Eixo Y logarítmico
# ====================
ax.set_yscale("log")
all_comparisons = [c for method in methods for c in method_data[method]["comparisons"]]
ymin = min(all_comparisons)
ymax = max(all_comparisons)
num_y_ticks = 10
log_ticks_y = np.logspace(np.log10(ymin), np.log10(ymax), num_y_ticks)
labels_y = [f"{int(y):,}" for y in log_ticks_y]  # formata números grandes com vírgula

ax.set_yticks(log_ticks_y)
ax.set_yticklabels(labels_y)

ax.grid(True, which="major", axis="y", linestyle="--", linewidth=0.5)

# Labels e título
ax.set_xlabel("Tamanho da Entrada")
ax.set_ylabel("Altura da Árvore")
ax.set_title("Desempenho da Árvore BST vs. Árvore AVL")
ax.legend()

# ====================
# Criar janela Tk sem bordas
# ====================
root = tk.Tk()
root.attributes("-fullscreen", True)  # ativa tela cheia

canvas = FigureCanvasTkAgg(fig, master=root)
canvas.draw()
canvas.get_tk_widget().pack(fill="both", expand=True)

root.mainloop()
