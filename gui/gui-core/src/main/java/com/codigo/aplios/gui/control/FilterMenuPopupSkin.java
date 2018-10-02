package com.codigo.aplios.gui.control;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Skin;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FilterMenuPopupSkin extends StackPane implements Skin<FilterMenuPopup> {

	private FilterMenuPopup popup;

	public FilterMenuPopupSkin(final FilterMenuPopup popup) {

		this.popup = popup;

		final ContentStack contentStack = new ContentStack(popup.getContentNode());
		this.getChildren()
		    .add(contentStack);

		this.idProperty()
		    .bind(popup.idProperty());
		this.styleProperty()
		    .bind(popup.styleProperty());
		this.getStyleClass()
		    .setAll(popup.getStyleClass());
	}

	@Override
	public FilterMenuPopup getSkinnable() {

		return this.popup;
	}

	@Override
	public Node getNode() {

		return this;
	}

	@Override
	public void dispose() {

		this.popup = null;
	}

	class ContentStack extends BorderPane {

		public ContentStack(final Node contentNode) {

			this.getStyleClass()
			    .add("content");

			final Label titleLabel = new Label();
			titleLabel.textProperty()
			    .bind(FilterMenuPopupSkin.this.popup.titleProperty());

			final StackPane topPane = new StackPane();
			topPane.getChildren()
			    .addAll(new Separator(), titleLabel);
			topPane.getStyleClass()
			    .add("top");
			this.setTop(topPane);

			contentNode.getStyleClass()
			    .add("center");
			this.setCenter(contentNode);

			final HBox buttons = new HBox();
			buttons.getStyleClass()
			    .add("buttons");
			buttons.setPrefWidth(Region.USE_COMPUTED_SIZE);
			buttons.setPrefHeight(Region.USE_COMPUTED_SIZE);
			buttons.setSpacing(4);
			buttons.getChildren()
			    .addAll(FilterMenuPopupSkin.this.popup.getSaveButton(), FilterMenuPopupSkin.this.popup.getResetButton(),
			            FilterMenuPopupSkin.this.popup.getCancelButton());

			final VBox bottom = new VBox();
			bottom.getStyleClass()
			    .add("bottom");
			bottom.getChildren()
			    .addAll(new Separator(), buttons);
			this.setBottom(bottom);
		}
	}
}