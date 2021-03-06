package com.codigo.aplios.gui.core.gridview;

/*
 *
 * @(#)TreeTableModelAdapter.java 1.2 98/10/27
 *
 * Copyright 1997, 1998 by Sun Microsystems, Inc., 901 San Antonio Road, Palo Alto, California, 94303, U.S.A. All rights
 * reserved.
 *
 * This software is the confidential and proprietary information of Sun Microsystems, Inc. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Sun.
 */
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;

/**
 * This is a wrapper class takes a TreeTableModel and implements the table model interface. The
 * implementation is trivial, with all of the event dispatching support provided by the superclass:
 * the AbstractTableModel.
 *
 * @version 1.2 10/27/98
 *
 * @author Philip Milne
 * @author Scott Violet
 */
public class TreeTableModelAdapter extends AbstractTableModel {

	private static final long serialVersionUID = -4197012030989392568L;

	JTree tree;

	TreeTableModel treeTableModel;

	public TreeTableModelAdapter(final TreeTableModel treeTableModel, final JTree tree) {

		this.tree = tree;
		this.treeTableModel = treeTableModel;

		tree.addTreeExpansionListener(new TreeExpansionListener() {
			// Don't use fireTableRowsInserted() here; the selection model
			// would get updated twice.
			@Override
			public void treeExpanded(final TreeExpansionEvent event) {

				TreeTableModelAdapter.this.fireTableDataChanged();
			}

			@Override
			public void treeCollapsed(final TreeExpansionEvent event) {

				TreeTableModelAdapter.this.fireTableDataChanged();
			}

		});

		// Install a TreeModelListener that can update the table when
		// tree changes. We use delayedFireTableDataChanged as we can
		// not be guaranteed the tree will have finished processing
		// the event before us.
		treeTableModel.addTreeModelListener(new TreeModelListener() {
			@Override
			public void treeNodesChanged(final TreeModelEvent e) {

				TreeTableModelAdapter.this.delayedFireTableDataChanged();
			}

			@Override
			public void treeNodesInserted(final TreeModelEvent e) {

				TreeTableModelAdapter.this.delayedFireTableDataChanged();
			}

			@Override
			public void treeNodesRemoved(final TreeModelEvent e) {

				TreeTableModelAdapter.this.delayedFireTableDataChanged();
			}

			@Override
			public void treeStructureChanged(final TreeModelEvent e) {

				TreeTableModelAdapter.this.delayedFireTableDataChanged();
			}

		});
	}

	// Wrappers, implementing TableModel interface.
	@Override
	public int getColumnCount() {

		return this.treeTableModel.getColumnCount();
	}

	@Override
	public String getColumnName(final int column) {

		return this.treeTableModel.getColumnName(column);
	}

	@Override
	public Class<?> getColumnClass(final int column) {

		return this.treeTableModel.getColumnClass(column);
	}

	@Override
	public int getRowCount() {

		return this.tree.getRowCount();
	}

	protected Object nodeForRow(final int row) {

		final TreePath treePath = this.tree.getPathForRow(row);
		return treePath.getLastPathComponent();
	}

	@Override
	public Object getValueAt(final int row, final int column) {

		return this.treeTableModel.getValueAt(nodeForRow(row), column);
	}

	@Override
	public boolean isCellEditable(final int row, final int column) {

		return this.treeTableModel.isCellEditable(nodeForRow(row), column);
	}

	@Override
	public void setValueAt(final Object value, final int row, final int column) {

		this.treeTableModel.setValueAt(value, nodeForRow(row), column);
	}

	/**
	 * Invokes fireTableDataChanged after all the pending events have been processed.
	 * SwingUtilities.invokeLater is used to handle this.
	 */
	protected void delayedFireTableDataChanged() {

		SwingUtilities.invokeLater(() -> TreeTableModelAdapter.this.fireTableDataChanged());
	}

}
