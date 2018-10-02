package com.codigo.aplios.sdk.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class UIDefaultsTreeModel implements TreeModel {
	DefaultTreeModel innerModel;

	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
		"UIDefaults");

	DefaultMutableTreeNode colorNode = new DefaultMutableTreeNode(
		"Color Resources");

	DefaultMutableTreeNode borderNode = new DefaultMutableTreeNode(
		"Border Resources");

	DefaultMutableTreeNode fontNode = new DefaultMutableTreeNode(
		"Font Resources");

	DefaultMutableTreeNode iconNode = new DefaultMutableTreeNode(
		"Icon Resources");

	DefaultMutableTreeNode otherNode = new DefaultMutableTreeNode(
		"Other Resources");

	public UIDefaultsTreeModel() {

		this.innerModel = new DefaultTreeModel(
			this.rootNode);
		this.innerModel.insertNodeInto(this.colorNode, this.rootNode, 0);
		this.innerModel.insertNodeInto(this.borderNode, this.rootNode, 1);
		this.innerModel.insertNodeInto(this.fontNode, this.rootNode, 2);
		this.innerModel.insertNodeInto(this.iconNode, this.rootNode, 3);
		this.innerModel.insertNodeInto(this.otherNode, this.rootNode, 4);
		final UIDefaults defaults = UIManager.getDefaults();
		final Enumeration<?> elems = defaults.keys();
		String keyName;
		Object valueForKey;
		while (elems.hasMoreElements()) {
			DefaultMutableTreeNode newKeyNode;
			DefaultMutableTreeNode newValueNode;
			try {
				keyName = elems.nextElement()
						.toString();
				valueForKey = defaults.get(keyName);

				newKeyNode = new DefaultMutableTreeNode(
					keyName);
				newValueNode = new DefaultMutableTreeNode(
					valueForKey);

				if (valueForKey instanceof java.awt.Color)
					this.innerModel.insertNodeInto(newKeyNode, this.colorNode, 0);
				else if (valueForKey instanceof javax.swing.border.Border)
					this.innerModel.insertNodeInto(newKeyNode, this.borderNode, 0);
				else if (valueForKey instanceof java.awt.Font)
					this.innerModel.insertNodeInto(newKeyNode, this.fontNode, 0);
				else if (valueForKey instanceof javax.swing.Icon)
					this.innerModel.insertNodeInto(newKeyNode, this.iconNode, 0);
				else
					this.innerModel.insertNodeInto(newKeyNode, this.otherNode, 0);
				this.innerModel.insertNodeInto(newValueNode, newKeyNode, 0);
			}
			catch (final NullPointerException e) {
			}
		}
	}

	@Override
	public Object getRoot() {

		return this.innerModel.getRoot();
	}

	@Override
	public Object getChild(final Object parm1, final int parm2) {

		return this.innerModel.getChild(parm1, parm2);
	}

	@Override
	public int getChildCount(final Object parm1) {

		return this.innerModel.getChildCount(parm1);
	}

	@Override
	public boolean isLeaf(final Object parm1) {

		return this.innerModel.isLeaf(parm1);
	}

	@Override
	public void valueForPathChanged(final TreePath parm1, final Object parm2) {

		this.innerModel.valueForPathChanged(parm1, parm2);
	}

	@Override
	public int getIndexOfChild(final Object parm1, final Object parm2) {

		return this.innerModel.getIndexOfChild(parm1, parm2);
	}

	@Override
	public void addTreeModelListener(final TreeModelListener parm1) {

		this.innerModel.addTreeModelListener(parm1);
	}

	@Override
	public void removeTreeModelListener(final TreeModelListener parm1) {

		this.innerModel.removeTreeModelListener(parm1);
	}

	public static void main(final String[] args) {

		final JFrame treeFrame1 = new JFrame();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			// UIManager.setLookAndFeel(lookModel.getLookAndFeels()
			// .get(getLookAndFeel()));
			// for (Window window : JFrame.getWindows()) {
			// SwingUtilities.updateComponentTreeUI(window);
			// }
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		final JScrollPane jScrollPane1 = new JScrollPane();
		final JTree jTree1 = new JTree(
			new UIDefaultsTreeModel());

		treeFrame1.setSize(new Dimension(
			400, 300));
		treeFrame1.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(final WindowEvent e) {

				System.exit(0);
			}
		});
		treeFrame1.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		treeFrame1.getContentPane()
				.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport()
				.add(jTree1, null);

		treeFrame1.setVisible(true);
	}
}
