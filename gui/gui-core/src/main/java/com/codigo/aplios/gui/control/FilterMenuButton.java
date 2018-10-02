package com.codigo.aplios.gui.control;

//import com.sun.javafx.css.StyleManager;
import java.util.Objects;

//import com.sun.javafx.css.StyleManager;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.PopupControl;
import javafx.stage.Window;

/**
 * A button that controls displaying the filter menu when clicked
 *
 * @author jhs
 *
 */
public class FilterMenuButton extends Button {

	static {
		// StyleManager.getInstance()
		// .addUserAgentStylesheet(
		// FilterMenuButton.class.getResource(FilterMenuButton.class.getSimpleName() + ".css")
		// .toString());
	}

	private final SimpleBooleanProperty active = new SimpleBooleanProperty();

	public FilterMenuButton(final FilterMenuPopup popup) {

		getStyleClass().add("filter-menu-button");

		// When the active property is true, append an active class to this button
		this.active.addListener((ChangeListener<Boolean>) (ov, oldVal, newVal) -> {

			if (Objects.equals(newVal, Boolean.TRUE))
				FilterMenuButton.this.getStyleClass()
						.add("active");
			else
				FilterMenuButton.this.getStyleClass()
						.remove("active");
		});

		// Toggle popup display when clicked
		setOnAction(event -> {

			if (popup.isShowing())
				popup.hide();
			else {
				final Control c = (Control) event.getSource();
				final Bounds b = c.localToScene(c.getLayoutBounds());
				final PopupControl menu = popup;

				final Scene scene = c.getScene();
				final Window window = scene.getWindow();
				menu.show(c, window.getX() + scene.getX() + b.getMinX(), window.getY() + scene.getY() + b.getMaxY());
			}
		});

	}

	public SimpleBooleanProperty activeProperty() {

		return this.active;
	}

	public void setActive(final boolean b) {

		this.active.set(b);
	}

	public boolean isActive() {

		return this.active.get();
	}

}
