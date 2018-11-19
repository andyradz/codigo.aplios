package com.codigo.aplios.gui.control.calendar;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class FXCalendarCell {

	/**
	 * AbstractCell
	 *
	 * @author Sai.Dandem
	 *
	 */
	abstract class AbstractCell extends StackPane {

		protected Text txt;

		public AbstractCell() {

			super();
		}

		public abstract void setCellId(String id);

		public abstract void setCellWidth(double width);

		public abstract void setCellHeight(double height);

		public abstract void setCellStyle(String styleClass);

		/**
		 * @return the txt
		 */
		public Text getTxt() {

			return this.txt;
		}

		/**
		 * @param txt
		 *        the txt to set
		 */
		public void setTxt(final Text txt) {

			this.txt = txt;
		}

	}

	/**
	 * DateCell
	 *
	 * @author Sai.Dandem
	 *
	 */
	class DateCell extends AbstractCell {

		private final SimpleIntegerProperty	cellDate		= new SimpleIntegerProperty();
		private final SimpleIntegerProperty	cellMonth		= new SimpleIntegerProperty();
		private final SimpleIntegerProperty	cellYear		= new SimpleIntegerProperty();
		private boolean						previousState	= false;
		private boolean						weekNumCell		= false;

		public DateCell(final String id, final double width, final double height) {

			super();
			setCellId(id);
			setCellWidth(width - 1);
			setCellHeight(height - 1);
			getStyleClass().add("fx-calendar-basic-datecell");

			super.txt = new Text();
			this.txt.getStyleClass()
					.add("fx-calendar-datetext");
			getChildren().add(this.txt);

			setOnMouseEntered(arg0 -> {

				DateCell.this.previousState = DateCell.this.txt.isDisable();
				DateCell.this.txt.setDisable(false);
			});

			setOnMouseExited(arg0 -> DateCell.this.txt.setDisable(DateCell.this.previousState));
			// Disabling the cell and clearing the text if the date is below 01/01/01.
			cellYearProperty().addListener((InvalidationListener) paramObservable -> {

				if (getCellYear() < 1) {
					DateCell.this.txt.setText("");
					DateCell.this.setDisable(true);
				}
				else
					DateCell.this.setDisable(false);
			});
		}

		@Override
		public void setCellId(final String id) {

			super.setId(id);
		}

		@Override
		public void setCellWidth(final double width) {

			super.setPrefWidth(width);
		}

		@Override
		public void setCellHeight(final double height) {

			super.setPrefHeight(height);
		}

		@Override
		public void setCellStyle(final String styleClass) {

			getStyleClass().add(styleClass);
		}

		/**
		 * @return the cellDate
		 */
		public SimpleIntegerProperty cellDateProperty() {

			return this.cellDate;
		}

		/**
		 * @return the cellDate
		 */
		public Integer getCellDate() {

			return this.cellDate.getValue();
		}

		/**
		 * @param cellDate
		 *        the cellDate to set
		 */
		public void setCellDate(final Integer cellDate) {

			this.cellDate.set(cellDate);
		}

		/**
		 * @return the cellMonth
		 */
		public SimpleIntegerProperty cellMonthProperty() {

			return this.cellMonth;
		}

		/**
		 * @return the cellMonth
		 */
		public Integer getCellMonth() {

			return this.cellMonth.getValue();
		}

		/**
		 * @param cellMonth
		 *        the cellMonth to set
		 */
		public void setCellMonth(final Integer cellMonth) {

			this.cellMonth.set(cellMonth);
		}

		/**
		 * @return the cellYear
		 */
		public SimpleIntegerProperty cellYearProperty() {

			return this.cellYear;
		}

		/**
		 * @return the cellYear
		 */
		public Integer getCellYear() {

			return this.cellYear.getValue();
		}

		/**
		 * @param cellYear
		 *        the cellYear to set
		 */
		public void setCellYear(final Integer cellYear) {

			this.cellYear.set(cellYear);
		}

		/**
		 * @return the weekNumCell
		 */
		public boolean isWeekNumCell() {

			return this.weekNumCell;
		}

		/**
		 * @param weekNumCell
		 *        the weekNumCell to set
		 */
		public void setWeekNumCell(final boolean weekNumCell) {

			this.weekNumCell = weekNumCell;
		}

		public void setCellFocused(final boolean b) {

			super.setFocused(b);
		}

		public boolean getCellFocused() {

			return super.isFocused();
		}
	}

	/**
	 * WeekCell
	 *
	 * @author Sai.Dandem
	 *
	 */
	class WeekCell extends AbstractCell {

		public WeekCell(final String id, final String content, final double width, final double height) {

			super();
			setCellId(id);
			setCellWidth(width - 1);
			setCellHeight(height);

			super.txt = new Text(
				content);
			this.txt.getStyleClass()
					.add("fx-calendar-weektext");
			getChildren().add(this.txt);
		}

		public void setContent(final String str) {

			super.txt.setText(str);
		}

		@Override
		public void setCellId(final String id) {

			super.setId(id);
		}

		@Override
		public void setCellWidth(final double width) {

			super.setPrefWidth(width);
		}

		@Override
		public void setCellHeight(final double height) {

			super.setPrefHeight(height);
		}

		@Override
		public void setCellStyle(final String styleClass) {

			getStyleClass().add(styleClass);
		}

	}

}
