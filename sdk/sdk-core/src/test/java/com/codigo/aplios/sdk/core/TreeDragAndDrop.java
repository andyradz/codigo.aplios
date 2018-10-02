package com.codigo.aplios.sdk.core;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreeDragAndDrop {
	private JScrollPane getContent() {

		final JTree tree = new JTree();
		tree.setDragEnabled(true);
		tree.setDropMode(DropMode.ON_OR_INSERT);
		tree.setTransferHandler(new TreeTransferHandler());
		tree.getSelectionModel()
				.setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
		expandTree(tree);
		tree.setEditable(true);
		return new JScrollPane(
			tree);
	}

	private void expandTree(final JTree tree) {

		final DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel()
				.getRoot();
		final Enumeration<?> e = root.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			if (node.isLeaf())
				continue;
			final int row = tree.getRowForPath(new TreePath(
				node.getPath()));
			tree.expandRow(row);
		}
	}

	public static void main(final String[] args) {

		final JFrame f = new JFrame();
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.add(new TreeDragAndDrop().getContent());
		f.setSize(400, 400);
		f.setLocation(200, 200);
		f.setVisible(true);
	}
}

class TreeTransferHandler extends TransferHandler {
	/**
	 *
	 */
	private static final long	serialVersionUID	= 6844823025782625308L;
	DataFlavor					nodesFlavor;
	DataFlavor[]				flavors				= new DataFlavor[1];
	DefaultMutableTreeNode[]	nodesToRemove;

	public TreeTransferHandler() {
		try {
			final String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=\""
					+ javax.swing.tree.DefaultMutableTreeNode[].class.getName() + "\"";
			this.nodesFlavor = new DataFlavor(
				mimeType);
			this.flavors[0] = this.nodesFlavor;
		}
		catch (final ClassNotFoundException e) {
			System.out.println("ClassNotFound: " + e.getMessage());
		}
	}

	@Override
	public boolean canImport(final TransferHandler.TransferSupport support) {

		if (!support.isDrop())
			return false;
		support.setShowDropLocation(true);
		if (!support.isDataFlavorSupported(this.nodesFlavor))
			return false;
		// Do not allow a drop on the drag source selections.
		final JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
		final JTree tree = (JTree) support.getComponent();
		final int dropRow = tree.getRowForPath(dl.getPath());
		final int[] selRows = tree.getSelectionRows();
		for (final int selRow : selRows)
			if (selRow == dropRow)
				return false;
		// Do not allow MOVE-action drops if a non-leaf node is
		// selected unless all of its children are also selected.
		final int action = support.getDropAction();
		if (action == TransferHandler.MOVE)
			return haveCompleteNode(tree);
		// Do not allow a non-leaf node to be copied to a level
		// which is less than its source level.
		final TreePath dest = dl.getPath();
		final DefaultMutableTreeNode target = (DefaultMutableTreeNode) dest.getLastPathComponent();
		final TreePath path = tree.getPathForRow(selRows[0]);
		final DefaultMutableTreeNode firstNode = (DefaultMutableTreeNode) path.getLastPathComponent();
		if ((firstNode.getChildCount() > 0) && (target.getLevel() < firstNode.getLevel()))
			return false;
		return true;
	}

	private boolean haveCompleteNode(final JTree tree) {

		final int[] selRows = tree.getSelectionRows();
		TreePath path = tree.getPathForRow(selRows[0]);
		final DefaultMutableTreeNode first = (DefaultMutableTreeNode) path.getLastPathComponent();
		final int childCount = first.getChildCount();
		// first has children and no children are selected.
		if ((childCount > 0) && (selRows.length == 1))
			return false;
		// first may have children.
		for (int i = 1; i < selRows.length; i++) {
			path = tree.getPathForRow(selRows[i]);
			final DefaultMutableTreeNode next = (DefaultMutableTreeNode) path.getLastPathComponent();
			if (first.isNodeChild(next))
				// Found a child of first.
				if (childCount > (selRows.length - 1))
					// Not all children of first are selected.
					return false;
		}
		return true;
	}

	@Override
	protected Transferable createTransferable(final JComponent c) {

		final JTree tree = (JTree) c;
		final TreePath[] paths = tree.getSelectionPaths();
		if (paths != null) {
			// Make up a node array of copies for transfer and
			// another for/of the nodes that will be removed in
			// exportDone after a successful drop.
			final List<DefaultMutableTreeNode> copies = new ArrayList<>();
			final List<DefaultMutableTreeNode> toRemove = new ArrayList<>();
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) paths[0].getLastPathComponent();
			final DefaultMutableTreeNode copy = copy(node);
			copies.add(copy);
			toRemove.add(node);
			for (int i = 1; i < paths.length; i++) {
				final DefaultMutableTreeNode next = (DefaultMutableTreeNode) paths[i].getLastPathComponent();
				// Do not allow higher level nodes to be added to list.
				if (next.getLevel() < node.getLevel())
					break;
				else if (next.getLevel() > node.getLevel())
					copy.add(copy(next));
				// node already contains child
				else { // sibling
					copies.add(copy(next));
					toRemove.add(next);
				}
			}
			final DefaultMutableTreeNode[] nodes = copies.toArray(new DefaultMutableTreeNode[copies.size()]);
			this.nodesToRemove = toRemove.toArray(new DefaultMutableTreeNode[toRemove.size()]);
			return new NodesTransferable(
				nodes);
		}
		return null;
	}

	/** Defensive copy used in createTransferable. */
	private DefaultMutableTreeNode copy(final TreeNode node) {

		return new DefaultMutableTreeNode(
			node);
	}

	@Override
	protected void exportDone(final JComponent source, final Transferable data, final int action) {

		if ((action & TransferHandler.MOVE) == TransferHandler.MOVE) {
			final JTree tree = (JTree) source;
			final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
			// Remove nodes saved in nodesToRemove in createTransferable.
			for (final DefaultMutableTreeNode element : this.nodesToRemove)
				model.removeNodeFromParent(element);
		}
	}

	@Override
	public int getSourceActions(final JComponent c) {

		return TransferHandler.COPY_OR_MOVE;
	}

	@Override
	public boolean importData(final TransferHandler.TransferSupport support) {

		if (!canImport(support))
			return false;
		// Extract transfer data.
		DefaultMutableTreeNode[] nodes = null;
		try {
			final Transferable t = support.getTransferable();
			nodes = (DefaultMutableTreeNode[]) t.getTransferData(this.nodesFlavor);
		}
		catch (final UnsupportedFlavorException ufe) {
			System.out.println("UnsupportedFlavor: " + ufe.getMessage());
		}
		catch (final java.io.IOException ioe) {
			System.out.println("I/O error: " + ioe.getMessage());
		}
		// Get drop location info.
		final JTree.DropLocation dl = (JTree.DropLocation) support.getDropLocation();
		final int childIndex = dl.getChildIndex();
		final TreePath dest = dl.getPath();
		final DefaultMutableTreeNode parent = (DefaultMutableTreeNode) dest.getLastPathComponent();
		final JTree tree = (JTree) support.getComponent();
		final DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		// Configure for drop mode.
		int index = childIndex; // DropMode.INSERT
		if (childIndex == -1)
			index = parent.getChildCount();
		// Add data to model.
		for (final DefaultMutableTreeNode node : nodes)
			model.insertNodeInto(node, parent, index++);
		return true;
	}

	@Override
	public String toString() {

		return getClass().getName();
	}

	public class NodesTransferable implements Transferable {
		DefaultMutableTreeNode[] nodes;

		public NodesTransferable(final DefaultMutableTreeNode[] nodes) {
			this.nodes = nodes;
		}

		@Override
		public Object getTransferData(final DataFlavor flavor) throws UnsupportedFlavorException {

			if (!isDataFlavorSupported(flavor))
				throw new UnsupportedFlavorException(
					flavor);
			return this.nodes;
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {

			return TreeTransferHandler.this.flavors;
		}

		@Override
		public boolean isDataFlavorSupported(final DataFlavor flavor) {

			return TreeTransferHandler.this.nodesFlavor.equals(flavor);
		}
	}
}
