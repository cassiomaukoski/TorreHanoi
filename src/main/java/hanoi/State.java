package hanoi;

import java.util.List;
import java.util.Stack;

public record State(List<Stack<Integer>> towers) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State(List<Stack<Integer>> towers1))) return false;
        return this.towers.equals(towers1);
    }

}