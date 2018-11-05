package com.codigo.aplios.gui.control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

public class RoundedCornerBorder extends AbstractBorder {

	/**
	 *
	 */
	private static final long serialVersionUID = 2302755149747288864L;

	@Override
	public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width,
			final int height) {

		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		final int r = height - 1;
		final RoundRectangle2D round = new RoundRectangle2D.Float(
			x, y, width - 1, height - 1, r, r);
		final Container parent = c.getParent();
		if (parent != null) {
			g2.setColor(parent.getBackground());
			final Area corner = new Area(
				new Rectangle2D.Float(
					x, y, width, height));
			corner.subtract(new Area(
				round));
			g2.fill(corner);
		}
		g2.setColor(Color.GRAY);
		g2.draw(round);
		g2.dispose();
	}

	@Override
	public Insets getBorderInsets(final Component c) {

		return new Insets(
			4, 8, 4, 8);
		// return new Insets(2, 4, 2, 4);
	}

	@Override
	public Insets getBorderInsets(final Component c, final Insets insets) {

		insets.left = insets.right = 4;
		insets.top = insets.bottom = 2;
		return insets;
	}
}
