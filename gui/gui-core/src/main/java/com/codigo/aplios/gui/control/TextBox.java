package com.codigo.aplios.gui.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class TextBox extends JTextField {

	/**
	 *
	 */
	private static final long serialVersionUID = 5833753981845624907L;

	public static void main(final String[] args) {

		SwingUtilities.invokeLater(TextBox::createUI);
	}

	private static void createUI() {

		final JFrame frmMain = new JFrame(
			"Test placeholder");
		frmMain.setBounds(new Rectangle(
			800, 600));
		frmMain.setLayout(new FlowLayout());

		final TextBox txtBox = new TextBox(
			"Nazwa kontrahenta");
		txtBox.setPreferredSize(new Dimension(
			550, 25));
		txtBox.setBorder(BorderFactory.createLineBorder(Color.gray));
		frmMain.add(txtBox);

		frmMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmMain.setVisible(true);
	}

	private final String placeholder;

	public TextBox(final String placeholder) {

		this.placeholder = placeholder;
	}

	@Override
	protected void paintComponent(final java.awt.Graphics g) {

		super.paintComponent(g);

		if ((this.placeholder.length() == 0) || !getText().isEmpty())
			return;

		// FocusManager.getCurrentKeyboardFocusManager()

		final Graphics2D gp = (Graphics2D) g;
		gp.setFont(getFont().deriveFont(Font.ITALIC));
		gp.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		gp.setColor(getDisabledTextColor());
		gp.drawString(this.placeholder, getInsets().left + 2, g.getFontMetrics()
				.getHeight() + getInsets().top);
	}
}
