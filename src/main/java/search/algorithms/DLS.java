package search.algorithms;

import search.AbstractSearch;
import search.Node;
import search.Problem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DLS<T> extends AbstractSearch<T> implements Searcher<T> {

    private final int limit;

    public DLS(int limit) {
        this.limit = limit;
    }

    @Override
    public List<Node<T>> search(Problem<T> problem) {
        return solve(
            new Node<>(problem.getInitialState(), null, 0, null),
            problem,
            this.limit,
            new HashMap<>()
        );
    }

    private List<Node<T>> solve(Node<T> node, Problem<T> problem, int limit, Map<T, Integer> visited) {
        if (problem.getGoalTest().test(node.state())) return buildPath(node);
        if (node.depth() >= limit) return null;

        if (visited.getOrDefault(node.state(), Integer.MAX_VALUE) <= node.depth()) return null;
        visited.put(node.state(), node.depth());

        for (Node<T> childBase : problem.getSuccessorFunction().apply(node.state())) {
            Node<T> child = new Node<>(
                    childBase.state(),
                    node,
                    node.depth() + 1,
                    childBase.moveDescription()
            );

            List<Node<T>> result = solve(child, problem, limit, visited);
            if (result != null) return result;
        }
        return null;
    }
}