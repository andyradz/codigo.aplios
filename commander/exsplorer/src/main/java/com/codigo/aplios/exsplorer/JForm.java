/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.exsplorer;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author andrzej.radziszewski
 */
public final class JForm extends JPanel {

	private static final long serialVersionUID = 7935839568425340764L;

	private static final int W = 4;

	private static final Color BORDER_COLOR = new Color(
		100, 100, 100);

	private final SideLabel left = new SideLabel(
		Side.W);

	private final SideLabel right = new SideLabel(
		Side.E);

	private final SideLabel top = new SideLabel(
		Side.N);

	private final SideLabel bottom = new SideLabel(
		Side.S);

	private final SideLabel topleft = new SideLabel(
		Side.NW);

	private final SideLabel topright = new SideLabel(
		Side.NE);

	private final SideLabel bottomleft = new SideLabel(
		Side.SW);

	private final SideLabel bottomright = new SideLabel(
		Side.SE);

	private final JPanel contentPanel = new JPanel(
		new BorderLayout());

	private final JPanel resizePanel = new JPanel(
		new BorderLayout()) {

		private static final long serialVersionUID = 3901365094086277753L;

		@Override
		protected void paintComponent(final Graphics g) {

			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.0f)); // draw
			// transparent
			// background
			super.paintComponent(g);
			((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.99f)); // turn
			final Graphics2D g2 = (Graphics2D) g.create();
			final int w = getWidth();
			final int h = getHeight();
			g2.setPaint(Color.ORANGE);
			g2.fillRect(0, 0, w, h);
			g2.setPaint(JForm.BORDER_COLOR);
			g2.drawRect(0, 0, w - 1, h - 1);

			g2.drawLine(0, 2, 2, 0);
			g2.drawLine(w - 3, 0, w - 1, 2);

			g2.clearRect(0, 0, 2, 1);
			g2.clearRect(0, 0, 1, 2);
			g2.clearRect(w - 2, 0, 2, 1);
			g2.clearRect(w - 1, 0, 1, 2);

			g2.dispose();
		}

	};

	public JForm() {

		super(
				new BorderLayout());
		setPreferredSize(new Dimension(
			320, 240));
	}

	private static JButton makeCloseButton() {

		final JButton button = new JButton(
			new CloseIcon());
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setOpaque(true);
		button.setBackground(Color.ORANGE);
		button.addActionListener(e -> {
			final JComponent b = (JComponent) e.getSource();
			final Container c = b.getTopLevelAncestor();
			if (c instanceof Window) {
				final Window w = (Window) c;
				w.dispatchEvent(new WindowEvent(
					w, WindowEvent.WINDOW_CLOSING));
			}
		});
		return button;
	}

	private static JButton makeIconifyButton(final JFrame frame) {

		final JButton iconify = new JButton(
			"_");
		iconify.setContentAreaFilled(false);
		iconify.setFocusPainted(false);
		iconify.setBorder(BorderFactory.createEmptyBorder());
		iconify.setOpaque(true);
		iconify.setBackground(Color.ORANGE);
		iconify.addActionListener(e -> frame.setExtendedState(Frame.MAXIMIZED_BOTH));
		return iconify;
	}

	public JFrame makeFrame(final String str) {

		// try {
		// UIManager.setLookAndFeel("Window");
		// } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
		// | UnsupportedLookAndFeelException ex) {
		// ex.printStackTrace();
		// }
		final JFrame frame = new JFrame(
			str) {

			private static final long serialVersionUID = -352221354394587067L;

			@Override
			public Container getContentPane() {

				return JForm.this.contentPanel;
			}

		};
		frame.setUndecorated(true);
		frame.setBackground(new Color(
			255, 255, 255, 0));

		final JPanel title = new JPanel(
			new BorderLayout());
		final MouseInputListener dwl = new DragWindowListener();
		title.addMouseListener(dwl);
		title.addMouseMotionListener(dwl);
		title.setOpaque(false);
		title.setBackground(Color.ORANGE);
		title.setBorder(BorderFactory.createEmptyBorder(JForm.W, JForm.W, JForm.W, JForm.W));

		title.add(new JLabel(
			str, SwingConstants.CENTER));
		title.add(JForm.makeCloseButton(), BorderLayout.EAST);
		title.add(JForm.makeIconifyButton(frame), BorderLayout.WEST);

		final MouseInputListener rwl = new ResizeWindowListener();
		for (final SideLabel l : Arrays.asList(this.left, this.right, this.top, this.bottom, this.topleft,
				this.topright, this.bottomleft, this.bottomright)) {
			l.addMouseListener(rwl);
			l.addMouseMotionListener(rwl);
		}

		final JPanel titlePanel = new JPanel(
			new BorderLayout());
		titlePanel.add(this.top, BorderLayout.NORTH);
		titlePanel.add(title, BorderLayout.CENTER);

		final JPanel northPanel = new JPanel(
			new BorderLayout());
		northPanel.add(this.topleft, BorderLayout.WEST);
		northPanel.add(titlePanel, BorderLayout.CENTER);
		northPanel.add(this.topright, BorderLayout.EAST);

		final JPanel southPanel = new JPanel(
			new BorderLayout());
		southPanel.add(this.bottomleft, BorderLayout.WEST);
		southPanel.add(this.bottom, BorderLayout.CENTER);
		southPanel.add(this.bottomright, BorderLayout.EAST);

		this.resizePanel.add(this.left, BorderLayout.WEST);
		this.resizePanel.add(this.right, BorderLayout.EAST);
		this.resizePanel.add(northPanel, BorderLayout.NORTH);
		this.resizePanel.add(southPanel, BorderLayout.SOUTH);
		this.resizePanel.add(this.contentPanel, BorderLayout.CENTER);

		titlePanel.setOpaque(false);
		northPanel.setOpaque(false);
		southPanel.setOpaque(false);

		this.contentPanel.setOpaque(false);
		this.resizePanel.setOpaque(false);
		frame.setContentPane(this.resizePanel);
		return frame;
	}

	public static void main(final String... args) {

		EventQueue.invokeLater(() -> JForm.createAndShowGUI());
	}

	public static void createAndShowGUI() {

		try {
			UIManager.setLookAndFeel("Window");
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
		final JForm p = new JForm();
		final JFrame frame = p.makeFrame("File Grapper ver.1.9");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane()
				.add(p);
		frame.setMinimumSize(new Dimension(
			1024, 800));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

enum Side {

	N(Cursor.N_RESIZE_CURSOR, 0, 4),
	W(Cursor.W_RESIZE_CURSOR, 4, 0),
	E(Cursor.E_RESIZE_CURSOR, 4, 0),
	S(Cursor.S_RESIZE_CURSOR, 0, 4),
	NW(Cursor.NW_RESIZE_CURSOR, 4, 4),
	NE(Cursor.NE_RESIZE_CURSOR, 4, 4),
	SW(Cursor.SW_RESIZE_CURSOR, 4, 4),
	SE(Cursor.SE_RESIZE_CURSOR, 4, 4);

	public final int cursor;

	public final int width;

	public final int height;

	Side(final int cursor, final int width, final int height) {

		this.cursor = cursor;
		this.width = width;
		this.height = height;
	}

}

class SideLabel extends JLabel {

	private static final long	serialVersionUID	= -6844188612088850025L;
	public final Side			side;

	protected SideLabel(final Side side) {

		super();
		this.side = side;
		setCursor(Cursor.getPredefinedCursor(side.cursor));
	}

	@Override
	public Dimension getPreferredSize() {

		return new Dimension(
			this.side.width, this.side.height);
	}

	@Override
	public Dimension getMinimumSize() {

		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {

		return getPreferredSize();
	}

}

class ResizeWindowListener extends MouseInputAdapter {

	private final Rectangle rect = new Rectangle();

	@Override
	public void mousePressed(final MouseEvent e) {

		final Component p = SwingUtilities.getRoot(e.getComponent());
		if (p instanceof Window)
			this.rect.setBounds(((Window) p).getBounds());
	}

	@Override
	public void mouseDragged(final MouseEvent e) {

		final Component c = e.getComponent();
		final Component p = SwingUtilities.getRoot(c);
		if (!this.rect.isEmpty() && (c instanceof SideLabel) && (p instanceof Window)) {
			final Side side = ((SideLabel) c).side;
			((Window) p).setBounds(ResizeWindowListener.getResizedRect(this.rect, side, e.getX(), e.getY()));
		}
	}

	private static Rectangle getResizedRect(final Rectangle r, final Side side, final int dx, final int dy) {

		switch (side) {
		case NW:
			r.y += dy;
			r.height -= dy;
			r.x += dx;
			r.width -= dx;
			break;
		case N:
			r.y += dy;
			r.height -= dy;
			break;
		case NE:
			r.y += dy;
			r.height -= dy;
			r.width += dx;
			break;
		case W:
			r.x += dx;
			r.width -= dx;
			break;
		case E:
			r.width += dx;
			break;
		case SW:
			r.height += dy;
			r.x += dx;
			r.width -= dx;
			break;
		case S:
			r.height += dy;
			break;
		case SE:
			r.height += dy;
			r.width += dx;
			break;
		default:
			throw new AssertionError(
				"Unknown SideLabel");
		}
		return r;
	}

}

class DragWindowListener extends MouseInputAdapter {

	private final Point startPt = new Point();

	@Override
	public void mousePressed(final MouseEvent e) {

		if (SwingUtilities.isLeftMouseButton(e))
			this.startPt.setLocation(e.getPoint());
	}

	@Override
	public void mouseDragged(final MouseEvent e) {

		final Component c = SwingUtilities.getRoot(e.getComponent());
		if ((c instanceof Window) && SwingUtilities.isLeftMouseButton(e)) {
			final Window window = (Window) c;
			final Point pt = window.getLocation();
			window.setLocation((pt.x - this.startPt.x) + e.getX(), (pt.y - this.startPt.y) + e.getY());
		}
	}

}

class CloseIcon implements Icon {

	@Override
	public void paintIcon(final Component c, final Graphics g, final int x, final int y) {

		// ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.0f));
		// // draw transparent background
		// super.paintComponent(g);
		// ((Graphics2D) g).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.9f));
		// // turn on opacity
		// g.setColor(Color.RED);
		// g.fillRect(20, 20, 500, 300);
		final Graphics2D g2 = (Graphics2D) g.create();
		g2.translate(x, y);
		g2.setPaint(Color.GRAY);
		g2.drawLine(4, 4, 11, 11);
		g2.drawLine(4, 5, 10, 11);
		g2.drawLine(5, 4, 11, 10);
		g2.drawLine(11, 4, 4, 11);
		g2.drawLine(11, 5, 5, 11);
		g2.drawLine(10, 4, 4, 10);
		g2.dispose();
	}

	@Override
	public int getIconWidth() {

		return 16;
	}

	@Override
	public int getIconHeight() {

		return 16;
	}

}
