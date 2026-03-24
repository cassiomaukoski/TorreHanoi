package hanoi;

import java.util.List;
import java.util.Stack;

public record State(List<Stack<Integer>> towers) {
}