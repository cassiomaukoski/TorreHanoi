package search.breadthfirst;

import search.AbstractSearch;
import search.Node; // Reutilizando a sua classe Node

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class BFS<T> extends AbstractSearch<T> {

    public List<Node<T>> search(
            T initialState,
            Predicate<T> goalTest,
            Function<T, List<Node<T>>> successorFunction
    ) {
        Queue<Node<T>> frontier = new LinkedList<>();
        Set<T> explored = new HashSet<>();

        Node<T> startNode = new Node<>(initialState, null, 0, null);

        if (goalTest.test(startNode.state())) {
            return buildPath(startNode);
        }

        frontier.add(startNode);
        explored.add(initialState);

        while (!frontier.isEmpty()) {
            Node<T> current = frontier.poll();

            for (Node<T> childBase : successorFunction.apply(current.state())) {
                T childState = childBase.state();

                if (!explored.contains(childState)) {
                    Node<T> childNode = new Node<>(
                            childState,
                            current,
                            current.depth() + 1,
                            childBase.moveDescription()
                    );

                    if (goalTest.test(childState)) {
                        return buildPath(childNode);
                    }

                    explored.add(childState);
                    frontier.add(childNode);
                }
            }
        }
        return null;
    }

}