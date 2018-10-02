package com.codigo.aplios.sdk.core;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

public class CobmboboxWithTreeList {

    public static void main(final String[] args) {

        final String[] m = {"A", "B", "C"};
        final DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Node("Values"));
        root.add(new DefaultMutableTreeNode(new Node("Value 1", m)));
        root.add(new DefaultMutableTreeNode(new Node("Value 2", m)));
        final DefaultMutableTreeNode leaf = new DefaultMutableTreeNode(new Node("Value 3", m));
        root.add(leaf);
        leaf.add(new DefaultMutableTreeNode(new Node("Value 3A", m)));
        leaf.add(new DefaultMutableTreeNode(new Node("Value 3B", m)));

        final JTree tree = new JTree(root);
        final RendererDispatcher rendererDispatcher = new RendererDispatcher(new JComboBox<String>());
        final RendererDispatcher editorDispatcher = new RendererDispatcher(new JComboBox<String>());
        tree.setCellRenderer(rendererDispatcher);
        tree.setCellEditor(editorDispatcher);
        tree.setEditable(true);

        final JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane()
                .add(new JScrollPane(tree));
        f.setSize(320, 240);
        f.setVisible(true);
    }

}

class Node {

    String name;

    String[] Values;

    int myIndex;

    public Node(final String name, final String... Values) {

        this.name = name;
        this.Values = Values;
    }

    @Override
    public String toString() {

        return this.name;
    }

    public int getMyIndex() {

        return this.myIndex;
    }

    public void setIndex(final int selectedValueIndex) {

        this.myIndex = selectedValueIndex;
    }

    public String[] getValues() {

        return this.Values;
    }

}

class RendererDispatcher
        extends DefaultCellEditor
        implements TreeCellRenderer {

    JPanel panel = new JPanel();

    JLabel ValueName = new JLabel();

    JComboBox<String> comboBox;

    Node node;

    public RendererDispatcher(final JComboBox<String> comboBox) {

        super(comboBox);
        this.comboBox = comboBox;
        this.panel.setOpaque(false);
        this.panel.add(this.ValueName);
        this.panel.add(comboBox);
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected,
            final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {

        final Node node = RendererDispatcher.extractNode(value);
        this.setContents(node);
        return this.panel;
    }

    @Override
    public Component getTreeCellEditorComponent(final JTree tree, final Object value, final boolean selected,
            final boolean expanded, final boolean leaf, final int row) {

        final Node node = RendererDispatcher.extractNode(value);
        this.setContents(node);
        this.node = node;
        return this.panel;
    }

    @Override
    public Object getCellEditorValue() {

        final Object o = super.getCellEditorValue();
        final DefaultComboBoxModel<String> m = (DefaultComboBoxModel<String>)this.comboBox.getModel();
        final Node n = new Node(this.ValueName.getText(), this.node.getValues());
        n.setIndex(m.getIndexOf(o));
        return n;
    }

    @Override
    public boolean isCellEditable(final EventObject event) {

        final Object source = event.getSource();
        if (!(source instanceof JTree) || !(event instanceof MouseEvent))
            return false;
        final JTree tree = (JTree)source;
        final MouseEvent mouseEvent = (MouseEvent)event;
        final TreePath path = tree.getPathForLocation(mouseEvent.getX(), mouseEvent.getY());
        if (path == null)
            return false;
        final Object node = path.getLastPathComponent();
        if ((node == null) || !(node instanceof DefaultMutableTreeNode))
            return false;

        final Rectangle r = tree.getPathBounds(path);
        if (r == null)
            return false;
        final Dimension d = this.panel.getPreferredSize();
        r.setSize(new Dimension(d.width, r.height));
        if (r.contains(mouseEvent.getX(), mouseEvent.getY())) {
            final Point pt = SwingUtilities.convertPoint(tree, mouseEvent.getPoint(), this.panel);
            final Object o = SwingUtilities.getDeepestComponentAt(this.panel, pt.x, pt.y);
            if (o instanceof JComboBox)
                this.comboBox.showPopup();
            else if (o instanceof Component) {
                final Object oo = SwingUtilities.getAncestorOfClass(JComboBox.class, (Component)o);
                if (oo instanceof JComboBox)
                    this.comboBox.showPopup();
            }
            return true;
        }
        return this.delegate.isCellEditable(event);
    }

    private static Node extractNode(final Object value) {

        if (value instanceof DefaultMutableTreeNode) {
            final DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            final Object userObject = node.getUserObject();
            if (userObject instanceof Node)
                return (Node)userObject;
        }
        return null;
    }

    private void setContents(final Node node) {

        if (node == null)
            return;
        this.ValueName.setText(node.toString());
        final DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>)this.comboBox.getModel();
        model.removeAllElements();
        if (node.getValues().length > 0) {
            this.panel.add(this.comboBox);
            for (final String s : node.getValues())
                model.addElement(s);
            this.comboBox.setSelectedIndex(node.getMyIndex());
        } else
            this.panel.remove(this.comboBox);
    }

}
