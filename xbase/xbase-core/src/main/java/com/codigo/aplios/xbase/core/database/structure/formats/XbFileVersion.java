package com.codigo.aplios.xbase.core.database.structure.formats;

import com.codigo.aplios.xbase.core.XbCodePages;
import com.codigo.aplios.xbase.core.database.structure.column.XbFieldType;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 0x63 dBASE IV SQL system files, no memo 0x83 FoxBASE+/dBASE III PLUS, with memo 0x8B dBASE IV with memo 0xCB dBASE IV
 * SQL table files, with memo 0xF5 FoxPro 2.x (or earlier) with memo 0xFB FoxBASE
 */
enum XbFileVersion {

    /**
     * 0x02 FoxBASE
     */
    XBASE2(0x02),
    /**
     * 0x03 FoxBASE+/Dbase III plus, no memo FoxBASE+/dBASE III PLUS, with memo
     */
    XBASE3(0x03, 0x83),
    /**
     * 0x30 Visual FoxPro 0x31 Visual FoxPro, autoincrement enabled 0x32 Visual FoxPro, Varchar, Varbinary, or
     * Blob-enabled 0xF5 - FoxPro 2.x (or earlier) with memo
     */
    FOXPRO(0x30, 0x31, 0x32, 0xF5),
    /**
     * 0x43 dBASE IV SQL table files, no memo 0x63 dBASE IV SQL system files, no memo 0x8B dBASE IV with memo 0xCB dBASE
     * IV SQL table files, with memo
     */
    XBASE4(0x43, 0x63, 0x8B, 0xCB),
    HIPERSIX(0xE5),
    FOXBASE(0xFB);

    // DBASE_5,
    // CLIPPER_5,
    // FOXPRO_26;
    /**
     * Atrybut wskazuje wersje formatu XBase dla różnych wydań. Jedna wersja może miec kilka wydań różniących isę
     * obsługa formatu danych.
     */
    private final int[] versionNumbers;

    /**
     * Podstawowy konstruktor klasy.
     *
     * @param versionCodes
     *                     Numery kodowe wersji zbioru XBase
     */
    XbFileVersion(final int... versionCodes) {

        this.versionNumbers = versionCodes;
    }

    public static Optional<XbFileVersion> ofVersionCode(final int versionCode) {

        return Stream.of(XbFileVersion.values())
                .filter(item -> Arrays.stream(item.versionNumbers)
                        .filter(i -> Integer.valueOf(i)
                                .equals(versionCode))
                        .count() == 1)
                .findAny();
    }

    private static final EnumMap<XbFileVersion, XbFieldType> fieldEntities = new EnumMap<>(
            XbFileVersion.class);

    private static final EnumMap<XbFileVersion, EnumSet<XbCodePages>> charsetEntities = new EnumMap<>(
            XbFileVersion.class);
    // DataRow limits
    // TableLimits
    // ValueLimits

    // UNIX STREAM https://github.com/benas/unix-stream
    static {
        XbFileVersion.charsetEntities.put(XBASE3, EnumSet.allOf(XbCodePages.class));
        XbFileVersion.charsetEntities.put(XBASE4, EnumSet.of(XbCodePages.MACROMAN));
        XbFileVersion.charsetEntities.put(XbFileVersion.FOXPRO, EnumSet.allOf(XbCodePages.class));

        // XbFileVersion.fieldEntities.put(XbFileVersion.XBASE3, XbFieldType.map(XbFieldDataType.CHARACTER)
        // .setCode('C')
        // .setMaxLength(65534)
        // .setMinLength(1)
        // .setMaxSize(100)
        // .build());
        //
        // XbFileVersion.fieldEntities.put(XbFileVersion.XBASE4, XbFieldType.map(XbFieldDataType.CHARACTER)
        //
        // .setCode('C')
        // .setMaxLength(65534)
        // .setMinLength(1)
        // .setMaxSize(100)
        // .build());
        //
        // XbFileVersion.fieldEntities.put(XbFileVersion.XBASE3, XbFieldType.map(XbFieldDataType.NUMERIC)
        // .setCode('N')
        // .setMaxLength(32)
        // .setMinLength(1)
        // // .setMaxDecimalPlaces(2)
        // // .setMinDecimalPlaces(0)
        // .build());
        //
        // XbFileVersion.fieldEntities.put(XbFileVersion.XBASE3, XbFieldType.map(XbFieldDataType.DATE)// date
        // .setCode('D')
        // .setMaxLength(8)
        // .setMinLength(8)
        // .build());
        //
        // XbFileVersion.fieldEntities.put(XbFileVersion.XBASE3, XbFieldType.map(XbFieldDataType.LOGICAL)// logical
        // .setCode('L')
        // .setMaxSize(1)
        // .setMinSize(1)
        // .setMaxLength(1)
        // .setMinLength(1)
        // .setAllowValues('T', 't', 'Y', 'y', '1', 'F', 'f', 'N', 'n', '0')
        // .build());
    }

    public static List<XbFieldType> typesFor1(final XbFileVersion version) {

        return XbFileVersion.fieldEntities.entrySet()
                .stream()
                .filter(item -> item.getKey()
                        .equals(version))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public static void main(final String[] args) {
        // final Object ff = XbFileVersion.fieldEntities.entrySet();
        // System.out.println(ff);

        final XbFileVersion version = XbFileVersion.ofVersionCode(1)
                .orElse(XBASE3);
        XbFileVersion.typesFor1(version);
    }

}
