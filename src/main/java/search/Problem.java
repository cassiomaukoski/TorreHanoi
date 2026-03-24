package search;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Problem<T> {
    private final T initialState;
    private final Predicate<T> goalTest;
    private final Function<T, List<Node<T>>> successorFunction;

    public Problem(T initialState, Predicate<T> goalTest, Function<T, List<Node<T>>> successorFunction) {
        this.initialState = initialState;
        this.goalTest = goalTest;
        this.successorFunction = successorFunction;
    }

    public T getInitialState() { return initialState; }
    public Predicate<T> getGoalTest() { return goalTest; }
    public Function<T, List<Node<T>>> getSuccessorFunction() { return successorFunction; }
}