package com.codigo.aplios.gui.control.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FormTemplate {

	protected JFrame			frame;
	protected final Dimension	minFormSize;
	protected JTextField		txtWerwerewr;
	protected JTextField		textField_1;
	protected JTextField		textField_2;
	protected JTable			table;

	/**
	 * Create the application.
	 *
	 * @wbp.parser.entryPoint
	 */
	public FormTemplate() {

		super();
		this.minFormSize = new Dimension(
			800, 600);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 *
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.frame = new JFrame();
		this.frame.setBackground(Color.GREEN);
		this.frame.getContentPane()
				.setBackground(new Color(
					227, 227, 227));

		final JPanel panel = new JPanel();
		panel.setBackground(new Color(
			0, 139, 139));

		final JPanel panel_1 = new JPanel();
		panel_1.setForeground(Color.BLUE);
		panel_1.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		panel_1.setBorder(null);
		panel_1.setBackground(SystemColor.control);

		final JPanel panel_2 = new JPanel();
		panel_2.setForeground(Color.BLUE);
		panel_2.setFont(new Font(
			"Tahoma", Font.PLAIN, 11));
		panel_2.setBorder(null);
		panel_2.setBackground(SystemColor.menu);
		final GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 91, 207, 0 };
		gbl_panel_2.rowHeights = new int[] { 25, 25, 25, 25, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		final JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.BLUE);
		panel_3.setFont(new Font(
			"Tahoma", Font.PLAIN, 11));
		panel_3.setBorder(null);
		panel_3.setBackground(SystemColor.menu);
		final GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[] { 91, 180, 0 };
		gbl_panel_3.rowHeights = new int[] { 30, 0 };
		gbl_panel_3.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_3.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_3.setLayout(gbl_panel_3);

		final JLabel lblRedefinicjaParametrw = new JLabel(
			"Redefinicja parametrów:");
		lblRedefinicjaParametrw.setOpaque(true);
		final GridBagConstraints gbc_lblRedefinicjaParametrw = new GridBagConstraints();
		gbc_lblRedefinicjaParametrw.fill = GridBagConstraints.VERTICAL;
		gbc_lblRedefinicjaParametrw.anchor = GridBagConstraints.EAST;
		gbc_lblRedefinicjaParametrw.insets = new Insets(
			0, 5, 0, 5);
		gbc_lblRedefinicjaParametrw.gridx = 0;
		gbc_lblRedefinicjaParametrw.gridy = 0;
		panel_3.add(lblRedefinicjaParametrw, gbc_lblRedefinicjaParametrw);

		final JPanel panel_4 = new JPanel();
		panel_4.setForeground(Color.BLUE);
		panel_4.setFont(new Font(
			"Tahoma", Font.PLAIN, 11));
		panel_4.setBorder(null);
		panel_4.setBackground(SystemColor.menu);
		final GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 91, 0 };
		gbl_panel_4.rowHeights = new int[] { 30, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);
		final GroupLayout groupLayout = new GroupLayout(
			this.frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(1)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(1)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addGap(2)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
						.addGap(70)));
		final GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] {
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				0.0,
				1.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		final JLabel lblNewLabel_3 = new JLabel(
			"New label");
		lblNewLabel_3.setFont(new Font(
			"Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setForeground(new Color(
			255, 255, 255));
		final GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(
			0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 0;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		final JLabel lblNewLabel_2 = new JLabel(
			"New label");
		final GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(
			0, 0, 0, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		this.table = new JTable(
			new DefaultTableModel(
				new Object[][] {
						{ "Id", null, "Data" },
						{
								new Integer(
									1),
								null,
								null },
						{
								new Integer(
									2),
								null,
								null },
						{ null, null, null },
						{ null, null, null },
						{ null, null, null },
						{ null, null, null },
						{ null, null, null },
						{ null, null, null },
						{ null, null, null },
						{ null, null, null }, },
				new String[] { "Id", "Name", "Data" }) {

				boolean[] columnEditables = new boolean[] { true, false, true };

				@Override
				public boolean isCellEditable(final int row, final int column) {

					return this.columnEditables[column];
				}
			});
		this.table.setFillsViewportHeight(true);
		this.table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		final GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		panel_4.add(this.table, gbc_table);
		new TableColumn(
			1);

		this.table.getSelectionModel()
				.addSelectionInterval(0, 2);

		final JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		final GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_3.add(comboBox, gbc_comboBox);

		final JRadioButton rdbtnNewRadioButton_3 = new JRadioButton(
			"Input reflected");
		rdbtnNewRadioButton_3.setFocusPainted(false);
		rdbtnNewRadioButton_3.setForeground(Color.RED);
		final GridBagConstraints gbc_rdbtnNewRadioButton_3 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_3.insets = new Insets(
			0, 0, 5, 5);
		gbc_rdbtnNewRadioButton_3.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_3.gridx = 0;
		gbc_rdbtnNewRadioButton_3.gridy = 0;
		panel_2.add(rdbtnNewRadioButton_3, gbc_rdbtnNewRadioButton_3);

		final JRadioButton rdbtnNewRadioButton_4 = new JRadioButton(
			"Result reflected");
		rdbtnNewRadioButton_4.setFocusPainted(false);
		rdbtnNewRadioButton_4.setForeground(Color.RED);
		final GridBagConstraints gbc_rdbtnNewRadioButton_4 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_4.anchor = GridBagConstraints.WEST;
		gbc_rdbtnNewRadioButton_4.insets = new Insets(
			0, 0, 5, 0);
		gbc_rdbtnNewRadioButton_4.gridx = 1;
		gbc_rdbtnNewRadioButton_4.gridy = 0;
		panel_2.add(rdbtnNewRadioButton_4, gbc_rdbtnNewRadioButton_4);

		final JLabel lblNewLabel_1 = new JLabel(
			"Polonominal:");
		final GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(
			0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);

		this.txtWerwerewr = new JTextField();
		lblNewLabel_1.setLabelFor(this.txtWerwerewr);
		final GridBagConstraints gbc_txtWerwerewr = new GridBagConstraints();
		gbc_txtWerwerewr.insets = new Insets(
			0, 0, 5, 0);
		gbc_txtWerwerewr.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtWerwerewr.gridx = 1;
		gbc_txtWerwerewr.gridy = 1;
		panel_2.add(this.txtWerwerewr, gbc_txtWerwerewr);
		this.txtWerwerewr.setColumns(10);

		final JLabel lblInitialValue = new JLabel(
			"Initial Value:");
		final GridBagConstraints gbc_lblInitialValue = new GridBagConstraints();
		gbc_lblInitialValue.anchor = GridBagConstraints.EAST;
		gbc_lblInitialValue.insets = new Insets(
			0, 0, 5, 5);
		gbc_lblInitialValue.gridx = 0;
		gbc_lblInitialValue.gridy = 2;
		panel_2.add(lblInitialValue, gbc_lblInitialValue);

		this.textField_1 = new JTextField();
		lblInitialValue.setLabelFor(this.textField_1);
		this.textField_1.setColumns(10);
		final GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(
			0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_2.add(this.textField_1, gbc_textField_1);

		final JLabel lblFinalXorValue = new JLabel(
			"Final Xor Value:");
		final GridBagConstraints gbc_lblFinalXorValue = new GridBagConstraints();
		gbc_lblFinalXorValue.insets = new Insets(
			0, 0, 5, 5);
		gbc_lblFinalXorValue.anchor = GridBagConstraints.EAST;
		gbc_lblFinalXorValue.gridx = 0;
		gbc_lblFinalXorValue.gridy = 3;
		panel_2.add(lblFinalXorValue, gbc_lblFinalXorValue);

		this.textField_2 = new JTextField();
		lblFinalXorValue.setLabelFor(this.textField_2);
		this.textField_2.setColumns(10);
		final GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(
			0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panel_2.add(this.textField_2, gbc_textField_2);
		final GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 91, 61, 46, 69, 0 };
		gbl_panel_1.rowHeights = new int[] { 25, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		final JLabel lblNewLabel = new JLabel(
			"Rodzaj sumy CRC:");
		lblNewLabel.setFont(new Font(
			"Segoe UI", lblNewLabel.getFont()
					.getStyle(),
			lblNewLabel.getFont()
					.getSize()));
		lblNewLabel.setOpaque(true);
		final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(
			0, 5, 0, 0);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton(
			"CRC-8");
		rdbtnNewRadioButton_1.setForeground(new Color(
			0, 128, 0));
		rdbtnNewRadioButton_1.setFont(new Font(
			"Tahoma", Font.PLAIN, 11));
		final GridBagConstraints gbc_rdbtnNewRadioButton_1 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_1.fill = GridBagConstraints.BOTH;
		gbc_rdbtnNewRadioButton_1.gridx = 1;
		gbc_rdbtnNewRadioButton_1.gridy = 0;
		panel_1.add(rdbtnNewRadioButton_1, gbc_rdbtnNewRadioButton_1);

		final JRadioButton rdbtnNewRadioButton = new JRadioButton(
			"CRC-16");
		rdbtnNewRadioButton.setFont(new Font(
			"Tahoma", Font.PLAIN, 11));
		rdbtnNewRadioButton.setForeground(new Color(
			0, 128, 0));
		final GridBagConstraints gbc_rdbtnNewRadioButton = new GridBagConstraints();
		gbc_rdbtnNewRadioButton.fill = GridBagConstraints.BOTH;
		gbc_rdbtnNewRadioButton.gridx = 2;
		gbc_rdbtnNewRadioButton.gridy = 0;
		panel_1.add(rdbtnNewRadioButton, gbc_rdbtnNewRadioButton);

		final JRadioButton rdbtnNewRadioButton_2 = new JRadioButton(
			"CRC-32");
		rdbtnNewRadioButton_2.setFont(new Font(
			"Tahoma", Font.PLAIN, 11));
		rdbtnNewRadioButton_2.setForeground(new Color(
			0, 128, 0));
		final GridBagConstraints gbc_rdbtnNewRadioButton_2 = new GridBagConstraints();
		gbc_rdbtnNewRadioButton_2.fill = GridBagConstraints.BOTH;
		gbc_rdbtnNewRadioButton_2.gridx = 3;
		gbc_rdbtnNewRadioButton_2.gridy = 0;
		panel_1.add(rdbtnNewRadioButton_2, gbc_rdbtnNewRadioButton_2);
		groupLayout.setAutoCreateGaps(true);
		this.frame.getContentPane()
				.setLayout(groupLayout);
		this.frame.setBounds(0, 0, 800, 600);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setPreferredSize(this.minFormSize);
		this.frame.setMinimumSize(this.minFormSize);
	}
}
