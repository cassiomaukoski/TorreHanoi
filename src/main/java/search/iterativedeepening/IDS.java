package search.iterativedeepening;

import search.AbstractSearch;
import search.Node;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class IDS<T> extends AbstractSearch<T> {

    public List<Node<T>> search(
            T initialState,
            Predicate<T> goalTest,
            Function<T, List<Node<T>>> successorFunction,
            int maxDepth
    ) {
        for (int limit = 0; limit <= maxDepth; limit++) {
            
            Map<T, Integer> visitedDepths = new HashMap<>();

            Node<T> result = dls(
                    new Node<>(initialState, null, 0, null),
                    goalTest,
                    successorFunction,
                    limit,
                    visitedDepths
            );

            if (result != null) {
                return buildPath(result);
            }
        }
        return null;
    }

    private Node<T> dls(
            Node<T> node,
            Predicate<T> goalTest,
            Function<T, List<Node<T>>> successorFunction,
            int limit,
            Map<T, Integer> visitedDepths
    ) {
        if (goalTest.test(node.state())) return node;

        if (node.depth() >= limit) return null;

        Integer previousDepth = visitedDepths.get(node.state());
        if (previousDepth != null && previousDepth <= node.depth()) {
            return null;
        }

        visitedDepths.put(node.state(), node.depth());

        for (Node<T> childBase : successorFunction.apply(node.state())) {

            Node<T> child = new Node<>(
                    childBase.state(),
                    node,
                    node.depth() + 1,
                    childBase.moveDescription()
            );

            Node<T> result = dls(child, goalTest, successorFunction, limit, visitedDepths);

            if (result != null) return result;
        }

        return null;
    }
}