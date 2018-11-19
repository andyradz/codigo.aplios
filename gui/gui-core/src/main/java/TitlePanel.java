
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * TitlePanel.java
 * <p/>
 * An example of for custom JPanel.
 * 
 * @author JDhilsukh
 *
 */
public class TitlePanel extends JPanel {

	int					width		= 0;
	private Color		startColor	= Color.WHITE;	// new Color(238, 238, 238);
	private Color		endColor	= Color.GRAY;	// new Color(255, 255, 255);
	GeneralPath			path;
	Color				accentColor	= new Color(
		0x80ffffff);
	Color				textColor	= new Color(
		0x0f0f0f);
	private Image		imageIcon;
	private String		title		= "Test";
	private Container	parent;
	private boolean		isToggleIcon;

	public TitlePanel(final String title, final Image imageIcon, final int width) {

		super();
		this.width = width;
		this.imageIcon = imageIcon;
		this.title = title;
		final MouseListener mouseListener = new MouseAdapter() {

			@Override
			public void mouseClicked(final MouseEvent e) {

				getParentNode((Container) e.getSource());
				if (TitlePanel.this.parent != null)
					TitlePanel.this.parent.dispatchEvent(e);
			}

			@Override
			public void mouseEntered(final MouseEvent e) {

				setCursor("HAND");
			}

			@Override
			public void mouseExited(final MouseEvent e) {

				setCursor("DEFAULT");
			}
		};

		addMouseListener(mouseListener);
		setPreferredSize(new Dimension(
			getWidth(), 30));
	}

	private void getParentNode(final Container panel) {

		if (panel == null)
			return;
		if ((panel instanceof SlidePaneFactory))
			this.parent = panel;

		getParentNode(panel.getParent());
	}

	private void setCursor(final String state) {

		if ("HAND".equals(state))
			this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		else
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public TitlePanel(final Color color1, final Color color2) {

		super();
		this.startColor = color1;
		this.endColor = color2;

	}

	@Override
	public int getWidth() {

		return this.width;
	}

	@Override
	public int getHeight() {

		return 30;
	}

	/**
	 * Override the default paintComponent method to paint the gradient in the panel.
	 *
	 * @param g
	 */
	@Override
	public void paintComponent(final Graphics g) {

		final Graphics2D g2d = (Graphics2D) g.create();
		final int h = getHeight();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/**
		 * Top Polygon
		 */
		final GeneralPath path = new GeneralPath();
		path.moveTo(70, 0);
		path.lineTo(8, 0);
		path.quadTo(0, 0, 0, 7);
		path.lineTo(0, 55);
		path.lineTo(getWidth() - 1, 55);
		path.lineTo(getWidth() - 1, 7);
		path.quadTo(getWidth() - 1, 0, getWidth() - 8, 0);
		path.lineTo(30, 0);

		final Rectangle bounds1 = path.getBounds();
		final GradientPaint painter = new GradientPaint(
			0, path.getBounds().y, true ? this.endColor : this.startColor, 0, (bounds1.y + bounds1.height) - 1,
			true ? this.startColor : this.endColor);
		g2d.setPaint(painter);
		g2d.fill(path);

		/**
		 * Middle Rectangle
		 */
		g2d.setPaint(new Color(
			240, 240, 240));
		g2d.fillRect(0, 31, getWidth() - 1, h - 50);

		/**
		 * Title
		 */
		g2d.setFont(g2d.getFont()
				.deriveFont(Font.BOLD)
				.deriveFont((float) g2d.getFont()
						.getSize() + 1));

		if (this.title != null) {
			g2d.setColor(this.accentColor);
			g2d.drawString(this.title, 40, 22);
			g2d.setColor(this.textColor);
			g2d.drawString(this.title, 40, 21);
		}

		/**
		 * image
		 */
		if (this.imageIcon != null)
			// new ImageIcon("images/self.png").getImage();
			g2d.drawImage(this.imageIcon, 5, 5, 20, 20, null, null);
		if (this.isToggleIcon)
			g2d.drawImage(new ImageIcon(
				"images/arrowUp.png").getImage(), getWidth() - 20, 10, 10, 10, null, null);
		else
			g2d.drawImage(new ImageIcon(
				"images/arrowDown.png").getImage(), getWidth() - 20, 10, 10, 10, null, null);
	}

	/**
	 * This method sets the Actual Background Color of the Button
	 */
	public void setStartColor(final Color color) {

		this.startColor = color;
	}

	/**
	 * This method sets the Pressed Color of the Button
	 */
	public void setEndColor(final Color pressedColor) {

		this.endColor = pressedColor;
	}

	/**
	 * @return Starting Color of the Button
	 */
	public Color getStartColor() {

		return this.startColor;
	}

	/**
	 * @return Ending Color of the Button
	 */
	public Color getEndColor() {

		return this.endColor;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {

		return this.title;
	}

	public void setTitle(final String title) {

		this.title = title;
	}

	public void toggleState(final boolean state) {

		this.isToggleIcon = state;
	}
}
