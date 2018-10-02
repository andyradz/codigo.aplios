package com.codigo.aplios.xbase.core.database.provider;

public class XbLimitations {

    // https://vivaclipper.wordpress.com/tag/max-dbf-file-size/
    // dBase IV limitations
    //
    // Last updated: 15 Nov 2003 by XpertSS.com
    //
    // Approach can use a number of different database file structures, but uses dBaseIV by default. dBaseIV has the
    // following limitations that you might need to be aware of:
    //
    // maximum file size = 2 gigabytes (per type .dbf or .dbt file)
    // maximum records per database = 1,000,000,000 (1 billion)
    // maximum of 30 open dBaseIV databases on a single computer
    // maximum number of fields per record = 255
    // maximum size of a record = 4000 characters (= all the field-lengths added together)
    // - Text field size equals the size defined in characters
    // - Date field uses 8 characters stored as text YYYYMMDD
    // - Time field uses 8 characters stored as text HHMMSSHH
    // - Boolean field uses 1 character (T, F or blank is stored)
    // - Memo or PicturePlus field uses 10 characters stored as a "record number" in the .DBT file
    // - Numeric field uses characters equal to the size definition plus 1. (Numeric 10.2 stores in 13 characters, 5.3
    // stores in 9 characters.
    //
    // maximum length of a text field = 254 characters
    // maximum number of characters in a dBaseIV memo field is 30,000 characters in releases prior to V9.8, which was
    // fixed in that release to allow up to 65,536 (64KB).
    // Some common error messages when using dBaseIV files:
    // -1003 when adding a field makes the record too long
    // -1035 when the .DBT file has reached the 2 gigabyte limit and no more records can be added
    // Information about field names:
    // Approach Field Definition allows up to 32 characters for field names, and it allows alphabetic letters, whole
    // numbers, spaces, commas, periods, and arithmetic signs. If you delete the SmartIndex file (.ADX):
    // Your field names are truncated to the dBaseIV standard of 10 characters, all capitals, with no blanks or special
    // characters other than the underscore.
    // Non-allowed characters and blanks will be replaced with the underscore.
    // A "Time" field name will always end with the characters "TM9" so that Approach will know it is a time field.
    // PicturePlus field names always end with the characters "PC9" so that Approach will know it is a PicturePlus
    // field.
    // dBaseIV Field names must be unique, so if you have field names that are the same in the first 10 characters, you
    // will see a series of similar field names which may be difficult to work with. For example, "Customer Name",
    // "Customer Number", "Customer Notice" will become "CUSTOMER_N", "CUSTOMER_2" and "CUSTOMER_3".
    // Information about sharing dBaseIV files:
    // Approach does not create or maintain the native dBaseIV index file for a database. This is important if you plan
    // to also use the database in an application that depends on the presence of this index. Approach PicturePlus
    // fields cannot be viewed in other dBASE application
}
