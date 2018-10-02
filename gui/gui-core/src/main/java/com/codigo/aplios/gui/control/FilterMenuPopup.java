package com.codigo.aplios.gui.control;

//import com.sun.javafx.css.StyleManager;

//import com.sun.javafx.css.StyleManager;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Separator;
import javafx.scene.control.Skin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * A menu for displaying column filter settings. There is a {@link #saveButton},
 * {@link #cancelButton} and {@link #resetButton}. Only one instance of this popup will be visible
 * at one time.
 *
 * @author jhs
 *
 */
public class FilterMenuPopup extends PopupControl {

	/**
	 * Use context-menu's CSS settings as a base, and override with our filter-popup-menu settings
	 */
	private static final String[] DEFAULT_STYLE_CLASS = { "filter-popup-menu", "context-menu" };

	/*
	 * static { StyleManager.getInstance() .addUserAgentStylesheet(
	 * FilterMenuPopup.class.getResource(FilterMenuPopup.class.getSimpleName() + ".css") .toString()); }
	 */

	private static FilterMenuPopup currentlyVisibleMenu;

	private final ObjectProperty<Node> contentNode = new SimpleObjectProperty<>();

	private final SimpleObjectProperty<Button> saveButton;

	private final SimpleObjectProperty<Button> resetButton;

	private final SimpleObjectProperty<Button> cancelButton;

	private final SimpleStringProperty title;

	/**
	 * Popup constructor
	 *
	 * @param title
	 */
	public FilterMenuPopup(final String title) {

		setHideOnEscape(true);
		setAutoHide(true);

		// Listen for ESC key events; hide/cancel if one's caught
		final EventHandler<KeyEvent> cancelEvent = event -> {

			if (event.getCode() == KeyCode.ESCAPE)
				FilterMenuPopup.this.hide();
		};

		this.title = new SimpleStringProperty(
			title);

		final Button buttonSave = new Button(
			"Save");
		this.saveButton = new SimpleObjectProperty<>(
			buttonSave);
		buttonSave.getStyleClass()
				.add("save-button");
		buttonSave.addEventFilter(KeyEvent.KEY_PRESSED, cancelEvent);
		buttonSave.setDefaultButton(true);

		final Button buttonReset = new Button(
			"Reset");
		this.resetButton = new SimpleObjectProperty<>(
			buttonReset);
		buttonReset.getStyleClass()
				.add("reset-button");
		buttonReset.addEventFilter(KeyEvent.KEY_PRESSED, cancelEvent);

		final Button buttonCancel = new Button(
			"Cancel");
		this.cancelButton = new SimpleObjectProperty<>(
			buttonCancel);
		buttonCancel.getStyleClass()
				.add("cancel-button");
		buttonCancel.addEventFilter(KeyEvent.KEY_PRESSED, cancelEvent);
		buttonCancel.setCancelButton(true);
		buttonCancel.setOnMouseClicked(event -> FilterMenuPopup.this.hide());

		getStyleClass().setAll(FilterMenuPopup.DEFAULT_STYLE_CLASS);

		setSkin(new FilterMenuPopupSkin2());
	}

	public ObjectProperty<Node> contentNodeProperty() {

		return this.contentNode;
	}

	/**
	 * Set the content to display in the filter menu
	 *
	 * @param value
	 */
	public final void setContentNode(final Node value) {

		contentNodeProperty().set(value);
	}

	public final Node getContentNode() {

		return this.contentNode.get();
	}

	public SimpleStringProperty titleProperty() {

		return this.title;
	}

	public String getTitle() {

		return this.title.get();
	}

	public void setTitle(final String title) {

		this.title.set(title);
	}

	public SimpleObjectProperty<Button> saveButtonProperty() {

		return this.saveButton;
	}

	public Button getSaveButton() {

		return this.saveButton.get();
	}

	public SimpleObjectProperty<Button> cancelButtonProperty() {

		return this.saveButton;
	}

	public Button getCancelButton() {

		return this.cancelButton.get();
	}

	public SimpleObjectProperty<Button> resetButtonProperty() {

		return this.saveButton;
	}

	public Button getResetButton() {

		return this.resetButton.get();
	}

	/**
	 * Set the event to fire when the save button is pressed
	 *
	 * @param event
	 */
	public void setSaveEvent(final EventHandler<ActionEvent> event) {

		this.saveButton.get()
				.setOnAction(event);
	}

	/**
	 * Set the event to fire when the reset button is pressed
	 *
	 * @param event
	 */
	public void setResetEvent(final EventHandler<ActionEvent> event) {

		this.resetButton.get()
				.setOnAction(event);
	}

	@Override
	protected void show() {

		highlander();
		super.show();
	}

	@Override
	public void show(final Window window) {

		highlander();
		super.show(window);
	}

	@Override
	public void show(final Window window, final double d, final double d1) {

		highlander();
		super.show(window, d, d1);
	}

	@Override
	public void show(final Node node, final double d, final double d1) {

		highlander();
		super.show(node, d, d1);
	}

	@Override
	public void hide() {

		if (FilterMenuPopup.currentlyVisibleMenu == this)
			FilterMenuPopup.currentlyVisibleMenu = null;
		super.hide();
	}

	/**
	 * There can be only one... visible FilterMenuPopup
	 */
	private void highlander() {

		if ((FilterMenuPopup.currentlyVisibleMenu != null) && (FilterMenuPopup.currentlyVisibleMenu != this))
			FilterMenuPopup.currentlyVisibleMenu.hide();
		FilterMenuPopup.currentlyVisibleMenu = this;
	}

	// XXX: I'm not sure how to set the skin properly for PopupControl in JavaFX 8;
	// it somehow changed.
	// Just making the skin a private class so I can use setSkin() is easier than
	// trying to figure it out
	// (I think you have to call -fx-skin on the CSSBridge somehow)
	private class FilterMenuPopupSkin2 extends StackPane implements Skin<FilterMenuPopup> {

		public FilterMenuPopupSkin2() {

			final ContentStack contentStack = new ContentStack();
			getChildren().add(contentStack);

			idProperty().bind(FilterMenuPopup.this.idProperty());
			styleProperty().bind(FilterMenuPopup.this.styleProperty());
			getStyleClass().setAll(FilterMenuPopup.this.getStyleClass());
		}

		@Override
		public FilterMenuPopup getSkinnable() {

			return FilterMenuPopup.this;
		}

		@Override
		public Node getNode() {

			return this;
		}

		@Override
		public void dispose() {

			getChildren().clear();
		}

		class ContentStack extends BorderPane {

			public ContentStack() {

				getStyleClass().add("content");

				final Label titleLabel = new Label();
				titleLabel.textProperty()
						.bind(titleProperty());

				final StackPane topPane = new StackPane();
				topPane.getChildren()
						.addAll(new Separator(), titleLabel);
				topPane.getStyleClass()
						.add("top");
				setTop(topPane);

				contentNodeProperty().addListener((ChangeListener<Node>) (paramObservableValue, paramT1, paramT2) -> {

					paramT2.getStyleClass()
							.add("center");
					ContentStack.this.setCenter(paramT2);
				});

				if (getContentNode() != null)
					getContentNode().getStyleClass()
							.add("center");
				setCenter(getContentNode());

				final HBox buttons = new HBox();
				buttons.getStyleClass()
						.add("buttons");
				buttons.setPrefWidth(Region.USE_COMPUTED_SIZE);
				buttons.setPrefHeight(Region.USE_COMPUTED_SIZE);
				buttons.setSpacing(4);
				buttons.getChildren()
						.addAll(getSaveButton(), getResetButton(), getCancelButton());

				final VBox bottom = new VBox();
				bottom.getStyleClass()
						.add("bottom");
				bottom.getChildren()
						.addAll(new Separator(), buttons);
				setBottom(bottom);
			}

		}
	}
}
