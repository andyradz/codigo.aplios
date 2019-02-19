package com.codigo.aplios.gui.core.gridview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

// Example from http://www.crionics.com/products/opensource/faq/swing_ex/SwingExamples.html
// http://www.java2s.com/Code/Java/Swing-Components/MultiSpanCellTableExample.htm
// http://www.java2s.com/Code/Java/Swing-Components/HideColumnTableExample.htm
// http://www.java2s.com/Code/Java/Swing-Components/JSortTable.htm
/**
 * @version 1.0 11/26/98
 */
@SuppressWarnings("serial")
public class MultipleTableCell extends JFrame {

	public MultipleTableCell() {

		super(
				"Multi-Span Cell Example");

		final AttributiveCellTableModel ml = new AttributiveCellTableModel(
			10, 6);
		/*
		 * AttributiveCellTableModel ml = new AttributiveCellTableModel(10,6) { public Object getValueAt(int
		 * row, int col) { return "" + row + ","+ col; } };
		 */
		final CellSpan cellAtt = (CellSpan) ml.getCellAttribute();
		final MultiSpanCellTable table = new MultiSpanCellTable(
			ml);
		final JScrollPane scroll = new JScrollPane(
			table);

		final JButton b_one = new JButton(
			"Combine");
		b_one.addActionListener(e -> {

			final int[] columns = table.getSelectedColumns();
			final int[] rows = table.getSelectedRows();
			cellAtt.combine(rows, columns);
			table.clearSelection();
			table.revalidate();
			table.repaint();
		});
		final JButton b_split = new JButton(
			"Split");
		b_split.addActionListener(e -> {

			final int column = table.getSelectedColumn();
			final int row = table.getSelectedRow();
			cellAtt.split(row, column);
			table.clearSelection();
			table.revalidate();
			table.repaint();
		});
		final JPanel p_buttons = new JPanel();
		p_buttons.setLayout(new GridLayout(
			2, 1));
		p_buttons.add(b_one);
		p_buttons.add(b_split);

		final Box box = new Box(
			BoxLayout.X_AXIS);
		box.add(scroll);
		box.add(new JSeparator(
			SwingConstants.HORIZONTAL));
		box.add(p_buttons);
		getContentPane().add(box);
		this.setSize(400, 200);
		setVisible(true);
	}

	public static void main(final String[] args) {

		final MultipleTableCell frame = new MultipleTableCell();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {

				System.exit(0);
			}

		});
	}

}

/**
 * @version 1.0 11/22/98
 */
class AttributiveCellTableModel extends DefaultTableModel {

	private static final long	serialVersionUID	= -2570526096808961583L;
	protected CellAttribute		cellAtt;

	public AttributiveCellTableModel() {

		this(
				(Vector<?>) null,
				0);
	}

	public AttributiveCellTableModel(final int numRows, final int numColumns) {

		final Vector<?> names = new Vector(
			numColumns);
		names.setSize(numColumns);
		this.setColumnIdentifiers(names);
		this.dataVector = new Vector<>();
		setNumRows(numRows);
		this.cellAtt = new DefaultCellAttribute(
			numRows, numColumns);
	}

	public AttributiveCellTableModel(final Vector<?> columnNames, final int numRows) {

		this.setColumnIdentifiers(columnNames);
		this.dataVector = new Vector<>();
		setNumRows(numRows);
		this.cellAtt = new DefaultCellAttribute(
			numRows, columnNames.size());
	}

	public AttributiveCellTableModel(final Object[] columnNames, final int numRows) {

		this(
				DefaultTableModel.convertToVector(columnNames),
				numRows);
	}

	public AttributiveCellTableModel(final Vector data, final Vector columnNames) {

		this.setDataVector(data, columnNames);
	}

	public AttributiveCellTableModel(final Object[][] data, final Object[] columnNames) {

		this.setDataVector(data, columnNames);
	}

	@Override
	public void setDataVector(final Vector newData, final Vector columnNames) {

		if (newData == null)
			throw new IllegalArgumentException(
				"setDataVector() - Null parameter");
		this.dataVector = new Vector<>(
			0);
		this.setColumnIdentifiers(columnNames);
		this.dataVector = newData;

		//
		this.cellAtt = new DefaultCellAttribute(
			this.dataVector.size(), this.columnIdentifiers.size());

		newRowsAdded(new TableModelEvent(
			this, 0, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	@Override
	public void addColumn(final Object columnName, final Vector<?> columnData) {

		if (columnName == null)
			throw new IllegalArgumentException(
				"addColumn() - null parameter");
		this.columnIdentifiers.addElement(columnName);
		int index = 0;
		final Enumeration eeration = this.dataVector.elements();
		while (eeration.hasMoreElements()) {
			Object value;
			if ((columnData != null) && (index < columnData.size()))
				value = columnData.elementAt(index);
			else
				value = null;
			((Vector) eeration.nextElement()).addElement(value);
			index++;
		}

		//
		this.cellAtt.addColumn();

		fireTableStructureChanged();
	}

	@Override
	public void addRow(final Vector rowData) {

		Vector newData = null;
		if (rowData == null)
			newData = new Vector(
				getColumnCount());
		else
			rowData.setSize(getColumnCount());
		this.dataVector.addElement(newData);

		//
		this.cellAtt.addRow();

		newRowsAdded(new TableModelEvent(
			this, getRowCount() - 1, getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	@Override
	public void insertRow(final int row, Vector rowData) {

		if (rowData == null)
			rowData = new Vector(
				getColumnCount());
		else
			rowData.setSize(getColumnCount());

		this.dataVector.insertElementAt(rowData, row);

		//
		this.cellAtt.insertRow(row);

		newRowsAdded(new TableModelEvent(
			this, row, row, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
	}

	public CellAttribute getCellAttribute() {

		return this.cellAtt;
	}

	public void setCellAttribute(final CellAttribute newCellAtt) {

		final int numColumns = getColumnCount();
		final int numRows = getRowCount();
		if ((newCellAtt.getSize().width != numColumns) || (newCellAtt.getSize().height != numRows))
			newCellAtt.setSize(new Dimension(
				numRows, numColumns));
		this.cellAtt = newCellAtt;
		fireTableDataChanged();
	}

	/*
	 * public void changeCellAttribute(int row, int column, Object command) {
	 * cellAtt.changeAttribute(row, column, command); }
	 *
	 * public void changeCellAttribute(int[] rows, int[] columns, Object command) {
	 * cellAtt.changeAttribute(rows, columns, command); }
	 */
}

/**
 * @version 1.0 11/22/98
 */
class DefaultCellAttribute
		// implements CellAttribute ,CellSpan {
		implements CellAttribute, CellSpan, ColoredCell, CellFont {

	//
	// !!!! CAUTION !!!!!
	// these values must be synchronized to Table data
	//
	protected int rowSize;

	protected int columnSize;

	protected int[][][] span; // CellSpan

	protected Color[][] foreground; // ColoredCell

	protected Color[][] background; //

	protected Font[][] font; // CellFont

	public DefaultCellAttribute() {

		this(
				1,
				1);
	}

	public DefaultCellAttribute(final int numRows, final int numColumns) {

		setSize(new Dimension(
			numColumns, numRows));
	}

	protected void initValue() {

		for (int i = 0; i < this.span.length; i++)
			for (int j = 0; j < this.span[i].length; j++) {
				this.span[i][j][CellSpan.COLUMN] = 1;
				this.span[i][j][CellSpan.ROW] = 1;
			}
	}

	//
	// CellSpan
	//
	@Override
	public int[] getSpan(final int row, final int column) {

		if (this.isOutOfBounds(row, column)) {
			final int[] ret_code = { 1, 1 };
			return ret_code;
		}
		return this.span[row][column];
	}

	@Override
	public void setSpan(final int[] span, final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.span[row][column] = span;
	}

	@Override
	public boolean isVisible(final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return false;
		if ((this.span[row][column][CellSpan.COLUMN] < 1) || (this.span[row][column][CellSpan.ROW] < 1))
			return false;
		return true;
	}

	@Override
	public void combine(final int[] rows, final int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		final int rowSpan = rows.length;
		final int columnSpan = columns.length;
		final int startRow = rows[0];
		final int startColumn = columns[0];
		for (int i = 0; i < rowSpan; i++)
			for (int j = 0; j < columnSpan; j++)
				if ((this.span[startRow + i][startColumn + j][CellSpan.COLUMN] != 1)
						|| (this.span[startRow + i][startColumn + j][CellSpan.ROW] != 1))
					// System.out.println("can't combine");
					return;
		for (int i = 0, ii = 0; i < rowSpan; i++, ii--)
			for (int j = 0, jj = 0; j < columnSpan; j++, jj--) {
				this.span[startRow + i][startColumn + j][CellSpan.COLUMN] = jj;
				this.span[startRow + i][startColumn + j][CellSpan.ROW] = ii;
				// System.out.println("r " +ii +" c " +jj);
			}
		this.span[startRow][startColumn][CellSpan.COLUMN] = columnSpan;
		this.span[startRow][startColumn][CellSpan.ROW] = rowSpan;

	}

	@Override
	public void split(final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return;
		final int columnSpan = this.span[row][column][CellSpan.COLUMN];
		final int rowSpan = this.span[row][column][CellSpan.ROW];
		for (int i = 0; i < rowSpan; i++)
			for (int j = 0; j < columnSpan; j++) {
				this.span[row + i][column + j][CellSpan.COLUMN] = 1;
				this.span[row + i][column + j][CellSpan.ROW] = 1;
			}
	}

	//
	// ColoredCell
	//
	@Override
	public Color getForeground(final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return null;
		return this.foreground[row][column];
	}

	@Override
	public void setForeground(final Color color, final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.foreground[row][column] = color;
	}

	@Override
	public void setForeground(final Color color, final int[] rows, final int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		setValues(this.foreground, color, rows, columns);
	}

	@Override
	public Color getBackground(final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return null;
		return this.background[row][column];
	}

	@Override
	public void setBackground(final Color color, final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.background[row][column] = color;
	}

	@Override
	public void setBackground(final Color color, final int[] rows, final int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		setValues(this.background, color, rows, columns);
	}
	//

	//
	// CellFont
	//
	@Override
	public Font getFont(final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return null;
		return this.font[row][column];
	}

	@Override
	public void setFont(final Font font, final int row, final int column) {

		if (this.isOutOfBounds(row, column))
			return;
		this.font[row][column] = font;
	}

	@Override
	public void setFont(final Font font, final int[] rows, final int[] columns) {

		if (this.isOutOfBounds(rows, columns))
			return;
		setValues(this.font, font, rows, columns);
	}
	//

	//
	// CellAttribute
	//
	@Override
	public void addColumn() {

		final int[][][] oldSpan = this.span;
		final int numRows = oldSpan.length;
		final int numColumns = oldSpan[0].length;
		this.span = new int[numRows][numColumns + 1][2];
		System.arraycopy(oldSpan, 0, this.span, 0, numRows);
		for (int i = 0; i < numRows; i++) {
			this.span[i][numColumns][CellSpan.COLUMN] = 1;
			this.span[i][numColumns][CellSpan.ROW] = 1;
		}
	}

	@Override
	public void addRow() {

		final int[][][] oldSpan = this.span;
		final int numRows = oldSpan.length;
		final int numColumns = oldSpan[0].length;
		this.span = new int[numRows + 1][numColumns][2];
		System.arraycopy(oldSpan, 0, this.span, 0, numRows);
		for (int i = 0; i < numColumns; i++) {
			this.span[numRows][i][CellSpan.COLUMN] = 1;
			this.span[numRows][i][CellSpan.ROW] = 1;
		}
	}

	@Override
	public void insertRow(final int row) {

		final int[][][] oldSpan = this.span;
		final int numRows = oldSpan.length;
		final int numColumns = oldSpan[0].length;
		this.span = new int[numRows + 1][numColumns][2];
		if (0 < row)
			System.arraycopy(oldSpan, 0, this.span, 0, row - 1);
		System.arraycopy(oldSpan, 0, this.span, row, numRows - row);
		for (int i = 0; i < numColumns; i++) {
			this.span[row][i][CellSpan.COLUMN] = 1;
			this.span[row][i][CellSpan.ROW] = 1;
		}
	}

	@Override
	public Dimension getSize() {

		return new Dimension(
			this.rowSize, this.columnSize);
	}

	@Override
	public void setSize(final Dimension size) {

		this.columnSize = size.width;
		this.rowSize = size.height;
		this.span = new int[this.rowSize][this.columnSize][2]; // 2: COLUMN,ROW
		this.foreground = new Color[this.rowSize][this.columnSize];
		this.background = new Color[this.rowSize][this.columnSize];
		this.font = new Font[this.rowSize][this.columnSize];
		initValue();
	}

	/*
	 * public void changeAttribute(int row, int column, Object command) { }
	 *
	 * public void changeAttribute(int[] rows, int[] columns, Object command) { }
	 */
	protected boolean isOutOfBounds(final int row, final int column) {

		if ((row < 0) || (this.rowSize <= row) || (column < 0) || (this.columnSize <= column))
			return true;
		return false;
	}

	protected boolean isOutOfBounds(final int[] rows, final int[] columns) {

		for (final int row2 : rows)
			if ((row2 < 0) || (this.rowSize <= row2))
				return true;
		for (final int column2 : columns)
			if ((column2 < 0) || (this.columnSize <= column2))
				return true;
		return false;
	}

	protected void setValues(final Object[][] target, final Object value, final int[] rows, final int[] columns) {

		for (final int row2 : rows) {
			final int row = row2;
			for (final int column2 : columns) {
				final int column = column2;
				target[row][column] = value;
			}
		}
	}

}

/*
 * (swing1.1beta3)
 *
 */
/**
 * @version 1.0 11/22/98
 */
interface CellAttribute {

	public void addColumn();

	public void addRow();

	public void insertRow(int row);

	public Dimension getSize();

	public void setSize(Dimension size);

}

/*
 * (swing1.1beta3)
 *
 */
/**
 * @version 1.0 11/22/98
 */
interface CellSpan {

	public final int ROW = 0;

	public final int COLUMN = 1;

	public int[] getSpan(int row, int column);

	public void setSpan(int[] span, int row, int column);

	public boolean isVisible(int row, int column);

	public void combine(int[] rows, int[] columns);

	public void split(int row, int column);

}

/*
 * (swing1.1beta3)
 *
 */
/**
 * @version 1.0 11/26/98
 */
class MultiSpanCellTable extends JTable {

	private static final long serialVersionUID = 2664936063124898552L;

	public MultiSpanCellTable(final TableModel model) {

		super(
				model);
		this.setUI(new MultiSpanCellTableUI());
		getTableHeader().setReorderingAllowed(false);
		setCellSelectionEnabled(true);
		setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	}

	@Override
	public Rectangle getCellRect(int row, int column, final boolean includeSpacing) {

		final Rectangle sRect = super.getCellRect(row, column, includeSpacing);
		if ((row < 0) || (column < 0) || (getRowCount() <= row) || (getColumnCount() <= column))
			return sRect;
		final CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel()).getCellAttribute();
		if (!cellAtt.isVisible(row, column)) {
			final int temp_row = row;
			final int temp_column = column;
			row += cellAtt.getSpan(temp_row, temp_column)[CellSpan.ROW];
			column += cellAtt.getSpan(temp_row, temp_column)[CellSpan.COLUMN];
		}
		final int[] n = cellAtt.getSpan(row, column);

		int index = 0;
		final int columnMargin = getColumnModel().getColumnMargin();
		final Rectangle cellFrame = new Rectangle();
		final int aCellHeight = this.rowHeight + this.rowMargin;
		cellFrame.y = row * aCellHeight;
		cellFrame.height = n[CellSpan.ROW] * aCellHeight;

		final Enumeration eeration = getColumnModel().getColumns();
		while (eeration.hasMoreElements()) {
			final TableColumn aColumn = (TableColumn) eeration.nextElement();
			cellFrame.width = aColumn.getWidth() + columnMargin;
			if (index == column)
				break;
			cellFrame.x += cellFrame.width;
			index++;
		}
		for (int i = 0; i < (n[CellSpan.COLUMN] - 1); i++) {
			final TableColumn aColumn = (TableColumn) eeration.nextElement();
			cellFrame.width += aColumn.getWidth() + columnMargin;
		}

		if (!includeSpacing) {
			final Dimension spacing = getIntercellSpacing();
			cellFrame.setBounds(cellFrame.x + (spacing.width / 2), cellFrame.y + (spacing.height / 2),
					cellFrame.width - spacing.width, cellFrame.height - spacing.height);
		}
		return cellFrame;
	}

	private int[] rowColumnAtPoint(final Point point) {

		final int[] retValue = { -1, -1 };
		final int row = point.y / (this.rowHeight + this.rowMargin);
		if ((row < 0) || (getRowCount() <= row))
			return retValue;
		final int column = getColumnModel().getColumnIndexAtX(point.x);

		final CellSpan cellAtt = (CellSpan) ((AttributiveCellTableModel) getModel()).getCellAttribute();

		if (cellAtt.isVisible(row, column)) {
			retValue[CellSpan.COLUMN] = column;
			retValue[CellSpan.ROW] = row;
			return retValue;
		}
		retValue[CellSpan.COLUMN] = column + cellAtt.getSpan(row, column)[CellSpan.COLUMN];
		retValue[CellSpan.ROW] = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
		return retValue;
	}

	@Override
	public int rowAtPoint(final Point point) {

		return rowColumnAtPoint(point)[CellSpan.ROW];
	}

	@Override
	public int columnAtPoint(final Point point) {

		return rowColumnAtPoint(point)[CellSpan.COLUMN];
	}

	@Override
	public void columnSelectionChanged(final ListSelectionEvent e) {

		this.repaint();
	}

	@Override
	public void valueChanged(final ListSelectionEvent e) {

		final int firstIndex = e.getFirstIndex();
		final int lastIndex = e.getLastIndex();
		if ((firstIndex == -1) && (lastIndex == -1))
			this.repaint();
		final Rectangle dirtyRegion = getCellRect(firstIndex, 0, false);
		final int numCoumns = getColumnCount();
		int index = firstIndex;
		for (int i = 0; i < numCoumns; i++)
			dirtyRegion.add(getCellRect(index, i, false));
		index = lastIndex;
		for (int i = 0; i < numCoumns; i++)
			dirtyRegion.add(getCellRect(index, i, false));
		this.repaint(dirtyRegion.x, dirtyRegion.y, dirtyRegion.width, dirtyRegion.height);
	}

}

/**
 * @version 1.0 11/26/98
 */
class MultiSpanCellTableUI extends BasicTableUI {

	@Override
	public void paint(final Graphics g, final JComponent c) {

		final Rectangle oldClipBounds = g.getClipBounds();
		final Rectangle clipBounds = new Rectangle(
			oldClipBounds);
		final int tableWidth = this.table.getColumnModel()
				.getTotalColumnWidth();
		clipBounds.width = Math.min(clipBounds.width, tableWidth);
		g.setClip(clipBounds);

		final int firstIndex = this.table.rowAtPoint(new Point(
			0, clipBounds.y));
		final int lastIndex = this.table.getRowCount() - 1;

		final Rectangle rowRect = new Rectangle(
			0, 0, tableWidth, this.table.getRowHeight() + this.table.getRowMargin());
		rowRect.y = firstIndex * rowRect.height;

		for (int index = firstIndex; index <= lastIndex; index++) {
			if (rowRect.intersects(clipBounds))
				// System.out.println(); // debug
				// System.out.print("" + index +": "); // row
				paintRow(g, index);
			rowRect.y += rowRect.height;
		}
		g.setClip(oldClipBounds);
	}

	private void paintRow(final Graphics g, final int row) {

		final Rectangle rect = g.getClipBounds();
		boolean drawn = false;

		final AttributiveCellTableModel tableModel = (AttributiveCellTableModel) this.table.getModel();
		final CellSpan cellAtt = (CellSpan) tableModel.getCellAttribute();
		final int numColumns = this.table.getColumnCount();

		for (int column = 0; column < numColumns; column++) {
			final Rectangle cellRect = this.table.getCellRect(row, column, true);
			int cellRow, cellColumn;
			if (cellAtt.isVisible(row, column)) {
				cellRow = row;
				cellColumn = column;
				// System.out.print(" "+column+" "); // debug
			}
			else {
				cellRow = row + cellAtt.getSpan(row, column)[CellSpan.ROW];
				cellColumn = column + cellAtt.getSpan(row, column)[CellSpan.COLUMN];
				// System.out.print(" ("+column+")"); // debug
			}
			if (cellRect.intersects(rect)) {
				drawn = true;
				paintCell(g, cellRect, cellRow, cellColumn);
			}
			else if (drawn)
				break;
		}

	}

	private void paintCell(final Graphics g, final Rectangle cellRect, final int row, final int column) {

		final int spacingHeight = this.table.getRowMargin();
		final int spacingWidth = this.table.getColumnModel()
				.getColumnMargin();

		final Color c = g.getColor();
		g.setColor(this.table.getGridColor());
		g.drawRect(cellRect.x, cellRect.y, cellRect.width - 1, cellRect.height - 1);
		g.setColor(c);

		cellRect.setBounds(cellRect.x + (spacingWidth / 2), cellRect.y + (spacingHeight / 2),
				cellRect.width - spacingWidth, cellRect.height - spacingHeight);

		if (this.table.isEditing() && (this.table.getEditingRow() == row)
				&& (this.table.getEditingColumn() == column)) {
			final Component component = this.table.getEditorComponent();
			component.setBounds(cellRect);
			component.validate();
		}
		else {
			final TableCellRenderer renderer = this.table.getCellRenderer(row, column);
			final Component component = this.table.prepareRenderer(renderer, row, column);

			if (component.getParent() == null)
				this.rendererPane.add(component);
			this.rendererPane.paintComponent(g, component, this.table, cellRect.x, cellRect.y, cellRect.width,
					cellRect.height, true);
		}
	}

}

interface CellFont {

	public Font getFont(int row, int column);

	public void setFont(Font font, int row, int column);

	public void setFont(Font font, int[] rows, int[] columns);

}

interface ColoredCell {

	public Color getForeground(int row, int column);

	public void setForeground(Color color, int row, int column);

	public void setForeground(Color color, int[] rows, int[] columns);

	public Color getBackground(int row, int column);

	public void setBackground(Color color, int row, int column);

	public void setBackground(Color color, int[] rows, int[] columns);

}
