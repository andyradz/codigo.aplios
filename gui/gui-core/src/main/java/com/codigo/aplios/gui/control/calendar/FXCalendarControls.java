package com.codigo.aplios.gui.control.calendar;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class FXCalendarControls {

	/**
	 * Arrow Control
	 *
	 * @author Sai.Dandem
	 *
	 */
	class Arrow extends StackPane {
		private final SimpleObjectProperty<Color> fillColor = new SimpleObjectProperty<>();

		public Arrow() {

			this(Side.BOTTOM);
		}

		public Arrow(final Side side) {

			getStyleClass().add("fx-calendar-arrow");
			setFillColor(Color.WHITE);
			setScaleX(1.2);
			setScaleY(1.2);
			switch (side) {
			case LEFT:
				setRotate(90);
				break;
			case TOP:
				setRotate(180);
				break;
			case RIGHT:
				setRotate(270);
				break;
			default:
				setRotate(0);
			}
		}

		/**
		 * @return the fillColor object
		 */
		public SimpleObjectProperty<Color> fillColorProperty() {

			return this.fillColor;
		}

		/**
		 * @return the fillColor
		 */
		public Color getFillColor() {

			return this.fillColor.get();
		}

		/**
		 * @param fillColor
		 *        the fillColor to set
		 */
		public void setFillColor(final Color fillColor) {

			this.fillColor.set(fillColor);
			setStyle("-fx-background-color: " + FXCalendarUtility.rgbToHex(fillColor) + ";");
		}

	}

	/**
	 * BaseNavigatorArrowButton
	 *
	 * @author Sai.Dandem
	 *
	 */
	class BaseNavigatorArrowButton extends Group {

		public BaseNavigatorArrowButton(final Side side, final Color baseColor) {

			final StackPane sp = new StackPane();
			FXCalendarUtility.setBaseColorToNode(this, baseColor);
			sp.setPrefHeight(16);
			sp.setPrefWidth(16);

			final Rectangle rect = new Rectangle(
				15, 15);
			FXCalendarUtility.setBaseColorToNode(rect, baseColor);
			rect.getStyleClass()
					.add("fx-calendar-navigator-btn");
			Arrow arrow;
			final Group gp = new Group();
			switch (side) {
			case LEFT:
				arrow = new Arrow(
					Side.LEFT);
				gp.setTranslateX(-2);
				break;
			default:
				arrow = new Arrow(
					Side.RIGHT);
				gp.setTranslateX(2);
			}

			gp.getChildren()
					.add(arrow);
			sp.getChildren()
					.addAll(rect, gp);
			getChildren().addAll(sp);
			getStyleClass().add("fx-calendar-navigator-btnGrp");
		}
	}

	/**
	 * CalendarToggleButton
	 *
	 * @author Sai.Dandem
	 *
	 */
	class CalendarToggleButton extends StackPane {
		private final Text		txt;
		private final StackPane	sp;

		public CalendarToggleButton(final String text, final Object userData) {

			setUserData(userData);
			setPrefHeight(18);
			setPrefWidth(44);

			this.sp = new StackPane();
			this.sp.getStyleClass()
					.add("fx-calendar-toggleButton");
			this.sp.setPrefHeight(16);
			this.sp.setPrefWidth(44);

			this.txt = new Text(
				text);
			this.txt.getStyleClass()
					.add("fx-calendar-toggleButton-txt");
			this.sp.getChildren()
					.add(this.txt);

			getChildren().add(this.sp);
		}

		public void setBaseColor(final Color color) {

			FXCalendarUtility.setBaseColorToNode(this.sp, color);
			FXCalendarUtility.setBaseColorToNode(this.txt, color);
		}

		public void setText(final String text) {

			this.txt.setText(text);
		}

		public void setData(final Object obj) {

			setUserData(obj);
		}

	}

	/**
	 * NormalButton
	 *
	 * @author Sai.Dandem
	 *
	 */
	class NormalButton extends Button {
		public NormalButton(final String txt) {

			super(txt);
			// getStyleClass().add("calendarButton");
			super.setSkin(new ButtonSkin(
				this));
		}
	}

	/**
	 * YearNavigatorArrowButton
	 *
	 * @author Sai.Dandem
	 *
	 */
	class YearNavigatorArrowButton extends Group {

		public YearNavigatorArrowButton(final Side side, final Color baseColor) {

			final StackPane sp = new StackPane();
			FXCalendarUtility.setBaseColorToNode(this, baseColor);
			sp.setPrefHeight(16);
			sp.setPrefWidth(16);

			final Rectangle rect = new Rectangle(
				15, 15);
			FXCalendarUtility.setBaseColorToNode(rect, baseColor);
			rect.getStyleClass()
					.add("fx-calendar-year-navigator-btn");

			Arrow arrow;
			final Group gp = new Group();
			switch (side) {
			case LEFT:
				arrow = new Arrow(
					Side.LEFT);
				gp.setTranslateX(-1);
				break;
			default:
				arrow = new Arrow(
					Side.RIGHT);
				gp.setTranslateX(1);
			}
			arrow.setFillColor(baseColor);
			gp.getChildren()
					.add(arrow);
			sp.getChildren()
					.addAll(rect, gp);
			getChildren().addAll(sp);
			getStyleClass().add("fx-calendar-year-navigator-btnGrp");

		}
	}

}
