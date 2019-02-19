package com.codigo.aplios.gui.control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ToolTipTable {
	public static void main(final String[] args) {

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (final Exception evt) {
		}

		final JFrame f = new JFrame(
			"Tool Tip Table");
		final JTable tbl = new JTable(
			new CurrencyTableModel());
		tbl.setDefaultRenderer(java.lang.Number.class, new ToolTipFractionCellRenderer(
			10, 3, 6, SwingConstants.RIGHT));

		final TableColumnModel tcm = tbl.getColumnModel();
		tcm.getColumn(0)
				.setPreferredWidth(150);
		tcm.getColumn(0)
				.setMinWidth(150);
		final TextWithIconCellRenderer renderer = new TextWithIconCellRenderer();
		tcm.getColumn(0)
				.setCellRenderer(renderer);
		tcm.getColumn(1)
				.setPreferredWidth(100);
		tcm.getColumn(1)
				.setMinWidth(100);

		// Add the stripe renderer.
		StripedTableCellRenderer.installInTable(tbl, Color.lightGray, Color.white, null, null);

		// Add the custom header renderer
		final MultiLineHeaderRenderer headerRenderer = new MultiLineHeaderRenderer(
			SwingConstants.CENTER, SwingConstants.BOTTOM);
		headerRenderer.setBackground(Color.blue);
		headerRenderer.setForeground(Color.white);
		headerRenderer.setFont(new Font(
			"Dialog", Font.BOLD, 12));
		final int columns = ToolTipTable.tableHeaders.length;
		for (int i = 0; i < columns; i++) {
			tcm.getColumn(i)
					.setHeaderRenderer(headerRenderer);
			tcm.getColumn(i)
					.setHeaderValue(ToolTipTable.tableHeaders[i]);
		}

		tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.setPreferredScrollableViewportSize(tbl.getPreferredSize());

		final JScrollPane sp = new JScrollPane(
			tbl);
		f.getContentPane()
				.add(sp, "Center");
		f.pack();
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent evt) {

				System.exit(0);
			}
		});
		f.setVisible(true);
	}

	// Header values. Note the table model provides
	// string column names that are the default header
	// values.
	public static Object[][] tableHeaders = new Object[][] {
			new String[] { "Currency" },
			new String[] { "Yesterday's", "Rate" },
			new String[] { "Today's", "Rate" },
			new String[] { "Rate", "Change" } };
}

@SuppressWarnings("serial")
class FractionCellRenderer extends DefaultTableCellRenderer {
	public FractionCellRenderer(final int integer, final int fraction, final int align) {

		this.integer = integer; // maximum integer digits
		this.fraction = fraction; // exact number of fraction digits
		this.align = align; // alignment (LEFT, CENTER, RIGHT)
	}

	@Override
	protected void setValue(final Object value) {

		if ((value != null) && (value instanceof Number)) {
			FractionCellRenderer.formatter.setMaximumIntegerDigits(this.integer);
			FractionCellRenderer.formatter.setMaximumFractionDigits(this.fraction);
			FractionCellRenderer.formatter.setMinimumFractionDigits(this.fraction);
			setText(FractionCellRenderer.formatter.format(((Number) value).doubleValue()));
		}
		else
			super.setValue(value);
		setHorizontalAlignment(this.align);
	}

	protected int integer;

	protected int fraction;

	protected int align;

	protected static NumberFormat formatter = NumberFormat.getInstance();
}

class TextWithIconCellRenderer extends DefaultTableCellRenderer {
	@Override
	protected void setValue(final Object value) {

		setText("");
		setIcon(null);
		if (value instanceof DataWithIcon) {
			if (value != null) {
				final DataWithIcon d = (DataWithIcon) value;
				final Object dataValue = d.getData();

				setText(dataValue == null ? "" : dataValue.toString());
				setIcon(d.getIcon());
				setHorizontalTextPosition(SwingConstants.RIGHT);
				setVerticalTextPosition(SwingConstants.CENTER);
				setHorizontalAlignment(SwingConstants.LEFT);
				setVerticalAlignment(SwingConstants.CENTER);
			}
		}
		else
			super.setValue(value);
	}
}

class MultiLineHeaderRenderer extends JPanel implements TableCellRenderer {

	private static final long serialVersionUID = -2611067759297112439L;

	public MultiLineHeaderRenderer(final int horizontalAlignment, final int verticalAlignment) {

		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
		switch (horizontalAlignment) {
		case SwingConstants.LEFT:
			this.alignmentX = (float) 0.0;
			break;

		case SwingConstants.CENTER:
			this.alignmentX = (float) 0.5;
			break;

		case SwingConstants.RIGHT:
			this.alignmentX = (float) 1.0;
			break;

		default:
			throw new IllegalArgumentException(
				"Illegal horizontal alignment value");
		}
		setBorder(this.headerBorder);
		setLayout(new BoxLayout(
			this, BoxLayout.Y_AXIS));
		setOpaque(true);

		this.background = null;
	}

	@Override
	public void setForeground(final Color foreground) {

		this.foreground = foreground;
		super.setForeground(foreground);
	}

	@Override
	public void setBackground(final Color background) {

		this.background = background;
		super.setBackground(background);
	}

	@Override
	public void setFont(final Font font) {

		this.font = font;
	}

	// Implementation of TableCellRenderer interface
	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {

		removeAll();
		invalidate();

		if (value == null)
			// Do nothing if no value
			return this;

		// Set the foreground and background colors
		// from the table header if they are not set
		if (table != null) {
			final JTableHeader header = table.getTableHeader();
			if (header != null) {
				if (this.foreground == null)
					super.setForeground(header.getForeground());

				if (this.background == null)
					super.setBackground(header.getBackground());
			}
		}

		if (this.verticalAlignment != SwingConstants.TOP)
			add(Box.createVerticalGlue());

		Object[] values;
		int length;
		if (value instanceof Object[])
			// Input is an array - use it
			values = (Object[]) value;
		else {
			// Not an array - turn it into one
			values = new Object[1];
			values[0] = value;
		}
		length = values.length;

		// Configure each row of the header using
		// a separate JLabel. If a given row is
		// a JComponent, add it directly..
		for (int i = 0; i < length; i++) {
			final Object thisRow = values[i];

			if (thisRow instanceof JComponent)
				add((JComponent) thisRow);
			else {
				final JLabel l = new JLabel();
				setValue(l, thisRow, i);
				add(l);
			}
		}

		if (this.verticalAlignment != SwingConstants.BOTTOM)
			add(Box.createVerticalGlue());
		return this;
	}

	// Configures a label for one line of the header.
	// This can be overridden by derived classes
	protected void setValue(final JLabel l, final Object value, final int lineNumber) {

		if ((value != null) && (value instanceof Icon))
			l.setIcon((Icon) value);
		else
			l.setText(value == null ? "" : value.toString());
		l.setHorizontalAlignment(this.horizontalAlignment);
		l.setAlignmentX(this.alignmentX);
		l.setOpaque(false);
		l.setForeground(this.foreground);
		l.setFont(this.font);
	}

	protected int verticalAlignment;

	protected int horizontalAlignment;

	protected float alignmentX;

	// These attributes may be explicitly set
	// They are defaulted to the colors and attributes
	// of the table header
	protected Color foreground;

	protected Color background;

	// These attributes have fixed defaults
	protected Border headerBorder = UIManager.getBorder("TableHeader.cellBorder");

	protected Font font = UIManager.getFont("TableHeader.font");
}

class DataWithIcon {
	public DataWithIcon(final Object data, final Icon icon) {

		this.data = data;
		this.icon = icon;
	}

	public Icon getIcon() {

		return this.icon;
	}

	public Object getData() {

		return this.data;
	}

	@Override
	public String toString() {

		return this.data.toString();
	}

	protected Icon icon;

	protected Object data;
}

class CurrencyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 8401204432358133497L;

	protected String[] columnNames = { "Currency", "Yesterday", "Today", "Change" };

	// Constructor: calculate currency change to create the last column
	public CurrencyTableModel() {

		for (int i = 0; i < this.data.length; i++)
			this.data[i][CurrencyTableModel.DIFF_COLUMN] = Double
					.valueOf(((Double) this.data[i][CurrencyTableModel.NEW_RATE_COLUMN]).doubleValue()
							- ((Double) this.data[i][CurrencyTableModel.OLD_RATE_COLUMN]).doubleValue());
	}

	// Implementation of TableModel interface
	@Override
	public int getRowCount() {

		return this.data.length;
	}

	@Override
	public int getColumnCount() {

		return CurrencyTableModel.COLUMN_COUNT;
	}

	@Override
	public Object getValueAt(final int row, final int column) {

		return this.data[row][column];
	}

	@Override
	public Class<?> getColumnClass(final int column) {

		return (this.data[0][column]).getClass();
	}

	@Override
	public String getColumnName(final int column) {

		return this.columnNames[column];
	}

	protected static final int OLD_RATE_COLUMN = 1;

	protected static final int NEW_RATE_COLUMN = 2;

	protected static final int DIFF_COLUMN = 3;

	protected static final int COLUMN_COUNT = 4;

	protected static final Class<?> thisClass = CurrencyTableModel.class;

	protected Object[][] data = new Object[][] {
			{
					new DataWithIcon(
						"Belgian Franc", new ImageIcon(
							CurrencyTableModel.thisClass.getResource("belgium.gif"))),
					Double.valueOf(37.6460110),
					Double.valueOf(37.6508921),
					null },
			null };
}

class ToolTipFractionCellRenderer extends FractionCellRenderer {
	/**
	 *
	 */
	private static final long serialVersionUID = 7596304764121836978L;

	public ToolTipFractionCellRenderer(final int integer, final int fraction, final int maxFraction, final int align) {

		super(
				integer,
				fraction,
				align);
		this.maxFraction = maxFraction; // Number of tooltip fraction digits
	}

	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {

		final Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		if ((value != null) && (value instanceof Number)) {
			FractionCellRenderer.formatter.setMaximumIntegerDigits(this.integer);
			FractionCellRenderer.formatter.setMaximumFractionDigits(this.maxFraction);
			FractionCellRenderer.formatter.setMinimumFractionDigits(this.maxFraction);
			((JComponent) comp).setToolTipText(FractionCellRenderer.formatter.format(((Number) value).doubleValue()));
		}

		return comp;
	}

	protected int maxFraction;
}

class StripedTableCellRenderer implements TableCellRenderer {
	public StripedTableCellRenderer(final TableCellRenderer targetRenderer, final Color evenBack, final Color evenFore,
			final Color oddBack, final Color oddFore) {

		this.targetRenderer = targetRenderer;
		this.evenBack = evenBack;
		this.evenFore = evenFore;
		this.oddBack = oddBack;
		this.oddFore = oddFore;
	}

	// Implementation of TableCellRenderer interface
	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {

		TableCellRenderer renderer = this.targetRenderer;
		if (renderer == null)
			// Get default renderer from the table
			renderer = table.getDefaultRenderer(table.getColumnClass(column));

		// Let the real renderer create the component
		final Component comp = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		// Now apply the stripe effect
		if ((isSelected == false) && (hasFocus == false))
			if ((row & 1) == 0) {
				comp.setBackground(this.evenBack != null ? this.evenBack : table.getBackground());
				comp.setForeground(this.evenFore != null ? this.evenFore : table.getForeground());
			}
			else {
				comp.setBackground(this.oddBack != null ? this.oddBack : table.getBackground());
				comp.setForeground(this.oddFore != null ? this.oddFore : table.getForeground());
			}

		return comp;
	}

	// Convenience method to apply this renderer to single column
	public static void installInColumn(final JTable table, final int columnIndex, final Color evenBack,
			final Color evenFore, final Color oddBack, final Color oddFore) {

		final TableColumn tc = table.getColumnModel()
				.getColumn(columnIndex);

		// Get the cell renderer for this column, if any
		final TableCellRenderer targetRenderer = tc.getCellRenderer();

		// Create a new StripedTableCellRenderer and install it
		tc.setCellRenderer(new StripedTableCellRenderer(
			targetRenderer, evenBack, evenFore, oddBack, oddFore));
	}

	// Convenience method to apply this renderer to an entire table
	public static void installInTable(final JTable table, final Color evenBack, final Color evenFore,
			final Color oddBack, final Color oddFore) {

		StripedTableCellRenderer sharedInstance = null;
		final int columns = table.getColumnCount();
		for (int i = 0; i < columns; i++) {
			final TableColumn tc = table.getColumnModel()
					.getColumn(i);
			final TableCellRenderer targetRenderer = tc.getCellRenderer();
			if (targetRenderer != null)
				// This column has a specific renderer
				tc.setCellRenderer(new StripedTableCellRenderer(
					targetRenderer, evenBack, evenFore, oddBack, oddFore));
			else {
				// This column uses a class renderer - use a shared renderer
				if (sharedInstance == null)
					sharedInstance = new StripedTableCellRenderer(
						null, evenBack, evenFore, oddBack, oddFore);
				tc.setCellRenderer(sharedInstance);
			}
		}
	}

	protected TableCellRenderer targetRenderer;

	protected Color evenBack;

	protected Color evenFore;

	protected Color oddBack;

	protected Color oddFore;
}
