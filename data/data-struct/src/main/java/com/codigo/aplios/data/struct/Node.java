package com.codigo.aplios.data.struct;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Node<T> implements INode<T> {

	private T data; // dane

	private Node<T> parent; // referencja do rodzica

	private LinkedList<Node<T>> children; // lista dzieci

	/**
	 * Domyślny konstruktor obiekty klasy klasy <code>Node</code>
	 */
	public Node() {

		this.parent = null;
		this.children = new LinkedList<>();
	}

	/**
	 * @param parent
	 */
	public Node(final Node<T> parent) { // konstruktor jednoparametrowy

		this();
		this.parent = parent;
	}

	/**
	 * @param parent
	 * @param data
	 */
	public Node(final Node<T> parent, final T data) { // konstruktor dwuparametrowy

		this(
				parent);
		this.data = data;
	}

	@Override
	public Node<T> getParent() {

		return this.parent;
	}

	@Override
	public void setParent(final Node<T> parent) {

		this.parent = parent;
	}

	@Override
	public T getData() {

		return this.data;
	}

	@Override
	public void setData(final T data) {

		this.data = data;
	}

	@Override
	public int getDegree() {

		return this.children.size();
	}

	@Override
	public Node<T> getChild(final int i) {

		return this.children.get(i);
	}

	@Override
	public boolean isLeaf() {

		return this.children.isEmpty();
	}

	@Override
	public Node<T> addChild(final Node<T> child) {

		child.setParent(this);
		this.children.add(child);
		return child;
	}

	@Override
	public Node<T> addChild(final T data) {

		final Node<T> child = new Node<>(this, data);
		this.children.add(child);
		return child;
	}

	@Override
	public Node<T> removeChild(final int i) {

		return this.children.remove(i);
	}

	@Override
	public void removeChildren() {

		this.children.clear();
	}

	@Override
	public Node<T> getLeftMostChild() {

		if (this.children.isEmpty())
			return null;
		return this.children.get(0);
	}

	@Override
	public List<Node<T>> getChildren() {

		if (this.children.isEmpty())
			return Collections.emptyList();
		return this.children;
	}

	@Override
	public Node<T> getRightSibling() {

		if (this.parent != null) {
			final LinkedList<Node<T>> childrenParent = (LinkedList<Node<T>>) this.parent.getChildren();
			final int pozycja = childrenParent.indexOf(this);

			if (childrenParent.size() > (pozycja + 1))
				return childrenParent.get(pozycja + 1);
		}
		return null;
	}

	@Override
	public String toString() {

		return this.data.toString();
	}

	@Override
	public T accept(final INodeVisitable<T> visitor) {

		// TODO Auto-generated method stub
		return null;
	}

}
