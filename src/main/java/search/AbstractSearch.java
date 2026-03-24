package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSearch<T> {

    protected List<Node<T>> buildPath(Node<T> node) {
        List<Node<T>> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent();
        }
        Collections.reverse(path);
        return path;
    }
}