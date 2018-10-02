package com.codigo.aplios.sdk.core;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RunMe extends Application {
	public static void main(final String[] args) {
		Application.launch(RunMe.class);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		// The primaryStage is the top-level container
		stage.setTitle("example Gui");
		// The BorderPane has the same areas laid out as the
		// BorderLayout layout manager
		final BorderPane componentLayout = new BorderPane();
		componentLayout.setPadding(new Insets(
			20, 0, 20, 20));
		// The FlowPane is a conatiner that uses a flow layout
		final FlowPane choicePane = new FlowPane();
		choicePane.setHgap(100);
		final Label choiceLbl = new Label(
			"Fruits");
		// The choicebox is populated from an observableArrayList
		final ChoiceBox<String> fruits = new ChoiceBox<>(
			FXCollections.observableArrayList("Asparagus", "Beans", "Broccoli", "Cabbage", "Carrot", "Celery",
					"Cucumber", "Leek", "Mushroom", "Pepper", "Radish", "Shallot", "Spinach", "Swede", "Turnip"));
		// Add the label and choicebox to the flowpane
		choicePane.getChildren()
				.add(choiceLbl);
		choicePane.getChildren()
				.add(fruits);
		// put the flowpane in the top area of the BorderPane
		componentLayout.setTop(choicePane);
		final FlowPane listPane = new FlowPane();
		listPane.setHgap(100);
		final Label listLbl = new Label(
			"Vegetables");
		final ListView<String> vegetables = new ListView<>(
			FXCollections.observableArrayList("Apple", "Apricot", "Banana", "Cherry", "Date", "Kiwi", "Orange", "Pear",
					"Strawberry"));
		listPane.getChildren()
				.add(listLbl);
		listPane.getChildren()
				.add(vegetables);
		listPane.setVisible(false);
		componentLayout.setCenter(listPane);
		// The button uses an inner class to handle the button click event
		final Button vegFruitBut = new Button(
			"Fruit or Veg");
		vegFruitBut.setOnAction(evtent -> {

			choicePane.setVisible(!choicePane.isVisible());
			listPane.setVisible(!listPane.isVisible());

		});
		componentLayout.setBottom(vegFruitBut);

		final Button showModal = new Button(
			"Show window");
		showModal.setOnAction(evtent -> {

			final Stage myDialog = new Stage();
			myDialog.initModality(Modality.APPLICATION_MODAL);

			final Button okButton = new Button(
				"CLOSE");
			okButton.setOnAction(event -> myDialog.close());

			// final VBox box = new VBox();
			// box.getChildren()
			// .add(new Text(
			// "Hello! it's My Dialog."), okButton);
			//
			// final Scene myDialogScene = new Scene(
			// VBox.Builder.create()
			// .children(new Text(
			// "Hello! it's My Dialog."), okButton)
			// .alignment(Pos.CENTER)
			// .padding(new Insets(
			// 300))
			// .build());

			// myDialogScene.addEventHandler(KeyEvent.KEY_PRESSED, t -> {
			// if (t.getCode() == KeyCode.ESCAPE)
			// myDialog.close();
			// });

			// myDialog.setScene(myDialogScene);
			// myDialog.show();

		});
		componentLayout.setTop(showModal);

		// Add the BorderPane to the Scene
		final Scene appScene = new Scene(
			componentLayout, 1024, 800);
		// Add the Scene to the Stage
		stage.setScene(appScene);
		stage.show();
	}

}
