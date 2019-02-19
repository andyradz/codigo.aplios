package com.codigo.aplios.gui.core.kinect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * @see http://stackoverflow.com/questions/4430356
 */
public class DemosDoubleBuffering extends JPanel implements ActionListener {

	private static final long serialVersionUID = -3917874990924925348L;

	private static final int W = 800;

	private static final int H = 600;

	private static final int r = 80;

	private int xs = 3;

	private int ys = this.xs;

	private int x = 0;

	private int y = 0;

	private final BufferedImage bi;

	private final JLabel jl = new JLabel();

	private final Timer t = new Timer(
		10, this);

	public static void main(final String[] args) {

		EventQueue.invokeLater(() -> {
			final JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			frame.add(new DemosDoubleBuffering());
			frame.setLocationRelativeTo(null);
			frame.pack();
			frame.setVisible(true);
		});
	}

	public DemosDoubleBuffering() {

		super(
				true);
		setLayout(new GridLayout());
		setPreferredSize(new Dimension(
			DemosDoubleBuffering.W, DemosDoubleBuffering.H));
		this.bi = new BufferedImage(
			DemosDoubleBuffering.W, DemosDoubleBuffering.H, BufferedImage.TYPE_INT_ARGB);
		this.jl.setIcon(new ImageIcon(
			this.bi));
		this.add(this.jl);
		this.t.start();
	}

	@Override
	public void actionPerformed(final ActionEvent e) {

		this.move();
		drawSquare(this.bi);
		this.jl.repaint();
	}

	private void drawSquare(final BufferedImage bi) {

		final Graphics2D g = bi.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, DemosDoubleBuffering.W, DemosDoubleBuffering.H);
		g.setColor(Color.blue);
		g.fillRect(this.x, this.y, DemosDoubleBuffering.r, DemosDoubleBuffering.r);
		g.dispose();
	}

	private void move() {

		if (!(((this.x + this.xs) >= 0) && ((this.x + this.xs + DemosDoubleBuffering.r) < this.bi.getWidth())))
			this.xs = -this.xs;
		if (!(((this.y + this.ys) >= 0) && ((this.y + this.ys + DemosDoubleBuffering.r) < this.bi.getHeight())))
			this.ys = -this.ys;
		this.x += this.xs;
		this.y += this.ys;
	}

}
