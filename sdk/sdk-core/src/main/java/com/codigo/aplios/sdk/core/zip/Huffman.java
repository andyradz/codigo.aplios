package com.codigo.aplios.sdk.core.zip;

//
/// ******************************************************************************
// * Compilation: javac Huffman.java
// * Execution: java Huffman - < input.txt (compress)
// * Execution: java Huffman + < input.txt (expand)
// * Dependencies: BinaryIn.java BinaryOut.java
// * Data files: http://algs4.cs.princeton.edu/55compression/abra.txt
// * http://algs4.cs.princeton.edu/55compression/tinytinyTale.txt
// * http://algs4.cs.princeton.edu/55compression/medTale.txt
// * http://algs4.cs.princeton.edu/55compression/tale.txt
// *
// * Compress or expand a binary input stream using the Huffman algorithm.
// *
// * % java Huffman - < abra.txt | java BinaryDump 60
// * 010100000100101000100010010000110100001101010100101010000100
// * 000000000000000000000000000110001111100101101000111110010100
// * 120 bits
// *
// * % java Huffman - < abra.txt | java Huffman +
// * ABRACADABRA!
// *
// ******************************************************************************/
//
/// **
// * The {@code Huffman} class provides static methods for compressing
// * and expanding a binary input using Huffman codes over the 8-bit extended
// * ASCII alphabet.
// * <p>
// * For additional documentation,
// * see <a href="http://algs4.cs.princeton.edu/55compress">Section 5.5</a> of
// * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
// *
// * @author Robert Sedgewick
// * @author Kevin Wayne
// */
// public class Huffman {
//
// // alphabet size of extended ASCII
// private static final int R = 256;
//
// /**
// * Podstawowy konstruktor klasy Konstruktor prywatny
// */
// private Huffman() {
//
// }
//
// private static class Node implements Comparable<Node> {
//
// private final int freq;
// private final Node left, right;
//
// Node(final char ch, final int freq, final Node left, final Node right) {
//
// this.freq = freq;
// this.left = left;
// this.right = right;
// }
//
// /*
// * (non-Javadoc)
// *
// * @see java.lang.Comparable#compareTo(java.lang.Object) compare, based on frequency
// */
// @Override
// public int compareTo(final Node that) {
//
// return this.freq - that.freq;
// }
// }
//
// //
// // /**
// // * Reads a sequence of 8-bit bytes from standard input; compresses them
// // * using Huffman codes with an 8-bit alphabet; and writes the results
// // * to standard output.
// // */
// public static void compress() {
//
// // read the input
// final String s = "it was the best of times it was the worst of times";
// final char[] input = s.toCharArray();
//
// // tabulate frequency counts
// final int[] freq = new int[Huffman.R];
// for (int i = 0; i < input.length; i++)
// freq[input[i]]++;
//
// Huffman.buildTrie(freq);
// }
//
// //
// // build the Huffman trie given frequencies
// private static Node buildTrie(final int[] freq) {
//
// // initialze priority queue with singleton trees
// final MinPQ<Node> pq = new MinPQ<>(
// Node.class);
// for (char i = 0; i < Huffman.R; i++)
// if (freq[i] > 0)
// pq.insert(new Node(
// i, freq[i], null, null));
//
// // special case in case there is only one character with a nonzero frequency
// if (pq.size() == 1)
// if (freq['\0'] == 0)
// pq.insert(new Node(
// '\0', 0, null, null));
// else
// pq.insert(new Node(
// '\1', 0, null, null));
//
// // // merge two smallest trees
// while (pq.size() > 1) {
// final Node left = pq.deleteMin();
// final Node right = pq.deleteMin();
// final Node parent = new Node(
// '\0', left.freq + right.freq, left, right);
// pq.insert(parent);
// }
// return pq.deleteMin();
// }
//
// //
// // // write bitstring-encoded trie to standard output
// // private static void writeTrie(Node x) {
// //
// // if (x.isLeaf()) {
// // BinaryStdOut.write(true);
// // BinaryStdOut.write(x.ch, 8);
// // return;
// // }
// // BinaryStdOut.write(false);
// // writeTrie(x.left);
// // writeTrie(x.right);
// // }
// //
// // // make a lookup table from symbols and their encodings
// // private static void buildCode(String[] st, Node x, String s) {
// //
// // if (!x.isLeaf()) {
// // buildCode(st, x.left, s
// // + '0');
// // buildCode(st, x.right, s
// // + '1');
// // } else {
// // st[x.ch] = s;
// // }
// // }
// //
// // /**
// // * Reads a sequence of bits that represents a Huffman-compressed message from
// // * standard input; expands them; and writes the results to standard output.
// // */
// // public static void expand() {
// //
// // // read in Huffman trie from input stream
// // Node root = readTrie();
// //
// // // number of bytes to write
// // int length = BinaryStdIn.readInt();
// //
// // // decode using the Huffman trie
// // for (int i = 0; i < length; i++) {
// // Node x = root;
// // while (!x.isLeaf()) {
// // boolean bit = BinaryStdIn.readBoolean();
// // if (bit)
// // x = x.right;
// // else
// // x = x.left;
// // }
// // BinaryStdOut.write(x.ch, 8);
// // }
// // BinaryStdOut.close();
// // }
// //
// // private static Node readTrie() {
// //
// // boolean isLeaf = BinaryStdIn.readBoolean();
// // if (isLeaf) {
// // return new Node(BinaryStdIn.readChar(), -1, null, null);
// // } else {
// // return new Node('\0', -1, readTrie(), readTrie());
// // }
// // }
// //
// // /**
// // * Sample client that calls {@code compress()} if the command-line
// // * argument is "-" an {@code expand()} if it is "+".
// // *
// // * @param args
// // * the command-line arguments
// // */
// public static void main(final String[] args) {
//
// if (args[0].equals("-"))
// Huffman.compress();
// // else if (args[0].equals("+"))
// // expand();
// else
// throw new IllegalArgumentException(
// "Illegal command line argument");
//
// System.out.println(Huffman.avg(new int[] { 1, 10, 100, 1000, 10000 }));
// }
//
// public static double avg(final int[] arr) {
//
// double avg = 0;
// final int size = arr.length;
// for (int idx = 0; idx < size; idx++)
// avg += arr[idx];
//
// return avg / size;
// }
//
// }
