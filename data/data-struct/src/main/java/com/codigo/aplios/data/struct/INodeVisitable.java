package com.codigo.aplios.data.struct;

public interface INodeVisitable<T> {

    T visit(Node<T> node);

}
