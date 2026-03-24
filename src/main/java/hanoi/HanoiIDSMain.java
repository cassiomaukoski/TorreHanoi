package hanoi;

import iterativedeepeningsearch.IDS;
import iterativedeepeningsearch.Node;
import utils.Printer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

public class HanoiIDSMain {

    static void main() {

        final int numDisks = 7;
        final int maxDepthLimit = (int) Math.pow(2, numDisks) - 1;

        List<Stack<Integer>> towers = new ArrayList<>();
        for (int i = 0; i < 3; i++) towers.add(new Stack<>());

        for (int i = numDisks; i >= 1; i--) {
            towers.getFirst().push(i);
        }

        State initialState = new State(towers);
        IDS<State> ids = new IDS<>();

        Predicate<State> goal = e -> e.towers().get(2).size() == numDisks;

        Function<State, List<Node<State>>> successors = e -> {
            List<Node<State>> list = new ArrayList<>();

            for (int i = 0; i < 3; i++) {
                if (e.towers().get(i).isEmpty()) continue;

                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;

                    Stack<Integer> origin = e.towers().get(i);
                    Stack<Integer> destination = e.towers().get(j);

                    if (destination.isEmpty() || origin.peek() < destination.peek()) {

                        List<Stack<Integer>> newTowers = cloneTowers(e.towers());

                        int disk = newTowers.get(i).pop();
                        newTowers.get(j).push(disk);

                        String move = "Mover disco " + disk + " de " + towerName(i) + " para " + towerName(j);

                        list.add(new Node<>(new State(newTowers), null, 0, move));
                    }
                }
            }

            return list;
        };

        System.out.println("-------------------- TORRE DE HANOI COM IDS ---------------------");
        System.out.println("Procurando uma solução com " + numDisks + " discos...");
        System.out.println("Profundidade máxima calculada: " + maxDepthLimit);
        System.out.println("-----------------------------------------------------------------");
        long startTime = System.currentTimeMillis();
        List<Node<State>> path = ids.search(initialState, goal, successors, maxDepthLimit);
        long endTime = System.currentTimeMillis();
        Printer.print(startTime, endTime, path);
    }

    private static List<Stack<Integer>> cloneTowers(List<Stack<Integer>> towers) {
        List<Stack<Integer>> newTowers = new ArrayList<>();
        for (Stack<Integer> t : towers) {
            Stack<Integer> newTower = new Stack<>();
            newTower.addAll(t);
            newTowers.add(newTower);
        }
        return newTowers;
    }

    private static String towerName(int i) {
        return switch (i) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            default -> "?";
        };
    }
}