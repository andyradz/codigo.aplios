package com.codigo.aplios.gui.control.gridview;

/**
 * Assembles the UI. The UI consists of a JTreeTable and a status label. As nodes are loaded by the
 * FileSystemModel2, in a background thread, the status label updates as well as the renderer to
 * draw the node that is being loaded differently.
 *
 * @author Scott Violet
 * @author Philip Milne
 */
public class TreeTableExample2 {
	// /** Number of instances of TreeTableExample2. */
	// protected static int ttCount;
	//
	// /** Model for the JTreeTable. */
	// protected FileSystemModel2 model;
	// /** Used to represent the model. */
	// protected JTreeTable treeTable;
	// /** Row the is being reloaded. */
	// protected int reloadRow;
	// /** TreePath being reloaded. */
	// protected TreePath reloadPath;
	// /**
	// * A counter increment as the Timer fies and the same path is being reloaded.
	// */
	// protected int reloadCounter;
	// /** Timer used to update reload state. */
	// protected Timer timer;
	// /** Used to indicate status. */
	// protected JLabel statusLabel;
	// /** Frame containing everything. */
	// protected JFrame frame;
	// /** Path created with. */
	// protected String path;
	//
	// public TreeTableExample2(final String path) {
	//
	// this.path = path;
	// TreeTableExample2.ttCount++;
	//
	// this.frame = createFrame();
	//
	// final Container cPane = this.frame.getContentPane();
	// final JMenuBar mb = createMenuBar();
	//
	// this.model = createModel(path);
	// this.treeTable = createTreeTable();
	// this.statusLabel = createStatusLabel();
	// cPane.add(new JScrollPane(
	// this.treeTable));
	// cPane.add(this.statusLabel, BorderLayout.SOUTH);
	//
	// this.reloadRow = -1;
	// this.frame.setJMenuBar(mb);
	// this.frame.pack();
	// this.frame.setVisible(true);
	// SwingUtilities.invokeLater(() -> reload(TreeTableExample2.this.model.getRoot()));
	// }
	//
	// /**
	// * Creates and return a JLabel that is used to indicate the status of loading.
	// */
	// protected JLabel createStatusLabel() {
	//
	// final JLabel retLabel = new JLabel(
	// " ");
	//
	// retLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	// retLabel.setBorder(new BevelBorder(
	// BevelBorder.LOWERED));
	// return retLabel;
	// }
	//
	// /**
	// * Creates and returns the instanceof JTreeTable that will be used. This also creates, but does
	// not
	// * start, the Timer that is used to update the display as files are loaded.
	// */
	// protected JTreeTable createTreeTable() {
	//
	// final JTreeTable treeTable = new JTreeTable(
	// this.model);
	// treeTable.getColumnModel()
	// .getColumn(1)
	// .setCellRenderer(new IndicatorRenderer());
	//
	// final Reloader rl = new Reloader();
	//
	// this.timer = new Timer(
	// 700, rl);
	// this.timer.setRepeats(true);
	// treeTable.getTree()
	// .addTreeExpansionListener(rl);
	// return treeTable;
	// }
	//
	// /**
	// * Creates the FileSystemModel2 that will be used.
	// */
	// protected FileSystemModel2 createModel(final String path) {
	//
	// return new FileSystemModel2(
	// path);
	// }
	//
	// /**
	// * Creates the JFrame that will contain everything.
	// */
	// protected JFrame createFrame() {
	//
	// final JFrame retFrame = new JFrame(
	// "TreeTable II");
	//
	// retFrame.addWindowListener(new WindowAdapter() {
	// @Override
	// public void windowClosing(final WindowEvent we) {
	//
	// if (--TreeTableExample2.ttCount == 0)
	// System.exit(0);
	// }
	// });
	// return retFrame;
	// }
	//
	// /**
	// * Creates a menu bar.
	// */
	// protected JMenuBar createMenuBar() {
	//
	// final JMenu fileMenu = new JMenu(
	// "File");
	// JMenuItem menuItem;
	//
	// menuItem = new JMenuItem(
	// "Open");
	// menuItem.addActionListener(ae -> {
	//
	// final JFileChooser fc = new JFileChooser(
	// TreeTableExample2.this.path);
	//
	// fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	//
	// final int result = fc.showOpenDialog(TreeTableExample2.this.frame);
	//
	// if (result == JFileChooser.APPROVE_OPTION) {
	// final String newPath = fc.getSelectedFile()
	// .getPath();
	//
	// new TreeTableExample2(
	// newPath);
	// }
	// });
	// fileMenu.add(menuItem);
	// fileMenu.addSeparator();
	//
	// menuItem = new JMenuItem(
	// "Reload");
	// menuItem.addActionListener(ae -> {
	//
	// final TreePath path = TreeTableExample2.this.treeTable.getTree()
	// .getSelectionPath();
	//
	// if (path != null) {
	// TreeTableExample2.this.model.stopLoading();
	// reload(path.getLastPathComponent());
	// }
	// });
	// fileMenu.add(menuItem);
	//
	// menuItem = new JMenuItem(
	// "Stop");
	// menuItem.addActionListener(ae -> TreeTableExample2.this.model.stopLoading());
	// fileMenu.add(menuItem);
	//
	// fileMenu.addSeparator();
	//
	// menuItem = new JMenuItem(
	// "Exit");
	// menuItem.addActionListener(ae -> System.exit(0));
	// fileMenu.add(menuItem);
	//
	// // Create a menu bar
	// final JMenuBar menuBar = new JMenuBar();
	//
	// menuBar.add(fileMenu);
	//
	// // Menu for the look and feels (lafs).
	// final UIManager.LookAndFeelInfo[] lafs = UIManager.getInstalledLookAndFeels();
	// final ButtonGroup lafGroup = new ButtonGroup();
	//
	// final JMenu optionsMenu = new JMenu(
	// "Options");
	//
	// menuBar.add(optionsMenu);
	//
	// for (final LookAndFeelInfo laf : lafs) {
	// final JRadioButtonMenuItem rb = new JRadioButtonMenuItem(
	// laf.getName());
	// optionsMenu.add(rb);
	// rb.setSelected(UIManager.getLookAndFeel()
	// .getName()
	// .equals(laf.getName()));
	// rb.putClientProperty("UIKey", laf);
	// rb.addItemListener(ae -> {
	//
	// final JRadioButtonMenuItem rb2 = (JRadioButtonMenuItem) ae.getSource();
	// if (rb2.isSelected()) {
	// final UIManager.LookAndFeelInfo info = (UIManager.LookAndFeelInfo)
	// rb2.getClientProperty("UIKey");
	// try {
	// UIManager.setLookAndFeel(info.getClassName());
	// SwingUtilities.updateComponentTreeUI(TreeTableExample2.this.frame);
	// }
	// catch (final Exception e) {
	// System.err.println("unable to set UI " + e.getMessage());
	// }
	// }
	// });
	// lafGroup.add(rb);
	// }
	// return menuBar;
	// }
	//
	// /**
	// * Invoked to reload the children of a particular node. This will also restart the timer.
	// */
	// protected void reload(final Object node) {
	//
	// this.model.reloadChildren(node);
	// if (!this.timer.isRunning())
	// this.timer.start();
	// }
	//
	// /**
	// * Updates the status label based on reloadRow.
	// */
	// protected void updateStatusLabel() {
	//
	// if (this.reloadPath != null) {
	// this.statusLabel.setText("Reloading: " +
	// this.model.getPath(this.reloadPath.getLastPathComponent()));
	// if ((this.reloadCounter % 4) < 2)
	// this.statusLabel.setForeground(Color.red);
	// else
	// this.statusLabel.setForeground(Color.blue);
	// }
	// else if (!this.model.isReloading()) {
	// this.statusLabel.setText("Total Size: " + NumberFormat.getInstance()
	// .format(this.model.getTotalSize(this.model.getRoot())));
	// this.statusLabel.setForeground(Color.black);
	// }
	// }
	//
	// /**
	// * Reloader is the ActionListener used in the Timer. In response to the timer updating it will
	// reset
	// * the reloadRow/reloadPath and generate the necessary event so that the display will update. It
	// * also implements the TreeExpansionListener so that if the tree is altered while loading the
	// * reloadRow is updated accordingly.
	// */
	// class Reloader implements ActionListener, TreeExpansionListener {
	// @Override
	// public void actionPerformed(final ActionEvent ae) {
	//
	// if (!TreeTableExample2.this.model.isReloading()) {
	// // No longer loading.
	// TreeTableExample2.this.timer.stop();
	// if (TreeTableExample2.this.reloadRow != -1)
	// generateChangeEvent(TreeTableExample2.this.reloadRow);
	// TreeTableExample2.this.reloadRow = -1;
	// TreeTableExample2.this.reloadPath = null;
	// }
	// else {
	// // Still loading, see if paths changed.
	// final TreePath newPath = TreeTableExample2.this.model.getPathLoading();
	//
	// if (newPath == null) {
	// // Hmm... Will usually indicate the reload thread
	// // completed between time we asked if reloading.
	// if (TreeTableExample2.this.reloadRow != -1)
	// generateChangeEvent(TreeTableExample2.this.reloadRow);
	// TreeTableExample2.this.reloadRow = -1;
	// TreeTableExample2.this.reloadPath = null;
	// }
	// else {
	// // Ok, valid path, see if matches last path.
	// final int newRow = TreeTableExample2.this.treeTable.getTree()
	// .getRowForPath(newPath);
	//
	// if (newPath.equals(TreeTableExample2.this.reloadPath)) {
	// TreeTableExample2.this.reloadCounter = (TreeTableExample2.this.reloadCounter + 1) % 8;
	// if (newRow != TreeTableExample2.this.reloadRow) {
	// final int lastRow = TreeTableExample2.this.reloadRow;
	//
	// TreeTableExample2.this.reloadRow = newRow;
	// generateChangeEvent(lastRow);
	// }
	// generateChangeEvent(TreeTableExample2.this.reloadRow);
	// }
	// else {
	// final int lastRow = TreeTableExample2.this.reloadRow;
	//
	// TreeTableExample2.this.reloadCounter = 0;
	// TreeTableExample2.this.reloadRow = newRow;
	// TreeTableExample2.this.reloadPath = newPath;
	// if (lastRow != TreeTableExample2.this.reloadRow)
	// generateChangeEvent(lastRow);
	// generateChangeEvent(TreeTableExample2.this.reloadRow);
	// }
	// }
	// }
	// updateStatusLabel();
	// }
	//
	// /**
	// * Generates and update event for the specified row. FileSystemModel2 could do this, but it would
	// * not know when the row has changed as a result of expanding/collapsing nodes in the tree.
	// */
	// protected void generateChangeEvent(final int row) {
	//
	// if (row != -1) {
	// final AbstractTableModel tModel = (AbstractTableModel)
	// TreeTableExample2.this.treeTable.getModel();
	//
	// tModel.fireTableChanged(new TableModelEvent(
	// tModel, row, row, 1));
	// }
	// }
	//
	// //
	// // TreeExpansionListener
	// //
	//
	// /**
	// * Invoked when the tree has expanded.
	// */
	// @Override
	// public void treeExpanded(final TreeExpansionEvent te) {
	//
	// updateRow();
	// }
	//
	// /**
	// * Invoked when the tree has collapsed.
	// */
	// @Override
	// public void treeCollapsed(final TreeExpansionEvent te) {
	//
	// updateRow();
	// }
	//
	// /**
	// * Updates the reloadRow and path, this does not genernate a change event.
	// */
	// protected void updateRow() {
	//
	// TreeTableExample2.this.reloadPath = TreeTableExample2.this.model.getPathLoading();
	//
	// if (TreeTableExample2.this.reloadPath != null)
	// TreeTableExample2.this.reloadRow = TreeTableExample2.this.treeTable.getTree()
	// .getRowForPath(TreeTableExample2.this.reloadPath);
	// }
	// }
	//
	// /**
	// * A renderer that will give an indicator when a cell is being reloaded.
	// */
	// class IndicatorRenderer extends DefaultTableCellRenderer {
	// /**
	// * Makes sure the number of displayed in an internationalized manner.
	// */
	// protected NumberFormat formatter;
	// /** Row that is currently being painted. */
	// protected int lastRow;
	//
	// IndicatorRenderer() {
	//
	// setHorizontalAlignment(SwingConstants.RIGHT);
	// this.formatter = NumberFormat.getInstance();
	// }
	//
	// /**
	// * Invoked as part of DefaultTableCellRenderers implemention. Sets the text of the label.
	// */
	// @Override
	// public void setValue(final Object value) {
	//
	// setText((value == null) ? "---" : this.formatter.format(value));
	// }
	//
	// /**
	// * Returns this.
	// */
	// @Override
	// public Component getTableCellRendererComponent(final JTable table, final Object value, final
	// boolean isSelected,
	// final boolean hasFocus, final int row, final int column) {
	//
	// super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	// this.lastRow = row;
	// return this;
	// }
	//
	// /**
	// * If the row being painted is also being reloaded this will draw a little indicator.
	// */
	// @Override
	// public void paint(final Graphics g) {
	//
	// if (this.lastRow == TreeTableExample2.this.reloadRow) {
	// final int width = getWidth();
	// final int height = getHeight();
	//
	// g.setColor(getBackground());
	// g.fillRect(0, 0, width, height);
	// g.setColor(getForeground());
	//
	// final int diameter = Math.min(width, height);
	//
	// if (TreeTableExample2.this.reloadCounter < 5)
	// g.fillArc((width - diameter) / 2, (height - diameter) / 2, diameter, diameter, 90,
	// -(TreeTableExample2.this.reloadCounter * 90));
	// else
	// g.fillArc((width - diameter) / 2, (height - diameter) / 2, diameter, diameter, 90,
	// (4 - (TreeTableExample2.this.reloadCounter % 4)) * 90);
	// }
	// else
	// super.paint(g);
	// }
	// }
	//
	// public static void main(final String[] args) {
	//
	// if (args.length > 0)
	// for (int counter = args.length - 1; counter >= 0; counter--)
	// new TreeTableExample2(
	// args[counter]);
	// else {
	// String path;
	//
	// try {
	// path = System.getProperty("user.home");
	// if (path != null)
	// new TreeTableExample2(
	// path);
	// }
	// catch (final SecurityException se) {
	// path = null;
	// }
	// if (path == null)
	// System.out.println("Could not determine home directory");
	// }
	// }
}
