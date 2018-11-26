package com.codigo.aplios.gui.core.transparent;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

class Surface extends JPanel {

	private void doDrawing(final Graphics g) {

		final Graphics2D g2d = (Graphics2D) g.create();

		g2d.setPaint(Color.blue);

		for (int i = 1; i <= 10; i++) {

			final float alpha = i * 0.1f;
			final AlphaComposite alcom = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g2d.setComposite(alcom);
			g2d.fillRect(50 * i, 20, 40, 40);
		}

		g2d.dispose();
	}

	@Override
	public void paintComponent(final Graphics g) {

		super.paintComponent(g);
		doDrawing(g);
	}
}

public class TransparentRectangle extends JFrame {

	public TransparentRectangle() {

		initUI();
	}

	private void initUI() {

		this.add(new Surface2());

		setTitle("Transparent rectangles");
		this.setSize(590, 120);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void main(final String[] args) {

		EventQueue.invokeLater(() -> {

			final TransparentRectangle ex = new TransparentRectangle();
			ex.setVisible(true);
		});
	}
}
