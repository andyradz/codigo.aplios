package com.codigo.aplios.gui.core.kinect.segment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * A custom JPanel that displays the clock time and settings in a format similar to that of a 7
 * segment display.
 */
public class Display extends JComponent {

	private static final long serialVersionUID = 2693062994499829887L;

	/**
	 * Represents the digits to be displayed (any entry digit of a value other than 0-9 will not be
	 * displayed).
	 */
	int[] m_displayDigits;

	/**
	 * Represents whether a colon should be displayed or not.
	 */
	boolean m_displayColon;

	/**
	 * Represents the string to draw next to the clock display (am/pm).
	 */
	String m_displayMsg;

	/**
	 * Constructor. Sets the display variables.
	 *
	 * @post: m_displayDigits is intialized to an array of four 10s, m_display colon is initialized to
	 *        false, and m_displayMsg is initialized to an empty String.
	 */
	public Display() {

		// Set the display variables
		this.m_displayDigits = new int[] { 10, 10, 10, 10, 10, 10 };
		this.m_displayColon = false;
		this.m_displayMsg = "";
	}

	/**
	 * Returns the preferred size of the Display
	 *
	 * @return the preferred size of the display
	 */
	@Override
	public Dimension getPreferredSize() {

		return new Dimension(
			1500, 400);
	}

	/**
	 * Draws the clock display based off of the display information variables. This page in the Java
	 * documentation was referenced in creation of this method:
	 * https://docs.oracle.com/javase/tutorial/2d/geometry/arbitrary.html
	 *
	 * @param g
	 *        the Graphics object to paint on
	 * @post the display will be drawn representing the current state of m_displayDigits,
	 *       m_displayColon, and m_displayMsg
	 */
	@Override
	public void paintComponent(final Graphics g) {

		super.paintComponent(g);
		final Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Draw the background
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(
			0, 0, 1500, 400));

		g2.setColor(Color.RED);

		// These are the coordinates for the vertical pieces of the 7 segment display
		final int xVertSeg[] = { 10, 0, 0, 10, 20, 20 };
		final int yVertSeg[] = { 0, 10, 80, 100, 80, 10 };

		// These are the coordinates for the horizontal pieces of the 7 segment display
		final int xHorzSeg[] = { 0, 10, 80, 100, 80, 10 };
		final int yHorzSeg[] = { 10, 0, 0, 10, 40, 40 };

		if (this.m_displayColon) {
			g.fillOval(462, 100, 30, 30);
			g.fillOval(462, 250, 30, 30);
			final Point pt = new Point();
			pt.setLocation(100.1D, 200.1D);

			g.fillOval(912, 100, 30, 30);
			g.fillOval(912, 250, 30, 30);
		}

		g.setFont(new Font(
			Font.SANS_SERIF, Font.BOLD, 30));

		// g.drawString(m_displayMsg, 1400, 80);
		for (int digit = 0; digit < 6; digit++) {
			final boolean[][] sevenSeg = digitToArray(this.m_displayDigits[digit]);
			final int digitSpacing = 60 + (digit * 225);
			// Draw the vertical Segments
			for (int xIter = 0; xIter < 2; xIter++) {
				final int xOffset = digitSpacing + (xIter * 120);
				for (int yIter = 0; yIter < 2; yIter++)
					if (sevenSeg[1][(xIter * 2) + yIter]) {
						final int yOffset = 80 + (yIter * 120);
						final GeneralPath segment = new GeneralPath(
							Path2D.WIND_EVEN_ODD, xVertSeg.length);

						segment.moveTo(xVertSeg[0] + xOffset, yVertSeg[0] + yOffset);

						for (int i = 1; i < xVertSeg.length; i++)
							segment.lineTo(xVertSeg[i] + xOffset, yVertSeg[i] + yOffset);

						segment.closePath();

						g2.fill(segment);
					}
			}

			// Draw the horizontal Segments
			final int xOffset = digitSpacing + 30;
			for (int yIter = 0; yIter < 3; yIter++) {
				final int yOffset = 50 + (yIter * 120);
				if (sevenSeg[0][yIter]) {
					final GeneralPath segment = new GeneralPath(
						Path2D.WIND_EVEN_ODD, xHorzSeg.length);
					segment.moveTo(xHorzSeg[0] + xOffset, yHorzSeg[0] + yOffset);
					for (int i = 1; i < xHorzSeg.length; i++)
						segment.lineTo(xHorzSeg[i] + xOffset, yHorzSeg[i] + yOffset);
					segment.closePath();
					g2.fill(segment);
				}
			}
		}
	}

	/**
	 * Sets m_displayDigits, m_displayColon, and m_displayMsg and refreshes the clock screen.
	 *
	 * @param digits
	 *        a four digit number to be displayed on the clock face
	 * @param colon
	 *        true if colon should be displayed, false if it should not
	 * @param msg
	 *        the message to be displayed next to the digit display (am/pm)
	 */
	public void setDisplay(final int[] digits, final boolean colon, final String msg) {

		this.m_displayDigits = digits.clone();
		this.m_displayColon = colon;
		this.m_displayMsg = msg;

		removeAll();
		revalidate();
		this.repaint();
	}

	/**
	 * Associates a given digit with it's 7 segment display representation as a 2d array of booleans.
	 * The array is organized as the first three horizontal segments descending and then the vertical
	 * segments moving down first then to the right. Anything other than a digit 0-9 will result in the
	 * function returning a blank representation (use this to display nothing).
	 *
	 * @param digit
	 *        the digit to retrieve 7 segment representation of
	 * @return a 2dimensional array of booleans representing the 7 segment representation of the given
	 *         digit
	 *
	 */
	private boolean[][] digitToArray(final int digit) {

		boolean[][] sevenSeg = new boolean[2][];
		switch (digit) {
		case 0:
			sevenSeg = new boolean[][] { { true, false, true }, { true, true, true, true } };
			break;
		case 1:
			sevenSeg = new boolean[][] { { false, false, false }, { false, false, true, true } };
			break;
		case 2:
			sevenSeg = new boolean[][] { { true, true, true }, { false, true, true, false } };
			break;
		case 3:
			sevenSeg = new boolean[][] { { true, true, true }, { false, false, true, true } };
			break;
		case 4:
			sevenSeg = new boolean[][] { { false, true, false }, { true, false, true, true } };
			break;
		case 5:
			sevenSeg = new boolean[][] { { true, true, true }, { true, false, false, true } };
			break;
		case 6:
			sevenSeg = new boolean[][] { { true, true, true }, { true, true, false, true } };
			break;
		case 7:
			sevenSeg = new boolean[][] { { true, false, false }, { true, false, true, true } };
			break;
		case 8:
			sevenSeg = new boolean[][] { { true, true, true }, { true, true, true, true } };
			break;
		case 9:
			sevenSeg = new boolean[][] { { true, true, true }, { true, false, true, true } };
			break;
		default:
			sevenSeg = new boolean[][] { { false, false, false }, { false, false, false, false } };
			break;
		}
		return sevenSeg;
	}

}
