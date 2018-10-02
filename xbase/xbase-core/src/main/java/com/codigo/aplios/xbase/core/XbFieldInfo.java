package com.codigo.aplios.xbase.core;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

// https://www.clicketyclick.dk/databases/xbase/format/dbf.html#DBF_STRUCT
/// struktura pliku XBAse
// http://wiki.dbfmanager.com/dbf-structure
// http://www.oocities.org/geoff_wass/dBASE/GaryWhite/dBASE/FAQ/qformt.htm
// http://web.tiscali.it/SilvioPitti/
// PHP CLASS BUILDER
// https://packagist.org/packages/org.majkel/dbase
// http://fileformats.archiveteam.org/wiki/DBF#Specifications
/**
 * What to check when opening a .DBF File
 *
 * Records:
 *
 * Length of record must be > 1 and < max length. (max length = 4000 B in dBASE III and IV, can be
 * 32KB in other systems). The number of records must be >= 0. Fields: The .DBF file must have at
 * least one field. The number of fields must be <= the maximum allowable number of fields. File
 * size:
 *
 * File size reported by the operating system must match the logical file size. Logical file size =
 * ( Length of header + ( Number of records * Length of each record ) )
 */
public class XbFieldInfo {

	// -----------------------------------------------------------------------------------------------------------------
	private static final Charset COLUMN_CHARSET;

	// -----------------------------------------------------------------------------------------------------------------
	private String fieldName; // 0-10

	// -----------------------------------------------------------------------------------------------------------------
	private byte fieldType; // 11

	// -----------------------------------------------------------------------------------------------------------------
	private byte[] fieldDisplacement; // 12-15 in bytes

	// -----------------------------------------------------------------------------------------------------------------
	private byte fieldSize; // 16 in bytes

	// -----------------------------------------------------------------------------------------------------------------
	private byte fieldDecimalPlaces; // 17

	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------
	// TOD: wymyslec sposób określania ile bajtów nalezy pobrać
	// -----------------------------------------------------------------------------------------------------------------
	static {
		COLUMN_CHARSET = Charset.forName("ASCII");
	}

	// -----------------------------------------------------------------------------------------------------------------
	public XbFieldInfo(final ByteBuffer fieldData) {

		super();
		initalize(fieldData);
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void initalize(final ByteBuffer fieldData) {

		readFieldName(fieldData);
		readFieldType(fieldData);
		readFieldDisplacment(fieldData);
		readFieldSize(fieldData);
		readFieldDecimalPlaces(fieldData);
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void readFieldName(final ByteBuffer fieldData) {

		final byte[] fieldName = new byte[11];
		fieldData.get(fieldName);
		this.fieldName = new String(
			fieldName, XbFieldInfo.COLUMN_CHARSET);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public String getFieldName() {

		return this.fieldName.trim();
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void readFieldType(final ByteBuffer fieldData) {

		this.fieldType = fieldData.get();
	}

	// -----------------------------------------------------------------------------------------------------------------
	public String getFieldType() {

		return String.valueOf((char) this.fieldType);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public int getFieldDisplacment() {

		final ByteBuffer buffer = ByteBuffer.wrap(this.fieldDisplacement);
		return buffer.getInt();
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void readFieldDisplacment(final ByteBuffer fieldData) {

		this.fieldDisplacement = new byte[4];
		fieldData.get(this.fieldDisplacement);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public byte getFieldSize() {

		return (byte) Math.abs(this.fieldSize);
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void readFieldSize(final ByteBuffer fieldData) {

		this.fieldSize = fieldData.get();
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void readFieldDecimalPlaces(final ByteBuffer fieldData) {

		this.fieldDecimalPlaces = fieldData.get();
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * @return
	 */
	public byte getFieldDecimalPlaces() {

		return this.fieldDecimalPlaces;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public XbFieldType getFielSpec() {

		return null;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "XbFieldInfo [Name=" + getFieldName() + "]" + "[Type=" + getFieldType() + "]" + "[Size=" + getFieldSize()
				+ "]" + "[Displacment=" + getFieldDisplacment() + "]" + "[DecimalCount=" + getFieldDecimalPlaces()
				+ "]";
	}

	// -----------------------------------------------------------------------------------------------------------------
}
