package com.codigo.aplios.gui.control.grid;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TableRowRenderingTip extends JPanel {

	private static final long serialVersionUID = 6333573922522726215L;

	public TableRowRenderingTip() {

		final Object[] columnNames = { "Type", "Company", "Shares", "Price", "Boolean" };
		final Object[][] data = { { "Buy", "IBM", Integer.valueOf(1000), Double.valueOf(80.5), Boolean.TRUE }, {} };

		final DefaultTableModel model = new DefaultTableModel(
			data, columnNames) {

			private static final long serialVersionUID = -5681989186521474211L;

			@Override
			public Class<?> getColumnClass(final int column) {

				return getValueAt(0, column).getClass();
			}
		};

		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Alternating", createAlternating(model));
		tabbedPane.addTab("Border", createBorder(model));
		tabbedPane.addTab("Data", createData(model));
		this.add(tabbedPane);
	}

	private JComponent createAlternating(final DefaultTableModel model) {

		final JTable table = new JTable(
			model) {

			private static final long serialVersionUID = 4431659024472592674L;

			@Override
			public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {

				final Component c = super.prepareRenderer(renderer, row, column);

				// Alternate row color

				if (!isRowSelected(row))
					c.setBackground((row % 2) == 0 ? getBackground() : Color.LIGHT_GRAY);

				return c;
			}
		};

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
		return new JScrollPane(
			table);
	}

	private JComponent createBorder(final DefaultTableModel model) {

		final JTable table = new JTable(
			model) {

			private static final long	serialVersionUID	= 4796513908683705227L;
			private final Border		outside				= new MatteBorder(
				1, 0, 1, 0, Color.ORANGE);
			private final Border		inside				= new EmptyBorder(
				0, 1, 0, 1);
			private final Border		highlight			= new CompoundBorder(
				this.outside, this.inside);

			@Override
			public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {

				final Component c = super.prepareRenderer(renderer, row, column);
				final JComponent jc = (JComponent) c;

				// Add a border to the selected row

				if (isRowSelected(row))
					jc.setBorder(this.highlight);

				return c;
			}
		};

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
		return new JScrollPane(
			table);
	}

	private JComponent createData(final DefaultTableModel model) {

		final JTable table = new JTable(
			model) {

			private static final long serialVersionUID = 4515189191319036608L;

			@Override
			public Component prepareRenderer(final TableCellRenderer renderer, final int row, final int column) {

				final Component c = super.prepareRenderer(renderer, row, column);

				// Color row based on a cell value

				if (!isRowSelected(row)) {
					c.setBackground(getBackground());
					final int modelRow = convertRowIndexToModel(row);
					final String type = (String) getModel().getValueAt(modelRow, 0);
					if ("Buy".equals(type))
						c.setBackground(Color.GREEN);
					if ("Sell".equals(type))
						c.setBackground(Color.YELLOW);
					if ("Short Sell".equals(type))
						c.setBackground(Color.CYAN);
				}

				return c;
			}
		};

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.changeSelection(0, 0, false, false);
		table.setAutoCreateRowSorter(true);
		return new JScrollPane(
			table);
	}

	public static void main(final String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.invokeLater(() -> TableRowRenderingTip.createAndShowGUI());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void createAndShowGUI() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		final JFrame frame = new JFrame(
			"Table Row Rendering");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new TableRowRenderingTip());
		frame.setSize(800, 600);
		// frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}