package com.codigo.aplios.gui.control;

import java.util.ArrayList;
import java.util.EnumSet;

import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 *
 * @author JHS
 */
public class BooleanFilterEditor extends AbstractFilterEditor<BooleanOperator> {

	private BooleanOperator.Type previousType;

	private final ToggleGroup typeGroup = new ToggleGroup();

	private final BooleanOperator.Type DEFAULT_TYPE;

	public BooleanFilterEditor(final String title) {

		this(title, BooleanOperator.VALID_TYPES);
	}

	public BooleanFilterEditor(final String title, final EnumSet<BooleanOperator.Type> types) {

		this(title, types.toArray(new BooleanOperator.Type[0]));
	}

	public BooleanFilterEditor(final String title, final BooleanOperator.Type[] types) {

		super(title);

		this.DEFAULT_TYPE = BooleanOperator.Type.NONE;

		final RadioButton rbNone = new RadioButton(BooleanOperator.Type.NONE.toString());
		rbNone.setUserData(BooleanOperator.Type.NONE);
		rbNone.setToggleGroup(this.typeGroup);

		final RadioButton rbTrue = new RadioButton(BooleanOperator.Type.TRUE.toString());
		rbTrue.setUserData(BooleanOperator.Type.TRUE);
		rbTrue.setToggleGroup(this.typeGroup);

		final RadioButton rbFalse = new RadioButton(BooleanOperator.Type.FALSE.toString());
		rbFalse.setUserData(BooleanOperator.Type.FALSE);
		rbFalse.setToggleGroup(this.typeGroup);

		this.setSelectedToggle(this.DEFAULT_TYPE);

		final VBox box = new VBox();
		box.setSpacing(4);
		box.getChildren()
		    .addAll(rbNone, rbTrue, rbFalse);

		this.setFilterMenuContent(box);
	}

	@Override
	public BooleanOperator[] getFilters() throws Exception {

		final ArrayList<BooleanOperator> retList = new ArrayList<>();
		final BooleanOperator.Type selectedType = (BooleanOperator.Type) this.typeGroup.getSelectedToggle()
		    .getUserData();
		if (selectedType == BooleanOperator.Type.NONE)
			retList.add(new BooleanOperator(selectedType, null));
		else
			retList.add(new BooleanOperator(selectedType, selectedType == BooleanOperator.Type.TRUE));
		return retList.toArray(new BooleanOperator[0]);
	}

	@Override
	public void cancel() {

		this.setSelectedToggle(this.previousType);
	}

	@Override
	public boolean save() throws Exception {

		boolean changed = false;

		final BooleanOperator.Type selectedType = (BooleanOperator.Type) this.typeGroup.getSelectedToggle()
		    .getUserData();
		if (selectedType == this.DEFAULT_TYPE)
			changed = this.clear();
		else {
			changed = this.previousType != selectedType;
			this.previousType = selectedType;
			this.setFiltered(true);
		}

		return changed;
	}

	@Override
	public boolean clear() throws Exception {

		boolean changed = false;

		this.previousType = this.DEFAULT_TYPE;
		this.setSelectedToggle(this.DEFAULT_TYPE);

		if (this.isFiltered()) {
			this.setFiltered(false);
			changed = true;
		}

		return changed;
	}

	private void setSelectedToggle(final BooleanOperator.Type type) {

		for (final Toggle t : this.typeGroup.getToggles()) {
			final BooleanOperator.Type tmp = (BooleanOperator.Type) t.getUserData();
			if (type == tmp) {
				t.setSelected(true);
				break;
			}
		}
	}

}
