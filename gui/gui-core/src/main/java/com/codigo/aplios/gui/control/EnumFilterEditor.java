package com.codigo.aplios.gui.control;

import java.util.ArrayList;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;

/**
 *
 * @author JHS
 * @param <T>
 */
public class EnumFilterEditor<T>
        extends AbstractFilterEditor<EnumOperator<T>> {

    private static final String TOGGLE_ALL = "(Toggle All)";

    private final CheckBox toggleAllChbx = new CheckBox(EnumFilterEditor.TOGGLE_ALL);

    private boolean selectedByDefault;

    private boolean showToggle;

    private boolean[] previousSelections;

    private final ObservableList<CheckBox> enumCombos;

    public EnumFilterEditor(final String title, final T[] values) {

        this(title, values, false);
    }

    public EnumFilterEditor(final String title, final T[] values, final boolean selectedByDefault) {

        this(title, values, false, false);
    }

    public EnumFilterEditor(final String title, final T[] values, final boolean selectedByDefault,
            final boolean showToggle) {

        super(title);
        this.selectedByDefault = selectedByDefault;
        this.enumCombos = FXCollections.observableArrayList();
        this.showToggle = showToggle;
        this.populateMenuItems(values);
    }

    final public void populateMenuItems(final T[] values) {

        final int len = values == null ? 0 : values.length;
        this.previousSelections = new boolean[len + 1]; // add 1 for Toggle All chbx

        this.enumCombos.clear();

        if (values != null) {
            // Create a checkbox to toggle selection of the rest
            this.toggleAllChbx.setSelected(this.selectedByDefault);
            this.toggleAllChbx.setOnMouseClicked(t -> {
                EnumFilterEditor.this.toggleAll(EnumFilterEditor.this.toggleAllChbx.isSelected());
                t.consume();
            });

            this.showToggleAll(this.showToggle);

            // Create a property that will update toggleAllChbx when items get (de)selected
            final SimpleIntegerProperty itemsChecked = new SimpleIntegerProperty();
            itemsChecked.addListener((ChangeListener<Number>)(observable, oldValue, newValue) -> {
                final int itemsSel = newValue.intValue();
                if (itemsSel == 0) {
                    EnumFilterEditor.this.toggleAllChbx.setSelected(false);
                    EnumFilterEditor.this.toggleAllChbx.setIndeterminate(false);
                } else if (itemsSel == (EnumFilterEditor.this.enumCombos.size() - 1)) {
                    EnumFilterEditor.this.toggleAllChbx.setSelected(true);
                    EnumFilterEditor.this.toggleAllChbx.setIndeterminate(false);
                } else
                    EnumFilterEditor.this.toggleAllChbx.setIndeterminate(true);
            });

            // Populate checkboxes with the values; set default toggle state
            for (final T value : values) {
                final CheckBox ecb = new CheckBox(value.toString());
                ecb.selectedProperty()
                        .addListener((ChangeListener<Boolean>)(observable, oldValue, newValue) ->
                        {
                            final int currVal = itemsChecked.getValue();
                            itemsChecked.set(ecb.isSelected() ? currVal + 1 : currVal - 1);
                        });
                ecb.setUserData(value);
                ecb.setSelected(this.selectedByDefault);
                this.enumCombos.add(ecb);
            }
        }

        final ListView<CheckBox> list = new ListView<>(this.enumCombos);
        list.setEditable(false);
        list.setMaxHeight(215);
        list.setMaxWidth(400);
        list.setPrefWidth(200);
        list.setPrefHeight(25 * Math.max(this.enumCombos.size(), 2));
        list.getItems()
                .addListener((ListChangeListener<Control>)c ->
                {
                    final int items = c.getList()
                            .size();
                    list.setPrefHeight(25 * Math.max(items, 2));
                });
        // We don't allow edit mode, so we can let escape key events bubble to the popup
        list.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE)
                list.getScene()
                        .getWindow()
                        .hide();
        });
        // Checkbox doesn't fill the entire cell. Change selection on clicks outside the
        // checkbox
        list.setOnMouseClicked(event -> {
            final CheckBox cb = list.getSelectionModel()
                    .getSelectedItem();
            if (cb != null) {
                cb.setSelected(!cb.isSelected());
                cb.requestFocus();
            }
        });
        this.setFilterMenuContent(list);
    }

    @SuppressWarnings("unchecked")
    @Override
    public EnumOperator<T>[] getFilters() throws Exception {

        final ArrayList<EnumOperator<?>> retList = new ArrayList<>();

        for (final CheckBox emt : this.enumCombos)
            if ((emt != this.toggleAllChbx) && emt.isSelected())
                retList.add(new EnumOperator<>(EnumOperator.Type.EQUALS, emt.getUserData()));

        if (retList.isEmpty())
            retList.add(new EnumOperator<>(EnumOperator.Type.NONE, null));

        return retList.toArray(new EnumOperator[0]);
    }

    @Override
    public void cancel() {

        int i = 0;
        for (final CheckBox emu : this.enumCombos)
            emu.setSelected(this.previousSelections[i++]);
    }

    @Override
    public boolean save() throws Exception {

        boolean changed = false;

        // Determine if there are any changes
        boolean noSelections = true;
        selectionCheck:
        for (int i = 0; i < this.enumCombos.size(); i++) {
            final CheckBox emu = this.enumCombos.get(i);
            if (emu.isSelected() != this.selectedByDefault) {
                noSelections = false;
                break selectionCheck;
            }
        }

        if (noSelections)
            changed = this.clear();
        else {
            this.setFiltered(true);

            // Determine if anything's changed
            changedCheck:
            for (int i = 0; i < this.enumCombos.size(); i++) {
                final CheckBox emu = this.enumCombos.get(i);
                if (this.previousSelections[i] != emu.isSelected()) {
                    changed = true;
                    break changedCheck;
                }
            }

            // Save to previousSelection
            for (int i = 0; i < this.enumCombos.size(); i++) {
                final CheckBox emu = this.enumCombos.get(i);
                this.previousSelections[i] = emu.isSelected();
            }
        }

        return changed;
    }

    @Override
    public boolean clear() throws Exception {

        boolean changed = false;

        for (int i = 0; i < this.previousSelections.length; i++)
            this.previousSelections[i] = this.selectedByDefault;
        for (final CheckBox emu : this.enumCombos)
            emu.setSelected(this.selectedByDefault);

        if (this.isFiltered()) {
            this.setFiltered(false);
            changed = true;
        }

        return changed;
    }

    public void selectedByDefault(final boolean selected) {

        this.selectedByDefault = selected;
    }

    public void toggleAll(final boolean selected) {

        for (int i = 0; i < this.enumCombos.size(); i++) {
            final CheckBox ecb = this.enumCombos.get(i);
            this.previousSelections[i] = ecb.isSelected();
            ecb.setSelected(selected);
        }
    }

    public void showToggleAll(final boolean showToggle) {

        if (showToggle) {
            if (this.enumCombos.isEmpty() || (this.enumCombos.get(0) != this.toggleAllChbx))
                this.enumCombos.add(0, this.toggleAllChbx);
        } else if (!this.enumCombos.isEmpty() && (this.enumCombos.get(0) == this.toggleAllChbx))
            this.enumCombos.remove(0);
    }

}
