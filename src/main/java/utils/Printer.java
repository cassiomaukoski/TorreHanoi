package utils;

import hanoi.AbstractHanoi;
import hanoi.State;
import search.Node;

import java.util.List;
import java.util.Stack;

public class Printer {

    public static void print(long startTime, long endTime, List<Node<State>> path){
        if (path != null) {
            int step = 0;
            for (Node<State> node : path) {
                System.out.println("Passo " + step++);

                if (node.moveDescription() != null) {
                    System.out.println(node.moveDescription());
                }

                printTowers(node.state());
                System.out.println("-----------------------------------------------------------------");
            }
            System.out.println("Resolvido em " + (endTime - startTime) + " milissegundos!");
        } else {
            System.out.println("Solution not found within the depth limit.");
        }
    }

    private static void printTowers(State e) {
        int maxDisks = e.towers().get(0).size()
                + e.towers().get(1).size()
                + e.towers().get(2).size();

        for (int level = maxDisks; level >= 0; level--) {
            for (int t = 0; t < 3; t++) {
                Stack<Integer> tower = e.towers().get(t);

                if (level < tower.size()) {
                    int disk = tower.get(level);
                    printCenteredDisk(disk, maxDisks);
                } else {
                    printCenteredDisk(0, maxDisks);
                }
            }
            System.out.println();
        }

        for (int i = 0; i < 3; i++) {
            String label = AbstractHanoi.towerName(i);
            System.out.print(" ".repeat(maxDisks + 1) + label + " ".repeat(maxDisks + 1) + " ");
        }
        System.out.println();
    }

    private static void printCenteredDisk(int size, int max) {
        if (size == 0) {
            System.out.print(" ".repeat(max + 1) + "|" + " ".repeat(max + 1) + " ");
            return;
        }

        int totalWidth = (size * 2) + 1;
        String label = String.valueOf(size);

        int equalHalves = (totalWidth - label.length()) / 2;
        String content = "=".repeat(equalHalves) + label + "=".repeat(equalHalves);

        int outerSpaces = max - size + 1;

        System.out.print(" ".repeat(outerSpaces) + content + " ".repeat(outerSpaces) + " ");
    }

}
