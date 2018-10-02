package com.codigo.aplios.data.struct;

public class Tree<T> {

    public static void main(final String[] args) {

        final Node<String> korzen = new Node<>(null,
                "G");

        final Node<String> n1 = korzen.addChild("E");
        final Node<String> n2 = korzen.addChild("C");
        final Node<String> n3 = korzen.addChild("V");
        n1.addChild("Z");
        final Node<String> n4 = n1.addChild("M");
        n1.addChild("P");
        n4.addChild("J");
        final Node<String> n5 = n2.addChild("X");
        n5.addChild("H");
        n5.addChild("O");
        n3.addChild("B");
        final Node<String> n6 = n3.addChild("S");
        n6.addChild("F");

        final Tree<String> drzewo = new Tree<>(korzen);

        System.out.print("Pre Order: ");
        drzewo.preOrder(korzen);
        System.out.print("\nPost Order: ");
        drzewo.postOrder(korzen);
        System.out.print("\nIn Order: ");
        drzewo.inOrder(korzen);
        System.out.println();

    }

    private final Node<T> root; // referencja do korzenia

    public Tree() { // konstruktor domyślny

        this.root = null;
    }

    public Tree(final Node<T> root) { // konstruktor jednoparametrowy

        this.root = root;
    }

    public Node<T> getRoot() {

        return this.root;
    }

    public void preOrder(final Node<T> n) {

        System.out.print(n + " ");
        Node<T> temp = n.getLeftMostChild();
        while (temp != null) {
            this.preOrder(temp);
            temp = temp.getRightSibling();
        }
    }

    public void inOrder(final Node<T> n) {

        if (n.isLeaf())
            System.out.print(n + " ");
        else {
            Node<T> temp = n.getLeftMostChild();
            this.inOrder(temp);
            System.out.print(n + " ");
            temp = temp.getRightSibling();
            while (temp != null) {
                this.inOrder(temp);
                temp = temp.getRightSibling();
            }
        }
    }

    public void postOrder(final Node<T> n) {

        Node<T> temp = n.getLeftMostChild();
        while (temp != null) {
            this.postOrder(temp);
            temp = temp.getRightSibling();
        }
        System.out.print(n + " ");
    }

}
