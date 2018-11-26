package com.codigo.aplios.gui.control.widget;

import java.awt.EventQueue;

public class FormDashboard extends FormTemplate {

	/**
	 * Launch the application.
	 *
	 */
	public static void main(final String[] args) {

		EventQueue.invokeLater(() -> {

			try {
				final FormDashboard window = new FormDashboard();
				window.frame.setVisible(true);
			}
			catch (final Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the application.
	 *
	 */
	public FormDashboard() {

		super();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 */
	private void initialize() {

		// final FormDashboard window = new FormDashboard();
		// window.frame.setVisible(true);
		// this.frame.setBounds(100, 100, 450, 300);
		// this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
