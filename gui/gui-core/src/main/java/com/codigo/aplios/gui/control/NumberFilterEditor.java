package com.codigo.aplios.gui.control;

import com.codigo.aplios.gui.control.IFilterOperator.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author JHS
 */
public class NumberFilterEditor<T extends Number>
        extends AbstractFilterEditor<NumberOperator<T>> {

    private final Class<T> klass;

    private final NumberFilterEditor<T>.Picker picker1;

    private final NumberFilterEditor<T>.Picker picker2;

    public NumberFilterEditor(final String title, final Class<T> klass) {

        this(title, klass, NumberOperator.VALID_TYPES);
    }

    public NumberFilterEditor(final String title, final Class<T> klass, final EnumSet<NumberOperator.Type> types) {

        this(title, klass, types.toArray(new NumberOperator.Type[0]));
    }

    public NumberFilterEditor(final String title, final Class<T> klass, final NumberOperator.Type[] types) {

        super(title);
        this.klass = klass;

        final List<NumberOperator.Type> set1 = new ArrayList<>(20);
        final List<NumberOperator.Type> set2 = new ArrayList<>(20);
        this.parseTypes(types, set1, set2);

        this.picker1 = new Picker(set1.toArray(new NumberOperator.Type[0]));
        this.picker2 = new Picker(set2.toArray(new NumberOperator.Type[0]));

        final VBox box = new VBox();
        box.getChildren()
                .addAll(this.picker1.box, this.picker2.box);
        this.setFilterMenuContent(box);

        // Disable the 2nd picker if the 1st picker isn't of a range
        this.picker2.setEnabled(false);
        this.picker1.typeBox.getSelectionModel()
                .selectedItemProperty()
                .addListener((ChangeListener<Type>)(ov, old,
                                                    newVal) ->
                        NumberFilterEditor.this.picker2.setEnabled(newVal.equals(NumberOperator.Type.GREATERTHAN) ||
                                newVal.equals(NumberOperator.Type.GREATERTHANEQUALS) ||
                                newVal.equals(NumberOperator.Type.LESSTHAN) ||
                                newVal.equals(NumberOperator.Type.LESSTHANEQUALS)));
    }

    private void parseTypes(final NumberOperator.Type[] types, final List<NumberOperator.Type> set1,
            final List<NumberOperator.Type> set2) {

        set1.add(NumberOperator.Type.NONE);
        set2.add(NumberOperator.Type.NONE);
        for (final NumberOperator.Type type : types) {
            // Only these range types should show up in 2nd picker
            if (type.equals(NumberOperator.Type.GREATERTHAN) ||
                    type.equals(NumberOperator.Type.GREATERTHANEQUALS) || type.equals(NumberOperator.Type.LESSTHAN) ||
                    type.equals(NumberOperator.Type.LESSTHANEQUALS))
                if (!set2.contains(type))
                    set2.add(type);

            if (!set1.contains(type))
                set1.add(type);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public NumberOperator<T>[] getFilters() throws Exception {

        final NumberOperator<T> val1 = this.picker1.getFilter();
        final NumberOperator<T> val2 = this.picker2.getFilter();

        // TODO: if the Types are ranges, we should probably check that they're within
        // the proper bounds. Need a separate check for each <T> though
        return new NumberOperator[]{val1, val2};
    }

    @Override
    public void cancel() {

        this.picker1.cancel();
        this.picker2.cancel();
    }

    @Override
    public boolean save() throws Exception {

        boolean changed = false;

        final NumberOperator<T> do1 = this.picker1.getFilter();
        final NumberOperator<T> do2 = this.picker2.getFilter();

        if ((do1.getType() == this.picker1.DEFAULT_TYPE) && (do2.getType() == this.picker2.DEFAULT_TYPE))
            changed = this.clear();
        else {
            final boolean changed1 = this.picker1.save();
            final boolean changed2 = this.picker2.save();
            this.setFiltered(true);
            changed = changed1 || changed2;
        }

        return changed;
    }

    @Override
    public boolean clear() throws Exception {

        boolean changed = false;

        this.picker1.clear();
        this.picker2.clear();

        if (this.isFiltered()) {
            this.setFiltered(false);
            changed = true;
        }

        return changed;
    }

    /**
     * Separate code out so we can reuse it for multiple Date picker groups
     */
    private class Picker {

        private final String DEFAULT_TEXT = "";

        private final NumberOperator.Type DEFAULT_TYPE = NumberOperator.Type.NONE;

        private String previousText = this.DEFAULT_TEXT;

        private NumberOperator.Type previousType = this.DEFAULT_TYPE;

        final GridPane box = new GridPane();

        private final TextField textField;

        private final ComboBox<NumberOperator.Type> typeBox;

        private Picker(final NumberOperator.Type[] choices) {

            this.textField = new TextField(this.DEFAULT_TEXT);

            this.typeBox = new ComboBox<>();
            this.typeBox.setMaxWidth(Double.MAX_VALUE);
            this.typeBox.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((ChangeListener<Type>)(ov, old, newVal) -> Picker.this.textField
                            .setDisable(newVal == NumberOperator.Type.NONE));
            this.typeBox.getSelectionModel()
                    .select(this.DEFAULT_TYPE);
            this.typeBox.getItems()
                    .addAll(choices);

            GridPane.setRowIndex(this.typeBox, 0);
            GridPane.setColumnIndex(this.typeBox, 0);
            GridPane.setMargin(this.typeBox, new Insets(4, 0, 0, 0));
            GridPane.setRowIndex(this.textField, 1);
            GridPane.setColumnIndex(this.textField, 0);
            GridPane.setMargin(this.textField, new Insets(4, 0, 0, 0));
            final ColumnConstraints boxConstraint = new ColumnConstraints();
            boxConstraint.setPercentWidth(100);
            this.box.getColumnConstraints()
                    .addAll(boxConstraint);
            this.box.getChildren()
                    .addAll(this.typeBox, this.textField);

            NumberFilterEditor.this.setFilterMenuContent(this.box);
        }

        public void setEnabled(final boolean enable) {

            this.typeBox.setDisable(!enable);
            this.textField.setDisable(!enable ||
                    (this.typeBox.getSelectionModel()
                            .getSelectedItem() == NumberOperator.Type.NONE));
        }

        public void cancel() {

            this.textField.setText(this.previousText);
            this.typeBox.getSelectionModel()
                    .select(this.previousType);
        }

        public void clear() {

            this.previousText = this.DEFAULT_TEXT;
            this.previousType = this.DEFAULT_TYPE;

            this.textField.setText(this.DEFAULT_TEXT);
            this.typeBox.getSelectionModel()
                    .select(this.DEFAULT_TYPE);
        }

        public boolean save() {

            final boolean changed = (this.previousType != this.typeBox.getSelectionModel()
                    .getSelectedItem()) ||
                    ((this.typeBox.getSelectionModel()
                            .getSelectedItem() != NumberOperator.Type.NONE) &&
                    (this.previousText.equals(this.textField.getText()) == false));

            this.previousText = this.textField.getText();
            this.previousType = this.typeBox.getSelectionModel()
                    .getSelectedItem();

            return changed;
        }

        @SuppressWarnings({"rawtypes", "unchecked"})
        public NumberOperator<T> getFilter() throws Exception {

            final String text = this.textField.getText();
            final NumberOperator.Type selectedType = this.typeBox.getSelectionModel()
                    .getSelectedItem();

            if (this.typeBox.isDisable() || (selectedType == NumberOperator.Type.NONE))
                return new NumberOperator(NumberOperator.Type.NONE, 0);
            else if (text.isEmpty())
                throw new Exception("Filter text cannot be empty");
            else {
                Number number;
                if (NumberFilterEditor.this.klass == BigInteger.class)
                    number = new BigInteger(text);
                else if (NumberFilterEditor.this.klass == BigDecimal.class)
                    number = new BigDecimal(text);
                else if (NumberFilterEditor.this.klass == Byte.class)
                    number = Byte.parseByte(text);
                else if (NumberFilterEditor.this.klass == Short.class)
                    number = Short.parseShort(text);
                else if (NumberFilterEditor.this.klass == Integer.class)
                    number = Integer.parseInt(text);
                else if (NumberFilterEditor.this.klass == Long.class)
                    number = Long.parseLong(text);
                else if (NumberFilterEditor.this.klass == Float.class)
                    number = Float.parseFloat(text);
                else
                    number = Double.parseDouble(text);

                return new NumberOperator(selectedType, number);
            }
        }

    };

}
