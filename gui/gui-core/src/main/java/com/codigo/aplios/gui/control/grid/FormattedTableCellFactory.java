package com.codigo.aplios.gui.control.grid;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author ROBT
 */
public class FormattedTableCellFactory<S, T> implements Callback<TableColumn<S, T>, TableCell<S, T>> {

	public FormattedTableCellFactory() {

	}

	@Override
	public TableCell<S, T> call(final TableColumn<S, T> p) {

		final TableCell<S, T> cell = new TableCell<>() {

			@Override
			protected void updateItem(final Object item, final boolean empty) {

				final String cssStyle = "";

				// InboundBean inboundBean = null;
				// if (this.getTableRow() != null) {
				// inboundBean = (InboundBean) this.getTableRow()
				// .getItem();
				// }

				// Remove all previously assigned CSS styles from the cell.
				// this.getStyleClass()
				// .remove(planAssignedStyle);
				// this.getStyleClass()
				// .remove(planNotAssignedStyle);
				// this.getStyleClass()
				// .remove(vfcRecoveredStyle);
				// this.getStyleClass()
				// .remove(defaultTableStyle);
				//
				// super.updateItem((T) item, empty);
				//
				// // Determine how to format the cell based on the status of the container.
				// if (inboundBean == null) {
				// cssStyle = defaultTableStyle;
				// }
				// else if (inboundBean.isRecovered()) {
				// cssStyle = vfcRecoveredStyle;
				// }
				// else if (inboundBean.getVfcPlan() != null && inboundBean.getVfcPlan()
				// .length() > 0) {
				// cssStyle = planAssignedStyle;
				// }
				// else {
				// cssStyle = planNotAssignedStyle;
				// }

				// Set the CSS style on the cell and set the cell's text.
				getStyleClass().add(cssStyle);
				if (item != null)
					setText(item.toString());
				else
					setText("");
			}
		};
		return cell;
	}
}
