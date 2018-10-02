package com.codigo.aplios.gui.control;

import com.codigo.aplios.gui.control.IFilterOperator.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class DateFilterEditor
        extends AbstractFilterEditor<DateOperator> {

    private final Picker picker1;

    private final Picker picker2;

    public DateFilterEditor(final String title) {

        this(title, "yyyy-MM-dd HH:mm", DateOperator.VALID_TYPES);
    }

    public DateFilterEditor(final String title, final String dateFormat) {

        this(title, dateFormat, DateOperator.VALID_TYPES);
    }

    public DateFilterEditor(final String title, final DateOperator.Type[] types) {

        this(title, "yyyy-MM-dd HH:mm", types);
    }

    public DateFilterEditor(final String title, final String dateFormat, final EnumSet<DateOperator.Type> types) {

        this(title, dateFormat, types.toArray(new DateOperator.Type[0]));
    }

    public DateFilterEditor(final String title, final String dateFormat, final DateOperator.Type[] types) {

        super(title);

        final List<DateOperator.Type> set1 = new ArrayList<>(20);
        final List<DateOperator.Type> set2 = new ArrayList<>(20);
        this.parseTypes(types, set1, set2);

        this.picker1 = new Picker(dateFormat, set1.toArray(new DateOperator.Type[0]));
        this.picker2 = new Picker(dateFormat, set2.toArray(new DateOperator.Type[0]));

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
                        DateFilterEditor.this.picker2.setEnabled(newVal.equals(DateOperator.Type.BEFORE) ||
                                newVal.equals(DateOperator.Type.BEFOREON) || newVal.equals(DateOperator.Type.AFTER) ||
                                newVal.equals(DateOperator.Type.AFTERON)));
    }

    private void parseTypes(final DateOperator.Type[] types, final List<DateOperator.Type> set1,
            final List<DateOperator.Type> set2) {

        set1.add(DateOperator.Type.NONE);
        set2.add(DateOperator.Type.NONE);
        for (final DateOperator.Type type : types) {
            // Only these range types should show up in 2nd picker
            if (type.equals(DateOperator.Type.BEFORE) ||
                    type.equals(DateOperator.Type.BEFOREON) || type.equals(DateOperator.Type.AFTER) ||
                    type.equals(DateOperator.Type.AFTERON))
                if (!set2.contains(type))
                    set2.add(type);

            if (!set1.contains(type))
                set1.add(type);
        }
    }

    @Override
    public DateOperator[] getFilters() throws Exception {

        final DateOperator val1 = this.picker1.getFilter();
        final DateOperator val2 = this.picker2.getFilter();
        final Date d1 = val1.getValue();
        final Date d2 = val2.getValue();

        // Bounds check the dates
        if ((d1 != null) &&
                (d2 != null) && (d1.after(d2) || d1.equals(d2)) &&
                (((DateOperator.Type.AFTER == val1.getType()) && (DateOperator.Type.BEFORE == val2.getType())) ||
                ((DateOperator.Type.AFTER == val1.getType()) &&
                (DateOperator.Type.BEFOREON == val2.getType())) ||
                ((DateOperator.Type.AFTERON == val1.getType()) &&
                (DateOperator.Type.BEFORE == val2.getType())) ||
                ((DateOperator.Type.AFTERON == val1.getType()) &&
                (DateOperator.Type.BEFOREON == val2.getType()))))
            throw new Exception("Second date cannot be before or the same as the first date");

        return new DateOperator[]{val1, val2};
    }

    @Override
    public void cancel() {

        this.picker1.cancel();
        this.picker2.cancel();
    }

    @Override
    public boolean save() throws Exception {

        boolean changed = false;

        final DateOperator do1 = this.picker1.getFilter();
        final DateOperator do2 = this.picker2.getFilter();

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

        private final Date DEFAULT_DATE = null;

        private final DateOperator.Type DEFAULT_TYPE = DateOperator.Type.NONE;

        private Date previousDate = this.DEFAULT_DATE;

        private DateOperator.Type previousType = this.DEFAULT_TYPE;

        private final GridPane box = new GridPane();

        private final DatePicker datePicker;

        private final ComboBox<DateOperator.Type> typeBox;

        private Picker(final String dateFormat, final DateOperator.Type[] choices) {

            this.datePicker = new DatePicker();
            this.datePicker.setSelectedDate(this.DEFAULT_DATE);
            this.datePicker.setDateFormat(new SimpleDateFormat(dateFormat));

            this.typeBox = new ComboBox<>();
            this.typeBox.setMaxWidth(Double.MAX_VALUE);
            this.typeBox.getSelectionModel()
                    .selectedItemProperty()
                    .addListener((ChangeListener<Type>)(ov, old, newVal) -> Picker.this.datePicker
                            .setDisable(newVal == DateOperator.Type.NONE));
            this.typeBox.getSelectionModel()
                    .select(this.DEFAULT_TYPE);
            this.typeBox.getItems()
                    .addAll(choices);

            GridPane.setRowIndex(this.typeBox, 0);
            GridPane.setColumnIndex(this.typeBox, 0);
            GridPane.setMargin(this.typeBox, new Insets(4, 0, 0, 0));
            GridPane.setRowIndex(this.datePicker, 1);
            GridPane.setColumnIndex(this.datePicker, 0);
            GridPane.setMargin(this.datePicker, new Insets(4, 0, 0, 0));
            final ColumnConstraints boxConstraint = new ColumnConstraints();
            boxConstraint.setPercentWidth(100);
            this.box.getColumnConstraints()
                    .addAll(boxConstraint);
            this.box.getChildren()
                    .addAll(this.typeBox, this.datePicker);

            DateFilterEditor.this.setFilterMenuContent(this.box);
        }

        public void setEnabled(final boolean enable) {

            this.typeBox.setDisable(!enable);
            this.datePicker.setDisable(!enable ||
                    (this.typeBox.getSelectionModel()
                            .getSelectedItem() == DateOperator.Type.NONE));
        }

        public void cancel() {

            this.datePicker.setSelectedDate(this.previousDate);
            this.typeBox.getSelectionModel()
                    .select(this.previousType);
        }

        public void clear() {

            this.previousDate = this.DEFAULT_DATE;
            this.previousType = this.DEFAULT_TYPE;

            this.datePicker.setSelectedDate(this.DEFAULT_DATE);
            this.typeBox.getSelectionModel()
                    .select(this.DEFAULT_TYPE);
        }

        public boolean save() {

            final boolean changed = (this.previousType != this.typeBox.getSelectionModel()
                    .getSelectedItem()) ||
                    ((this.typeBox.getSelectionModel()
                            .getSelectedItem() != DateOperator.Type.NONE) &&
                    (this.previousDate.equals(this.datePicker.getSelectedDate()) == false));

            this.previousDate = this.datePicker.getSelectedDate();
            this.previousType = this.typeBox.getSelectionModel()
                    .getSelectedItem();
            return changed;
        }

        public DateOperator getFilter() throws Exception {

            final Date date = this.datePicker.getSelectedDate();
            final DateOperator.Type selectedType = this.typeBox.getSelectionModel()
                    .getSelectedItem();
            if (this.typeBox.isDisable() || (selectedType == DateOperator.Type.NONE))
                return new DateOperator(DateOperator.Type.NONE, this.DEFAULT_DATE);
            else if (date == null)
                throw new Exception("Filter text cannot be empty");
            else
                return new DateOperator(selectedType, date);
        }

    };

}
