/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose
 * Tools | Templates and open the template in the editor.
 */
package com.codigo.aplios.exsplorer;

/*
 * import java.awt.Color; import java.awt.Component; import java.awt.Dimension; import
 * java.awt.Graphics; import java.awt.Graphics2D; import java.awt.Image; import java.awt.Point;
 * import java.awt.Rectangle; import java.awt.RenderingHints; import java.awt.event.ActionEvent;
 * import java.awt.event.ComponentAdapter; import java.awt.event.ComponentEvent; import
 * java.awt.event.KeyAdapter; import java.awt.event.KeyEvent; import java.io.File; import
 * java.io.FileNotFoundException; import java.io.IOException; import java.nio.file.DirectoryStream;
 * import java.nio.file.Files; import java.nio.file.Path; import java.nio.file.Paths; import
 * java.util.ArrayList; import java.util.Arrays; import java.util.List;
 * 
 * import javax.swing.AbstractAction; import javax.swing.Icon; import javax.swing.ImageIcon; import
 * javax.swing.JComponent; import javax.swing.JFrame; import javax.swing.JScrollPane; import
 * javax.swing.JTable; import javax.swing.JViewport; import javax.swing.KeyStroke; import
 * javax.swing.UIManager; import javax.swing.WindowConstants; import
 * javax.swing.filechooser.FileSystemView; import javax.swing.table.DefaultTableCellRenderer; import
 * javax.swing.table.TableCellRenderer; import javax.swing.table.TableModel;
 * 
 *//**
	 *
	 * @author andrzej.radziszewski
	 *//*
		 * public class FileTableDemo {
		 * 
		 * public static String getRelativePath(final File base, final File name) throws IOException {
		 * 
		 * final File parent = base.getParentFile();
		 * 
		 * if (parent == null) throw new IOException( "No common directory");
		 * 
		 * final String bpath = base.getCanonicalPath(); final String fpath = name.getCanonicalPath();
		 * 
		 * if (fpath.startsWith(bpath)) return fpath.substring(bpath.length() + 1); else return (".." +
		 * File.separator + FileTableDemo.getRelativePath(parent, name)); }
		 * 
		 * public static void scrollToVisible(final JTable table, final int rowIndex, final int vColIndex) {
		 * 
		 * if (!(table.getParent() instanceof JViewport)) return; final JViewport viewport = (JViewport)
		 * table.getParent();
		 * 
		 * // This rectangle is relative to the table where the // northwest corner of cell (0,0) is always
		 * (0,0). final Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
		 * 
		 * // The location of the viewport relative to the table final Point pt =
		 * viewport.getViewPosition();
		 * 
		 * // Translate the cell location so that it is relative // to the view, assuming the northwest
		 * corner of the // view is (0,0) rect.setLocation(rect.x - pt.x, rect.y - pt.y);
		 * 
		 * table.scrollRectToVisible(rect);
		 * 
		 * // Scroll the area into view viewport.scrollRectToVisible(rect); table.changeSelection(rowIndex,
		 * vColIndex, false, false); }
		 * 
		 * private static void createKeybindings(final JTable table) {
		 * 
		 * table.getInputMap(); table.getActionMap();
		 * 
		 * // KeyStroke spaceKey = KeyStroke.getKeyStroke(KeyEvent.KEY_PRESSED, 0); // im.put(spaceKey,
		 * "passed"); // am.put("passed", new AbstractAction() { // @Override // public void
		 * actionPerformed(ActionEvent ae) { // // Enumeration<TableColumn> cols =
		 * table.getColumnModel().getColumns(); // while (cols.hasMoreElements()) //
		 * cols.nextElement().setCellRenderer(new StatusColumnCellRenderer()); // // } // }); //
		 * im.put(spaceKey, "released"); // am.put("released", new AbstractAction() { // @Override // public
		 * void actionPerformed(ActionEvent ae) { // // Enumeration<TableColumn> cols =
		 * table.getColumnModel().getColumns(); // //while (cols.hasMoreElements()) //
		 * //cols.nextElement().setCellRenderer(new DefaultTableCellRenderer()); // // } // });
		 * table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
		 * .put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		 * 
		 * table.getActionMap() .put("Enter", new AbstractAction() {
		 * 
		 * @Override public void actionPerformed(final ActionEvent ae) {
		 * 
		 * final int column = 1; final int row = table.getSelectedRow(); final String value =
		 * table.getModel() .getValueAt(row, column) .toString();
		 * 
		 * if (!Paths.get(value) .toFile() .isDirectory()) return;
		 * 
		 * /// System.out.println(value); ((FileTableModel) table.getModel()).filenames =
		 * FileTableDemo.fileList(value); ((FileTableModel) table.getModel()).fireTableDataChanged();
		 * 
		 * table.requestFocus(false); table.changeSelection(0, 0, false, false);
		 * 
		 * // File file = new File("c:/windows"); sun.awt.shell.ShellFolder sf; try { sf =
		 * sun.awt.shell.ShellFolder.getShellFolder(Paths.get(value) .toFile()); final Icon icon = new
		 * ImageIcon( sf.getIcon(true)); // System.out.println("type = " + sf.getFolderType());
		 * 
		 * final Image image = ((ImageIcon) icon).getImage();
		 * 
		 * // try { // // save to file // //File outputfile = new File("d://saved.png"); //
		 * //ImageIO.write(buffered, "png", outputfile); // } catch (IOException e) { //
		 * e.printStackTrace(); // } } catch (final FileNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * }); }
		 * 
		 * public static void showCell(final int row, final int column, final JTable table) {
		 * 
		 * final Rectangle rect = table.getCellRect(row, column, true); table.scrollRectToVisible(rect);
		 * table.clearSelection(); table.setRowSelectionInterval(row, row); //
		 * ((AbstractTableModel)table.getModel()).fireTableDataChanged(); //
		 * table.getModel().fireTableDataChanged(); // notify the model }
		 * 
		 * public static void scrolltable(final JTable table) {
		 * 
		 * table.addComponentListener(new ComponentAdapter() {
		 * 
		 * @Override public void componentResized(final ComponentEvent e) {
		 * 
		 * // int lastIndex =table. // table.changeSelection(lastIndex, 0,false,false); }
		 * 
		 * }); }
		 * 
		 * public static String[] fileList(final String directory) {
		 * 
		 * final List<String> fileNames = new ArrayList<>(); try (DirectoryStream<Path> directoryStream =
		 * Files.newDirectoryStream(Paths.get(directory))) { boolean parentPath = false; if (!parentPath) {
		 * parentPath = true; new File( directory).toURI() .relativize(new File(
		 * directory.toString()).toURI()) .getPath(); final Path p2 = new File( directory).toPath();
		 * fileNames.add(p2.getParent() == null ? p2.getRoot() .toString() : p2.getParent() .toString()); }
		 * for (final Path path : directoryStream) fileNames.add(path.toString());
		 * 
		 * } catch (final IOException ex) { ex.printStackTrace(); } final Object[] arr =
		 * fileNames.toArray(); return Arrays.copyOf(arr, arr.length, String[].class); }
		 * 
		 * public static void main(final String[] args) throws IllegalAccessException {
		 * 
		 * FileSystemView.getFileSystemView();
		 * 
		 * final File[] drives = File.listRoots(); if ((drives != null) && (drives.length > 0)) for (final
		 * File aDrive : drives) { // System.out.println("Drive Letter: " + aDrive); //
		 * System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive)); //
		 * System.out.println("\tTotal space: " + aDrive.getTotalSpace()); //
		 * System.out.println("\tFree space: " + aDrive.getFreeSpace()); // System.out.println(); }
		 * 
		 * final Path sourceFile = Paths.get("D:/kdpw.grafika/material-design-icons-master.zip"); final Path
		 * targetFile = Paths.get("D:/"); sourceFile.relativize(targetFile); //
		 * System.out.println(relativePath); try { for (final javax.swing.UIManager.LookAndFeelInfo info :
		 * javax.swing.UIManager.getInstalledLookAndFeels()) if ("Windows".equals(info.getName())) {
		 * UIManager.setLookAndFeel(info.getClassName()); // FontUIResource font = new
		 * FontUIResource("Tahoma", Font.PLAIN, 12); // UIManager.put("Table.font", font);
		 * 
		 * // UIManager.put("Table.foreground", Color.RED); break; } } catch (ClassNotFoundException |
		 * InstantiationException | javax.swing.UnsupportedLookAndFeelException ex) {
		 * 
		 * }
		 * 
		 * // Figure out what directory to display File dir; if (args.length > 0) dir = new File( args[0]);
		 * else dir = new File( "C:");
		 * 
		 * // Create a TableModel object to represent the contents of the directory final FileTableModel
		 * model = new FileTableModel( dir);
		 * 
		 * // Display it all in a scrolling window and make the window appear // JFrame frame = new
		 * JFrame("FileTableDemo"); final JForm form = new JForm(); final JFrame frame =
		 * form.makeFrame("Smart Explorer 1.00 Alfa"); // Create a JTable and tell it to display our model
		 * final FileTable table = new FileTable( model); table.setRowHeight(24);
		 * table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		 * 
		 * FileTableDemo.createKeybindings(table); table.setAutoCreateRowSorter(true);
		 * table.getColumnModel() .getColumn(0) .setMaxWidth(20); table.getColumnModel() .getColumn(2)
		 * .setWidth(10); table.clearSelection(); table.setIntercellSpacing(new Dimension( 0, 1));
		 * table.setSelectionBackground(Color.BLACK); table.setSelectionForeground(Color.WHITE);
		 * frame.getContentPane() .add(new JScrollPane( table));
		 * 
		 * // table.setShowHorizontalLines(false); table.setShowVerticalLines(false);
		 * 
		 * // table.setFocusable(true); table.getColumnModel() .getColumn(0) .setCellRenderer(new
		 * ImageTableCellRender()); table.getColumnModel() .getColumn(1) .setCellRenderer(new
		 * FileNameTableCellRender()); table.requestFocus(true); table.changeSelection(0, 0, false, false);
		 * 
		 * table.addKeyListener(new KeyAdapter() {
		 * 
		 * @Override public void keyPressed(final KeyEvent e) {
		 * 
		 * e.getID(); table.getSelectedColumn();
		 * 
		 * // Update info in table // for(int i = 0; i < model.getRowCount(); i++){ // String value =
		 * (String)model.getValueAt(i, selectedColumn); // model.setValueAt(value + key, i, selectedColumn);
		 * // } }
		 * 
		 * });
		 * 
		 * table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
		 * 
		 * @Override public void paintComponent(final Graphics g) {
		 * 
		 * final Graphics2D g2d = (Graphics2D) g; g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		 * RenderingHints.VALUE_ANTIALIAS_ON); getWidth(); getHeight();
		 * 
		 * // Color color1 = getBackground(); // Color color2 = color1.darker(); // Paint a gradient from
		 * top to bottom // GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2); //
		 * g2d.setPaint(gp); // g2d.fillRect(0, 0, w, h); // g2d.drawString("1212", 1, 9);
		 * super.paintComponent(g);
		 * 
		 * g2d.dispose(); }
		 * 
		 * @Override public Component getTableCellRendererComponent(final JTable table, final Object value,
		 * final boolean isSelected, final boolean hasFocus, final int row, final int col) {
		 * 
		 * final Component l = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
		 * col); // if (row%2 == 0){ // l.setBackground(Color.WHITE); // } // else { //
		 * l.setBackground(Color.LIGHT_GRAY); // }
		 * 
		 * // if (table.isCellSelected(row, col)) // setForeground(Color.red); // else if
		 * (table.isRowSelected(row)) // setForeground(Color.green); // else if
		 * (table.isColumnSelected(col)) // setForeground(Color.blue); // else //
		 * setForeground(Color.black); return l; }
		 * 
		 * }); FileTableDemo.scrollToVisible(table, 0, 0); frame.setSize(1024, 860);
		 * frame.setLocationRelativeTo(null); frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 * frame.setVisible(true); }
		 * 
		 * }
		 * 
		 * class FileTable extends JTable {
		 * 
		 * public FileTable(final TableModel model) {
		 * 
		 * super(model); }
		 * 
		 * //
		 * -------------------------------------------------------------------------------------------------
		 * -----------------
		 * 
		 * @Override public Component prepareRenderer(final TableCellRenderer renderer, final int row, final
		 * int column) {
		 * 
		 * { final Component c = super.prepareRenderer(renderer, row, column);
		 * 
		 * // TableColumn tableColumn = getColumnModel().getColumn(column); // int preferredWidth =
		 * tableColumn.getMinWidth(); // int maxWidth = tableColumn.getMaxWidth(); // // TableCellRenderer
		 * cellRenderer = getCellRenderer(row, column); // // Component c = prepareRenderer(cellRenderer,
		 * row, column); // int width = c.getPreferredSize().width + getIntercellSpacing().width; //
		 * preferredWidth = Math.max(preferredWidth, width); // // // We've exceeded the maximum width, no
		 * need to check other rows // // if (preferredWidth >= maxWidth) { // preferredWidth = maxWidth; //
		 * } // // tableColumn.setPreferredWidth(preferredWidth); final Color alternateColor = new Color(
		 * 252, 242, 206); final Color whiteColor = Color.WHITE; if (!c.getBackground()
		 * .equals(getSelectionBackground())) { Color bg = ((row % 2) == 0 ? alternateColor : whiteColor);
		 * c.setBackground(bg); bg = null; } return c; }
		 * 
		 * // Component component = super.prepareRenderer(renderer, row, column); // int rendererWidth =
		 * component.getPreferredSize().width; // TableColumn tableColumn =
		 * getColumnModel().getColumn(column); // tableColumn.setPreferredWidth(Math.min(rendererWidth +
		 * getIntercellSpacing().width, // tableColumn // .getPreferredWidth())); // return component; }
		 * 
		 * }
		 */
