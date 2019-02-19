package com.codigo.aplios.gui.control;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Simple component for displaying a visual representation of the index of a list of items with
 * markers for specific indices in that list. Markers can be clicked on to fire a selection event to
 * interested listeners.
 * <p/>
 * Author: Darren Scott, blog.darrenscott.com
 */
public class ListIndexBar extends JComponent {

	private static final long serialVersionUID = 2806056782392656349L;

	public static void main(final String[] args) {

		final Color MARKER_COLOUR = Color.GREEN;

		SwingUtilities.invokeLater(() -> {
			// try {
			// // UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
			// // if you don't want to use the Substance L&F, you can use an alternative e.g.
			// UIManager.setLookAndFeel(new WindowsLookAndFeel());
			// JFrame.setDefaultLookAndFeelDecorated(true);
			// }
			// catch (final UnsupportedLookAndFeelException e) {
			// e.printStackTrace();
			// }

			final JPanel panel = new JPanel(
				new BorderLayout());

			// create a list of 100 items which the list will be populated with
			final List<String> items = new ArrayList<>();
			for (int i = 0; i < 100; i++)
				items.add("Item " + (i + 1));

			// create the list and it's containing scrollpane
			final JList list = new JList(
				items.toArray(new String[items.size()]));
			final JScrollPane scrollPane = new JScrollPane(
				list);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setPreferredSize(new Dimension(
				120, 300));

			// create the list index bar and configure it
			final ListIndexBar bar = new ListIndexBar(
				items.size());
			bar.setBackground(Color.WHITE);
			bar.setForeground(MARKER_COLOUR);
			bar.setOpaque(false);

			// add a set of example markers
			bar.addMarkers(Arrays.asList(4, 15, 32, 36, 58, 74, 92));

			// add a selection listener to select the corresponding item in the list when the marker is selected
			bar.addSelectionListener(e -> {
				final int selectedIndex = e.getFirstIndex();
				System.out.println("index selected " + selectedIndex);
				list.setSelectedIndex(selectedIndex);
				list.ensureIndexIsVisible(selectedIndex);
			});

			// use a custom list cell renderer to highlight those items which have markers in the list index bar
			// list.setCellRenderer(new SubstanceDefaultListCellRenderer() {
			// @Override
			// public Component getListCellRendererComponent(JList list, Object value, int index, boolean
			// isSelected,
			// boolean cellHasFocus) {
			// JLabel comp = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
			// cellHasFocus);
			// if (bar.getMarkerSet().contains(index)) {
			// if (isSelected) {
			// comp.setBackground(MARKER_COLOUR);
			// } else {
			// comp.setForeground(MARKER_COLOUR);
			// }
			// }
			// return comp;
			// }
			// });

			// create the frame in which to show everything
			panel.add(scrollPane, BorderLayout.CENTER);
			panel.add(bar, BorderLayout.EAST);

			final JFrame frame = new JFrame();
			frame.setContentPane(panel);
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		});

	}

	private int		itemCount;
	private double	scaleFactor;
	private int		markerHeight;

	// set of list indices associated with items to be marked
	private final Set<Integer> markerSet = new HashSet<>();

	// the index of the currently highlighted marker index
	// gets set when the pointer hovers over a marker and cleared when the mouse is moved off a marker
	// or the pointer leaves the component completely
	private int highlightedIndex = -1;

	// keep track of listeners interested in marker selection events
	private final List<ListSelectionListener> listeners = new ArrayList<>();

	public ListIndexBar(final int itemCount) {

		this.itemCount = itemCount;
		recalc();

		// add a mouse motion listener to track the current highlighted marker
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(final MouseEvent e) {

				// calculate the list index which is under the mouse pointer
				final int pos = (int) (ListIndexBar.this.itemCount * (e.getPoint()
						.getY() / ListIndexBar.this.getHeight()));
				if (ListIndexBar.this.markerSet.contains(pos)) {
					// we're over one of the markers so record the index and change the cursor
					ListIndexBar.this.highlightedIndex = pos;
					ListIndexBar.this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}
				else {
					// we're not over any marker so clear the highlighted index and reset the cursor
					ListIndexBar.this.highlightedIndex = -1;
					ListIndexBar.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
		});

		// add a mouse listener to handle mouse clicks on markers
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {

				if (ListIndexBar.this.highlightedIndex != -1) {
					final ListSelectionEvent event = new ListSelectionEvent(
						ListIndexBar.this, ListIndexBar.this.highlightedIndex, ListIndexBar.this.highlightedIndex,
						false);
					for (final ListSelectionListener listener : ListIndexBar.this.listeners)
						listener.valueChanged(event);
				}
			}

			@Override
			public void mouseExited(final MouseEvent e) {

				// clear the highlighted index when we leave this component
				ListIndexBar.this.highlightedIndex = -1;
			}
		});

		// give the component a min and preferred size
		setMinimumSize(new Dimension(
			16, 60));
		setPreferredSize(new Dimension(
			16, 60));
	}

	public void addSelectionListener(final ListSelectionListener listener) {

		this.listeners.add(listener);
	}

	public void removeSelectionListener(final ListSelectionListener listener) {

		this.listeners.remove(listener);
	}

	public int getItemCount() {

		return this.itemCount;
	}

	public void setItemCount(final int itemCount) {

		this.itemCount = itemCount;
		recalc();
		this.repaint();
	}

	private void recalc() {

		this.scaleFactor = getHeight() / (double) this.itemCount;
		this.markerHeight = Math.max(2, (int) this.scaleFactor);
	}

	@Override
	public void setBounds(final int x, final int y, final int width, final int height) {

		super.setBounds(x, y, width, height);
		recalc();
	}

	@Override
	public void setBounds(final Rectangle r) {

		super.setBounds(r);
		recalc();
	}

	public Set<Integer> getMarkerSet() {

		return this.markerSet;
	}

	public void addMarkers(final Collection<Integer> markers) {

		this.markerSet.addAll(markers);
		this.repaint();
	}

	public void removeMarkers(final Collection<Integer> markers) {

		this.markerSet.removeAll(markers);
		this.repaint();
	}

	public void clearMarkers() {

		this.markerSet.clear();
		this.repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {

		// cast to a Graphics2D so we can do more with it
		final Graphics2D g2 = (Graphics2D) g;

		// paint or clear the background depending on whether this component is opaque or not
		final Composite composite = g2.getComposite();
		g2.setColor(getBackground());
		if (!isOpaque())
			// if not opaque, set the alpha composite to clear the background
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.DST));
		g2.fillRect(0, 0, getWidth(), getHeight());
		g2.setComposite(composite);

		// markers will be drawn with the foreground colour
		g2.setColor(getForeground());

		int pos;
		for (final Integer marker : this.markerSet) {
			// for each marker, calculate the appropriate Y position and paint a marker of required size
			pos = (int) (marker * this.scaleFactor);
			g2.fillRect(0, pos, getWidth(), this.markerHeight);
		}
	}

}
