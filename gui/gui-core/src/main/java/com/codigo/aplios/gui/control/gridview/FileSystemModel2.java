package com.codigo.aplios.gui.control.gridview;

/**
 * FileSystemModel2 is a TreeTableModel representing a hierarchical file system.
 * <p>
 * This will recursively load all the children from the path it is created with. The loading is done
 * with the method reloadChildren, and happens in another thread. The method isReloading can be
 * invoked to check if there are active threads. The total size of all the files are also
 * accumulated.
 * <p>
 * By default links are not descended. java.io.File does not have a way to distinguish links, so a
 * file is assumed to be a link if its canonical path does not start with its parent path. This may
 * not cover all cases, but works for the time being.
 * <p>
 * Reloading happens such that all the files of the directory are loaded and immediately available.
 * The background thread then recursively loads all the files of each of those directories. When
 * each directory has finished loading all its sub files they are attached and an event is generated
 * in the event dispatching thread. A more ambitious approach would be to attach each set of
 * directories as they are loaded and generate an event. Then, once all the direct descendants of
 * the directory being reloaded have finished loading, it is resorted based on total size.
 * <p>
 * While you can invoke reloadChildren as many times as you want, care should be taken in doing
 * this. You should not invoke reloadChildren on a node that is already being reloaded, or going to
 * be reloaded (meaning its parent is reloading but it hasn't started reloading that directory yet).
 * If this is done odd results may happen. FileSystemModel2 does not enforce any policy in this
 * manner, and it is up to the user of FileSystemModel2 to ensure it doesn't happen.
 *
 * @version 1.12 05/12/98
 * @author Philip Milne
 * @author Scott Violet
 */

public class FileSystemModel2 extends AbstractTreeTableModel {

	public FileSystemModel2(final Object root) {

		super(root);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColumnCount() {

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getColumnName(final int column) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValueAt(final Object node, final int column) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getChild(final Object parent, final int index) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildCount(final Object parent) {

		// TODO Auto-generated method stub
		return 0;
	}

	// // Names of the columns.
	// static protected String[] cNames = { "Name", "Size", "Type", "Modified", "IsHidden" };
	//
	// // Types of the columns.
	// static protected Class[] cTypes = { TreeTableModel.class, Integer.class, String.class,
	// Date.class, Boolean.class };
	//
	// // The the returned file length for directories.
	// public static final Integer ZERO = new Integer(
	// 0);
	//
	// /** An array of MergeSorter sorters, that will sort based on size. */
	// static Stack sorters = new Stack();
	//
	// /**
	// * True if the receiver is valid, once set to false all Threads loading files will stop.
	// */
	// protected boolean isValid;
	//
	// /**
	// * Node currently being reloaded, this becomes somewhat muddy if reloading is happening in
	// multiple
	// * threads.
	// */
	// protected FileNode reloadNode;
	//
	// /** > 0 indicates reloading some nodes. */
	// int reloadCount;
	//
	// /** Returns true if links are to be descended. */
	// protected boolean descendLinks;
	//
	// /**
	// * Returns a MergeSort that can sort on the totalSize of a FileNode.
	// */
	// protected static MergeSort getSizeSorter() {
	//
	// synchronized (FileSystemModel2.sorters) {
	// if (FileSystemModel2.sorters.size() == 0)
	// return new SizeSorter();
	// return (MergeSort) FileSystemModel2.sorters.pop();
	// }
	// }
	//
	// /**
	// * Should be invoked when a MergeSort is no longer needed.
	// */
	// protected static void recycleSorter(final MergeSort sorter) {
	//
	// synchronized (FileSystemModel2.sorters) {
	// FileSystemModel2.sorters.push(sorter);
	// }
	// }
	//
	// /**
	// * Creates a FileSystemModel2 rooted at File.separator, which is usually the root of the file
	// * system. This does not load it, you should invoke <code>reloadChildren</code> with the root to
	// * start loading.
	// */
	// public FileSystemModel2() {
	//
	// this(File.separator);
	// }
	//
	// /**
	// * Creates a FileSystemModel2 with the root being <code>rootPath</code>. This does not load it,
	// you
	// * should invoke <code>reloadChildren</code> with the root to start loading.
	// */
	// public FileSystemModel2(final String rootPath) {
	//
	// super(null);
	// this.isValid = true;
	// this.root = new FileNode(
	// new File(
	// rootPath));
	// }
	//
	// //
	// // The TreeModel interface
	// //
	//
	// /**
	// * Returns the number of children of <code>node</code>.
	// */
	// @Override
	// public int getChildCount(final Object node) {
	//
	// final Object[] children = getChildren(node);
	// return (children == null) ? 0 : children.length;
	// }
	//
	// /**
	// * Returns the child of <code>node</code> at index <code>i</code>.
	// */
	// @Override
	// public Object getChild(final Object node, final int i) {
	//
	// return getChildren(node)[i];
	// }
	//
	// /**
	// * Returns true if the passed in object represents a leaf, false otherwise.
	// */
	// @Override
	// public boolean isLeaf(final Object node) {
	//
	// return ((FileNode) node).isLeaf();
	// }
	//
	// //
	// // The TreeTableNode interface.
	// //
	//
	// /**
	// * Returns the number of columns.
	// */
	// @Override
	// public int getColumnCount() {
	//
	// return FileSystemModel2.cNames.length;
	// }
	//
	// /**
	// * Returns the name for a particular column.
	// */
	// @Override
	// public String getColumnName(final int column) {
	//
	// return FileSystemModel2.cNames[column];
	// }
	//
	// /**
	// * Returns the class for the particular column.
	// */
	// @Override
	// public Class getColumnClass(final int column) {
	//
	// return FileSystemModel2.cTypes[column];
	// }
	//
	// /**
	// * Returns the value of the particular column.
	// */
	// @Override
	// public Object getValueAt(final Object node, final int column) {
	//
	// final FileNode fn = (FileNode) node;
	//
	// try {
	// switch (column) {
	// case 0:
	// return fn.getFile()
	// .getName();
	// case 1:
	// if (fn.isTotalSizeValid())
	// return new Integer(
	// (int) ((FileNode) node).totalSize());
	// return null;
	// case 2:
	// return fn.isLeaf() ? "File" : "Directory";
	// case 3:
	// return fn.lastModified();
	// case 4:
	// return fn.getFile()
	// .isHidden();
	// }
	// }
	// catch (final SecurityException se) {
	// }
	//
	// return null;
	// }
	//
	// //
	// // Some convenience methods.
	// //
	//
	// /**
	// * Reloads the children of the specified node.
	// */
	// public void reloadChildren(final Object node) {
	//
	// final FileNode fn = (FileNode) node;
	//
	// synchronized (this) {
	// this.reloadCount++;
	// }
	// fn.resetSize();
	// new Thread(
	// new FileNodeLoader(
	// (FileNode) node)).start();
	// }
	//
	// /**
	// * Stops and waits for all threads to finish loading.
	// */
	// public void stopLoading() {
	//
	// this.isValid = false;
	// synchronized (this) {
	// while (this.reloadCount > 0)
	// try {
	// wait();
	// }
	// catch (final InterruptedException ie) {
	// }
	// }
	// this.isValid = true;
	// }
	//
	// /**
	// * If <code>newValue</code> is true, links are descended. Odd results may happen if you set this
	// * while other threads are loading.
	// */
	// public void setDescendsLinks(final boolean newValue) {
	//
	// this.descendLinks = newValue;
	// }
	//
	// /**
	// * Returns true if links are to be automatically descended.
	// */
	// public boolean getDescendsLinks() {
	//
	// return this.descendLinks;
	// }
	//
	// /**
	// * Returns the path <code>node</code> represents.
	// */
	// public String getPath(final Object node) {
	//
	// return ((FileNode) node).getFile()
	// .getPath();
	// }
	//
	// /**
	// * Returns the total size of the receiver.
	// */
	// public long getTotalSize(final Object node) {
	//
	// return ((FileNode) node).totalSize();
	// }
	//
	// /**
	// * Returns true if the receiver is loading any children.
	// */
	// public boolean isReloading() {
	//
	// return (this.reloadCount > 0);
	// }
	//
	// /**
	// * Returns the path to the node that is being loaded.
	// */
	// public TreePath getPathLoading() {
	//
	// final FileNode rn = this.reloadNode;
	//
	// if (rn != null)
	// return new TreePath(
	// rn.getPath());
	// return null;
	// }
	//
	// /**
	// * Returns the node being loaded.
	// */
	// public Object getNodeLoading() {
	//
	// return this.reloadNode;
	// }
	//
	// protected File getFile(final Object node) {
	//
	// final FileNode fileNode = ((FileNode) node);
	// return fileNode.getFile();
	// }
	//
	// protected Object[] getChildren(final Object node) {
	//
	// final FileNode fileNode = ((FileNode) node);
	// return fileNode.getChildren();
	// }
	//
	// protected static FileNode[] EMPTY_CHILDREN = new FileNode[0];
	//
	// // Used to sort the file names.
	// static private MergeSort fileMS = new MergeSort() {
	// @Override
	// public int compareElementsAt(final int beginLoc, final int endLoc) {
	//
	// return ((String) toSort[beginLoc]).compareTo((String) toSort[endLoc]);
	// }
	// };
	//
	// /**
	// * A FileNode is a derivative of the File class - though we delegate to the File object rather
	// than
	// * subclassing it. It is used to maintain a cache of a directory's children and therefore avoid
	// * repeated access to the underlying file system during rendering.
	// */
	// class FileNode {
	// /** java.io.File the receiver represents. */
	// protected File file;
	// /** Parent FileNode of the receiver. */
	// private FileNode parent;
	// /** Children of the receiver. */
	// protected FileNode[] children;
	// /** Size of the receiver and all its children. */
	// protected long totalSize;
	// /** Valid if the totalSize has finished being calced. */
	// protected boolean totalSizeValid;
	// /** Path of the receiver. */
	// protected String canonicalPath;
	// /**
	// * True if the canonicalPath of this instance does not start with the canonical path of the
	// parent.
	// */
	// protected boolean isLink;
	// /** Date last modified. */
	// protected Date lastModified;
	//
	// protected FileNode(final File file) {
	//
	// this(null, file);
	// }
	//
	// protected FileNode(final FileNode parent, final File file) {
	//
	// this.parent = parent;
	// this.file = file;
	// try {
	// this.canonicalPath = file.getCanonicalPath();
	// }
	// catch (final IOException ioe) {
	// this.canonicalPath = "";
	// }
	// if (parent != null)
	// this.isLink = !this.canonicalPath.startsWith(parent.getCanonicalPath());
	// else
	// this.isLink = false;
	// if (isLeaf()) {
	// this.totalSize = file.length();
	// this.totalSizeValid = true;
	// }
	// }
	//
	// /**
	// * Returns the date the receiver was last modified.
	// */
	// public Date lastModified() {
	//
	// if ((this.lastModified == null) && (this.file != null))
	// this.lastModified = new Date(
	// this.file.lastModified());
	// return this.lastModified;
	// }
	//
	// /**
	// * Returns the the string to be used to display this leaf in the JTree.
	// */
	// @Override
	// public String toString() {
	//
	// return this.file.getName();
	// }
	//
	// /**
	// * Returns the java.io.File the receiver represents.
	// */
	// public File getFile() {
	//
	// return this.file;
	// }
	//
	// /**
	// * Returns size of the receiver and all its children.
	// */
	// public long totalSize() {
	//
	// return this.totalSize;
	// }
	//
	// /**
	// * Returns the parent of the receiver.
	// */
	// public FileNode getParent() {
	//
	// return this.parent;
	// }
	//
	// /**
	// * Returns true if the receiver represents a leaf, that is it is isn't a directory.
	// */
	// public boolean isLeaf() {
	//
	// return this.file.isFile();
	// }
	//
	// /**
	// * Returns true if the total size is valid.
	// */
	// public boolean isTotalSizeValid() {
	//
	// return this.totalSizeValid;
	// }
	//
	// /**
	// * Clears the date.
	// */
	// protected void resetLastModified() {
	//
	// this.lastModified = null;
	// }
	//
	// /**
	// * Sets the size of the receiver to be 0.
	// */
	// protected void resetSize() {
	//
	// alterTotalSize(-this.totalSize);
	// }
	//
	// /**
	// * Loads the children, caching the results in the children instance variable.
	// */
	// protected FileNode[] getChildren() {
	//
	// return this.children;
	// }
	//
	// /**
	// * Recursively loads all the children of the receiver.
	// */
	// protected void loadChildren(final MergeSort sorter) {
	//
	// this.totalSize = this.file.length();
	// this.children = createChildren(null);
	// for (int counter = this.children.length - 1; counter >= 0; counter--) {
	// Thread.yield(); // Give the GUI CPU time to draw itself.
	// if (!this.children[counter].isLeaf()
	// && (FileSystemModel2.this.descendLinks || !this.children[counter].isLink()))
	// this.children[counter].loadChildren(sorter);
	// this.totalSize += this.children[counter].totalSize();
	// if (!FileSystemModel2.this.isValid)
	// counter = 0;
	// }
	// if (FileSystemModel2.this.isValid) {
	// if (sorter != null)
	// sorter.sort(this.children);
	// this.totalSizeValid = true;
	// }
	// }
	//
	// /**
	// * Loads the children of of the receiver.
	// */
	// protected FileNode[] createChildren(final MergeSort sorter) {
	//
	// FileNode[] retArray = null;
	//
	// try {
	// final String[] files = this.file.list();
	// if (files != null) {
	// if (sorter != null)
	// sorter.sort(files);
	// retArray = new FileNode[files.length];
	// final String path = this.file.getPath();
	// for (int i = 0; i < files.length; i++) {
	// final File childFile = new File(
	// path, files[i]);
	// retArray[i] = new FileNode(
	// this, childFile);
	// }
	// }
	// }
	// catch (final SecurityException se) {
	// }
	// if (retArray == null)
	// retArray = FileSystemModel2.EMPTY_CHILDREN;
	// return retArray;
	// }
	//
	// /**
	// * Returns true if the children have been loaded.
	// */
	// protected boolean loadedChildren() {
	//
	// return (this.file.isFile() || (this.children != null));
	// }
	//
	// /**
	// * Gets the path from the root to the receiver.
	// */
	// public FileNode[] getPath() {
	//
	// return getPathToRoot(this, 0);
	// }
	//
	// /**
	// * Returns the canonical path for the receiver.
	// */
	// public String getCanonicalPath() {
	//
	// return this.canonicalPath;
	// }
	//
	// /**
	// * Returns true if the receiver's path does not begin with the parent's canonical path.
	// */
	// public boolean isLink() {
	//
	// return this.isLink;
	// }
	//
	// protected FileNode[] getPathToRoot(final FileNode aNode, int depth) {
	//
	// FileNode[] retNodes;
	//
	// if (aNode == null) {
	// if (depth == 0)
	// return null;
	// else
	// retNodes = new FileNode[depth];
	// }
	// else {
	// depth++;
	// retNodes = getPathToRoot(aNode.getParent(), depth);
	// retNodes[retNodes.length - depth] = aNode;
	// }
	// return retNodes;
	// }
	//
	// /**
	// * Sets the children of the receiver, updates the total size, and if generateEvent is true a tree
	// * structure changed event is created.
	// */
	// protected void setChildren(final FileNode[] newChildren, final boolean generateEvent) {
	//
	// final long oldSize = this.totalSize;
	//
	// this.totalSize = this.file.length();
	// this.children = newChildren;
	// for (int counter = this.children.length - 1; counter >= 0; counter--)
	// this.totalSize += this.children[counter].totalSize();
	//
	// if (generateEvent) {
	// final FileNode[] path = getPath();
	//
	// fireTreeStructureChanged(FileSystemModel2.this, path, null, null);
	//
	// final FileNode parent = getParent();
	//
	// if (parent != null)
	// parent.alterTotalSize(this.totalSize - oldSize);
	// }
	// }
	//
	// protected synchronized void alterTotalSize(final long sizeDelta) {
	//
	// if ((sizeDelta != 0) && ((this.parent = getParent()) != null)) {
	// this.totalSize += sizeDelta;
	// nodeChanged();
	// this.parent.alterTotalSize(sizeDelta);
	// }
	// else
	// // Need a way to specify the root.
	// this.totalSize += sizeDelta;
	// }
	//
	// /**
	// * This should only be invoked on the event dispatching thread.
	// */
	// protected synchronized void setTotalSizeValid(final boolean newValue) {
	//
	// if (this.totalSizeValid != newValue) {
	// nodeChanged();
	// this.totalSizeValid = newValue;
	//
	// final FileNode parent = getParent();
	//
	// if (parent != null)
	// parent.childTotalSizeChanged(this);
	// }
	// }
	//
	// /**
	// * Marks the receivers total size as valid, but does not invoke node changed, nor message the
	// * parent.
	// */
	// protected synchronized void forceTotalSizeValid() {
	//
	// this.totalSizeValid = true;
	// }
	//
	// /**
	// * Invoked when a childs total size has changed.
	// */
	// protected synchronized void childTotalSizeChanged(final FileNode child) {
	//
	// if (this.totalSizeValid != child.isTotalSizeValid())
	// if (this.totalSizeValid)
	// setTotalSizeValid(false);
	// else {
	// final FileNode[] children = getChildren();
	//
	// for (int counter = children.length - 1; counter >= 0; counter--)
	// if (!children[counter].isTotalSizeValid())
	// return;
	// setTotalSizeValid(true);
	// }
	//
	// }
	//
	// /**
	// * Can be invoked when a node has changed, will create the appropriate event.
	// */
	// protected void nodeChanged() {
	//
	// final FileNode parent = getParent();
	//
	// if (parent != null) {
	// final FileNode[] path = parent.getPath();
	// final int[] index = { getIndexOfChild(parent, this) };
	// final Object[] children = { this };
	//
	// fireTreeNodesChanged(FileSystemModel2.this, path, index, children);
	// }
	// }
	// }
	//
	// /**
	// * FileNodeLoader can be used to reload all the children of a particular node. It first resets the
	// * children of the FileNode it is created with, and in its run method will reload all of that
	// nodes
	// * children. FileNodeLoader may not be running in the event dispatching thread. As swing is not
	// * thread safe it is important that we don't generate events in this thread.
	// * SwingUtilities.invokeLater is used so that events are generated in the event dispatching
	// thread.
	// */
	// class FileNodeLoader implements Runnable {
	// /** Node creating children for. */
	// FileNode node;
	// /** Sorter. */
	// MergeSort sizeMS;
	//
	// FileNodeLoader(final FileNode node) {
	//
	// this.node = node;
	// node.resetLastModified();
	// node.setChildren(node.createChildren(FileSystemModel2.fileMS), true);
	// node.setTotalSizeValid(false);
	// }
	//
	// @Override
	// public void run() {
	//
	// final FileNode[] children = this.node.getChildren();
	//
	// this.sizeMS = FileSystemModel2.getSizeSorter();
	// for (int counter = children.length - 1; counter >= 0; counter--) {
	// if (!children[counter].isLeaf()) {
	// FileSystemModel2.this.reloadNode = children[counter];
	// loadChildren(children[counter]);
	// FileSystemModel2.this.reloadNode = null;
	// }
	// if (!FileSystemModel2.this.isValid)
	// counter = 0;
	// }
	// FileSystemModel2.recycleSorter(this.sizeMS);
	// if (FileSystemModel2.this.isValid)
	// SwingUtilities.invokeLater(() -> {
	//
	// final MergeSort sorter = FileSystemModel2.getSizeSorter();
	//
	// sorter.sort(FileNodeLoader.this.node.getChildren());
	// FileSystemModel2.recycleSorter(sorter);
	// FileNodeLoader.this.node.setChildren(FileNodeLoader.this.node.getChildren(), true);
	// synchronized (FileSystemModel2.this) {
	// FileSystemModel2.this.reloadCount--;
	// FileSystemModel2.this.notifyAll();
	// }
	// });
	// else
	// synchronized (FileSystemModel2.this) {
	// FileSystemModel2.this.reloadCount--;
	// FileSystemModel2.this.notifyAll();
	// }
	// }
	//
	// protected void loadChildren(final FileNode node) {
	//
	// if (!node.isLeaf() && (FileSystemModel2.this.descendLinks || !node.isLink())) {
	// final FileNode[] children = node.createChildren(null);
	//
	// for (int counter = children.length - 1; counter >= 0; counter--) {
	// if (!children[counter].isLeaf())
	// if (FileSystemModel2.this.descendLinks || !children[counter].isLink())
	// children[counter].loadChildren(this.sizeMS);
	// else
	// children[counter].forceTotalSizeValid();
	// if (!FileSystemModel2.this.isValid)
	// counter = 0;
	// }
	// if (FileSystemModel2.this.isValid) {
	// final FileNode fn = node;
	//
	// // Reset the children
	// SwingUtilities.invokeLater(() -> {
	//
	// final MergeSort sorter = FileSystemModel2.getSizeSorter();
	//
	// sorter.sort(children);
	// FileSystemModel2.recycleSorter(sorter);
	// fn.setChildren(children, true);
	// fn.setTotalSizeValid(true);
	// fn.nodeChanged();
	// });
	// }
	// }
	// else
	// node.forceTotalSizeValid();
	// }
	// }
	//
	// /**
	// * Sorts the contents, which must be instances of FileNode based on totalSize.
	// */
	// static class SizeSorter extends MergeSort {
	//
	// @Override
	// public int compareElementsAt(final int beginLoc, final int endLoc) {
	//
	// final long firstSize = ((FileNode) toSort[beginLoc]).totalSize();
	// final long secondSize = ((FileNode) toSort[endLoc]).totalSize();
	//
	// if (firstSize != secondSize)
	// return (int) (secondSize - firstSize);
	// return ((FileNode) toSort[beginLoc]).toString()
	// .compareTo(((FileNode) toSort[endLoc]).toString());
	// }
	// }
}
