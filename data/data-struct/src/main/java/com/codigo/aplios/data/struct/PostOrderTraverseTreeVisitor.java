package com.codigo.aplios.data.struct;

public final class PostOrderTraverseTreeVisitor<T> implements INodeVisitable<T> {

	@Override
	public void visit(final Node<T> tree) {

		Node<T> temp = tree.getLeftMostChild();
		while (temp != null) {
			visit(temp);
			temp = temp.getRightSibling();
		}
		System.out.print(tree + " ");
	}
}