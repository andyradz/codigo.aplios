
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * A basic File Browser. Requires 1.6+ for the Desktop & SwingWorker classes, amongst other minor
 * things.
 *
 * Includes support classes FileTableModel & FileTreeCellRenderer.
 *
 * @TODO Bugs
 *       <li>Fix keyboard focus issues - especially when functions like rename/delete etc. are
 *       called that update nodes & file lists.
 *       <li>Needs more testing in general.
 *
 * @TODO Functionality
 *       <li>Double clicking a directory in the table, should update the tree
 *       <li>Move progress bar?
 *       <li>Add other file display modes (besides table) in CardLayout?
 *       <li>Menus + other cruft?
 *       <li>Implement history/back
 *       <li>Allow multiple selection
 *       <li>Add file search
 *
 * @author Andrew Thompson
 * @version 2011-06-08
 * @see http://codereview.stackexchange.com/q/4446/7784
 * @license LGPL
 */
class FileBrowser {

	/** Title of the application */
	public static final String	APP_TITLE	= "FileBro";
	/** Used to open/edit/print files. */
	private Desktop				desktop;
	/** Provides nice icons and names for files. */
	private FileSystemView		fileSystemView;

	/** currently selected File. */
	private File currentFile;

	/** Main GUI container */
	private JPanel gui;

	/** File-system tree. Built Lazily */
	private JTree				tree;
	private DefaultTreeModel	treeModel;

	/** Directory listing */
	private JTable					table;
	private JProgressBar			progressBar;
	/** Table model for File[]. */
	private FileTableModel1			fileTableModel;
	private ListSelectionListener	listSelectionListener;
	private boolean					cellSizesSet	= false;
	private final int				rowIconPadding	= 6;

	/* File controls. */
	private JButton	openFile;
	private JButton	printFile;
	private JButton	editFile;

	/* File details. */
	private JLabel			fileName;
	private JTextField		path;
	private JLabel			date;
	private JLabel			size;
	private JCheckBox		readable;
	private JCheckBox		writable;
	private JCheckBox		executable;
	private JRadioButton	isDirectory;
	private JRadioButton	isFile;

	/* GUI options/containers for new File/Directory creation. Created lazily. */
	private JPanel			newFilePanel;
	private JRadioButton	newTypeFile;
	private JTextField		name;

	public Container getGui() {

		if (this.gui == null) {
			this.gui = new JPanel(
				new BorderLayout(
					3, 3));
			this.gui.setBorder(new EmptyBorder(
				5, 5, 5, 5));

			this.fileSystemView = FileSystemView.getFileSystemView();
			this.desktop = Desktop.getDesktop();

			final JPanel detailView = new JPanel(
				new BorderLayout(
					3, 3));

			this.table = new JTable();
			this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.table.setAutoCreateRowSorter(true);
			this.table.setShowVerticalLines(false);

			this.listSelectionListener = lse -> {
				final int row = FileBrowser.this.table.getSelectionModel()
						.getLeadSelectionIndex();
				FileBrowser.this.setFileDetails(((FileTableModel1) FileBrowser.this.table.getModel()).getFile(row));
			};
			this.table.getSelectionModel()
					.addListSelectionListener(this.listSelectionListener);
			final JScrollPane tableScroll = new JScrollPane(
				this.table);
			final Dimension d = tableScroll.getPreferredSize();
			tableScroll.setPreferredSize(new Dimension(
				(int) d.getWidth(), (int) d.getHeight() / 2));
			detailView.add(tableScroll, BorderLayout.CENTER);

			// the File tree
			final DefaultMutableTreeNode root = new DefaultMutableTreeNode();
			this.treeModel = new DefaultTreeModel(
				root);

			final TreeSelectionListener treeSelectionListener = tse -> {
				final DefaultMutableTreeNode node = (DefaultMutableTreeNode) tse.getPath()
						.getLastPathComponent();
				FileBrowser.this.showChildren(node);
				FileBrowser.this.setFileDetails((File) node.getUserObject());
			};

			// show the file system roots.
			final File[] roots = this.fileSystemView.getRoots();
			for (final File fileSystemRoot : roots) {
				final DefaultMutableTreeNode node = new DefaultMutableTreeNode(
					fileSystemRoot);
				root.add(node);
				final File[] files = this.fileSystemView.getFiles(fileSystemRoot, true);
				for (final File file : files)
					if (file.isDirectory())
						node.add(new DefaultMutableTreeNode(
							file));
			}

			this.tree = new JTree(
				this.treeModel);
			this.tree.setRootVisible(false);
			this.tree.addTreeSelectionListener(treeSelectionListener);
			this.tree.setCellRenderer(new FileTreeCellRenderer());
			this.tree.expandRow(0);
			final JScrollPane treeScroll = new JScrollPane(
				this.tree);

			// as per trashgod tip
			this.tree.setVisibleRowCount(15);

			final Dimension preferredSize = treeScroll.getPreferredSize();
			final Dimension widePreferred = new Dimension(
				200, (int) preferredSize.getHeight());
			treeScroll.setPreferredSize(widePreferred);

			// details for a File
			final JPanel fileMainDetails = new JPanel(
				new BorderLayout(
					4, 2));
			fileMainDetails.setBorder(new EmptyBorder(
				0, 6, 0, 6));

			final JPanel fileDetailsLabels = new JPanel(
				new GridLayout(
					0, 1, 2, 2));
			fileMainDetails.add(fileDetailsLabels, BorderLayout.WEST);

			final JPanel fileDetailsValues = new JPanel(
				new GridLayout(
					0, 1, 2, 2));
			fileMainDetails.add(fileDetailsValues, BorderLayout.CENTER);

			fileDetailsLabels.add(new JLabel(
				"File", SwingConstants.TRAILING));
			this.fileName = new JLabel();
			fileDetailsValues.add(this.fileName);
			fileDetailsLabels.add(new JLabel(
				"Path/name", SwingConstants.TRAILING));
			this.path = new JTextField(
				5);
			this.path.setEditable(false);
			fileDetailsValues.add(this.path);
			fileDetailsLabels.add(new JLabel(
				"Last Modified", SwingConstants.TRAILING));
			this.date = new JLabel();
			fileDetailsValues.add(this.date);
			fileDetailsLabels.add(new JLabel(
				"File size", SwingConstants.TRAILING));
			this.size = new JLabel();
			fileDetailsValues.add(this.size);
			fileDetailsLabels.add(new JLabel(
				"Type", SwingConstants.TRAILING));

			final JPanel flags = new JPanel(
				new FlowLayout(
					FlowLayout.LEADING, 4, 0));

			this.isDirectory = new JRadioButton(
				"Directory");
			flags.add(this.isDirectory);

			this.isFile = new JRadioButton(
				"File");
			flags.add(this.isFile);
			fileDetailsValues.add(flags);

			final JToolBar toolBar = new JToolBar();
			// mnemonics stop working in a floated toolbar
			toolBar.setFloatable(false);

			final JButton locateFile = new JButton(
				"Locate");
			locateFile.setMnemonic('l');

			locateFile.addActionListener(ae -> {
				try {
					System.out.println("Locate: " + FileBrowser.this.currentFile.getParentFile());
					FileBrowser.this.desktop.open(FileBrowser.this.currentFile.getParentFile());
				}
				catch (final Throwable t) {
					FileBrowser.this.showThrowable(t);
				}
				FileBrowser.this.gui.repaint();
			});
			toolBar.add(locateFile);

			this.openFile = new JButton(
				"Open");
			this.openFile.setMnemonic('o');

			this.openFile.addActionListener(ae -> {
				try {
					System.out.println("Open: " + FileBrowser.this.currentFile);
					FileBrowser.this.desktop.open(FileBrowser.this.currentFile);
				}
				catch (final Throwable t) {
					FileBrowser.this.showThrowable(t);
				}
				FileBrowser.this.gui.repaint();
			});
			toolBar.add(this.openFile);

			this.editFile = new JButton(
				"Edit");
			this.editFile.setMnemonic('e');
			this.editFile.addActionListener(ae -> {
				try {
					FileBrowser.this.desktop.edit(FileBrowser.this.currentFile);
				}
				catch (final Throwable t) {
					FileBrowser.this.showThrowable(t);
				}
			});
			toolBar.add(this.editFile);

			this.printFile = new JButton(
				"Print");
			this.printFile.setMnemonic('p');
			this.printFile.addActionListener(ae -> {
				try {
					FileBrowser.this.desktop.print(FileBrowser.this.currentFile);
				}
				catch (final Throwable t) {
					FileBrowser.this.showThrowable(t);
				}
			});
			toolBar.add(this.printFile);

			// Check the actions are supported on this platform!
			this.openFile.setEnabled(this.desktop.isSupported(Desktop.Action.OPEN));
			this.editFile.setEnabled(this.desktop.isSupported(Desktop.Action.EDIT));
			this.printFile.setEnabled(this.desktop.isSupported(Desktop.Action.PRINT));

			flags.add(new JLabel(
				"::  Flags"));
			this.readable = new JCheckBox(
				"Read  ");
			this.readable.setMnemonic('a');
			flags.add(this.readable);

			this.writable = new JCheckBox(
				"Write  ");
			this.writable.setMnemonic('w');
			flags.add(this.writable);

			this.executable = new JCheckBox(
				"Execute");
			this.executable.setMnemonic('x');
			flags.add(this.executable);

			int count = fileDetailsLabels.getComponentCount();
			for (int ii = 0; ii < count; ii++)
				fileDetailsLabels.getComponent(ii)
						.setEnabled(false);

			count = flags.getComponentCount();
			for (int ii = 0; ii < count; ii++)
				flags.getComponent(ii)
						.setEnabled(false);

			final JPanel fileView = new JPanel(
				new BorderLayout(
					3, 3));

			fileView.add(toolBar, BorderLayout.NORTH);
			fileView.add(fileMainDetails, BorderLayout.CENTER);

			detailView.add(fileView, BorderLayout.SOUTH);

			final JSplitPane splitPane = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, treeScroll, detailView);
			this.gui.add(splitPane, BorderLayout.CENTER);

			final JPanel simpleOutput = new JPanel(
				new BorderLayout(
					3, 3));
			this.progressBar = new JProgressBar();
			simpleOutput.add(this.progressBar, BorderLayout.EAST);
			this.progressBar.setVisible(false);

			this.gui.add(simpleOutput, BorderLayout.SOUTH);

		}
		return this.gui;
	}

	public void showRootFile() {

		// ensure the main files are displayed
		this.tree.setSelectionInterval(0, 0);
	}

	private TreePath findTreePath(final File find) {

		for (int ii = 0; ii < this.tree.getRowCount(); ii++) {
			final TreePath treePath = this.tree.getPathForRow(ii);
			final Object object = treePath.getLastPathComponent();
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode) object;
			final File nodeFile = (File) node.getUserObject();

			if (nodeFile == find)
				return treePath;
		}
		// not found!
		return null;
	}

	private void showErrorMessage(final String errorMessage, final String errorTitle) {

		JOptionPane.showMessageDialog(this.gui, errorMessage, errorTitle, JOptionPane.ERROR_MESSAGE);
	}

	private void showThrowable(final Throwable t) {

		t.printStackTrace();
		JOptionPane.showMessageDialog(this.gui, t.toString(), t.getMessage(), JOptionPane.ERROR_MESSAGE);
		this.gui.repaint();
	}

	/** Update the table on the EDT */
	private void setTableData(final File[] files) {

		SwingUtilities.invokeLater(() -> {
			if (FileBrowser.this.fileTableModel == null) {
				FileBrowser.this.fileTableModel = new FileTableModel1();
				FileBrowser.this.table.setModel(FileBrowser.this.fileTableModel);
			}
			FileBrowser.this.table.getSelectionModel()
					.removeListSelectionListener(FileBrowser.this.listSelectionListener);
			FileBrowser.this.fileTableModel.setFiles(files);
			FileBrowser.this.table.getSelectionModel()
					.addListSelectionListener(FileBrowser.this.listSelectionListener);
			if (!FileBrowser.this.cellSizesSet) {
				final Icon icon = FileBrowser.this.fileSystemView.getSystemIcon(files[0]);

				// size adjustment to better account for icons
				FileBrowser.this.table.setRowHeight(icon.getIconHeight() + FileBrowser.this.rowIconPadding);

				FileBrowser.this.setColumnWidth(0, -1);
				FileBrowser.this.setColumnWidth(3, 60);
				FileBrowser.this.table.getColumnModel()
						.getColumn(3)
						.setMaxWidth(120);
				FileBrowser.this.setColumnWidth(4, -1);
				FileBrowser.this.setColumnWidth(5, -1);
				FileBrowser.this.setColumnWidth(6, -1);
				FileBrowser.this.setColumnWidth(7, -1);
				FileBrowser.this.setColumnWidth(8, -1);
				FileBrowser.this.setColumnWidth(9, -1);

				FileBrowser.this.cellSizesSet = true;
			}
		});
	}

	private void setColumnWidth(final int column, int width) {

		final TableColumn tableColumn = this.table.getColumnModel()
				.getColumn(column);
		if (width < 0) {
			// use the preferred width of the header..
			final JLabel label = new JLabel(
				(String) tableColumn.getHeaderValue());
			final Dimension preferred = label.getPreferredSize();
			// altered 10->14 as per camickr comment.
			width = (int) preferred.getWidth() + 14;
		}
		tableColumn.setPreferredWidth(width);
		tableColumn.setMaxWidth(width);
		tableColumn.setMinWidth(width);
	}

	/**
	 * Add the files that are contained within the directory of this node. Thanks to Hovercraft Full Of
	 * Eels for the SwingWorker fix.
	 */
	private void showChildren(final DefaultMutableTreeNode node) {

		this.tree.setEnabled(false);
		this.progressBar.setVisible(true);
		this.progressBar.setIndeterminate(true);

		final SwingWorker<Void, File> worker = new SwingWorker<>() {
			@Override
			public Void doInBackground() {

				final File file = (File) node.getUserObject();
				if (file.isDirectory()) {
					final File[] files = FileBrowser.this.fileSystemView.getFiles(file, true); // !!
					if (node.isLeaf())
						for (final File child : files)
							if (child.isDirectory())
								publish(child);
					FileBrowser.this.setTableData(files);
				}
				return null;
			}

			@Override
			protected void process(final List<File> chunks) {

				for (final File child : chunks)
					node.add(new DefaultMutableTreeNode(
						child));
			}

			@Override
			protected void done() {

				FileBrowser.this.progressBar.setIndeterminate(false);
				FileBrowser.this.progressBar.setVisible(false);
				FileBrowser.this.tree.setEnabled(true);
			}
		};
		worker.execute();
	}

	/** Update the File details view with the details of this File. */
	private void setFileDetails(final File file) {

		this.currentFile = file;
		final Icon icon = this.fileSystemView.getSystemIcon(file);
		this.fileName.setIcon(icon);
		this.fileName.setText(this.fileSystemView.getSystemDisplayName(file));
		this.path.setText(file.getPath());
		this.date.setText(new Date(
			file.lastModified()).toString());
		this.size.setText(file.length() + " bytes");
		this.readable.setSelected(file.canRead());
		this.writable.setSelected(file.canWrite());
		this.executable.setSelected(file.canExecute());
		this.isDirectory.setSelected(file.isDirectory());

		this.isFile.setSelected(file.isFile());

		final JFrame f = (JFrame) this.gui.getTopLevelAncestor();
		if (f != null)
			f.setTitle(FileBrowser.APP_TITLE + " :: " + this.fileSystemView.getSystemDisplayName(file));

		this.gui.repaint();
	}

	public static void main(final String[] args) {

		SwingUtilities.invokeLater(() -> {
			try {
				// Significantly improves the look of the output in
				// terms of the file names returned by FileSystemView!
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			catch (final Exception weTried1) {
			}
			final JFrame f = new JFrame(
				FileBrowser.APP_TITLE);
			f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			final FileBrowser FileBrowser = new FileBrowser();
			f.setContentPane(FileBrowser.getGui());

			try {
				final URL urlBig = FileBrowser.getClass()
						.getResource("fb-icon-32x32.png");
				final URL urlSmall = FileBrowser.getClass()
						.getResource("fb-icon-16x16.png");
				final ArrayList<Image> images = new ArrayList<>();
				images.add(ImageIO.read(urlBig));
				images.add(ImageIO.read(urlSmall));
				f.setIconImages(images);
			}
			catch (final Exception weTried2) {
			}

			f.pack();
			f.setLocationByPlatform(true);
			f.setMinimumSize(f.getSize());
			f.setVisible(true);

			FileBrowser.showRootFile();
		});
	}
}

/** A TableModel to hold File[]. */
class FileTableModel1 extends AbstractTableModel {

	private File[]					files;
	private final FileSystemView	fileSystemView	= FileSystemView.getFileSystemView();
	private final String[]			columns			= {
			"Icon",
			"File",
			"Path/name",
			"Size",
			"Last Modified",
			"R",
			"W",
			"E",
			"D",
			"F", };

	FileTableModel1() {

		this(new File[0]);
	}

	FileTableModel1(final File[] files) {

		this.files = files;
	}

	@Override
	public Object getValueAt(final int row, final int column) {

		final File file = this.files[row];
		switch (column) {
		case 0:
			return this.fileSystemView.getSystemIcon(file);
		case 1:
			return this.fileSystemView.getSystemDisplayName(file);
		case 2:
			return file.getPath();
		case 3:
			return file.length();
		case 4:
			return file.lastModified();
		case 5:
			return file.canRead();
		case 6:
			return file.canWrite();
		case 7:
			return file.canExecute();
		case 8:
			return file.isDirectory();
		case 9:
			return file.isFile();
		default:
			System.err.println("Logic Error");
		}
		return "";
	}

	@Override
	public int getColumnCount() {

		return this.columns.length;
	}

	@Override
	public Class<?> getColumnClass(final int column) {

		switch (column) {
		case 0:
			return ImageIcon.class;
		case 3:
			return Long.class;
		case 4:
			return Date.class;
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return Boolean.class;
		}
		return String.class;
	}

	@Override
	public String getColumnName(final int column) {

		return this.columns[column];
	}

	@Override
	public int getRowCount() {

		return this.files.length;
	}

	public File getFile(final int row) {

		return this.files[row];
	}

	public void setFiles(final File[] files) {

		this.files = files;
		fireTableDataChanged();
	}
}

/** A TreeCellRenderer for a File. */
class FileTreeCellRenderer extends DefaultTreeCellRenderer {

	private final FileSystemView fileSystemView;

	private final JLabel label;

	FileTreeCellRenderer() {

		this.label = new JLabel();
		this.label.setOpaque(true);
		this.fileSystemView = FileSystemView.getFileSystemView();
	}

	@Override
	public Component getTreeCellRendererComponent(final JTree tree, final Object value, final boolean selected,
			final boolean expanded, final boolean leaf, final int row, final boolean hasFocus) {

		final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		final File file = (File) node.getUserObject();
		this.label.setIcon(this.fileSystemView.getSystemIcon(file));
		this.label.setText(this.fileSystemView.getSystemDisplayName(file));
		this.label.setToolTipText(file.getPath());

		if (selected) {
			this.label.setBackground(this.backgroundSelectionColor);
			this.label.setForeground(this.textSelectionColor);
		}
		else {
			this.label.setBackground(this.backgroundNonSelectionColor);
			this.label.setForeground(this.textNonSelectionColor);
		}

		return this.label;
	}
}