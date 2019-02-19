package com.codigo.aplios.gui.core.gridview.editors;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class FiveCharacterEditor extends DefaultCellEditor {

	private static final long serialVersionUID = -3352104220279754899L;

	FiveCharacterEditor() {

		super(
				new JTextField());
	}

	@Override
	public boolean stopCellEditing() {

		try {
			final String editingValue = (String) getCellEditorValue();

			if (editingValue.length() != 5) {
				final JTextField textField = (JTextField) getComponent();
				textField.setBorder(new LineBorder(
					Color.red));
				textField.selectAll();
				textField.requestFocusInWindow();

				JOptionPane.showMessageDialog(null, "Please enter string with 5 letters.", "Alert!",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		catch (final ClassCastException exception) {
			return false;
		}

		return super.stopCellEditing();
	}

	@Override
	public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected,
			final int row, final int column) {

		final Component c = super.getTableCellEditorComponent(table, value, isSelected, row, column);
		((JComponent) c).setBorder(new LineBorder(
			Color.black));

		return c;
	}

}
