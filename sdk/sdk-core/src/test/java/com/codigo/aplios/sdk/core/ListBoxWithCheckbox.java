package com.codigo.aplios.sdk.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

public class ListBoxWithCheckbox {

    public static void main(String[] args) {
        final JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane()
                .add(new CheckCombo().getContent());
        f.setSize(300, 160);
        f.setLocation(200, 200);
        f.setVisible(true);
    }

}

class CheckCombo
        implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        final JComboBox cb = (JComboBox)e.getSource();
        final CheckComboStore store = (CheckComboStore)cb.getSelectedItem();
        final CheckComboRenderer ccr = (CheckComboRenderer)cb.getRenderer();
        ccr.checkBox.setSelected(store.state = !store.state);
    }

    public JPanel getContent() {
        final String[] ids = {"north", "west", "south", "east"};
        final Boolean[] values = {Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE};
        final CheckComboStore[] stores = new CheckComboStore[ids.length];
        for (int j = 0; j < ids.length; j++)
            stores[j] = new CheckComboStore(ids[j],
                    values[j]);
        final JComboBox combo = new JComboBox(stores);
        combo.setRenderer(new CheckComboRenderer());
        combo.addActionListener(this);
        final JPanel panel = new JPanel();
        panel.add(combo);
        return panel;
    }

}

class CheckComboRenderer
        implements ListCellRenderer {

    JCheckBox checkBox;

    public CheckComboRenderer() {
        this.checkBox = new JCheckBox();
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        final CheckComboStore store = (CheckComboStore)value;
        this.checkBox.setText(store.id);
        this.checkBox.setSelected(store.state.booleanValue());
        this.checkBox.setBackground(isSelected ? Color.red : Color.white);
        this.checkBox.setForeground(isSelected ? Color.white : Color.black);
        return this.checkBox;
    }

}

class CheckComboStore {

    String id;

    Boolean state;

    public CheckComboStore(String id, Boolean state) {
        this.id = id;
        this.state = state;
    }

}
