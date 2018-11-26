package com.codigo.aplios.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class TaskTest extends Application {

	@Override
	public void start(final Stage primaryStage) throws Exception {

		// TODO Auto-generated method stub

	}

	// public static void main(final String[] args) throws Exception {
	//
	// Application.launch(args);
	// }
	//
	// @Override
	// public void start(final Stage stage) throws Exception {
	//
	// final Label statusLabel = new Label(
	// "Status");
	// final Button runButton = new Button(
	// "Run");
	// final ListView<String> peopleView = new ListView<>();
	//
	// peopleView.setPrefSize(1024d, 600d);
	// final ProgressBar progressBar = new ProgressBar();
	// progressBar.setProgress(.0);
	// progressBar.prefWidthProperty()
	// .bind(peopleView.prefWidthProperty());
	//
	// runButton.setOnAction(actionEvent -> {
	//
	// final Task<ObservableList<String>> task = new Task<>() {
	//
	// @Override
	// protected ObservableList<String> call() throws InterruptedException {
	//
	// updateMessage("Finding friends . . .");
	// for (int i = 0; i < 10; i++) {
	// Thread.sleep(500);
	// this.updateProgress(i + 1, 10);
	// }
	// updateMessage("Finished.");
	// return FXCollections.observableArrayList("John", "Jim", "Geoff", "Jill", "Suki", "asdsa", "wqew",
	// "wqewq", "wqew", "23we", "adqaweqe123421eqwe", "qweq312321w", "12321ewqe", "qwewqeqw",
	// "asd", "asdas", "animator", "121", "1212", "121q", "12assS", "asdasdasdasd", "qsaaSas",
	// "dasdasdas", "sdasd", "qw1221", "qe");
	// }
	// // @Override protected void done() {
	// // super.done();
	// // System.out.println("This is bad, do not do this, this thread " +
	// // Thread.currentThread() + " is not the FXApplication thread.");
	// // runButton.setText("Voila!");
	// // }
	// };
	//
	// statusLabel.textProperty()
	// .bind(task.messageProperty());
	// runButton.disableProperty()
	// .bind(task.runningProperty());
	// peopleView.itemsProperty()
	// .bind(task.valueProperty());
	// progressBar.progressProperty()
	// .bind(task.progressProperty());
	// task.stateProperty()
	// .addListener((ChangeListener<State>) (observableValue, oldState, newState) -> {
	//
	// if (newState == Worker.State.SUCCEEDED) {
	// System.out.println("This is ok, this thread " + Thread.currentThread()
	// + " is the JavaFX Application thread.");
	// runButton.setText("Voila!");
	// }
	// });
	//
	// new Thread(
	// task).start();
	// });
	//
	//// final VBox layout = VBoxBuilder.create()
	//// .spacing(8)
	//// .children(VBoxBuilder.create()
	//// .spacing(5)
	//// .children(HBoxBuilder.create()
	//// .spacing(10)
	//// .children(runButton, statusLabel)
	//// .build(), progressBar)
	//// .build(), peopleView)
	//// .build();
	//// layout.setStyle("-fx-background-color: cornsilk; -fx-padding:10; -fx-font-size: 12;");
	//// final Scene scene = new Scene(
	//// layout, 1024d, 784d);
	// stage.setTitle("Contact Manager 2017");
	// stage.setResizable(false);
	// // stage.initStyle(StageStyle.);
	// stage.setScene(scene);
	// stage.show();
	//
	// }
}