package search.algorithms;

public enum SearchAlgorithms {
    BFS( "BFS","Breadth-First Search"),
    DLS("DLS","Depth-Limited Search"),
    IDS("IDS","Iterative Deepening Search");


    private final String label;

    private final String description;

    SearchAlgorithms(String label, String description) {
        this.label = label;
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription(){
        return description;
    }
}
