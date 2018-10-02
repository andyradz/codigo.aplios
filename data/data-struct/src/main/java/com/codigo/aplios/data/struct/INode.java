package com.codigo.aplios.data.struct;

import java.util.List;

/**
 * Dla każdego drzewa można określić: długość drogi u (głębokość) - liczba
 * wierzchołków, przez które należy przejść od korzenia do wierzchołka u
 * wysokość u - maksymalna liczba wierzchołków na drodze od u do pewnego liścia
 * wysokość drzewa = głębokość = wysokość korzenia +1 ścieżka z u do v - zbiór
 * wierzchołków, przez które należy przejść z wierzchołka u do v droga - ścieżka
 * skierowana stopień wierzchołka - liczba jego bezpośrednich następników
 * stopień drzewa - maksymalny stopień wierzchołka
 *
 * @author dp0470
 *
 * @param <T>
 */
public interface INode<T> {

    public Node<T> getParent(); // zwraca referencje rodzica

    public void setParent(Node<T> parent); // ustawia rodzica dla węzła

    public T getData(); // zwraca przechowywane dane

    public void setData(T data); // ustawia dane w węźle

    public int getDegree(); // zwraca stopień węzła

    public Node<T> getChild(int i); // zwraca referencje do i-tego dziecka

    public boolean isLeaf(); // sprawdza czy węzeł jest liściem

    public Node<T> addChild(Node<T> child); // dodaje do węzła dziecko (inny węzeł)

    public Node<T> addChild(T data); // tworzy i dodaje do węzła dziecko z danymi

    public Node<T> removeChild(int i); // usuwa i-te dziecko węzła

    public void removeChildren(); // usuwa wszystkie dzieci węzła

    public Node<T> getLeftMostChild(); // zwraca pierwsze dziecko węzła (z lewej)

    public List<Node<T>> getChildren(); // zwraca listę dzieci

    public Node<T> getRightSibling(); // zwraca kolejny element siostrzany węzła

    public T accept(final INodeVisitable<T> visitor);

    @Override
    public String toString(); // wyświetla węzeł (najczęściej dane)

}
