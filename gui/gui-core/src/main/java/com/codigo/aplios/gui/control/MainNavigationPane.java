package com.codigo.aplios.gui.control;
import java.util.Calendar;

import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * The main navigation pane.
 *
 * @author Christian Schudt
 */
final class MainNavigationPane extends HBox {

	private static final String	CSS_CALENDAR_NAVIGATION_ARROW	= "calendar-navigation-arrow";
	private static final String	CSS_CALENDAR_NAVIGATION_BUTTON	= "calendar-navigation-button";
	private static final String	CSS_CALENDAR_NAVIGATION_TITLE	= "calendar-navigation-title";
	private static final String	CSS_CALENDAR_HEADER				= "calendar-header";

	private CalendarView	calendarView;
	Button					titleButton;

	public MainNavigationPane(final CalendarView calendarView) {

		this.calendarView = calendarView;

		this.titleButton = new Button();
		this.titleButton.getStyleClass()
		    .add(MainNavigationPane.CSS_CALENDAR_NAVIGATION_TITLE);
		this.titleButton.textProperty()
		    .bind(calendarView.title);

		this.titleButton.setOnAction(actionEvent -> {

			switch (calendarView.currentlyViewing.get()) {
				case Calendar.MONTH:
					calendarView.currentlyViewing.set(Calendar.YEAR);
				break;
				case Calendar.YEAR:
					calendarView.currentlyViewing.set(Calendar.ERA);
			}
		});
		this.titleButton.disableProperty()
		    .bind(new BooleanBinding() {
			    {
				    super.bind(calendarView.ongoingTransitions, calendarView.currentlyViewing);
			    }

			    @Override
			    protected boolean computeValue() {

				    return (calendarView.currentlyViewing.get() == Calendar.ERA)
				            || (calendarView.ongoingTransitions.get() > 0);
			    }
		    });
		final HBox buttonBox = new HBox();
		buttonBox.getChildren()
		    .add(this.titleButton);
		buttonBox.setAlignment(Pos.CENTER);

		HBox.setHgrow(buttonBox, Priority.ALWAYS);

		this.getChildren()
		    .add(this.getNavigationButton(-1));
		this.getChildren()
		    .add(buttonBox);
		this.getChildren()
		    .add(this.getNavigationButton(1));

		this.getStyleClass()
		    .add(MainNavigationPane.CSS_CALENDAR_HEADER);
	}

	/**
	 * Gets a navigation button.
	 *
	 * @param direction
	 *        Either -1 (for left) or 1 (for right).
	 * @return The button.
	 */
	private Button getNavigationButton(final int direction) {

		final Button button = new Button();

		button.setOnAction(actionEvent -> {

			final Calendar calendar = MainNavigationPane.this.calendarView.getCalendar();
			switch (MainNavigationPane.this.calendarView.currentlyViewing.get()) {
				case Calendar.MONTH:
					calendar.add(Calendar.MONTH, 1 * direction);
				break;
				case Calendar.YEAR:
					calendar.add(Calendar.YEAR, 1 * direction);
				break;
				case Calendar.ERA:
					calendar.add(Calendar.YEAR, 20 * direction);
				break;
			}

			MainNavigationPane.this.calendarView.calendarDate.set(calendar.getTime());
		});

		// Make a region, so that -fx-shape can be applied from CSS.
		final Region rectangle = new Region();
		rectangle.setMaxWidth(Region.USE_PREF_SIZE);
		rectangle.setMaxHeight(Region.USE_PREF_SIZE);
		rectangle.setRotate(direction < 0 ? 90 : 270);
		rectangle.getStyleClass()
		    .add(MainNavigationPane.CSS_CALENDAR_NAVIGATION_ARROW);
		// Set that region as the button graphic.
		button.setGraphic(rectangle);
		button.getStyleClass()
		    .add(MainNavigationPane.CSS_CALENDAR_NAVIGATION_BUTTON);
		return button;
	}

}