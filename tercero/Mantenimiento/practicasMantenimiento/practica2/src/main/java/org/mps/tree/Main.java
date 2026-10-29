package org.mps.tree;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Comparator<Integer> comparator = Integer::compareTo;

        BinarySearchTree<Integer> tree = new BinarySearchTree<>(Integer::compareTo);
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(12);
        tree.removeValue(12);
        System.out.println(tree.render());


    }
}
