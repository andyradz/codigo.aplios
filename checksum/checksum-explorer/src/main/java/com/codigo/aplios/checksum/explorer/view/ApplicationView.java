package com.codigo.aplios.checksum.explorer.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ApplicationView extends JFrame {

	private static final long	serialVersionUID	= -8763109303537864322L;
	private final JPanel		contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {

		EventQueue.invokeLater(() -> {

			try {
				final ApplicationView frame = new ApplicationView();
				frame.setVisible(true);
			}
			catch (final Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ApplicationView() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(
			5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(
			0, 0));
		setContentPane(this.contentPane);
	}

}
