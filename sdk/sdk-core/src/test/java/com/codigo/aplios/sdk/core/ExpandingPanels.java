package com.codigo.aplios.sdk.core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class ExpandingPanels extends MouseAdapter {
	ActionPanel[]	aps;
	JPanel[]		panels;

	public ExpandingPanels() {
		assembleActionPanels();
		assemblePanels();
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		final ActionPanel ap = (ActionPanel) e.getSource();
		if (ap.target.contains(e.getPoint())) {
			ap.toggleSelection();
			togglePanelVisibility(ap);
		}
	}

	private void togglePanelVisibility(final ActionPanel ap) {
		final int index = getPanelIndex(ap);
		if (this.panels[index].isShowing())
			this.panels[index].setVisible(false);
		else
			this.panels[index].setVisible(true);
		ap.getParent()
				.validate();
	}

	private int getPanelIndex(final ActionPanel ap) {
		for (int j = 0; j < this.aps.length; j++)
			if (ap == this.aps[j])
				return j;
		return -1;
	}

	private void assembleActionPanels() {
		final String[] ids = { "level 1", "level 2", "level 3", "level 4" };
		this.aps = new ActionPanel[ids.length];
		for (int j = 0; j < this.aps.length; j++)
			this.aps[j] = new ActionPanel(
				ids[j], this);
	}

	private void assemblePanels() {
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(
			2, 1, 2, 1);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		final JPanel p1 = new JPanel(
			new GridBagLayout());
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		p1.add(new JButton(
			"button 1"), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		p1.add(new JButton(
			"button 2"), gbc);
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		p1.add(new JButton(
			"button 3"), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		p1.add(new JButton(
			"button 4"), gbc);

		final JPanel p2 = new JPanel(
			new GridBagLayout());
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		p2.add(new JLabel(
			"enter"), gbc);
		gbc.anchor = GridBagConstraints.WEST;
		p2.add(new JTextField(
			8), gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		p2.add(new JButton(
			"button 1"), gbc);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		p2.add(new JButton(
			"button 2"), gbc);

		final JPanel p3 = new JPanel(
			new BorderLayout());
		final JTextArea textArea = new JTextArea(
			8, 12);
		textArea.setLineWrap(true);
		p3.add(new JScrollPane(
			textArea));

		final JPanel p4 = new JPanel(
			new GridBagLayout());
		addComponents(new JLabel(
			"label 1"),
				new JTextField(
					12),
				p4, gbc);
		addComponents(new JLabel(
			"label 2"),
				new JTextField(
					16),
				p4, gbc);
		gbc.gridwidth = 2;
		gbc.gridy = 2;
		p4.add(new JSlider(), gbc);
		gbc.gridy++;
		final JPanel p5 = new JPanel(
			new GridBagLayout());
		p5.add(new JButton(
			"button 1"), gbc);
		p5.add(new JButton(
			"button 2"), gbc);
		p5.add(new JButton(
			"button 3"), gbc);
		p5.add(new JButton(
			"button 4"), gbc);
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		p4.add(p5, gbc);
		this.panels = new JPanel[] { p1, p2, p3, p4 };
	}

	private void addComponents(final Component c1, final Component c2, final Container c,
			final GridBagConstraints gbc) {
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		c.add(c1, gbc);
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		c.add(c2, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
	}

	private JPanel getComponent() {
		final JPanel panel = new JPanel(
			new GridBagLayout());
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(
			1, 3, 0, 3);
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		for (int j = 0; j < this.aps.length; j++) {
			panel.add(this.aps[j], gbc);
			panel.add(this.panels[j], gbc);
			this.panels[j].setVisible(false);
		}
		final JLabel padding = new JLabel();
		gbc.weighty = 1.0;
		panel.add(padding, gbc);
		return panel;
	}

	public static void main(final String[] args) {
		final ExpandingPanels test = new ExpandingPanels();
		final JFrame f = new JFrame();
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.getContentPane()
				.add(new JScrollPane(
					test.getComponent()));
		f.setSize(360, 500);
		f.setLocation(200, 100);
		f.setVisible(true);
	}
}

class ActionPanel extends JPanel {
	String			text;
	Font			font;
	private boolean	selected;
	BufferedImage	open, closed;
	Rectangle		target;
	final int		OFFSET	= 30, PAD = 5;

	public ActionPanel(final String text, final MouseListener ml) {
		this.text = text;
		addMouseListener(ml);
		this.font = new Font(
			"sans-serif", Font.PLAIN, 12);
		this.selected = false;
		setBackground(new Color(
			200, 200, 220));
		setPreferredSize(new Dimension(
			200, 20));
		setBorder(BorderFactory.createRaisedBevelBorder());
		setPreferredSize(new Dimension(
			200, 20));
		createImages();
		setRequestFocusEnabled(true);
	}

	public void toggleSelection() {
		this.selected = !this.selected;
		repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		getWidth();
		final int h = getHeight();
		if (this.selected)
			g2.drawImage(this.open, this.PAD, 0, this);
		else
			g2.drawImage(this.closed, this.PAD, 0, this);
		g2.setFont(this.font);
		final FontRenderContext frc = g2.getFontRenderContext();
		final LineMetrics lm = this.font.getLineMetrics(this.text, frc);
		final float height = lm.getAscent() + lm.getDescent();
		final float x = this.OFFSET;
		final float y = ((h + height) / 2) - lm.getDescent();
		g2.drawString(this.text, x, y);
	}

	private void createImages() {
		final int w = 20;
		final int h = getPreferredSize().height;
		this.target = new Rectangle(
			2, 0, 20, 18);
		this.open = new BufferedImage(
			w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = this.open.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(getBackground());
		g2.fillRect(0, 0, w, h);
		int[] x = { 2, w / 2, 18 };
		int[] y = { 4, 15, 4 };
		Polygon p = new Polygon(
			x, y, 3);
		g2.setPaint(Color.green.brighter());
		g2.fill(p);
		g2.setPaint(Color.blue.brighter());
		g2.draw(p);
		g2.dispose();
		this.closed = new BufferedImage(
			w, h, BufferedImage.TYPE_INT_RGB);
		g2 = this.closed.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(getBackground());
		g2.fillRect(0, 0, w, h);
		x = new int[] { 3, 13, 3 };
		y = new int[] { 4, h / 2, 16 };
		p = new Polygon(
			x, y, 3);
		g2.setPaint(Color.red);
		g2.fill(p);
		g2.setPaint(Color.blue.brighter());
		g2.draw(p);
		g2.dispose();
	}
}