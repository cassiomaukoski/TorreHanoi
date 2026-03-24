package hanoi;

import search.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractHanoi {

    protected static final int numDisks = 7;

    protected static State getInitialState() {
        List<Stack<Integer>> towers = new ArrayList<>();
        for (int i = 0; i < 3; i++) towers.add(new Stack<>());

        for (int i = numDisks; i >= 1; i--) {
            towers.getFirst().push(i);
        }
        return new State(towers);
    }

    protected static Predicate<State> getGoalTest() {
        return e -> e.towers().get(2).size() == numDisks;
    }

    protected static Function<State, List<Node<State>>> getSuccessorFunction() {
        return e -> {
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

    public static String towerName(int i) {
        return switch (i) {
            case 0 -> "A";
            case 1 -> "B";
            case 2 -> "C";
            default -> "?";
        };
    }
}