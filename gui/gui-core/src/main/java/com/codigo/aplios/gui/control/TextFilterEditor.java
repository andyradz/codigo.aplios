package com.codigo.aplios.gui.control;

import java.util.ArrayList;
import java.util.EnumSet;

import com.codigo.aplios.gui.control.IFilterOperator.Type;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 *
 * @author JHS
 */
public class TextFilterEditor extends AbstractFilterEditor<StringOperator> {

	private String				previousText;
	private StringOperator.Type	previousType;

	private final TextField						textField;
	private final ComboBox<StringOperator.Type>	typeBox;

	private final String				DEFAULT_TEXT;
	private final StringOperator.Type	DEFAULT_TYPE;

	public TextFilterEditor(final String title) {

		this(title, StringOperator.VALID_TYPES);
	}

	public TextFilterEditor(final String title, final EnumSet<StringOperator.Type> types) {

		this(title, types.toArray(new StringOperator.Type[0]));
	}

	public TextFilterEditor(final String title, final StringOperator.Type[] types) {

		super(title);

		this.DEFAULT_TEXT = "";
		this.DEFAULT_TYPE = StringOperator.Type.NONE;

		this.textField = new TextField();
		this.typeBox = new ComboBox<>();

		final GridPane box = new GridPane();
		GridPane.setRowIndex(this.typeBox, 0);
		GridPane.setColumnIndex(this.typeBox, 0);
		GridPane.setRowIndex(this.textField, 1);
		GridPane.setColumnIndex(this.textField, 0);
		GridPane.setMargin(this.typeBox, new Insets(4, 0, 0, 0));
		GridPane.setMargin(this.textField, new Insets(4, 0, 0, 0));
		final ColumnConstraints boxConstraint = new ColumnConstraints();
		boxConstraint.setPercentWidth(100);
		box.getColumnConstraints()
		    .addAll(boxConstraint);
		box.getChildren()
		    .addAll(this.typeBox, this.textField);

		this.setFilterMenuContent(box);

		this.previousText = this.DEFAULT_TEXT;
		this.previousType = this.DEFAULT_TYPE;

		this.typeBox.getSelectionModel()
		    .select(this.DEFAULT_TYPE);
		this.typeBox.setMaxWidth(Double.MAX_VALUE);
		this.typeBox.getItems()
		    .addAll(types);
		this.typeBox.getSelectionModel()
		    .selectedItemProperty()
		    .addListener((ChangeListener<Type>) (ov, old, newVal) -> TextFilterEditor.this.textField
		        .setDisable(newVal == StringOperator.Type.NONE));

		this.textField.setDisable(true);
	}

	@Override
	public StringOperator[] getFilters() throws Exception {

		final ArrayList<StringOperator> retList = new ArrayList<>();

		final String text = this.textField.getText();
		final StringOperator.Type selectedType = this.typeBox.getSelectionModel()
		    .getSelectedItem();
		if (selectedType == StringOperator.Type.NONE)
			retList.add(new StringOperator(selectedType, ""));
		else if (text.isEmpty())
			throw new Exception("Filter text cannot be empty");
		else
			retList.add(new StringOperator(selectedType, text));
		return retList.toArray(new StringOperator[0]);
	}

	@Override
	public void cancel() {

		this.textField.setText(this.previousText);
		this.typeBox.getSelectionModel()
		    .select(this.previousType);
	}

	@Override
	public boolean save() throws Exception {

		boolean changed = false;

		final StringOperator.Type selectedType = this.typeBox.getSelectionModel()
		    .getSelectedItem();
		if (selectedType == this.DEFAULT_TYPE)
			changed = this.clear();
		else {
			changed = (this.previousType != this.typeBox.getSelectionModel()
			    .getSelectedItem())
			        || ((this.typeBox.getSelectionModel()
			            .getSelectedItem() != StringOperator.Type.NONE)
			                && (this.previousText.equals(this.textField.getText()) == false));

			this.previousText = this.textField.getText();
			this.previousType = this.typeBox.getSelectionModel()
			    .getSelectedItem();
			this.setFiltered(true);
			// changed = true;
		}

		return changed;
	}

	@Override
	public boolean clear() throws Exception {

		boolean changed = false;

		this.previousText = this.DEFAULT_TEXT;
		this.previousType = this.DEFAULT_TYPE;

		this.textField.setText(this.DEFAULT_TEXT);
		this.typeBox.getSelectionModel()
		    .select(this.DEFAULT_TYPE);

		if (this.isFiltered()) {
			this.setFiltered(false);
			changed = true;
		}

		return changed;
	}

}
