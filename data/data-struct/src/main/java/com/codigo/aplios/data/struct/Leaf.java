package com.codigo.aplios.data.struct;

import java.util.LinkedList;
import java.util.UUID;

public class Leaf<T> implements INode<T> {

	@Override
	public Node<T> getParent() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(final Node<T> parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public T getData() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setData(final T data) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getDegree() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Node<T> getChild(final int i) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node<T> addChild(final T child) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> removeChild(final int i) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeChildren() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node<T> getLeftMostChild() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Node<T>> getChildren() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> getRightSibling() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node<T> addChild(final Node<T> child) {

		// TODO Auto-generated method stub
		return null;
	}

	public UUID getUniqueId() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T accept(final INodeVisitable<T> visitor) {

		// TODO Auto-generated method stub
		return null;
	}

}
