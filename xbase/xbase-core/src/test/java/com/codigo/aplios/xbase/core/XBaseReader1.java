package com.codigo.aplios.xbase.core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.codigo.aplios.xbase.core.database.structure.column.XbDataColumn;

public class XBaseReader1 {
	// -----------------------------------------------------------------------------------------------------------------

	private static final int FileDescriptorSize = 33; // 32bytes + terminator byte;

	// -----------------------------------------------------------------------------------------------------------------
	private static final int ColumnDescriptorSize = 32;

	// -----------------------------------------------------------------------------------------------------------------
	public void readDbf() throws IOException {
		// ...test dbf read with random access file

		// final Path dbfPath = Paths.get("d:\\codigo.warehouse\\Database\\XBase\\!michal\\",
		// "POZYCJE.DBF");
		// final Path dbfPath = Paths.get("d:\\codigo.warehouse\\Database\\XBase\\!alma\\", "ASORTYM.DBF");
		// final Path dbfPath = Paths.get("d:\\codigo.warehouse\\Database\\XBase\\!marex\\", "KLIENT.DBF");
		// final Path dbfPath = Paths.get("d:\\codigo.warehouse\\Database\\XBase\\!alma\\", "KONTRA.DBF");
		final Path dbfPath = Paths.get("d:\\codigo.warehouse\\Database\\XBase\\", "MG00GLIG.DBF");

		final BasicFileAttributeView view = Files.getFileAttributeView(dbfPath, BasicFileAttributeView.class);
		view.readAttributes();

		try (FileChannel fch = FileChannel.open(dbfPath)) {
			final ByteBuffer dbfBuffer = ByteBuffer.allocate(XBaseReader1.FileDescriptorSize - 1);

			dbfBuffer.order(ByteOrder.LITTLE_ENDIAN);
			dbfBuffer.clear();
			fch.read(dbfBuffer);
			dbfBuffer.flip();

			final XbFileHeader head = XbFileHeader.builder(dbfBuffer.array())
				.build();

			System.out.println(head);

			dbfBuffer.get();

			dbfBuffer.get();
			dbfBuffer.get();
			dbfBuffer.get();
			final int recCount = dbfBuffer.getInt();
			final short headBytes = dbfBuffer.getShort();
			final short recordBytes = dbfBuffer.getShort();

			// read the field length in bytes
			// if field type is char, then read FieldLength and Decimal count as one number to allow char fields
			// to be
			// longer than 256 bytes (ASCII char). This is the way Clipper and FoxPro do it, and there is really
			// no
			// downside
			// since for char fields decimal count should be zero for other versions that do not support this
			// extended
			// functionality.
			// ---------------------------------------------------------------------------------------------------------
			final int nNumFields = (headBytes - XBaseReader1.FileDescriptorSize) / XBaseReader1.ColumnDescriptorSize;
			final List<XbDataColumn> columns = new ArrayList<>();

			for (int idx = 0; idx < nNumFields; idx++) {
				dbfBuffer.clear();
				fch.read(dbfBuffer);
				dbfBuffer.flip();
				columns.add(new XbDataColumn(dbfBuffer));
			}

			final ByteBuffer terminal = ByteBuffer.allocate(2);
			fch.read(terminal);

			columns.forEach(System.out::println);
			final ByteBuffer data = ByteBuffer.allocate(recordBytes);
			int lineLen = 0;

			System.out.println(head.getLastUpdateYear());

			for (int jdx = 1; jdx <= recCount; jdx++) {
				data.clear();
				fch.read(data);
				data.flip();
				System.out.printf("%-4d|", jdx);

				for (int idx = 0; idx < nNumFields; idx++) {
					final byte[] raw = new byte[columns.get(idx)
						.getFieldSize()];
					data.get(raw);
					head.getCharset();
					final String value = new String(raw, head.getCharset());
					lineLen += columns.get(idx)
						.getFieldSize()
							+ columns.get(idx)
								.getFieldDecimalPlaces()
							+ 4;
					System.out.printf("%-" + (columns.get(idx)
						.getFieldSize()) + "s|", value);
				}
				System.out.println("");
				System.out.println(String.join("", Collections.nCopies(lineLen, "-")));
				lineLen = 4;
			}

			fch.close();
		}
		catch (final Exception ex) {
			System.out.println(ex.getMessage());
		}
		finally {

		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {

		new XBaseReader1().readDbf();
	}

}
