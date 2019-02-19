package com.codigo.aplios.gui.core.transparent;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * A demo program that manipulates colors of individual pixels in a BufferedImage. The program lets
 * the user draw using some basic shapes. The user can also load an image from a file; the loaded
 * image is scaled to exactly fill the panel. A copy of the panel content is stored in a
 * BufferedImage. The user can apply "filters" such as "Blur" to the image. The filter is computed
 * by hand, using the RGB color data in the BufferedImage. There is also a "Smudge" tool that the
 * user can drag on the image to spread color around like wet paint; it works with the pixel data
 * from the BufferedImage.
 */
public class JavaPixelManipulation extends JPanel {

	private static final long serialVersionUID = 1939218118180840971L;

	/**
	 * The main routine simply opens a window that shows a JavaPixelManipulation panel. The window is
	 * fixed size, and the size is set to allow the panel to have its preferred size.
	 */
	public static void main(final String[] args) {

		final JFrame window = new JFrame(
			"Java Paint Demo");
		final JavaPixelManipulation content = new JavaPixelManipulation();
		window.setContentPane(content);
		window.setJMenuBar(content.getMenuBar());
		window.pack();
		window.setResizable(false);
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		window.setLocation((screenSize.width - window.getWidth()) / 2, (screenSize.height - window.getHeight()) / 2);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	private final BufferedImage	OSC;					// Stores a copy of the panel content.
	private final Graphics2D	OSG;					// Graphics context for drawing to OSC/
	private String				tool	= "Sketch";		// Identifies the current drawing tool.
	private Color				color	= Color.BLACK;	// The current drawing color.
	private BasicStroke			stroke;					// The current stroke, used for lines and curves.

	private Image		saveLoadedImage	= null;	// Keeps a copy of the last loaded image,
												// for convenience. A "Reload Image" menu
												// item will load the same image.
	private JMenuItem	reloadImageMenuItem;	// The "Reload Image" menu item.

	private String	dragShape	= null;			// When non-null, the user is dragging with
												// the Oval, Rectangle, or Line tool. The
												// current shape is drawn in paintComponent()
												// over the BufferedImage. The shape is only
												// added to the image when the drag action ends.
	private int		dragStartX, dragStartY;		// Start point of drag for use with dragShape.
	private int		dragCurrentX, dragCurrentY;	// Current mouse position for use with dragShape.

	private final double[][] smudgeRed, smudgeBlue, smudgeGreen; // Data used by "Smudge" tool.

	/**
	 * The constructor sets the preferred size of the panel, creates the BufferedImage, and installs a
	 * mouse listener on the panel to implement drawing actions.
	 */
	public JavaPixelManipulation() {

		setPreferredSize(new Dimension(
			640, 480));
		this.OSC = new BufferedImage(
			640, 480, BufferedImage.TYPE_INT_RGB);
		this.OSG = this.OSC.createGraphics();
		this.OSG.setColor(Color.WHITE);
		this.OSG.fillRect(0, 0, 640, 480);
		this.OSG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		this.stroke = new BasicStroke(
			5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
		this.smudgeRed = new double[7][7];
		this.smudgeBlue = new double[7][7];
		this.smudgeGreen = new double[7][7];
		addMouseListener(new MouseHandler()); // nested class MouseHandler is defined below.
	}

	/**
	 * The paintComponent() copies the BufferedImage to the screen. If the user is dragging with the
	 * "Line", "Rectangle", or "Oval" tool, the shape is drawn over the image from the BufferedImage.
	 */
	@Override
	protected void paintComponent(final Graphics g) {

		g.drawImage(this.OSC, 0, 0, null);
		if (this.dragShape != null) {
			final Graphics2D g2 = (Graphics2D) g.create();
			g2.setStroke(this.stroke);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			putShape(g2, this.dragShape, this.dragStartX, this.dragStartY, this.dragCurrentX, this.dragCurrentY, false);
		}
	}

	/**
	 * For drawing the Line, Rectangle, or OvalShape defined by the two points (x1,y1) and (x2,y2). If
	 * the repaint parameter is true, then the panel's repaint() method is called for a rectangle that
	 * contains the shape. When this method is used to draw to the BufferedImage, repaint is true, so
	 * that the change to the BufferedImage will also be shown on the screen. When it is called to draw
	 * the dragShape in paintComponent(), repaint is false.
	 */
	private void putShape(final Graphics2D g, final String shape, final int x1, final int y1, final int x2,
			final int y2, final boolean repaint) {

		final int x = Math.min(x1, x2);
		final int y = Math.min(y1, y2);
		final int w = Math.abs(x1 - x2);
		final int h = Math.abs(y1 - y2);
		g.setColor(this.color);
		g.setStroke(this.stroke);
		switch (shape) {
		case "Line":
			g.drawLine(x1, y1, x2, y2);
			break;
		case "Rectangle":
			g.fillRect(x, y, w, h);
			break;
		case "Oval":
			g.fillOval(x, y, w, h);
			break;
		}
		if (repaint)
			this.repaint(x - 13, y - 13, w + 26, h + 26); // large enough to contain widest stroke
	}

	/**
	 * Apply the "Smudge" or "Erase" tool at the point (x,y)
	 */
	private void applyTool(final String tool, final int x, final int y) {

		if (tool.equals("Erase")) { // Clear a 10-by-10 square, centered at (x,y).
			this.OSG.setColor(Color.WHITE);
			this.OSG.fillRect(x - 5, y - 5, 10, 10); // Erase the sqaure in the BufferedImage.
			this.repaint(x - 5, y - 5, 10, 10); // Make change visible on the screen.
		}
		else { // For the "Smudge" tool, mix some of the "paint" on the tool with the image,
				// in a 7-by-7 square centered at x,y.
			swapSmudgeData(x, y);
			this.repaint(x - 4, y - 4, 8, 8); // Make change visible on the screen.
		}
	}

	/**
	 * Copy pixel colors from a 7-by-7 square centered at (x,y) into the smudge data arrays. The color
	 * components are separated into their red, green, and blue components, which are stored in separate
	 * arrays. The values are stored as type double, not int, since they will be used in averaging
	 * calculations that require real arithmetic. This method is called at the point where the user
	 * starts a drag operation with the "Smudge" tool.
	 */
	private void grabSmudgeData(final int x, final int y) {

		final int w = this.OSC.getWidth();
		final int h = this.OSC.getHeight();
		for (int i = 0; i < 7; i++)
			for (int j = 0; j < 7; j++) {
				final int r = (y + j) - 3;
				final int c = (x + i) - 3;
				if ((r < 0) || (r >= h) || (c < 0) || (c >= w))
					// A -1 in the smudgeRed array indicates that the
					// corresponding pixel was outside the canvas.
					this.smudgeRed[i][j] = -1;
				else {
					final int color = this.OSC.getRGB(c, r);
					this.smudgeRed[i][j] = (color >> 16) & 0xFF;
					this.smudgeGreen[i][j] = (color >> 8) & 0xFF;
					this.smudgeBlue[i][j] = color & 0xFF;
				}
			}
	}

	/**
	 * Swap some of the color stored in the smudge data arrays with color in a 7-by-7 square centered at
	 * (x,y) in the BufferedImage. That is, the color values in the arrays are replaced by a weighted
	 * average of the color values in the arrays and the color values in the image. At the same time,
	 * the color values in the image are replaced by a weighted average of the color values in the image
	 * and the color values in the arrays. This method is called at each point along the path that the
	 * mouse visits as the user drags the "Smudge" tool.
	 */
	private void swapSmudgeData(final int x, final int y) {

		final int w = this.OSC.getWidth();
		final int h = this.OSC.getHeight();
		for (int i = 0; i < 7; i++) { // row number in the smudge data arrays
			final int c = (x + i) - 3; // column number (x-coord) of a pixel in the image.
			for (int j = 0; j < 7; j++) { // column number in the smudge data arrays
				final int r = (y + j) - 3; // row number (y-coord) of a pixel in the image
				if (!((r < 0) || (r >= h) || (c < 0) || (c >= w) || (this.smudgeRed[i][j] == -1))) {
					final int curCol = this.OSC.getRGB(c, r); // Current color of the pixel in the image.
					final int curRed = (curCol >> 16) & 0xFF; // RGB components from image
					final int curGreen = (curCol >> 8) & 0xFF;
					final int curBlue = curCol & 0xFF;
					final int newRed = (int) ((curRed * 0.7) + (this.smudgeRed[i][j] * 0.3)); // New RGB's for
					// image.
					final int newGreen = (int) ((curGreen * 0.7) + (this.smudgeGreen[i][j] * 0.3));
					final int newBlue = (int) ((curBlue * 0.7) + (this.smudgeBlue[i][j] * 0.3));
					final int newCol = (newRed << 16) | (newGreen << 8) | newBlue;
					this.OSC.setRGB(c, r, newCol); // Replace the color of the pixel in the image.
					this.smudgeRed[i][j] = (curRed * 0.3) + (this.smudgeRed[i][j] * 0.7); // New RGBs for smudge
					// arrays
					this.smudgeGreen[i][j] = (curGreen * 0.3) + (this.smudgeGreen[i][j] * 0.7);
					this.smudgeBlue[i][j] = (curBlue * 0.3) + (this.smudgeBlue[i][j] * 0.7);
				}
			}
		}
	}

	/**
	 * For the "Smudge" and "Erase" tools, apply the tool to every point along the line from (x1,y1) to
	 * (x2,y2). This is called each time the mouse moves as the user drags the tool.
	 */
	private void applyToolAlongLine(final String tool, final int x1, final int y1, final int x2, final int y2) {

		if (Math.abs(x1 - x2) >= Math.abs(y1 - y2)) {
			// Horizontal distance is greater than vertical distance. Apply the
			// tool once for each x-value between x1 and x2, computing the
			// y-value for each x-value from the equation of a line.
			final double slope = (double) (y2 - y1) / (x2 - x1);
			if (x1 <= x2)
				for (int x = x1; x <= x2; x++) {
					final int y = (int) (y1 + (slope * (x - x1)) + 0.5);
					applyTool(tool, x, y);
				}
			else
				for (int x = x1; x >= x2; x--) {
					final int y = (int) (y1 + (slope * (x - x1)) + 0.5);
					applyTool(tool, x, y);
				}
		}
		else {
			// Vertical distance is greater than horizontal distance. Apply the
			// tool once for each y-value between y1 and y2, computing the
			// x-value for each y-value from the equation of a line.
			final double slope = (double) (x2 - x1) / (y2 - y1);
			if (y1 <= y2)
				for (int y = y1; y <= y2; y++) {
					final int x = (int) (x1 + (slope * (y - y1)) + 0.5);
					applyTool(tool, x, y);
				}
			else
				for (int y = y1; y >= y2; y--) {
					final int x = (int) (x1 + (slope * (y - y1)) + 0.5);
					applyTool(tool, x, y);
				}
		}
	}

	/**
	 * Defines the mouse listener object that responds to user mouse actions on the panel.
	 */
	private class MouseHandler extends MouseAdapter {
		boolean	dragging	= false;	// Set to true if a dragging operation is in progress.
		int		startX, startY;			// The point at which the drag action started.
		int		prevX, prevY;			// The location of the mouse the previous time mousePressed
										// or mouseDragged was called.

		@Override
		public void mousePressed(final MouseEvent evt) {

			if (this.dragging)
				return; // There is already a mouse drag in progress; don't try to start a new one.
			this.dragging = true;
			this.startX = this.prevX = evt.getX();
			this.startY = this.prevY = evt.getY();
			if (JavaPixelManipulation.this.tool.equals("Line") || JavaPixelManipulation.this.tool.equals("Oval")
					|| JavaPixelManipulation.this.tool.equals("Rectangle")) {
				JavaPixelManipulation.this.dragShape = JavaPixelManipulation.this.tool; // Tells paintComponent about
																						// the drag action.
				JavaPixelManipulation.this.dragStartX = this.startX;
				JavaPixelManipulation.this.dragStartY = this.startY;
			}
			else if (JavaPixelManipulation.this.tool.equals("Erase"))
				applyTool("Erase", this.startX, this.startY); // Erase a square around the
																// starting point.
			else if (JavaPixelManipulation.this.tool.equals("Smudge"))
				grabSmudgeData(this.startX, this.startY); // Get data from the image that is
															// needed for the
			// Smudge tool.
			addMouseMotionListener(this); // Monitor mouse moves during the drag operation.
		}

		@Override
		public void mouseDragged(final MouseEvent evt) {

			if (!this.dragging)
				return;
			final int x = evt.getX();
			final int y = evt.getY();
			if (JavaPixelManipulation.this.tool.equals("Line") || JavaPixelManipulation.this.tool.equals("Oval")
					|| JavaPixelManipulation.this.tool.equals("Rectangle")) {
				JavaPixelManipulation.this.dragCurrentX = x;
				JavaPixelManipulation.this.dragCurrentY = y;
				JavaPixelManipulation.this.repaint(); // paintComponent() will draw the current shape on top of the
														// image
				// content.
			}
			else if (JavaPixelManipulation.this.tool.equals("Sketch"))
				putShape(JavaPixelManipulation.this.OSG, "Line", this.prevX, this.prevY, x, y, true); // Draw line
																										// segment
																										// directly
																										// in
			// BufferedImage.
			else
				applyToolAlongLine(JavaPixelManipulation.this.tool, this.prevX, this.prevY, x, y); // For Smudge
																									// and Erase
																									// tools.
			this.prevX = x;
			this.prevY = y;
		}

		@Override
		public void mouseReleased(final MouseEvent evt) {

			if (!this.dragging)
				return;
			removeMouseMotionListener(this); // Stop monitoring mouse motions, since drag is
												// ending.
			this.dragging = false;
			JavaPixelManipulation.this.dragShape = null; // paintComponent will no longer draw the extra shape
			if (JavaPixelManipulation.this.tool.equals("Line") || JavaPixelManipulation.this.tool.equals("Oval")
					|| JavaPixelManipulation.this.tool.equals("Rectangle")) {
				putShape(JavaPixelManipulation.this.OSG, JavaPixelManipulation.this.tool, this.startX, this.startY,
						this.prevX, this.prevY, true); // add shape to
				// BufferedImage
				JavaPixelManipulation.this.repaint(); // should be unnecessary; just to be sure that the panel shows the
														// right
				// thing.
			}
		}
	}

	/**
	 * Apply one of the image filters from the "Filter" menu to the BufferedImage, and copy the result
	 * to the screen. A filters is implemented as a "convolution" with 3-by-3 arrays. That is, the RGB
	 * components of each pixel in the image is replaced with a weighted average of the RGB components
	 * of the pixels in a 3-by-3 square. The weighting factors are given by the convolution array. For
	 * example, for the "Blur" filter, all the weight factors in the array are equal, and the filter is
	 * just a simple averaging operation. (To make things easy on myself, I don't change the colors of
	 * the pixels along the border of the image; this lets me assume that when I work on a pixel, the
	 * 3-by-3 square centerd at that pixel is entirely within the image.) The filter must be one of the
	 * strings form the "Filter" menu.
	 */
	private void applyFilter(final String filter) {

		final int w = this.OSC.getWidth();
		final int h = this.OSC.getHeight();
		final int[] rgbArray = new int[w * h]; // An array to hold the RGB colors of the entire image.
		this.OSC.getRGB(0, 0, w, h, rgbArray, 0, w); // Grab the RGB color data from the image.
		switch (filter) {
		case "Blur":
			break;
		case "Sharpen":
			break;
		case "Emboss":
			break;
		case "Edge Detect":
			break;
		}
		for (int x = 1; x < (w - 1); x++)
			for (int y = 1; y < (h - 1); y++) {
				final double rNew = 0, gNew = 0, bNew = 0;
				int rgb, r, g, b;
				for (int j = y - 1; j <= (y + 1); j++)
					for (int i = x - 1; i <= (x + 1); i++) {
						rgb = rgbArray[i + (j * w)];
						r = (rgb >> 16) & 255;
						g = (rgb >> 8) & 255;
						b = rgb & 255;
					}
				r = (int) Math.round(Math.min(255, Math.abs(rNew)));
				g = (int) Math.round(Math.min(255, Math.abs(gNew)));
				b = (int) Math.round(Math.min(255, Math.abs(bNew)));
				rgb = (r << 16) | (g << 8) | b;
				this.OSC.setRGB(x, y, rgb);
			}
		this.repaint();
	}

	/**
	 * Load an image from a file selected by the user. The image is scaled to exactly fill the panel,
	 * possibly changing the aspect ratio.
	 */
	private void loadImageFile() {

		JFileChooser fileDialog;
		fileDialog = new JFileChooser();
		fileDialog.setSelectedFile(null);
		final int option = fileDialog.showOpenDialog(this);
		if (option != JFileChooser.APPROVE_OPTION)
			return; // User canceled or clicked the dialog's close box.
		final File selectedFile = fileDialog.getSelectedFile();
		FileInputStream stream;
		try {
			stream = new FileInputStream(
				selectedFile);
		}
		catch (final Exception e) {
			JOptionPane.showMessageDialog(this, "Sorry, but an error occurred while trying to open the file:\n" + e);
			return;
		}
		try {
			final BufferedImage image = ImageIO.read(stream);
			if (image == null)
				throw new Exception(
					"File does not contain a recognized image format.");
			final Graphics g = this.OSC.createGraphics();
			g.drawImage(image, 0, 0, this.OSC.getWidth(), this.OSC.getHeight(), null);
			g.dispose();
			this.repaint();
			this.saveLoadedImage = image; // Keep a copy of the image so it can be reused with "Reload
											// Image" command.
			this.reloadImageMenuItem.setEnabled(true); // Enable the "Reload Image command.
		}
		catch (final Exception e) {
			JOptionPane.showMessageDialog(this, "Sorry, but an error occurred while trying to read the image:\n" + e);
		}
	}

	/**
	 * Create the menus for the program, and provide listeners to implement the menu commands.
	 */
	private JMenuBar getMenuBar() {

		final JMenuBar menuBar = new JMenuBar();
		final ActionListener flistener = evt -> {

			switch (evt.getActionCommand()) {
			case "Clear":
				JavaPixelManipulation.this.OSG.setColor(Color.WHITE);
				JavaPixelManipulation.this.OSG.fillRect(0, 0, JavaPixelManipulation.this.OSC.getWidth(),
						JavaPixelManipulation.this.OSC.getHeight());
				JavaPixelManipulation.this.repaint();
				break;
			case "Quit":
				System.exit(0);
				break;
			case "Load Image...":
				JavaPixelManipulation.this.loadImageFile();
				break;
			case "Reload Image":
				JavaPixelManipulation.this.OSG.drawImage(JavaPixelManipulation.this.saveLoadedImage, 0, 0,
						JavaPixelManipulation.this.OSC.getWidth(), JavaPixelManipulation.this.OSC.getHeight(), null);
				JavaPixelManipulation.this.repaint();
			}
		};
		final JMenu file = new JMenu(
			"File");
		file.add(makeMenuItem("Clear", flistener));
		file.add(makeMenuItem("Load Image...", flistener));
		this.reloadImageMenuItem = makeMenuItem("Reload Image", flistener);
		file.add(this.reloadImageMenuItem);
		this.reloadImageMenuItem.setEnabled(false); // Command will be enabled when an image is loaded.
		file.addSeparator();
		file.add(makeMenuItem("Quit", flistener));
		menuBar.add(file);
		final ActionListener tlistener = evt -> JavaPixelManipulation.this.tool = evt.getActionCommand();
		final JMenu tools = new JMenu(
			"Tool");
		tools.add(makeMenuItem("Sketch", tlistener));
		tools.add(makeMenuItem("Line", tlistener));
		tools.add(makeMenuItem("Rectangle", tlistener));
		tools.add(makeMenuItem("Oval", tlistener));
		tools.addSeparator();
		tools.add(makeMenuItem("Smudge", tlistener));
		tools.add(makeMenuItem("Erase", tlistener));
		menuBar.add(tools);
		final ActionListener clistener = evt -> {

			if (JavaPixelManipulation.this.tool.equals("Smudge") || JavaPixelManipulation.this.tool.equals("Erase"))
				JavaPixelManipulation.this.tool = "Sketch";
			switch (evt.getActionCommand()) {
			case "Black":
				JavaPixelManipulation.this.color = Color.BLACK;
				return;
			case "Red":
				JavaPixelManipulation.this.color = Color.RED;
				return;
			case "Green":
				JavaPixelManipulation.this.color = Color.GREEN;
				return;
			case "Blue":
				JavaPixelManipulation.this.color = Color.BLUE;
				return;
			case "Cyan":
				JavaPixelManipulation.this.color = Color.CYAN;
				return;
			case "Magenta":
				JavaPixelManipulation.this.color = Color.MAGENTA;
				return;
			case "Yellow":
				JavaPixelManipulation.this.color = Color.YELLOW;
				return;
			case "Gray":
				JavaPixelManipulation.this.color = Color.GRAY;
				return;
			case "Custom...":
				final Color c = JColorChooser.showDialog(JavaPixelManipulation.this, "Select Drawing Color",
						JavaPixelManipulation.this.color);
				if (c != null)
					JavaPixelManipulation.this.color = c;
			}
		};
		final JMenu colors = new JMenu(
			"Color");
		colors.add(makeMenuItem("Black", clistener));
		colors.add(makeMenuItem("Red", clistener));
		colors.add(makeMenuItem("Green", clistener));
		colors.add(makeMenuItem("Blue", clistener));
		colors.add(makeMenuItem("Cyan", clistener));
		colors.add(makeMenuItem("Yellow", clistener));
		colors.add(makeMenuItem("Magenta", clistener));
		colors.add(makeMenuItem("Gray", clistener));
		colors.add(makeMenuItem("Custom...", clistener));
		menuBar.add(colors);
		final ActionListener wlistener = evt -> {

			final int lineWidth = Integer.parseInt(evt.getActionCommand());
			JavaPixelManipulation.this.stroke = new BasicStroke(
				lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
			if (JavaPixelManipulation.this.tool.equals("Smudge") || JavaPixelManipulation.this.tool.equals("Erase"))
				JavaPixelManipulation.this.tool = "Sketch";
		};
		final JMenu width = new JMenu(
			"LineWidth");
		width.add(makeMenuItem("1", wlistener));
		width.add(makeMenuItem("2", wlistener));
		width.add(makeMenuItem("3", wlistener));
		width.add(makeMenuItem("5", wlistener));
		width.add(makeMenuItem("7", wlistener));
		width.add(makeMenuItem("10", wlistener));
		width.add(makeMenuItem("15", wlistener));
		width.add(makeMenuItem("20", wlistener));
		width.add(makeMenuItem("25", wlistener));
		menuBar.add(width);
		final ActionListener filterlistener = evt -> {

			if (evt.getActionCommand()
					.startsWith("Blur 5")) {
				for (int i = 0; i < 5; i++)
					JavaPixelManipulation.this.applyFilter("Blur");
				if (evt.getActionCommand()
						.equals("Blur 5, Emboss"))
					JavaPixelManipulation.this.applyFilter("Emboss");
			}
			else
				JavaPixelManipulation.this.applyFilter(evt.getActionCommand());
		};
		final JMenu filter = new JMenu(
			"Filter");
		filter.add(makeMenuItem("Blur", filterlistener));
		filter.add(makeMenuItem("Sharpen", filterlistener));
		filter.add(makeMenuItem("Emboss", filterlistener));
		filter.add(makeMenuItem("Edge Detect", filterlistener));
		filter.addSeparator();
		filter.add(makeMenuItem("Blur 5 Times", filterlistener));
		filter.add(makeMenuItem("Blur 5, Emboss", filterlistener));
		menuBar.add(filter);
		return menuBar;
	}

	/**
	 * Utility method used by getMenuBar to create menu items.
	 */
	private JMenuItem makeMenuItem(final String itemName, final ActionListener listener) {

		final JMenuItem item = new JMenuItem(
			itemName);
		item.addActionListener(listener);
		return item;
	}

}
