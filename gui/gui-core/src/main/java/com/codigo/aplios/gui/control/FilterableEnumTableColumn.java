package com.codigo.aplios.gui.control;

public class FilterableEnumTableColumn<S, T>
        extends AbstractFilterableTableColumn<S, T, EnumOperator<T>, EnumFilterEditor<T>> {

    public FilterableEnumTableColumn() {

        this("", null);
    }

    public FilterableEnumTableColumn(final String text) {

        this(text, null);
    }

    public FilterableEnumTableColumn(final T[] enumValues) {

        this("", enumValues);
    }

    public FilterableEnumTableColumn(final String text, final T[] enumValues) {

        this(text, enumValues, false);
    }

    public FilterableEnumTableColumn(final String text, final T[] enumValues, final boolean selectedByDefault) {

        this(text, enumValues, false, false);
    }

    public FilterableEnumTableColumn(final String text, final T[] enumValues, final boolean selectedByDefault,
            final boolean showToggle) {

        super(text, new EnumFilterEditor<>(text, enumValues, selectedByDefault, showToggle));
    }

    public void setEnumValues(final T[] enumValues) {

        this.getFilterEditor()
                .populateMenuItems(enumValues);
    }

    /**
     * Select items in the list by default, and when you press the RESET button.
     * Calling this should not fire a filter change event.
     *
     * @param selected
     */
    public void selectByDefault(final boolean selected) {

        this.getFilterEditor()
                .selectedByDefault(selected);
        // This doesn't trigger a filter change event, so it's safe to call
        this.getFilterEditor()
                .toggleAll(selected);
    }

    /**
     * Show a check box that can be used to toggle the values of all other items in the list
     *
     * @param showToggle
     */
    public void showToggleAll(final boolean showToggle) {

        this.getFilterEditor()
                .showToggleAll(showToggle);
    }

}
