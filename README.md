# 🗼 Solucionador da Torre de Hanói com Algoritmos de Busca

Implementação em **Java** do clássico problema da **Torre de Hanói**, resolvido utilizando algoritmos de busca da área de Inteligência Artificial.

O projeto separa claramente:
- 🧩 **Definição do problema** (estado, regras, transições)
- 🧠 **Algoritmos de busca**

Isso permite trocar facilmente a estratégia de resolução.

---

## ⚠️ Pré-requisitos

❗ **Obrigatório:** JDK 25 ou superior

---

## ⚙️ Algoritmos Implementados

Disponíveis em `search.algorithms`:

| Algoritmo | Descrição |
|----------|----------|
| **BFS** | Busca em largura — garante o menor caminho |
| **DLS** | Busca com limite de profundidade |
| **IDS** | Aprofundamento iterativo (combina BFS + DFS) |

---

## 📂 Estrutura do Projeto

* `hanoi/`: Contém a definição específica do problema da Torre de Hanói (Estados e Regras de transição).
* `search/`: Estrutura base genérica para resolução de problemas (`Node`, `Problem`, `AbstractSearch`).
* `search.algorithms/`: Implementações dos algoritmos de busca (`BFS`, `DLS`, `IDS`).
* `utils/`: Ferramentas auxiliares, como o `Printer`, que desenha as torres no console de forma visual.

---

## 🛠️ Como usar

1. Defina o número de discos a ser ordenado:
```java
int numDisks = 2;
```

2. Escolha o algoritmo desejado (BFS, DLS ou IDS):

```java
SearchAlgorithms selected = SearchAlgorithms.BFS;
```

**⚠️ Importante:**
Para o DLS, é obrigatório informar o limite de profundidade através do método construtor.

## 💻 Exemplo prático

```java
void main() {
    int numDisks = 2;
    SearchAlgorithms selected = SearchAlgorithms.BFS;

    Problem<State> hanoiProblem = AbstractHanoi.createHanoiProblem(numDisks);

    Searcher<State> searcher = switch (selected) {
        case BFS -> new BFS<>();
        case IDS -> new IDS<>();
        case DLS -> new DLS<>(31);
    };


    System.out.println("-------------------- TORRE DE HANOI COM " + selected.getLabel() + " ---------------------");
    System.out.println("Procurando uma solução com " + numDisks + " discos...");
    System.out.println("-----------------------------------------------------------------");

    long start = System.currentTimeMillis();
    var path = searcher.search(hanoiProblem);
    long end = System.currentTimeMillis();

    Printer.print(start, end, path);
}
```

## 📌 Saída esperada

```
-------------------- TORRE DE HANOI COM BFS ---------------------
Procurando uma solução com 2 discos...
-----------------------------------------------------------------
Passo 0
   |       |       |    
  =1=      |       |    
 ==2==     |       |    
   A       B       C    
-----------------------------------------------------------------
Passo 1
Mover disco 1 de A para B
   |       |       |    
   |       |       |    
 ==2==    =1=      |    
   A       B       C    
-----------------------------------------------------------------
Passo 2
Mover disco 2 de A para C
   |       |       |    
   |       |       |    
   |      =1=    ==2==  
   A       B       C    
-----------------------------------------------------------------
Passo 3
Mover disco 1 de B para C
   |       |       |    
   |       |      =1=   
   |       |     ==2==  
   A       B       C    
-----------------------------------------------------------------
Resolvido em 5 milissegundos!
```

