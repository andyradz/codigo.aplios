package com.codigo.aplios.xbase.core.database.structure.value;

import com.codigo.aplios.xbase.core.database.structure.column.XbDataColumn;

/**
 *
 * @author andrzej.radziszewski
 */
abstract class XbAbstractValue
        implements IXbFieldValue {

    private final byte[] rawValue;

    protected XbAbstractValue(final XbDataColumn fieldInfo) {

        this(fieldInfo,
                new byte[]{});
    }

    protected XbAbstractValue(final XbDataColumn fieldInfo, final byte[] rawValue) {

        this.rawValue = rawValue;
    }

    @Override
    public byte[] getValue() {

        return this.rawValue;
    }

}
