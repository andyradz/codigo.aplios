package com.codigo.aplios.gui.core.gridview;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 * @version 1.0 03/03/99
 */
public class IndicatorTableExample extends JPanel {

	private static final long serialVersionUID = -1968925955553038674L;

	private static final int MAX = 100;

	private static final int MIN = 0;

	public IndicatorTableExample() {

		setLayout(new BorderLayout());

		final DefaultTableModel dm = new DefaultTableModel() {

			private static final long serialVersionUID = -7055226212230000655L;

			@Override
			public Class<?> getColumnClass(final int col) {

				switch (col) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				case 2:
					return Integer.class;
				default:
					return Object.class;
				}
			}

			@Override
			public boolean isCellEditable(final int row, final int col) {

				switch (col) {
				case 2:
					return false;
				default:
					return true;
				}
			}

			@Override
			public void setValueAt(final Object obj, final int row, final int col) {

				if (col != 1) {
					super.setValueAt(obj, row, col);
					return;
				}
				try {
					final Integer integer = Integer.valueOf(obj.toString());
					super.setValueAt(IndicatorTableExample.this.checkMinMax(integer), row, col);
				}
				catch (final NumberFormatException ex) {
					ex.printStackTrace();
				}
			}

		};
		dm.setDataVector(
				new Object[][] {
						{ "not human", Integer.valueOf(100), Integer.valueOf(100) },
						{ "hard worker", Integer.valueOf(76), Integer.valueOf(76) },
						{ "ordinary guy", Integer.valueOf(51), Integer.valueOf(51) },
						{ "lazy fellow", Integer.valueOf(12), Integer.valueOf(12) } },
				new Object[] { "Name", "Result", "Indicator" });

		final JTable table = new JTable(
			dm);
		table.setRowHeight(21);

		final IndicatorCellRenderer renderer = new IndicatorCellRenderer(
			IndicatorTableExample.MIN, IndicatorTableExample.MAX);
		renderer.setStringPainted(true);
		renderer.setBackground(table.getBackground());

		// set limit value and fill color
		final Hashtable<Integer, Color> limitColors = new Hashtable<>();
		limitColors.put(Integer.valueOf(0), Color.green);
		limitColors.put(Integer.valueOf(60), Color.yellow);
		limitColors.put(Integer.valueOf(80), Color.CYAN);
		renderer.setLimits(limitColors);
		table.getColumnModel()
				.getColumn(2)
				.setCellRenderer(renderer);

		table.getModel()
				.addTableModelListener(e -> {

					if (e.getType() == TableModelEvent.UPDATE) {
						int col = e.getColumn();
						if (col == 1) {
							final int row = e.getFirstRow();
							final TableModel model = (TableModel) e.getSource();
							final Integer value = (Integer) model.getValueAt(row, col);
							model.setValueAt(IndicatorTableExample.this.checkMinMax(value), row, ++col);
						}
					}
				});

		final JScrollPane pane = new JScrollPane(
			table);
		this.add(pane, BorderLayout.CENTER);
	}

	public static void main(final String[] args) {

		final JFrame f = new JFrame(
			"IndicatorTable Example");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		f.getContentPane()
				.add(new IndicatorTableExample(), BorderLayout.CENTER);
		f.setSize(400, 120);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {

				System.exit(0);
			}

		});
	}

	private Integer checkMinMax(final Integer value) {

		int intValue = value.intValue();
		if (intValue < IndicatorTableExample.MIN)
			intValue = IndicatorTableExample.MIN;
		else if (IndicatorTableExample.MAX < intValue)
			intValue = IndicatorTableExample.MAX;
		return Integer.valueOf(intValue);
	}

}

/**
 * @version 1.0 03/03/99
 */
class IndicatorCellRenderer extends JProgressBar implements TableCellRenderer {

	private static final long serialVersionUID = 2779629635308191814L;

	private Hashtable<Integer, Color> limitColors;

	private int[] limitValues;

	public IndicatorCellRenderer() {

		super(
				SwingConstants.HORIZONTAL);
		setBorderPainted(false);
	}

	public IndicatorCellRenderer(final int min, final int max) {

		super(
				SwingConstants.HORIZONTAL,
				min,
				max);
		setBorderPainted(false);
	}

	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {

		int n = 0;
		if (!(value instanceof Number)) {
			String str;
			if (value instanceof String)
				str = (String) value;
			else
				str = value.toString();
			try {
				n = Integer.valueOf(str)
						.intValue();
			}
			catch (final NumberFormatException ex) {
			}
		}
		else
			n = ((Number) value).intValue();
		final Color color = getColor(n);
		if (color != null)
			setForeground(color);
		setValue(n);
		return this;
	}

	public void setLimits(final Hashtable limitColors) {

		this.limitColors = limitColors;
		int i = 0;
		final int n = limitColors.size();
		this.limitValues = new int[n];
		final Enumeration<Integer> e = limitColors.keys();
		while (e.hasMoreElements())
			this.limitValues[i++] = e.nextElement()
					.intValue();
		sort(this.limitValues);
	}

	private Color getColor(final int value) {

		Color color = null;
		if (this.limitValues != null) {
			int i;
			for (i = 0; i < this.limitValues.length; i++)
				if (this.limitValues[i] < value)
					color = this.limitColors.get(Integer.valueOf(this.limitValues[i]));
		}
		return color;
	}

	private void sort(final int[] a) {

		final int n = a.length;
		for (int i = 0; i < (n - 1); i++) {
			int k = i;
			for (int j = i + 1; j < n; j++)
				if (a[j] < a[k])
					k = j;
			final int tmp = a[i];
			a[i] = a[k];
			a[k] = tmp;
		}
	}

}
