package search;

public record Node<T>(T state, Node<T> parent, int depth, String moveDescription) {
}