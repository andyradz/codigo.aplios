package com.codigo.aplios.gui.control;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JToolTip;

public final class Tooltip extends JToolTip {

	/**
	 *
	 */
	private static final long serialVersionUID = -1842756548656683078L;

	public Tooltip() {

		super();
		// make the tool tip not fill in its background
		setOpaque(false);
	}

	@Override
	public void paintComponent(final Graphics g) {

		/* body */
		// set the parent to not be opaque
		final Component parent = getParent();
		if (parent != null)
			if (parent instanceof JComponent) {
				final JComponent jparent = (JComponent) parent;
				if (jparent.isOpaque())
					jparent.setOpaque(false);
			}

		// create a round rectangle
		final Shape round = new RoundRectangle2D.Float(
			4, 4, getWidth() - 1 - 8, getHeight() - 1 - 8, 15, 15);

		// draw the white background
		final Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(
			102, 162, 214));
		g2.fill(round);

		// draw the gray border
		g2.setColor(new Color(
			0, 0, 0));
		g2.setStroke(new BasicStroke(
			2));
		g2.draw(round);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_DEFAULT);

		// draw the text
		final String text = this.getComponent()
				.getToolTipText();
		if (text != null) {
			final FontMetrics fm = g2.getFontMetrics();
			final int h = fm.getAscent();
			g2.setColor(new Color(
				255, 255, 255));
			g2.setFont(new Font(
				"Arial", Font.BOLD, 12));
			g2.drawString(text, 13, (getHeight() + h) / 2);
		}
	}

	@Override
	public Dimension getPreferredSize() {

		/* body */
		final Dimension dim = super.getPreferredSize();
		return new Dimension(
			(int) dim.getWidth() + 50, (int) dim.getHeight() + 20);
	}
}
