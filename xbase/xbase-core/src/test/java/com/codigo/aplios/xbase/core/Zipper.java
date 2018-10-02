// package com.codigo.aplios.xbase;
//
// import static java.nio.file.StandardOpenOption.READ;
//
// import java.io.IOException;
// import java.io.PrintStream;
// import java.nio.ByteBuffer;
// import java.nio.ByteOrder;
// import java.nio.IntBuffer;
// import java.nio.channels.FileChannel;
// import java.nio.charset.Charset;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.text.DecimalFormat;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.Comparator;
// import java.util.List;
// import java.util.function.Predicate;
// import java.util.zip.ZipEntry;
// import java.util.zip.ZipFile;
//
// import com.codigo.aplios.xglobic.xbase.XbCodePages;
// import com.codigo.aplios.xglobic.xbase.XbFieldInfo;
// import com.codigo.aplios.xglobic.xbase.database.structure.header.XbFileHeader;
//
// public class Zipper {
//
// // -----------------------------------------------------------------------------------------------------------------
//
// private static int FileDescriptorSize = 33; // 32bytes + terminator byte;
//
// // -----------------------------------------------------------------------------------------------------------------
//
// private static int ColumnDescriptorSize = 32;
//
// // -----------------------------------------------------------------------------------------------------------------
//
// public void readDbf() throws IOException {
//
// // ...test dbf read with random access file
// // final Path dbfPath = Paths.get("D:/State_vars1.dbf");
// // final Path dbfPath = Paths.get("D:/cars.dbf");
// // final List<XbFieldType> ver = XbFileVersion. typesFor1(XbFileVersion.XBASE3);
//
// // final Path dbfPath = Paths.get("D:/FREE.dbf");
// final Path dbfPath = Paths.get("d:/sample.dbf");
//
// try (FileChannel fch = FileChannel.open(dbfPath, READ)) {
// // FileLock fileLock = fch.lock();
// final ByteBuffer dbfBuffer = ByteBuffer.allocate(Zipper.FileDescriptorSize - 1);
//
// dbfBuffer.order(ByteOrder.LITTLE_ENDIAN);
// dbfBuffer.clear();
// fch.read(dbfBuffer);
// dbfBuffer.flip();
//
// final XbFileHeader head = XbFileHeader.builder(dbfBuffer.array())
// .build();
//
// final int nNumFields = (head.getHeaderSize() - Zipper.FileDescriptorSize) / Zipper.ColumnDescriptorSize;
// final List<XbFieldInfo> columns = new ArrayList<>();
// int lineLen = 0;
// for (int idx = 0; idx < nNumFields; idx++) {
// dbfBuffer.clear();
// fch.read(dbfBuffer);
// dbfBuffer.flip();
// columns.add(new XbFieldInfo(dbfBuffer));
// }
//
// columns.forEach(System.out::println);
// final ByteBuffer data = ByteBuffer.allocate(head.getRecordSize());
// for (int jdx = 1; jdx <= head.getRecordsCount(); jdx++) {
// data.clear();
// fch.read(data);
// data.flip();
// // System.out.printf("%-5d|", jdx);
// for (int idx = 0; idx < nNumFields; idx++) {
// final byte[] raw = new byte[columns.get(idx)
// .getFieldSize()];
// columns.get(idx)
// .getFieldType();
//
// lineLen += columns.get(idx)
// .getFieldSize();
// data.get(raw);
// final Charset chs = XbCodePages.ofCodepage(head.getCodepage());
// final String value = new String(raw, chs);
// final XbFieldInfo val = columns.get(idx);
// System.out.printf("%-" + val.getFieldSize() + "s|", value.trim());
// }
// System.out.println("");
// System.out.println(String.join("", Collections.nCopies(lineLen, "-")));
// lineLen = 0;
// // fileLock.release();
//
// }
// fch.close();
// }
// catch (final Exception ex) {
// System.out.println(ex.getMessage());
// }
// finally {
//
// }
//
// }
//
// public static void main(final String[] args) throws IOException {
//
// new Zipper().readDbf();
//
// // ...test bigendian
//
// final ByteBuffer bigEndian = ByteBuffer.allocate(16);
// bigEndian.order(ByteOrder.BIG_ENDIAN);
// final IntBuffer i = bigEndian.asIntBuffer();
// i.put(1)
// .put(2)
// .put(3)
// .put(4);
// bigEndian.rewind();
// bigEndian.get();
//
// // ByteBuffer littleEndian = ByteBuffer.wrap(bytes);
// // littleEndian.order(ByteOrder.LITTLE_ENDIAN);
// // littleEndian.rewind();
// // value = littleEndian.get();
//
// new DecimalFormat("#+,##0.00;#,##0.00-");
//
// // final Path filepath = Paths.get("D:/kdpw.install/rdi/RDI_V9.5_CORE_PRODUCT_INSTALL_1_E.zip");
// // final Path filepath = Paths.get("D:/kdpw.tools/jtoolbox/toolbox.jar");
// final Path filepath = Paths.get("F:/allmarks_2017.09.21.zip");
//
// Zipper.printEntries(System.out, filepath.toAbsolutePath()
// .toString());
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// public static void printEntries(final PrintStream stream, final String zip) {
//
// try (ZipFile zipFile = new ZipFile(zip)) {
// final Predicate<ZipEntry> isFile = ze -> !ze.isDirectory();
// final Predicate<ZipEntry> isJava = ze -> ze.getName()
// .matches(".*class");
// final Comparator<ZipEntry> bySize = (ze1, ze2) -> Long.valueOf(ze2.getSize() - ze1.getSize())
// .intValue();
// zipFile.stream()
// .filter(isFile.and(isJava))
// .sorted(bySize)
// .forEach(ze -> Zipper.print(stream, ze));
// }
// catch (final IOException e) {
// }
// }
//
// // -----------------------------------------------------------------------------------------------------------------
//
// private static void print(final PrintStream stream, final ZipEntry zipEntry) {
//
// stream.println(zipEntry.getName() + ", size = " + zipEntry.getSize());
// }
//
// // -----------------------------------------------------------------------------------------------------------------
// }
