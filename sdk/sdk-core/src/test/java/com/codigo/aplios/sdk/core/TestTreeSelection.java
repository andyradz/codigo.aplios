package com.codigo.aplios.sdk.core;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class TestTreeSelection {

	protected void initUI() {

		final DefaultMutableTreeNode root = new DefaultMutableTreeNode(
			"Root");
		fillTree(root, 5, "Some tree label");
		final DefaultTreeModel model = new DefaultTreeModel(
			root);
		final JTree tree = new JTree(
			model);
		tree.addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(final MouseEvent e) {

				boolean inside = false;
				final TreePath path = tree.getPathForLocation(e.getX(), e.getY());
				if (path != null) {
					final Rectangle pathBounds = tree.getPathBounds(path);
					inside = pathBounds.contains(e.getPoint());
				}
				if (inside)
					tree.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				else
					tree.setCursor(Cursor.getDefaultCursor());
			}
		});
		final JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.add(new JScrollPane(
			tree));
		f.setSize(400, 600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	private void fillTree(final DefaultMutableTreeNode parent, final int level, final String label) {

		for (int i = 0; i < 5; i++) {
			final DefaultMutableTreeNode node = new DefaultMutableTreeNode(
				label + " " + i);
			parent.add(node);
			if (level > 0)
				fillTree(node, level - 1, label);
		}
	}

	public static void main(final String[] args) {

		SwingUtilities.invokeLater(() -> new TestTreeSelection().initUI());
	}

}