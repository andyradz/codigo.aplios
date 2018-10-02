package com.codigo.aplios.data.struct;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

//http://eduinf.waw.pl/inf/alg/001_search/0107.php
/**
 * Kopiec binarny A binary heap is a heap data structure created using a binary
 * tree. It can be seen as a binary tree with two additional constraints:<br>
 * <br>
 * 1) The shape property: the tree is a complete binary tree; that is, all
 * levels of the tree, except possibly the last one (deepest) are fully filled,
 * and, if the last level of the tree is not complete, the nodes of that level
 * are filled from left to right.<br>
 * <br>
 * 2) The heap property: each node is right than or equal to each of its
 * children according to a comparison predicate defined for the data structure.
 * <p>
 *
 * @param <T>
 * @see <a href="https://en.wikipedia.org/wiki/Binary_heap">Binary Heap
 * (Wikipedia)</a> <br>
 * @author Justin Wetherell <phishman3579@gmail.com>
 * @category structure
 */
@SuppressWarnings("unchecked")
public interface BinaryHeap<T extends Comparable<T>>
        extends IHeap<T> {

    public enum HeapType {
        Tree,
        Array

    }

    public enum Type {
        MIN,
        MAX

    }

    /**
     * Get the heap in array form.
     *
     * @return array representing the heap.
     */
    public T[] getHeap();

    /**
     * A binary heap using an array to hold the nodes.
     *
     * @author Justin Wetherell <phishman3579@gmail.com>
     */
    public static class BinaryHeapArray<T extends Comparable<T>>
            implements BinaryHeap<T> {

        private static final int MINIMUM_SIZE = 1_024;

        private Type type = Type.MIN;

        private int size = 0;

        private T[] array;

        /**
         * Get the parent index of this index, will return Integer.MIN_VALUE if no
         * parent is possible.
         *
         * @param index
         *              of the node to find a parent for.
         * @return index of parent node or Integer.MIN_VALUE if no parent.
         */
        private static final int getParentIndex(final int index) {

            if (index > 0)
                return (int)Math.floor((index - 1) / 2);
            return Integer.MIN_VALUE;
        }

        /**
         * Get the left child index of this index.
         *
         * @param index
         *              of the node to find a left child for.
         * @return index of left child node.
         */
        private static final int getLeftIndex(final int index) {

            return 2 * index + 1;
        }

        /**
         * Get the right child index of this index.
         *
         * @param index
         *              of the node to find a right child for.
         * @return index of right child node.
         */
        private static final int getRightIndex(final int index) {

            return 2 * index + 2;
        }

        /**
         * Constructor for heap, defaults to a min-heap.
         */
        public BinaryHeapArray() {

            this.size = 0;
            this.array = (T[])new Comparable[BinaryHeapArray.MINIMUM_SIZE];
        }

        /**
         * Constructor for heap.
         *
         * @param type
         *             Heap type.
         */
        public BinaryHeapArray(final Type type) {

            this();
            this.type = type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {

            return this.size;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean insert(final T value) {

            if (this.size >= this.array.length)
                this.grow();
            this.array[this.size] = value;

            this.heapUp(this.size++);

            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T delete(final T value) {

            if (this.array.length == 0)
                return null;
            for (int i = 0; i < this.size; i++) {
                final T node = this.array[i];
                if (node.equals(value))
                    return this.delete(i);
            }
            return null;
        }

        private T delete(final int index) {

            if (index < 0 || index >= this.size)
                return null;

            final T t = this.array[index];
            this.array[index] = this.array[--this.size];
            this.array[this.size] = null;

            this.heapDown(index);

            final int shrinkSize = this.array.length >> 1;
            if (shrinkSize >= BinaryHeapArray.MINIMUM_SIZE && this.size < shrinkSize)
                this.shrink();

            return t;
        }

        protected void heapUp(final int idx) {

            int nodeIndex = idx;
            final T value = this.array[nodeIndex];
            if (value == null)
                return;

            while (nodeIndex >= 0) {
                final int parentIndex = BinaryHeapArray.getParentIndex(nodeIndex);
                if (parentIndex < 0)
                    return;

                final T parent = this.array[parentIndex];

                if (this.type == Type.MIN && value.compareTo(parent) < 0 ||
                        this.type == Type.MAX && value.compareTo(parent) > 0) {
                    // Node is greater/lesser than parent, switch node with parent
                    this.array[parentIndex] = value;
                    this.array[nodeIndex] = parent;
                } else
                    return;
                nodeIndex = parentIndex;
            }
        }

        protected void heapDown(final int index) {

            final T value = this.array[index];
            if (value == null)
                return;

            final int leftIndex = BinaryHeapArray.getLeftIndex(index);
            final int rightIndex = BinaryHeapArray.getRightIndex(index);
            final T left = leftIndex != Integer.MIN_VALUE && leftIndex < this.size ? this.array[leftIndex] : null;
            final T right = rightIndex != Integer.MIN_VALUE && rightIndex < this.size ? this.array[rightIndex] : null;

            if (left == null && right == null)
                // Nothing to do here
                return;

            T nodeToMove = null;
            int nodeToMoveIndex = -1;
            if (this.type == Type.MIN && left != null && right != null && value.compareTo(left) > 0 &&
                    value.compareTo(right) > 0 ||
                    this.type == Type.MAX && left != null && right != null && value.compareTo(left) < 0 &&
                    value.compareTo(right) < 0)
                // Both children are greater/lesser than node
                if (right != null && (this.type == Type.MIN && right.compareTo(left) < 0 ||
                        this.type == Type.MAX && right.compareTo(left) > 0)) {
                    // Right is greater/lesser than left
                    nodeToMove = right;
                    nodeToMoveIndex = rightIndex;
                } else if (left != null && (this.type == Type.MIN && left.compareTo(right) < 0 ||
                        this.type == Type.MAX && left.compareTo(right) > 0)) {
                    // Left is greater/lesser than right
                    nodeToMove = left;
                    nodeToMoveIndex = leftIndex;
                } else {
                    // Both children are equal, use right
                    nodeToMove = right;
                    nodeToMoveIndex = rightIndex;
                }
            else if (this.type == Type.MIN && right != null && value.compareTo(right) > 0 ||
                    this.type == Type.MAX && right != null && value.compareTo(right) < 0) {
                // Right is greater/lesser than node
                nodeToMove = right;
                nodeToMoveIndex = rightIndex;
            } else if (this.type == Type.MIN && left != null && value.compareTo(left) > 0 ||
                    this.type == Type.MAX && left != null && value.compareTo(left) < 0) {
                // Left is greater/lesser than node
                nodeToMove = left;
                nodeToMoveIndex = leftIndex;
            }
            // No node to move, stop recursion
            if (nodeToMove == null)
                return;

            // Re-factor heap sub-tree
            this.array[nodeToMoveIndex] = value;
            this.array[index] = nodeToMove;

            this.heapDown(nodeToMoveIndex);
        }

        // Grow the array by 50%
        private void grow() {

            final int growSize = this.size + (this.size << 1);
            this.array = Arrays.copyOf(this.array, growSize);
        }

        // Shrink the array by 50%
        private void shrink() {

            final int shrinkSize = this.array.length >> 1;
            this.array = Arrays.copyOf(this.array, shrinkSize);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clear() {

            this.size = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(final T value) {

            if (this.array.length == 0)
                return false;
            for (int i = 0; i < this.size; i++) {
                final T t = this.array[i];
                if (t.equals(value))
                    return true;
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean validate() {

            if (this.array.length == 0)
                return true;
            return this.validateNode(0);
        }

        /**
         * Validate the node for the heap invariants.
         *
         * @param index
         *              of node to validate for.
         * @return True if node is valid.
         */
        private boolean validateNode(final int index) {

            final T value = this.array[index];
            final int leftIndex = BinaryHeapArray.getLeftIndex(index);
            final int rightIndex = BinaryHeapArray.getRightIndex(index);

            // We shouldn't ever have a right node without a left in a heap
            if (rightIndex != Integer.MIN_VALUE && leftIndex == Integer.MIN_VALUE)
                return false;

            if (leftIndex != Integer.MIN_VALUE && leftIndex < this.size) {
                final T left = this.array[leftIndex];
                if (this.type == Type.MIN && value.compareTo(left) < 0 ||
                        this.type == Type.MAX && value.compareTo(left) > 0)
                    return this.validateNode(leftIndex);
                return false;
            }
            if (rightIndex != Integer.MIN_VALUE && rightIndex < this.size) {
                final T right = this.array[rightIndex];
                if (this.type == Type.MIN && value.compareTo(right) < 0 ||
                        this.type == Type.MAX && value.compareTo(right) > 0)
                    return this.validateNode(rightIndex);
                return false;
            }

            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T[] getHeap() {

            final T[] nodes = (T[])new Comparable[this.size];
            if (this.array.length == 0)
                return nodes;

            for (int i = 0; i < this.size; i++) {
                final T node = this.array[i];
                nodes[i] = node;
            }
            return nodes;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T getHeadValue() {

            if (this.array.length == 0)
                return null;
            return this.array[0];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T deleteHead() {

            return this.delete(this.getHeadValue());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public java.util.Collection<T> toCollection() {

            return new JavaCompatibleBinaryHeapArray<>(this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {

            return HeapPrinter.getString(this);
        }

        protected static class HeapPrinter {

            public static <T extends Comparable<T>> String getString(final BinaryHeapArray<T> tree) {

                if (tree.array.length == 0)
                    return "Tree has no nodes.";

                final T root = tree.array[0];
                if (root == null)
                    return "Tree has no nodes.";
                return HeapPrinter.getString(tree, 0, "", true);
            }

            private static <T extends Comparable<T>> String getString(final BinaryHeapArray<T> tree, final int index,
                    final String prefix, final boolean isTail) {

                final StringBuilder builder = new StringBuilder();

                final T value = tree.array[index];
                builder.append(prefix + (isTail ? "└── " : "├── ") + value + "\n");
                List<Integer> children = null;
                final int leftIndex = BinaryHeapArray.getLeftIndex(index);
                final int rightIndex = BinaryHeapArray.getRightIndex(index);
                if (leftIndex != Integer.MIN_VALUE || rightIndex != Integer.MIN_VALUE) {
                    children = new ArrayList<>(2);
                    if (leftIndex != Integer.MIN_VALUE && leftIndex < tree.size)
                        children.add(leftIndex);
                    if (rightIndex != Integer.MIN_VALUE && rightIndex < tree.size)
                        children.add(rightIndex);
                }
                if (children != null) {
                    for (int i = 0; i < children.size() - 1; i++)
                        builder.append(HeapPrinter.getString(tree, children.get(i), prefix + (isTail ? "    " : "│   "),
                                false));
                    if (children.size() >= 1)
                        builder.append(HeapPrinter.getString(tree, children.get(children.size() - 1),
                                prefix + (isTail ? "    " : "│   "), true));
                }

                return builder.toString();
            }

        }
    }

    public static class BinaryHeapTree<T extends Comparable<T>>
            implements BinaryHeap<T> {

        private Type type = Type.MIN;

        private int size = 0;

        private Node<T> root = null;

        /**
         * Constructor for heap, defaults to a min-heap.
         */
        public BinaryHeapTree() {

            this.root = null;
            this.size = 0;
        }

        /**
         * Constructor for heap.
         *
         * @param type
         *             Heap type.
         */
        public BinaryHeapTree(final Type type) {

            this();
            this.type = type;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {

            return this.size;
        }

        /**
         * Get the navigation directions through the tree to the index.
         *
         * @param idx
         *            index of the Node to get directions for.
         * @return Integer array representing the directions to the index.
         */
        private static int[] getDirections(final int idx) {

            int index = idx;
            final int directionsSize = (int)(Math.log10(index + 1) / Math.log10(2)) - 1;
            int[] directions = null;
            if (directionsSize > 0) {
                directions = new int[directionsSize];
                int i = directionsSize - 1;
                while (i >= 0) {
                    index = (index - 1) / 2;
                    directions[i--] = index > 0 && index % 2 == 0 ? 1 : 0; // 0=left, 1=right
                }
            }
            return directions;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean insert(final T value) {

            return this.add(new Node<>(null,
                    value));
        }

        private boolean add(final Node<T> newNode) {

            if (this.root == null) {
                this.root = newNode;
                this.size++;
                return true;
            }

            Node<T> node = this.root;
            final int[] directions = BinaryHeapTree.getDirections(this.size); // size == index of new node
            if (directions != null && directions.length > 0)
                for (final int d : directions)
                    if (d == 0)
                        // Go left
                        node = node.left;
                    else
                        // Go right
                        node = node.right;
            if (node.left == null)
                node.left = newNode;
            else
                node.right = newNode;
            newNode.parent = node;

            this.size++;

            this.heapUp(newNode);

            return true;
        }

        /**
         * Remove the root node.
         */
        private void removeRoot() {

            this.replaceNode(this.root);
        }

        private Node<T> getLastNode() {

            // Find the last node
            final int[] directions = BinaryHeapTree.getDirections(this.size - 1); // Directions to the last node
            Node<T> lastNode = this.root;
            if (directions != null && directions.length > 0)
                for (final int d : directions)
                    if (d == 0)
                        // Go left
                        lastNode = lastNode.left;
                    else
                        // Go right
                        lastNode = lastNode.right;
            if (lastNode.right != null)
                lastNode = lastNode.right;
            else if (lastNode.left != null)
                lastNode = lastNode.left;
            return lastNode;
        }

        /**
         * Replace the node with the last node and heap down.
         *
         * @param node
         *             to replace.
         */
        private void replaceNode(final Node<T> node) {

            final Node<T> lastNode = this.getLastNode();

            // Remove lastNode from tree
            final Node<T> lastNodeParent = lastNode.parent;
            if (lastNodeParent != null) {
                if (lastNodeParent.right != null)
                    lastNodeParent.right = null;
                else
                    lastNodeParent.left = null;
                lastNode.parent = null;
            }

            if (node.parent != null)
                if (node.parent.left.equals(node))
                    node.parent.left = lastNode;
                else
                    node.parent.right = lastNode;
            lastNode.parent = node.parent;

            lastNode.left = node.left;
            if (node.left != null)
                node.left.parent = lastNode;

            lastNode.right = node.right;
            if (node.right != null)
                node.right.parent = lastNode;

            if (node.equals(this.root))
                if (!lastNode.equals(this.root))
                    this.root = lastNode;
                else
                    this.root = null;

            this.size--;

            // Last node is the node to remove
            if (lastNode.equals(node))
                return;

            if (lastNode.equals(this.root))
                this.heapDown(lastNode);
            else {
                this.heapDown(lastNode);
                this.heapUp(lastNode);
            }
        }

        /**
         * Get the node in the startingNode sub-tree which has the value.
         *
         * @param startingNode
         *                     node rooted sub-tree to search in.
         * @param value
         *                     to search for.
         * @return Node<T> which equals value in sub-tree or NULL if not found.
         */
        private Node<T> getNode(final Node<T> startingNode, final T value) {

            Node<T> result = null;
            if (startingNode != null && startingNode.value.equals(value))
                result = startingNode;
            else if (startingNode != null && !startingNode.value.equals(value)) {
                final Node<T> left = startingNode.left;
                final Node<T> right = startingNode.right;
                if (left != null && (this.type == Type.MIN && left.value.compareTo(value) <= 0 ||
                        this.type == Type.MAX && left.value.compareTo(value) >= 0)) {
                    result = this.getNode(left, value);
                    if (result != null)
                        return result;
                }
                if (right != null && (this.type == Type.MIN && right.value.compareTo(value) <= 0 ||
                        this.type == Type.MAX && right.value.compareTo(value) >= 0)) {
                    result = this.getNode(right, value);
                    if (result != null)
                        return result;
                }
            }
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clear() {

            this.root = null;
            this.size = 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(final T value) {

            if (this.root == null)
                return false;
            final Node<T> node = this.getNode(this.root, value);
            return node != null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T delete(final T value) {

            if (this.root == null)
                return null;
            final Node<T> node = this.getNode(this.root, value);
            if (node != null) {
                final T t = node.value;
                this.replaceNode(node);
                return t;
            }
            return null;
        }

        /**
         * Heap up the heap from this node.
         *
         * @param nodeToHeapUp
         *                     to heap up.
         */
        protected void heapUp(final Node<T> nodeToHeapUp) {

            Node<T> node = nodeToHeapUp;
            while (node != null) {
                final Node<T> heapNode = node;
                final Node<T> parent = heapNode.parent;

                if (parent != null && (this.type == Type.MIN && node.value.compareTo(parent.value) < 0 ||
                        this.type == Type.MAX && node.value.compareTo(parent.value) > 0)) {
                    // Node is less than parent, switch node with parent
                    final Node<T> grandParent = parent.parent;
                    final Node<T> parentLeft = parent.left;
                    final Node<T> parentRight = parent.right;

                    parent.left = heapNode.left;
                    if (parent.left != null)
                        parent.left.parent = parent;
                    parent.right = heapNode.right;
                    if (parent.right != null)
                        parent.right.parent = parent;

                    if (parentLeft != null && parentLeft.equals(node)) {
                        heapNode.left = parent;
                        heapNode.right = parentRight;
                        if (parentRight != null)
                            parentRight.parent = heapNode;
                    } else {
                        heapNode.right = parent;
                        heapNode.left = parentLeft;
                        if (parentLeft != null)
                            parentLeft.parent = heapNode;
                    }
                    parent.parent = heapNode;

                    if (grandParent == null) {
                        // New root.
                        heapNode.parent = null;
                        this.root = heapNode;
                    } else {
                        final Node<T> grandLeft = grandParent.left;
                        if (grandLeft != null && grandLeft.equals(parent))
                            grandParent.left = heapNode;
                        else
                            grandParent.right = heapNode;
                        heapNode.parent = grandParent;
                    }
                } else
                    node = heapNode.parent;
            }
        }

        /**
         * Heap down the heap from this node.
         *
         * @param nodeToHeapDown
         *                       to heap down.
         */
        protected void heapDown(final Node<T> nodeToHeapDown) {

            if (nodeToHeapDown == null)
                return;

            final Node<T> node = nodeToHeapDown;
            final Node<T> heapNode = node;
            final Node<T> left = heapNode.left;
            final Node<T> right = heapNode.right;

            if (left == null && right == null)
                // Nothing to do here
                return;

            Node<T> nodeToMove = null;

            if (left != null && right != null &&
                    (this.type == Type.MIN && node.value.compareTo(left.value) > 0 &&
                    node.value.compareTo(right.value) > 0 ||
                    this.type == Type.MAX && node.value.compareTo(left.value) < 0 &&
                    node.value.compareTo(right.value) < 0))
                // Both children are greater/lesser than node
                if (this.type == Type.MIN && right.value.compareTo(left.value) < 0 ||
                        this.type == Type.MAX && right.value.compareTo(left.value) > 0)
                    // Right is greater/lesser than left
                    nodeToMove = right;
                else if (this.type == Type.MIN && left.value.compareTo(right.value) < 0 ||
                        this.type == Type.MAX && left.value.compareTo(right.value) > 0)
                    // Left is greater/lesser than right
                    nodeToMove = left;
                else
                    // Both children are equal, use right
                    nodeToMove = right;
            else if (this.type == Type.MIN && right != null && node.value.compareTo(right.value) > 0 ||
                    this.type == Type.MAX && right != null && node.value.compareTo(right.value) < 0)
                // Right is greater than node
                nodeToMove = right;
            else if (this.type == Type.MIN && left != null && node.value.compareTo(left.value) > 0 ||
                    this.type == Type.MAX && left != null && node.value.compareTo(left.value) < 0)
                // Left is greater than node
                nodeToMove = left;
            // No node to move, stop recursion
            if (nodeToMove == null)
                return;

            // Re-factor heap sub-tree
            final Node<T> nodeParent = heapNode.parent;
            if (nodeParent == null) {
                // heap down the root
                this.root = nodeToMove;
                this.root.parent = null;
            } else if (nodeParent.left != null && nodeParent.left.equals(node)) {
                // heap down a left
                nodeParent.left = nodeToMove;
                nodeToMove.parent = nodeParent;
            } else {
                // heap down a right
                nodeParent.right = nodeToMove;
                nodeToMove.parent = nodeParent;
            }

            final Node<T> nodeLeft = heapNode.left;
            final Node<T> nodeRight = heapNode.right;
            final Node<T> nodeToMoveLeft = nodeToMove.left;
            final Node<T> nodeToMoveRight = nodeToMove.right;
            if (nodeLeft != null && nodeLeft.equals(nodeToMove)) {
                nodeToMove.right = nodeRight;
                if (nodeRight != null)
                    nodeRight.parent = nodeToMove;

                nodeToMove.left = heapNode;
            } else {
                nodeToMove.left = nodeLeft;
                if (nodeLeft != null)
                    nodeLeft.parent = nodeToMove;

                nodeToMove.right = heapNode;
            }
            heapNode.parent = nodeToMove;

            heapNode.left = nodeToMoveLeft;
            if (nodeToMoveLeft != null)
                nodeToMoveLeft.parent = heapNode;

            heapNode.right = nodeToMoveRight;
            if (nodeToMoveRight != null)
                nodeToMoveRight.parent = heapNode;

            this.heapDown(node);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean validate() {

            if (this.root == null)
                return true;
            return this.validateNode(this.root);
        }

        /**
         * Validate node for heap invariants.
         *
         * @param node
         *             to validate for.
         * @return True if node is valid.
         */
        private boolean validateNode(final Node<T> node) {

            final Node<T> left = node.left;
            final Node<T> right = node.right;

            // We shouldn't ever have a right node without a left in a heap
            if (right != null && left == null)
                return false;

            if (left != null) {
                if (this.type == Type.MIN && node.value.compareTo(left.value) < 0 ||
                        this.type == Type.MAX && node.value.compareTo(left.value) > 0)
                    return this.validateNode(left);
                return false;
            }
            if (right != null) {
                if (this.type == Type.MIN && node.value.compareTo(right.value) < 0 ||
                        this.type == Type.MAX && node.value.compareTo(right.value) > 0)
                    return this.validateNode(right);
                return false;
            }

            return true;
        }

        /**
         * Populate the node in the array at the index.
         *
         * @param node
         *              to populate.
         * @param idx
         *              of node in array.
         * @param array
         *              where the node lives.
         */
        private void getNodeValue(final Node<T> node, final int idx, final T[] array) {

            int index = idx;
            array[index] = node.value;
            index = index * 2 + 1;

            final Node<T> left = node.left;
            if (left != null)
                this.getNodeValue(left, index, array);
            final Node<T> right = node.right;
            if (right != null)
                this.getNodeValue(right, index + 1, array);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T[] getHeap() {

            final T[] nodes = (T[])new Comparable[this.size];
            if (this.root != null)
                this.getNodeValue(this.root, 0, nodes);
            return nodes;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T getHeadValue() {

            T result = null;
            if (this.root != null)
                result = this.root.value;
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public T deleteHead() {

            T result = null;
            if (this.root != null) {
                result = this.root.value;
                this.removeRoot();
            }
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public java.util.Collection<T> toCollection() {

            return new JavaCompatibleBinaryHeapTree<>(this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {

            return HeapPrinter.getString(this);
        }

        protected static class HeapPrinter {

            public static <T extends Comparable<T>> void print(final BinaryHeapTree<T> tree) {

                System.out.println(HeapPrinter.getString(tree.root, "", true));
            }

            public static <T extends Comparable<T>> String getString(final BinaryHeapTree<T> tree) {

                if (tree.root == null)
                    return "Tree has no nodes.";
                return HeapPrinter.getString(tree.root, "", true);
            }

            private static <T extends Comparable<T>> String getString(final Node<T> node, final String prefix,
                    final boolean isTail) {

                final StringBuilder builder = new StringBuilder();

                builder.append(prefix + (isTail ? "└── " : "├── ") + node.value + "\n");
                List<Node<T>> children = null;
                if (node.left != null || node.right != null) {
                    children = new ArrayList<>(2);
                    if (node.left != null)
                        children.add(node.left);
                    if (node.right != null)
                        children.add(node.right);
                }
                if (children != null) {
                    for (int i = 0; i < children.size() - 1; i++)
                        builder.append(
                                HeapPrinter.getString(children.get(i), prefix + (isTail ? "    " : "│   "), false));
                    if (children.size() >= 1)
                        builder.append(HeapPrinter.getString(children.get(children.size() - 1),
                                prefix + (isTail ? "    " : "│   "), true));
                }

                return builder.toString();
            }

        }

        private static class Node<T extends Comparable<T>> {

            private T value = null;

            private Node<T> parent = null;

            private Node<T> left = null;

            private Node<T> right = null;

            private Node(final Node<T> parent, final T value) {

                this.value = value;
                this.parent = parent;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public String toString() {

                return "value=" + this.value + " parent=" + (this.parent != null ? this.parent.value : "NULL") +
                        " left=" + (this.left != null ? this.left.value : "NULL") + " right=" +
                        (this.right != null ? this.right.value : "NULL");
            }

        }
    }

    public static class JavaCompatibleBinaryHeapArray<T extends Comparable<T>>
            extends java.util.AbstractCollection<T> {

        private BinaryHeapArray<T> heap = null;

        public JavaCompatibleBinaryHeapArray() {

            this.heap = new BinaryHeapArray<>();
        }

        public JavaCompatibleBinaryHeapArray(final BinaryHeapArray<T> heap) {

            this.heap = heap;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean add(final T value) {

            return this.heap.insert(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean remove(final Object value) {

            return this.heap.delete((T)value) != null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(final Object value) {

            return this.heap.contains((T)value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {

            return this.heap.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public java.util.Iterator<T> iterator() {

            return new BinaryHeapArrayIterator<>(this.heap);
        }

        private static class BinaryHeapArrayIterator<T extends Comparable<T>>
                implements java.util.Iterator<T> {

            private BinaryHeapArray<T> heap = null;

            private int last = -1;

            private int index = -1;

            protected BinaryHeapArrayIterator(final BinaryHeapArray<T> heap) {

                this.heap = heap;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {

                if (this.index + 1 >= this.heap.size)
                    return false;
                return this.heap.array[this.index + 1] != null;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public T next() {

                if (++this.index >= this.heap.size)
                    return null;
                this.last = this.index;
                return this.heap.array[this.index];
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {

                this.heap.delete(this.last);
            }

        }
    }

    public static class JavaCompatibleBinaryHeapTree<T extends Comparable<T>>
            extends java.util.AbstractCollection<T> {

        private BinaryHeapTree<T> heap = null;

        public JavaCompatibleBinaryHeapTree() {

            this.heap = new BinaryHeapTree<>();
        }

        public JavaCompatibleBinaryHeapTree(final BinaryHeapTree<T> heap) {

            this.heap = heap;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean add(final T value) {

            return this.heap.insert(value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean remove(final Object value) {

            return this.heap.delete((T)value) != null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(final Object value) {

            return this.heap.contains((T)value);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {

            return this.heap.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public java.util.Iterator<T> iterator() {

            return new BinaryHeapTreeIterator<>(this.heap);
        }

        private static class BinaryHeapTreeIterator<C extends Comparable<C>>
                implements java.util.Iterator<C> {

            private BinaryHeapTree<C> heap = null;

            private BinaryHeapTree.Node<C> last = null;

            private final Deque<BinaryHeapTree.Node<C>> toVisit = new ArrayDeque<>();

            protected BinaryHeapTreeIterator(final BinaryHeapTree<C> heap) {

                this.heap = heap;
                if (heap.root != null)
                    this.toVisit.add(heap.root);
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {

                if (this.toVisit.size() > 0)
                    return true;
                return false;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public C next() {

                while (this.toVisit.size() > 0) {
                    // Go thru the current nodes
                    final BinaryHeapTree.Node<C> n = this.toVisit.pop();

                    // Add non-null children
                    if (n.left != null)
                        this.toVisit.add(n.left);
                    if (n.right != null)
                        this.toVisit.add(n.right);

                    // Update last node (used in remove method)
                    this.last = n;
                    return n.value;
                }
                return null;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public void remove() {

                this.heap.replaceNode(this.last);
            }

        }
    }
}
