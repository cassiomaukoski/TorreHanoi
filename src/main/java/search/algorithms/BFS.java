package search.algorithms;

import search.AbstractSearch;
import search.Node;
import search.Problem;
import java.util.*;

public class BFS<T> extends AbstractSearch<T> implements Searcher<T> {

    @Override
    public List<Node<T>> search(Problem<T> problem) {
        Queue<Node<T>> frontier = new LinkedList<>();
        Set<T> explored = new HashSet<>();

        Node<T> startNode = new Node<>(problem.getInitialState(), null, 0, null);

        if (problem.getGoalTest().test(startNode.state())) {
            return buildPath(startNode);
        }

        frontier.add(startNode);
        explored.add(startNode.state());

        while (!frontier.isEmpty()) {
            Node<T> current = frontier.poll();

            for (Node<T> childBase : problem.getSuccessorFunction().apply(current.state())) {
                T childState = childBase.state();

                if (!explored.contains(childState)) {
                    Node<T> childNode = new Node<>(
                            childState,
                            current,
                            current.depth() + 1,
                            childBase.moveDescription()
                    );

                    if (problem.getGoalTest().test(childState)) {
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