package search.algorithms;

import search.Node;
import search.Problem;

import java.util.List;

public interface Searcher<T> {
    List<Node<T>> search(Problem<T> problem);
}