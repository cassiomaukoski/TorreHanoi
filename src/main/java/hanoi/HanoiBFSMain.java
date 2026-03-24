package hanoi;

import search.breadthfirst.BFS;
import search.Node;
import utils.Printer;

import java.util.List;

public class HanoiBFSMain extends AbstractHanoi {

    static void main() {

        State initialState = getInitialState();
        BFS<State> bfs = new BFS<>();

        System.out.println("-------------------- TORRE DE HANOI COM BFS ---------------------");
        System.out.println("Procurando uma solução com " + numDisks + " discos...");
        System.out.println("Aviso: BFS com muitos discos pode consumir muita memória!");
        System.out.println("-----------------------------------------------------------------");
        
        long startTime = System.currentTimeMillis();
        
        List<Node<State>> path = bfs.search(
                initialState, 
                getGoalTest(),
                getSuccessorFunction()
        );
        
        long endTime = System.currentTimeMillis();
        Printer.print(startTime, endTime, path);
    }
}