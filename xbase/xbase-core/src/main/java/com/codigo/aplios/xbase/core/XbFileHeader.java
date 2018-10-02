package com.codigo.aplios.xbase.core;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// https://www.clicketyclick.dk/databases/xbase/format/dbf.html#DBF_NOTE_12_TARGET
/**
 * The data file with suffix DBF is the central table in an Xbase database. All other data files are related to this one
 * file. The Data File is a mix of binary and ASCII data. Header contains binary data. The records are all in ASCII
 * (except ofcause the binary objects like pictures).
 *
 * Several sources claim that dBASE clears the header on creation with blanks (20h). But I've seen data in the reserved
 * areas.
 *
 * Some documents states that deleted records are overwritten by new valid records. My experience is that new records
 * are appended to the data file - not inserted. deleted record can only be deleted physically using the PACK command.
 * Even after PACK the deleted record exists after the EOF mark. The file is not truncated in dBASE III (But don't count
 * on it ;-)
 *
 * Note that this structure is valid for Xbase - and dBASE v. III - 5. Later versions of dBASE has a different layout,
 * like dBASE 7 (see http://www.dbase.com/KnowledgeBase/int/db7_file_fmt.htm
 *
 * @author dp0470
 *
 */
public class XbFileHeader {

    public static XbFileHeaderBuilder builder() {

        return new XbFileHeader.XbFileHeaderBuilder(Charset.forName("ASCII"));
    }

    public static XbFileHeaderBuilder builder(final XbFileHeader fileHeader) {

        return new XbFileHeader.XbFileHeaderBuilder(fileHeader);
    }

    public static XbFileHeaderBuilder builder(final byte[] rawData) {

        return new XbFileHeader.XbFileHeaderBuilder(rawData);
    }

    private final Charset charset;

    private final byte version;

    private final short lastUpdateYear;

    private final byte lastUpdateMonth;

    private final byte lastUpdateDay;

    private final int recordsCount;

    /**
     * Length of the header structure
     */
    private final int headerSize;// = XbFileHeader.FileDescriptorSize;

    private final short recordSize;

    private final byte codepage;					// TODO: zamienić to na typ wyliczeniowy

    private byte terminator;

    private final List<XbFieldInfo> columns = new ArrayList<>();

    /**
     * Indicates whether header columns can be modified!
     */
    private boolean locked;

    /**
     * When object is modified dirty flag is set
     */
    boolean isDirty;

    private XbFileHeader(final XbFileHeaderBuilder headerBuilder) {

        this.charset = headerBuilder.charset;
        this.terminator = 0x0D;
        this.version = headerBuilder.version;
        this.lastUpdateYear = headerBuilder.lastUpdateYear;
        this.lastUpdateMonth = headerBuilder.lastUpdateMonth;
        this.lastUpdateDay = headerBuilder.lastUpdateDay;
        this.recordsCount = headerBuilder.recordsCount;
        this.headerSize = headerBuilder.headerSize;
        this.recordSize = headerBuilder.recordSize;
        this.codepage = headerBuilder.codepage;
        this.terminator = headerBuilder.terminator;
        this.columns.addAll(headerBuilder.columns);
    }

    public int getVersion() {

        return this.version;
    }

    public short getLastUpdateYear() {

        return (short)(this.lastUpdateYear + 1900);
    }

    public byte getLastUpdateMonth() {

        return this.lastUpdateMonth;
    }

    public byte getLastUpdateDay() {

        return this.lastUpdateDay;
    }

    public LocalDate getLastUpdateDate() {

        return LocalDate.of(this.lastUpdateYear, this.lastUpdateMonth, this.lastUpdateDay);
    }

    public int getHeaderSize() {

        return this.headerSize;
    }

    public short getRecordSize() {

        return this.recordSize;
    }

    public int getRecordsCount() {

        return this.recordsCount;
    }

    public byte getCodepage() {

        return this.codepage;
    }

    public byte getTerminator() {

        return this.terminator;
    }

    public Charset getCharset() {

        return this.charset;
    }

    public List<XbFieldInfo> getColumns() {

        return this.columns;
    }

    // public IXbFileHederSpec
    public Object test() {

        // dla danej versji odczytujemy konfiguracje, limitacje itp.
        return null;
    }

    boolean getLocked() {

        return this.locked;
    }

    public synchronized void unlock() {

        this.locked = false;
    }

    /**
     * Returns true when this object is modified after read or write.
     */
    public boolean getIsDirty() {

        return false;
    }

    public synchronized void setIsDirty(final boolean dirty) {

    }

    static class XbFileHeaderBuilder {

        private final Charset charset;

        private byte version;

        private short lastUpdateYear;

        private byte lastUpdateMonth;

        private byte lastUpdateDay;

        private int recordsCount;

        private short headerSize;

        private short recordSize;

        private byte codepage;

        private byte terminator;

        private final List<XbFieldInfo> columns = new ArrayList<>();

        public XbFileHeaderBuilder(final Charset charset) {

            this.charset = charset;
        }

        public XbFileHeaderBuilder(final XbFileHeader fileHeader) {

            this.charset = fileHeader.charset;
            this.setVersion(fileHeader.version);
            this.setLastUpdateYear(fileHeader.lastUpdateYear);
            this.setLastUpdateMonth(fileHeader.lastUpdateMonth);
            this.setLastUpdateDay(fileHeader.lastUpdateDay);
        }

        public XbFileHeaderBuilder(final byte[] rawData) {

            this(Charset.forName("ASCII"));

            final ByteBuffer buffer = ByteBuffer.wrap(rawData);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            this.setVersion(buffer.get());
            this.setLastUpdateYear(buffer.get());
            this.setLastUpdateMonth(buffer.get());
            this.setLastUpdateDay(buffer.get());
            this.setRecordsCount(buffer.getInt());
            this.setHeaderSize(buffer.getShort());
            this.setRecordSize(buffer.getShort());

            this.setCodepage(buffer.get(29));
            this.setTerminator(buffer.get(31));
            buffer.position(32);
            this.setColumns(buffer);
        }

        public XbFileHeaderBuilder setVersion(final byte version) {

            this.version = version;
            return this;
        }

        public XbFileHeaderBuilder setLastUpdateYear(final short year) {

            this.lastUpdateYear = year;
            return this;
        }

        public XbFileHeaderBuilder setLastUpdateMonth(final byte month) {

            this.lastUpdateMonth = month;
            return this;
        }

        public XbFileHeaderBuilder setLastUpdateDay(final byte day) {

            this.lastUpdateDay = day;
            return this;
        }

        public XbFileHeaderBuilder setRecordsCount(final int recordsCount) {

            this.recordsCount = recordsCount;
            return this;
        }

        public XbFileHeaderBuilder setHeaderSize(final short headerSize) {

            this.headerSize = headerSize;
            return this;
        }

        public XbFileHeaderBuilder setRecordSize(final short recordSize) {

            this.recordSize = recordSize;
            return this;
        }

        public XbFileHeaderBuilder setCodepage(final byte codePage) {

            this.codepage = codePage;
            return this;
        }

        public XbFileHeaderBuilder setTerminator(final byte terminator) {

            this.terminator = terminator;
            return this;
        }

        public XbFileHeaderBuilder setColumns(final ByteBuffer buffer) {

            // for (int idx = 0; idx < nNumFields; idx++) {
            // this.columns.add(new XbFieldInfo(buffer));
            // break;
            // }
            return this;
        }

        public XbFileHeader build() {

            return new XbFileHeader(this);
        }

    }

    static enum TransactionStatus {

    }

}
