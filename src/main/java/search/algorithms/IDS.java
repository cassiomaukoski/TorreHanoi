package search.algorithms;

import search.Node;
import search.Problem;
import java.util.List;

public class IDS<T> implements Searcher<T> {
    
    @Override
    public List<Node<T>> search(Problem<T> problem) {
        for (int limit = 0; limit < Integer.MAX_VALUE; limit++) {
            DLS<T> dls = new DLS<>(limit);
            List<Node<T>> result = dls.search(problem);
            
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}