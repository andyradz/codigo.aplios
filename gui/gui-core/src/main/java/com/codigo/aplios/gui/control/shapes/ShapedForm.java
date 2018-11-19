package com.codigo.aplios.sdk.controls.shapes;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShapedForm extends Application {

	private Double intx;
	private Double inty;

	@Override
	public void start(final Stage stage) {

		stage.initStyle(StageStyle.TRANSPARENT);
		Text text = new Text("Transparent STAGE");
		Circle dragger = new Circle(400, 400, 400, Color.CADETBLUE);
		text.setFont(new Font(40));
		dragger.setStroke(Color.RED);
		
		Group g = new Group();
		Button min = new Button("-");
		min.setOnAction(event -> stage.setIconified(true));

		Button close = new Button("X");
		close.setOnAction(event -> stage.close());

		dragger.setOnMousePressed(event -> {
			intx = (event.getScreenX() - stage.getX());
			inty = event.getScreenY() - stage.getY();
		});

		// when screen is dragged, translate it accordingly
		dragger.setOnMouseDragged(event -> {
			stage.setX(event.getScreenX() - intx);
			stage.setY(event.getScreenY() - inty);
		});

		dragger.setOnMouseClicked(mouseEvent -> {

			if ((mouseEvent.getButton()
					.equals(MouseButton.PRIMARY)) && (mouseEvent.getClickCount() == 2))

				if (stage.isMaximized())
					stage.setMaximized(false);
				else
					stage.setMaximized(true);

		});
		HBox hBox = new HBox();
		hBox.setSpacing(10);
		hBox.setPadding(new Insets(25, 0, 0, 60));
		hBox.setAlignment(Pos.TOP_CENTER);
		hBox.getChildren()
				.addAll(min, close);

		g.getChildren()
				.addAll(dragger, hBox);
		final Scene scene = new Scene(g, 800, 600);
		scene.setFill(Color.TRANSPARENT);
		dragger.setStroke(Color.RED);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {

		launch(args);
	}
}
