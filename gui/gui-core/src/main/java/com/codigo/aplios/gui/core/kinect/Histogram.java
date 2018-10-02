package com.codigo.aplios.gui.core.kinect;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 * A Histogram of particle velocities.
 *
 * @author John B. Matthews
 */
class Histogram extends JLabel implements Icon {

	private static final long serialVersionUID = -7038810887025404300L;

	private static final int WIDTH = 100;

	private static final int HEIGHT = 80;

	private static final int SPAN = 4;

	private static final int BINS = Histogram.WIDTH / Histogram.SPAN;

	private final int[] bins = new int[Histogram.BINS];

	private ArrayList<Particle> atoms;

	private double average = 0;

	/**
	 * Construct a histogram label.
	 */
	public Histogram(final ArrayList<Particle> atoms) {

		this.setIcon(this);
		this.atoms = atoms;
	}

	/**
	 * Draw a histogram of velocities at the specified location. This implementation ignores the Component parameter.
	 */
	@Override
	public void paintIcon(final Component c, final Graphics g, final int x, final int y) {

		if (this.atoms.isEmpty())
			return;
		Arrays.fill(this.bins, 0);
		double vMax = Double.MIN_VALUE;
		double sum = 0;
		for (final Particle atom : this.atoms) {
			final double v = atom.getVNorm();
			vMax = Math.max(vMax, v);
			sum += v;
		}
		this.average = sum / this.atoms.size();
		int binMax = 0;
		for (final Particle atom : this.atoms) {
			final double v = atom.getVNorm();
			final int binIndex = (int) ((v * (Histogram.BINS - 1)) / vMax);
			this.bins[binIndex]++;
			binMax = Math.max(binMax, this.bins[binIndex]);
		}
		g.setColor(Color.black);
		g.fillRect(0, Histogram.HEIGHT - 1, Histogram.WIDTH, 1);
		g.setColor(Color.blue);
		for (int i = 0; i < this.bins.length; i++) {
			final int h = ((Histogram.HEIGHT - 4) * this.bins[i]) / binMax;
			g.fillRect(x + (i * Histogram.SPAN), (y + Histogram.HEIGHT) - h, Histogram.SPAN, h);
		}
	}

	@Override
	public int getIconWidth() {

		return Histogram.WIDTH;
	}

	@Override
	public int getIconHeight() {

		return Histogram.HEIGHT;
	}

	/**
	 * Set the specified Ensemble of atoms.
	 */
	public void setAtoms(final ArrayList<Particle> atoms) {

		this.atoms = atoms;
	}

	/**
	 * Return the average velocity (rms) of the Ensemble.
	 */
	public double getAverage() {

		return this.average;
	}

}
