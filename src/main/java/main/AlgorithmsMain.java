package main;

import model.Person;
import symboltables.BinarySearchTree;

public class AlgorithmsMain {

    public static void main(String[] args) {
        BinarySearchTree<String, Person> bst = new BinarySearchTree<String, Person>();

        Person julia = new Person("Julia", "Leonardi", 5);
        bst.put("julia", julia);
        Person bia = new Person("Ana", "Bia", 10);
        bst.put("bia", bia);
        Person pri = new Person("Priscila", "Oliveira", 29);
        bst.put("pri", pri);
        Person virso = new Person("Virso", "Oliveira", 53);
        bst.put("virso", virso);
        Person ed = new Person("Ed", "Leonardi", 32);
        bst.put("ed", ed);

        System.out.println(bst.get("pri"));
        System.out.println(bst.get("julia"));
        System.out.println(bst.get("bia"));

        System.out.println("mira floor's : " + bst.floor("mira"));

        System.out.println("Pri's rank:" + bst.rank("pri"));
        System.out.println("Julia's rank:" + bst.rank("julia"));
        System.out.println("Bia's rank:" + bst.rank("bia"));

        System.out.println();
        System.out.println("Max key: " + bst.max());

        System.out.println();
        System.out.println("Min key: " + bst.min());

        System.out.println("\nDeleting min\n");
        bst.deleteMin();
        System.out.println("\nDeleting virso\n");
        bst.delete("virso");
        System.out.println("\nDeleting max\n");
        bst.deleteMax();

        int idx = 1;
        for (String key : bst.keys()) {
            System.out.println("key " + idx++ + " " + key);
        }

    }
}
