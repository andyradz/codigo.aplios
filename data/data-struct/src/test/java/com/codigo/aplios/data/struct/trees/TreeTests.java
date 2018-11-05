package com.codigo.aplios.data.struct.trees;

import org.junit.jupiter.api.Test;

import com.codigo.aplios.data.struct.Node;
import com.codigo.aplios.data.struct.Tree;

public class TreeTests {

	@Test
	public void test() {

		final Node<String> korzen = new Node<>(
			null, "G");

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

		final Tree<String> drzewo = new Tree<>(
			korzen);

		System.out.print("Pre Order: ");
		drzewo.preOrder(korzen);

		System.out.print("\nPost Order: ");
		drzewo.postOrder(korzen);

		// visitor.visit(korzen);
		System.out.print("\nIn Order: ");
		drzewo.inOrder(korzen);
		System.out.println();
	}
}
