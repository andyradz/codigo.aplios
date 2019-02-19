package com.codigo.aplios.gui.core.gridview;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class JSortTable extends JTable implements MouseListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -572355956202200758L;

	protected int sortedColumnIndex = -1;

	protected boolean sortedColumnAscending = true;

	public JSortTable() {

		this(
				new DefaultSortTableModel());
	}

	public JSortTable(final int rows, final int cols) {

		this(
				new DefaultSortTableModel(
					rows, cols));
	}

	public JSortTable(final Object[][] data, final Object[] names) {

		this(
				new DefaultSortTableModel(
					data, names));
	}

	public JSortTable(final Vector<Object> data, final Vector<String> names) {

		this(
				new DefaultSortTableModel(
					data, names));
	}

	public JSortTable(final SortTableModel model) {

		super(
				model);
		initSortHeader();
	}

	public JSortTable(final SortTableModel model, final TableColumnModel colModel) {

		super(
				model,
				colModel);
		initSortHeader();
	}

	public JSortTable(final SortTableModel model, final TableColumnModel colModel, final ListSelectionModel selModel) {

		super(
				model,
				colModel,
				selModel);
		initSortHeader();
	}

	protected void initSortHeader() {

		final JTableHeader header = getTableHeader();
		header.setDefaultRenderer(new SortHeaderRenderer());
		header.addMouseListener(this);
	}

	public int getSortedColumnIndex() {

		return this.sortedColumnIndex;
	}

	public boolean isSortedColumnAscending() {

		return this.sortedColumnAscending;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(final MouseEvent event) {

		final TableColumnModel colModel = getColumnModel();
		final int index = colModel.getColumnIndexAtX(event.getX());
		final int modelIndex = colModel.getColumn(index)
				.getModelIndex();

		final SortTableModel model = (SortTableModel) getModel();
		if (model.isSortable(modelIndex)) {
			// toggle ascension, if already sorted
			if (this.sortedColumnIndex == index)
				this.sortedColumnAscending = !this.sortedColumnAscending;
			this.sortedColumnIndex = index;

			model.sortColumn(modelIndex, this.sortedColumnAscending);
		}
	}

	@Override
	public void mousePressed(final MouseEvent event) {

	}

	@Override
	public void mouseClicked(final MouseEvent event) {

	}

	@Override
	public void mouseEntered(final MouseEvent event) {

	}

	@Override
	public void mouseExited(final MouseEvent event) {

	}

}

/*
 *
 *
 * DefaultSortTableModel.java
 *
 * Created by Claude Duguay Copyright (c) 2002 This was taken from a Java Pro magazine article
 * http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208
 *
 * I have NOT asked for permission to use this.
 *
 *
 */
class DefaultSortTableModel extends DefaultTableModel implements SortTableModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 7812314309121990112L;

	public DefaultSortTableModel() {

	}

	public DefaultSortTableModel(final int rows, final int cols) {

		super(
				rows,
				cols);
	}

	public DefaultSortTableModel(final Object[][] data, final Object[] names) {

		super(
				data,
				names);
	}

	public DefaultSortTableModel(final Object[] names, final int rows) {

		super(
				names,
				rows);
	}

	public DefaultSortTableModel(final Vector<String> names, final int rows) {

		super(
				names,
				rows);
	}

	public DefaultSortTableModel(final Vector<Object> data, final Vector<String> names) {

		super();
		// super(data, names);
	}

	@Override
	public boolean isSortable(final int col) {

		return true;
	}

	@Override
	public void sortColumn(final int col, final boolean ascending) {

		Collections.sort(getDataVector(), new ColumnComparator<>(
			col, ascending));
	}
}

/*
 *
 *
 * SortTableModel.java
 *
 * Created by Claude Duguay Copyright (c) 2002 This was taken from a Java Pro magazine article
 * http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208
 *
 * I have NOT asked for permission to use this.
 *
 */
interface SortTableModel extends TableModel {

	public boolean isSortable(int col);

	public void sortColumn(int col, boolean ascending);

}

/*
 *
 *
 * ColumnComparator.java
 *
 * Created by Claude Duguay Copyright (c) 2002 This was taken from a Java Pro magazine article
 * http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208
 *
 * I have NOT asked for permission to use this.
 *
 *
 */
class ColumnComparator<T> implements Comparator<Object> {

	protected int index;

	protected boolean ascending;

	public ColumnComparator(final int index, final boolean ascending) {

		this.index = index;
		this.ascending = ascending;
	}

	@Override
	public int compare(final Object one, final Object two) {

		if ((one instanceof Vector) && (two instanceof Vector)) {
			final Vector<?> vOne = (Vector<?>) one;
			final Vector<?> vTwo = (Vector<?>) two;
			final Object oOne = vOne.elementAt(this.index);
			final Object oTwo = vTwo.elementAt(this.index);
			if ((oOne instanceof Comparable) && (oTwo instanceof Comparable)) {
				final Comparable<Object> cOne = (Comparable<Object>) oOne;
				final Comparable<Object> cTwo = (Comparable<Object>) oTwo;
				if (this.ascending)
					return cOne.compareTo(cTwo);
				else
					return cTwo.compareTo(cOne);
			}
		}

		return 1;
	}

}

/*
 *
 *
 * SortHeaderRenderer.java
 *
 * Created by Claude Duguay Copyright (c) 2002 This was taken from a Java Pro magazine article
 * http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208
 *
 * I have NOT asked for permission to use this.
 *
 *
 */
class SortHeaderRenderer extends DefaultTableCellRenderer {

	/**
	 * Atrybut definiuje unikalny identyfikator serializacji
	 */
	private static final long serialVersionUID = -255765111753704204L;

	public static final Icon NONSORTED = new SortArrowIcon(
		SortDirection.NONE);

	public static final Icon ASCENDING = new SortArrowIcon(
		SortDirection.ASCENDING);

	public static final Icon DECENDING = new SortArrowIcon(
		SortDirection.DECENDING);

	Border padding = BorderFactory.createEmptyBorder(0, 2, 0, 2);

	/**
	 * Domyślny konstruktor obiektu klasy.
	 */
	public SortHeaderRenderer() {

		setHorizontalTextPosition(SwingConstants.LEFT);
		setHorizontalAlignment(SwingConstants.LEFT);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.table.DefaultTableCellRenderer#getTableCellRendererComponent(javax.swing.JTable,
	 * java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int col) {

		int index = -1;
		boolean ascending = true;
		if (table instanceof JSortTable) {
			final JSortTable sortTable = (JSortTable) table;
			index = sortTable.getSortedColumnIndex();
			ascending = sortTable.isSortedColumnAscending();
		}
		if (table != null) {
			final JTableHeader header = table.getTableHeader();
			if (header != null) {
				setForeground(header.getForeground());
				setBackground(header.getBackground());
				setFont(header.getFont()
						.deriveFont(Font.BOLD));
			}
		}

		final Icon icon = ascending ? SortHeaderRenderer.ASCENDING : SortHeaderRenderer.DECENDING;
		setIcon(col == index ? icon : SortHeaderRenderer.NONSORTED);
		setText((value == null) ? "" : value.toString());
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		setBorder(BorderFactory.createCompoundBorder(getBorder(), this.padding));

		return this;
	}

}

/*
 *
 *
 * SortArrowIcon.java
 *
 * Created by Claude Duguay Copyright (c) 2002 This was taken from a Java Pro magazine article
 * http://www.fawcette.com/javapro/codepage.asp?loccode=jp0208
 *
 * I have NOT asked for permission to use this.
 *
 *
 */
enum SortDirection {

	/**
	 * Kierunek sortowania nieokreślony
	 */
	NONE,
	/**
	 * Kierunek sortowania malejący
	 */
	DECENDING,
	/**
	 * kierunek sortowania rosnący
	 */
	ASCENDING

}

class SortArrowIcon implements Icon {

	/**
	 * Atrybut definiuje kierunek rysowania ikony sortowania kolumny
	 */
	protected SortDirection direction;

	/**
	 * Atrybut definiuje szerokość obszaru rysowania ikony
	 */
	protected int width = 6;

	/**
	 * Atrybut definiuje wysokość obszaru rysowania ikony
	 */
	protected int height = 6;

	/**
	 * Podstawowy konstruktor obiektu klasy.
	 *
	 * @param direction
	 *        Parametr określa kierunek rysowania ikony sortowania kolumny tabeli.
	 */
	public SortArrowIcon(final SortDirection direction) {

		this.direction = direction;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.Icon#getIconWidth()
	 */
	@Override
	public int getIconWidth() {

		return this.width;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.Icon#getIconHeight()
	 */
	@Override
	public int getIconHeight() {

		return this.height;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics, int, int)
	 */
	@Override
	public void paintIcon(final Component c, final Graphics g, final int x, final int y) {

		final Color COLOR_1 = Color.BLACK;
		final Color COLOR_2 = Color.BLACK;
		final Paint GRADIENT_PAINT = new GradientPaint(
			0, 0, COLOR_1, 20, 20, COLOR_2, true);
		final Path2D myPath = new Path2D.Double();

		final double firstX = (this.width / 2.0) * (1 - (1 / Math.sqrt(3)));
		final double firstY = (3.0 * this.height) / 4.0;

		myPath.moveTo(firstX, firstY);
		myPath.lineTo(this.width - firstX, firstY);
		myPath.lineTo(this.width / 2.0, this.height / 4.0);
		myPath.closePath();

		final Graphics2D g2 = (Graphics2D) g;

		// to smooth out the jaggies
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(GRADIENT_PAINT); // just for fun!
		// g2.fill(myPath); // fill my triangle

		// kolor tła kolumny nagłówka
		// final Color bg = c.getBackground();
		final Color bg = Color.BLACK;

		// kolor rozjaśniacza tła kolumny
		final Color light = bg.brighter();

		// kolor ściemniaca tła kolumny
		final Color shade = bg.darker();

		final int w = this.width;
		final int h = this.height;
		final int m = w / 2;

		// przypadek rysowania ikony dla sortowania rosnącego
		if (this.direction == SortDirection.ASCENDING) {
			g.setColor(shade);
			// g.setColor(Color.black);
			g.drawLine(x, y, x + w, y);
			g.drawLine(x, y, x + m, y + h);
			g.setColor(light);
			g.drawLine(x + w, y, x + m, y + h);
		}

		// przypadek rysowania ikony dla sortowania malejącego
		if (this.direction == SortDirection.DECENDING) {
			g.setColor(shade);
			g.drawLine(x + m, y, x, y + h);
			g.setColor(light);
			g.drawLine(x, y + h, x + w, y + h);
			g.drawLine(x + m, y, x + w, y + h);
		}
	}

}
