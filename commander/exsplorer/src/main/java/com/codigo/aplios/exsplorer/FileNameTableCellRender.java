/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.exsplorer;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author andrzej.radziszewski
 */
public class FileNameTableCellRender extends DefaultTableCellRenderer {

	private static final long serialVersionUID = -2199133597812949262L;

	@Override
	public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int col) {

		final JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		String myValue = String.valueOf(value);
		final int index = myValue.lastIndexOf("\\");

		myValue = myValue.substring(index + 1);
		setText(myValue);

		return l;
	}

}
