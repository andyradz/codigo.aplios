package com.codigo.aplios.gui.control;

/**
 * You probably shouldn't ever extend this class. It's only meant to be used by
 * certain Java built-in classes which extend {@link Number}, almost all of
 * which I've created sub-classes for.
 *
 * @author JHS
 */
public class AbstractFilterableNumberTableColumn<S, T extends Number>
        extends AbstractFilterableTableColumn<S, T, NumberOperator<T>, NumberFilterEditor<T>> {

    public AbstractFilterableNumberTableColumn(final Class<T> klass) {

        this("", klass);
    }

    public AbstractFilterableNumberTableColumn(final String text, final Class<T> klass) {

        super(text, new NumberFilterEditor<>(text, klass));
    }

}
