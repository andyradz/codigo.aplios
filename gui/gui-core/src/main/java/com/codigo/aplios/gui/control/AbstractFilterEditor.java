package com.codigo.aplios.gui.control;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

/**
 * This base class has default methods used to determine whether or not there
 * are filters applied to the table column this editor belongs to. It also
 * stores a reference to the {@link FilterMenuPopup} used to display filters.
 *
 * @see IFilterEditor
 *
 * @author JHS
 */
public abstract class AbstractFilterEditor<R extends IFilterOperator<?>>
        implements IFilterEditor<R> {

    private final FilterMenuPopup menu;

    private final SimpleBooleanProperty filtered;

    public AbstractFilterEditor(final String title) {

        this.menu = new FilterMenuPopup(title);
        this.filtered = new SimpleBooleanProperty(false);

        this.menu.setOnHidden(t -> AbstractFilterEditor.this.cancel());
    }

    @Override
    public FilterMenuPopup getFilterMenu() {

        return this.menu;
    }

    /**
     * Sets the content to display in the filter menu
     *
     * @param node
     */
    public void setFilterMenuContent(final Node node) {

        this.menu.setContentNode(node);
    }

    @Override
    public BooleanProperty filteredProperty() {

        return this.filtered;
    }

    @Override
    public boolean isFiltered() {

        return this.filtered.get();
    }

    /**
     * @param isFiltered
     * If there are any non-default filters applied
     */
    protected void setFiltered(final boolean isFiltered) {

        this.filtered.set(isFiltered);
    }

}
