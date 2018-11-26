package com.codigo.aplios.gui.control.grid;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TabValidationExample extends Application {

	@Override
	public void start(final Stage primaryStage) {

		try {
			final Parent root = FXMLLoader.load(this.getClass()
					.getResource("/TabValidation.fxml"));
			final Scene scene = new Scene(
				root, 800, 600);
			scene.getStylesheets()
					.add(TabValidationExample.class.getResource("/application.css")
							.toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] args) {

		Application.launch(args);
	}
}