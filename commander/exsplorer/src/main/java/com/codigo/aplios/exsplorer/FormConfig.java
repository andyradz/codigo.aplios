package com.codigo.aplios.exsplorer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author andrzej.radziszewski
 *
 *         Code file : FormConfig.java Create date: 19.04.2017
 */
public class FormConfig extends JFrame {

	private static final long	serialVersionUID	= -9125041662517529686L;
	private final JPanel		contentPane;

	/**
	 * Launch the application.
	 *
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(final String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(() -> {
			try {
				final FormConfig frame = new FormConfig();
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
	public FormConfig() {

		setTitle("JExplorer :: Ustawienia programu");
		setMinimumSize(new Dimension(
			800, 600));
		this.setSize(new Dimension(
			800, 600));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setMinimumSize(new Dimension(
			800, 600));
		this.contentPane.setBorder(null);
		setContentPane(this.contentPane);
		final GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);

		final JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		final GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		this.contentPane.add(panel, gbc_panel);
		final GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 150, 0 };
		gbl_panel.rowHeights = new int[] { 35, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(
			SystemColor.controlShadow));
		panel_1.setBackground(SystemColor.menu);
		final GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		final GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 141, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 16, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		final JLabel lblUstawieniaPodstawowerwerwerwerwere = new JLabel(
			"Ustawienia podstawowe:");
		lblUstawieniaPodstawowerwerwerwerwere.setHorizontalAlignment(SwingConstants.CENTER);
		lblUstawieniaPodstawowerwerwerwerwere.setForeground(Color.DARK_GRAY);
		lblUstawieniaPodstawowerwerwerwerwere.setFont(new Font(
			"Segoe UI", Font.BOLD, 12));
		lblUstawieniaPodstawowerwerwerwerwere.setBorder(new EmptyBorder(
			0, 10, 0, 0));
		lblUstawieniaPodstawowerwerwerwerwere.setBackground(SystemColor.control);
		final GridBagConstraints gbc_lblUstawieniaPodstawowerwerwerwerwere = new GridBagConstraints();
		gbc_lblUstawieniaPodstawowerwerwerwerwere.fill = GridBagConstraints.BOTH;
		gbc_lblUstawieniaPodstawowerwerwerwerwere.gridx = 0;
		gbc_lblUstawieniaPodstawowerwerwerwerwere.gridy = 0;
		panel_1.add(lblUstawieniaPodstawowerwerwerwerwere, gbc_lblUstawieniaPodstawowerwerwerwerwere);

		final JLabel lblWybierzPodstawoweOpcje = new JLabel(
			"Wybierz podstawowe opcje dotyczące działania JExplorer");
		lblWybierzPodstawoweOpcje.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWybierzPodstawoweOpcje.setForeground(Color.BLACK);
		lblWybierzPodstawoweOpcje.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		lblWybierzPodstawoweOpcje.setBorder(new EmptyBorder(
			0, 5, 0, 0));
		lblWybierzPodstawoweOpcje.setBackground(SystemColor.menu);
		final GridBagConstraints gbc_lblWybierzPodstawoweOpcje = new GridBagConstraints();
		gbc_lblWybierzPodstawoweOpcje.gridx = 1;
		gbc_lblWybierzPodstawoweOpcje.gridy = 0;
		panel_1.add(lblWybierzPodstawoweOpcje, gbc_lblWybierzPodstawoweOpcje);

		final JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(
			10, 16, 0, 0));
		panel_2.setOpaque(false);
		final GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		final GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] {
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
				Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		final JCheckBox chckbxNewCheckBox = new JCheckBox(
			"Pokaż pasek przycisków");
		chckbxNewCheckBox.setFocusPainted(false);
		chckbxNewCheckBox.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxNewCheckBox.setOpaque(false);
		final GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 0;
		panel_2.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		final JCheckBox chckbxPokaPasekPrzyciskw = new JCheckBox(
			"Pokaż pasek przycisków 2(pionowy)\r\n");
		chckbxPokaPasekPrzyciskw.setOpaque(false);
		chckbxPokaPasekPrzyciskw.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaPasekPrzyciskw.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaPasekPrzyciskw = new GridBagConstraints();
		gbc_chckbxPokaPasekPrzyciskw.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaPasekPrzyciskw.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaPasekPrzyciskw.gridx = 0;
		gbc_chckbxPokaPasekPrzyciskw.gridy = 1;
		panel_2.add(chckbxPokaPasekPrzyciskw, gbc_chckbxPokaPasekPrzyciskw);

		final JCheckBox chckbxPokaPaskiNapdw = new JCheckBox(
			"Pokaż paski napędów");
		chckbxPokaPaskiNapdw.setOpaque(false);
		chckbxPokaPaskiNapdw.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaPaskiNapdw.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaPaskiNapdw = new GridBagConstraints();
		gbc_chckbxPokaPaskiNapdw.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaPaskiNapdw.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaPaskiNapdw.gridx = 0;
		gbc_chckbxPokaPaskiNapdw.gridy = 2;
		panel_2.add(chckbxPokaPaskiNapdw, gbc_chckbxPokaPaskiNapdw);

		final JCheckBox checkBox = new JCheckBox(
			"Pokaż paski napędów");
		checkBox.setOpaque(false);
		checkBox.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		checkBox.setFocusPainted(false);
		final GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.insets = new Insets(
			0, 16, 5, 0);
		gbc_checkBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkBox.gridx = 0;
		gbc_checkBox.gridy = 3;
		panel_2.add(checkBox, gbc_checkBox);

		final JCheckBox checkBox_1 = new JCheckBox(
			"Pokaż paski napędów");
		checkBox_1.setOpaque(false);
		checkBox_1.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		checkBox_1.setFocusPainted(false);
		final GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.insets = new Insets(
			0, 16, 5, 0);
		gbc_checkBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkBox_1.gridx = 0;
		gbc_checkBox_1.gridy = 4;
		panel_2.add(checkBox_1, gbc_checkBox_1);

		final JCheckBox chckbxPokaListWyboru = new JCheckBox(
			"Pokaż listę wyboru napędów");
		chckbxPokaListWyboru.setOpaque(false);
		chckbxPokaListWyboru.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaListWyboru.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaListWyboru = new GridBagConstraints();
		gbc_chckbxPokaListWyboru.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaListWyboru.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaListWyboru.gridx = 0;
		gbc_chckbxPokaListWyboru.gridy = 5;
		panel_2.add(chckbxPokaListWyboru, gbc_chckbxPokaListWyboru);

		final JCheckBox chckbxPokaZakadkiFolderw = new JCheckBox(
			"Pokaż zakładki folderów");
		chckbxPokaZakadkiFolderw.setOpaque(false);
		chckbxPokaZakadkiFolderw.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaZakadkiFolderw.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaZakadkiFolderw = new GridBagConstraints();
		gbc_chckbxPokaZakadkiFolderw.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaZakadkiFolderw.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaZakadkiFolderw.gridx = 0;
		gbc_chckbxPokaZakadkiFolderw.gridy = 6;
		panel_2.add(chckbxPokaZakadkiFolderw, gbc_chckbxPokaZakadkiFolderw);

		final JCheckBox chckbxPokaNazwBiecego = new JCheckBox(
			"Pokaż nazwę bieżącego katalogu");
		chckbxPokaNazwBiecego.setOpaque(false);
		chckbxPokaNazwBiecego.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaNazwBiecego.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaNazwBiecego = new GridBagConstraints();
		gbc_chckbxPokaNazwBiecego.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaNazwBiecego.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaNazwBiecego.gridx = 0;
		gbc_chckbxPokaNazwBiecego.gridy = 7;
		panel_2.add(chckbxPokaNazwBiecego, gbc_chckbxPokaNazwBiecego);

		final JCheckBox chckbxPokaKlikanieCzci = new JCheckBox(
			"Pokaż klikanie części ścieżki dostępu (podzielony pasek)\r\n");
		chckbxPokaKlikanieCzci.setOpaque(false);
		chckbxPokaKlikanieCzci.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaKlikanieCzci.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaKlikanieCzci = new GridBagConstraints();
		gbc_chckbxPokaKlikanieCzci.insets = new Insets(
			0, 16, 5, 0);
		gbc_chckbxPokaKlikanieCzci.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaKlikanieCzci.gridx = 0;
		gbc_chckbxPokaKlikanieCzci.gridy = 8;
		panel_2.add(chckbxPokaKlikanieCzci, gbc_chckbxPokaKlikanieCzci);

		final JCheckBox checkBox_2 = new JCheckBox(
			"Pokaż klikanie części ścieżki dostępu (podzielony pasek)\r\n");
		checkBox_2.setActionCommand("Auto-rozwijanie przy przesunięciu myszy nad nim");
		checkBox_2.setOpaque(false);
		checkBox_2.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		checkBox_2.setFocusPainted(false);
		final GridBagConstraints gbc_checkBox_2 = new GridBagConstraints();
		gbc_checkBox_2.insets = new Insets(
			0, 32, 5, 0);
		gbc_checkBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkBox_2.gridx = 0;
		gbc_checkBox_2.gridy = 9;
		panel_2.add(checkBox_2, gbc_checkBox_2);

		final JCheckBox checkBox_3 = new JCheckBox(
			"Pokaż klikanie części ścieżki dostępu (podzielony pasek)\r\n");
		checkBox_3.setOpaque(false);
		checkBox_3.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		checkBox_3.setFocusPainted(false);
		final GridBagConstraints gbc_checkBox_3 = new GridBagConstraints();
		gbc_checkBox_3.insets = new Insets(
			0, 16, 5, 0);
		gbc_checkBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkBox_3.gridx = 0;
		gbc_checkBox_3.gridy = 10;
		panel_2.add(checkBox_3, gbc_checkBox_3);

		final JCheckBox chckbxPokaNagwkiKolumn = new JCheckBox(
			"Pokaż nagłówki kolumn");
		chckbxPokaNagwkiKolumn.setOpaque(false);
		chckbxPokaNagwkiKolumn.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaNagwkiKolumn.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaNagwkiKolumn = new GridBagConstraints();
		gbc_chckbxPokaNagwkiKolumn.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaNagwkiKolumn.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaNagwkiKolumn.gridx = 0;
		gbc_chckbxPokaNagwkiKolumn.gridy = 11;
		panel_2.add(chckbxPokaNagwkiKolumn, gbc_chckbxPokaNagwkiKolumn);

		final JCheckBox chckbxPokaPasekStanu = new JCheckBox(
			"Pokaż pasek stanu\r\n");
		chckbxPokaPasekStanu.setOpaque(false);
		chckbxPokaPasekStanu.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaPasekStanu.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaPasekStanu = new GridBagConstraints();
		gbc_chckbxPokaPasekStanu.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaPasekStanu.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaPasekStanu.gridx = 0;
		gbc_chckbxPokaPasekStanu.gridy = 12;
		panel_2.add(chckbxPokaPasekStanu, gbc_chckbxPokaPasekStanu);

		final JCheckBox chckbxPokaWierszPolece = new JCheckBox(
			"Pokaż wiersz poleceń\r\n\r\n");
		chckbxPokaWierszPolece.setOpaque(false);
		chckbxPokaWierszPolece.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaWierszPolece.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaWierszPolece = new GridBagConstraints();
		gbc_chckbxPokaWierszPolece.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaWierszPolece.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaWierszPolece.gridx = 0;
		gbc_chckbxPokaWierszPolece.gridy = 13;
		panel_2.add(chckbxPokaWierszPolece, gbc_chckbxPokaWierszPolece);

		final JCheckBox chckbxPokaPrzyciskKlawiszy = new JCheckBox(
			"Pokaż przycisk klawiszy funkcyjnych\r\n\r\n");
		chckbxPokaPrzyciskKlawiszy.setOpaque(false);
		chckbxPokaPrzyciskKlawiszy.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPokaPrzyciskKlawiszy.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPokaPrzyciskKlawiszy = new GridBagConstraints();
		gbc_chckbxPokaPrzyciskKlawiszy.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPokaPrzyciskKlawiszy.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPokaPrzyciskKlawiszy.gridx = 0;
		gbc_chckbxPokaPrzyciskKlawiszy.gridy = 14;
		panel_2.add(chckbxPokaPrzyciskKlawiszy, gbc_chckbxPokaPrzyciskKlawiszy);

		final JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.scrollbar);
		separator.setBackground(Color.WHITE);
		final GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(
			2, 4, 5, 20);
		gbc_separator.fill = GridBagConstraints.BOTH;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 15;
		panel_2.add(separator, gbc_separator);

		final JCheckBox chckbxPaskiInterfejsUytkowinka = new JCheckBox(
			"Płaski interfejs użytkowinka (pasek przycisków ustawiany osobno)");
		chckbxPaskiInterfejsUytkowinka.setOpaque(false);
		chckbxPaskiInterfejsUytkowinka.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxPaskiInterfejsUytkowinka.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxPaskiInterfejsUytkowinka = new GridBagConstraints();
		gbc_chckbxPaskiInterfejsUytkowinka.insets = new Insets(
			0, 0, 5, 0);
		gbc_chckbxPaskiInterfejsUytkowinka.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPaskiInterfejsUytkowinka.gridx = 0;
		gbc_chckbxPaskiInterfejsUytkowinka.gridy = 16;
		panel_2.add(chckbxPaskiInterfejsUytkowinka, gbc_chckbxPaskiInterfejsUytkowinka);

		final JCheckBox chckbxStylWindowsXp = new JCheckBox(
			"Styl Windows XP (menu i wszystkie paski)");
		chckbxStylWindowsXp.setOpaque(false);
		chckbxStylWindowsXp.setFont(new Font(
			"Segoe UI", Font.PLAIN, 11));
		chckbxStylWindowsXp.setFocusPainted(false);
		final GridBagConstraints gbc_chckbxStylWindowsXp = new GridBagConstraints();
		gbc_chckbxStylWindowsXp.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxStylWindowsXp.gridx = 0;
		gbc_chckbxStylWindowsXp.gridy = 17;
		panel_2.add(chckbxStylWindowsXp, gbc_chckbxStylWindowsXp);
	}

}
