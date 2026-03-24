import hanoi.AbstractHanoi;
import hanoi.State;
import search.Problem;
import search.algorithms.*;
import utils.Printer;


void main() {
    int numDisks = 7;
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