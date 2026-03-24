package hanoi;

import search.Node;
import search.Problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractHanoi {
    
    public static int numDisks = 5;

    public static Problem<State> createHanoiProblem(int numDisks) {
        AbstractHanoi.numDisks = numDisks;
        return new Problem<>(getInitialState(), getGoalTest(), getSuccessorFunction());
    }

    private static State getInitialState() {
        List<Stack<Integer>> towers = new ArrayList<>();
        for (int i = 0; i < 3; i++) towers.add(new Stack<>());
        for (int i = numDisks; i >= 1; i--) towers.getFirst().push(i);
        return new State(towers);
    }

    private static Predicate<State> getGoalTest() {
        return s -> s.towers().get(2).size() == numDisks;
    }

    private static Function<State, List<Node<State>>> getSuccessorFunction() {
        return state -> {
            List<Node<State>> successors = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                if (state.towers().get(i).isEmpty()) continue;
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;
                    
                    Stack<Integer> from = state.towers().get(i);
                    Stack<Integer> to = state.towers().get(j);

                    if (to.isEmpty() || from.peek() < to.peek()) {
                        List<Stack<Integer>> nextTowers = cloneTowers(state.towers());
                        int disk = nextTowers.get(i).pop();
                        nextTowers.get(j).push(disk);
                        
                        String move = "Mover disco " + disk + " de " + (char)('A'+i) + " para " + (char)('A'+j);
                        successors.add(new Node<>(new State(nextTowers), null, 0, move)); // Nó "molde"
                    }
                }
            }
            return successors;
        };
    }

    private static List<Stack<Integer>> cloneTowers(List<Stack<Integer>> towers) {
        List<Stack<Integer>> copy = new ArrayList<>();
        for (Stack<Integer> t : towers) {
            Stack<Integer> nt = new Stack<>();
            nt.addAll(t);
            copy.add(nt);
        }
        return copy;
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