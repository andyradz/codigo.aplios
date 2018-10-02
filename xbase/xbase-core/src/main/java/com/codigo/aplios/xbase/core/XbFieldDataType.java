package com.codigo.aplios.xbase.core;

/*
 * (FoxPro/FoxBase) Double integer *NOT* a memo field G General (dBASE V: like Memo) OLE Objects in MS Windows versions
 * P Picture (FoxPro) Like Memo fields, but not for text processing. Y Currency (FoxPro) T DateTime (FoxPro) I Integer
 * Length: 4 byte little endian integer (FoxPro)
 */
public enum XbFieldDataType {

    // *** Standard DBF Table Data Types ***
    /**
     * C Character All OEM code page characters - padded with blanks to the width of the field. Character less than 254
     * length ASCII text less than 254 characters long in dBASE.
     *
     * Character fields can be up to 32 KB long (in Clipper and FoxPro) using decimal count as high byte in field
     * length. It's possible to use up to 64KB long fields by reading length as unsigned.
     *
     */
    CHARACTER,
    /**
     * Number Length: less than 18 ASCII text up till 18 characters long (include sign and decimal point).
     *
     * Valid characters: "0" - "9" and "-". Number fields can be up to 20 characters long in FoxPro and Clipper.
     *
     * We are not enforcing this 18 char limit.
     */
    NUMERIC,
    /**
     * D Date Length: 8 Date in format YYYYMMDD. A date like 0000-00- 00 is *NOT* valid.
     */
    DATE,
    /**
     * L Logical Length: 1 Boolean/byte (8 bit)
     *
     * Legal values: ? Not initialised (default) Y,y Yes N,n No F,f False T,t True Logical fields are always displayed
     * using T/F/?. Some sources claims that space (ASCII 20h) is valid for not initialised. Space may occur, but is not
     * defined.
     */
    LOGICAL,
    /**
     * M Memo Length: 10 Pointer to ASCII text field in memo file 10 digits representing a pointer to a DBT block
     * (default is blanks).
     */
    MEMO,
    // *** Extended DBF Table Data Types ***

    /**
     * 4-byte long integer values from -2,147,483,648 to 2,147,483,647.
     */
    INTEGER,
    /**
     * 8-byte IEEE floating point value in the range 1.7E +/-308 (15 digits of precision). The decimal value affects NTX
     * indexes and the use of the field in expressions. It does not affect the precision of the stored data. If the
     * length is given, it will be ignored. For example, "salary, double, 10, 2" and "salary, double, 2" produce the
     * same field.
     */
    DOUBLE,
    /**
     * F Float Number stored as a string, right justified, and padded with blanks to the width of the field. example:
     * value = " 2.40000000000e+001" Length=19 Decimal_Count=11
     *
     * This type was added in DBF V4.
     */
    FLOAT,
    GENERAL,
    /**
     * Variable-length memo field containing binary image data. The size of each field is limited to 4 GB. The binary
     * image data is actually stored in a separate file, called a memo file, to reduce table bloat. If using the
     * Advantage CA-Visual Objects RDDs, Advantage Client Engine APIs must be used to set and retrieve the image data.
     * Most non-Advantage applications will interpret this data as a text memo field with a short text identifier.
     */
    IMAGE,
    /**
     * Variable-length memo field containing binary data. The size of each field is limited to 4 GB. The binary data is
     * actually stored in a separate file, called a memo file, to reduce table bloat. If using the Advantage CA-Visual
     * Objects RDDs, Advantage Client Engine APIs must be used to set and retrieve the binary data. Most non-Advantage
     * applications will interpret this data as a text memo field with a short text identifier.
     */
    BINARY,
    /**
     * 3-byte date field. This type supports the same range of dates as a standard date field
     */
    SHORTDATE;

    // *** Visual FoxPro DBF Table Data Type ***
}

class XbFieldType {

    public static XbFieldTypeBuilder map(final XbFieldDataType typeInfo) {

        return new XbFieldTypeBuilder(typeInfo);
    }

    private final char code;

    private final int minSize;

    private final int maxSize;

    private int length;

    private XbFieldType(final XbFieldTypeBuilder builder) {

        this.code = builder.code;
        this.minSize = builder.minSize;
        this.maxSize = builder.maxSize;
    }

    static class XbFieldTypeBuilder {

        private char code;

        private int minSize;

        private int maxSize;

        XbFieldTypeBuilder(final XbFieldDataType typeInfo) {

        }

        XbFieldTypeBuilder setCode(final char code) {

            this.code = code;
            return this;
        }

        XbFieldTypeBuilder setMinSize(final int minSize) {

            this.minSize = minSize;
            return this;
        }

        XbFieldTypeBuilder setMaxSize(final int maxSize) {

            this.maxSize = maxSize;
            return this;
        }

        XbFieldTypeBuilder setMinLength(final int minLength) {

            return this;
        }

        XbFieldTypeBuilder setMaxLength(final int maxLength) {

            return this;
        }

        XbFieldTypeBuilder setMinValue(final Object minValue) {

            return this;
        }

        XbFieldTypeBuilder setMaxValue(final Object maxValue) {

            return this;
        }

        XbFieldTypeBuilder setDefaultValue(final Object defaultValue) {

            return this;
        }

        XbFieldTypeBuilder setAllowValues(final Object... allowValues) {

            return this;
        }

        XbFieldTypeBuilder setChecker(final Object checker) {

            return this;
        }

        XbFieldType build() {

            return new XbFieldType(this);
        }

    }

    // może dodać validator
}

// http://devzone.advantagedatabase.com/dz/webhelp/advantage9.0/server1/dbf_field_types_and_specifications.htm
