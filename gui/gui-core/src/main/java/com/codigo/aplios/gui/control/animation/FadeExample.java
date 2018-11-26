package com.codigo.aplios.gui.control.animation;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FadeExample extends Application {

	private BorderPane		root;
	private Pane			headerContainer;
	private Screen			loginScreen;
	private Screen			mainScreen;
	private final Duration	fadeTime	= Duration.millis(550);

	private final ObjectProperty<Screen> currentScreen = new SimpleObjectProperty<>();

	@Override
	public void start(final Stage primaryStage) {

		this.root = new BorderPane();

		this.headerContainer = new StackPane();
		this.headerContainer.getStyleClass()
				.add("header-container");

		this.currentScreen.addListener((obs, previousScreen, newScreen) -> switchScreens(previousScreen, newScreen));

		this.loginScreen = new Screen(
			createHeader("Login", "Please provide your username and password"), createLoginContent());
		this.mainScreen = new Screen(
			createHeader("Application", "Welcome to the application. This is a test."), createMainContent());

		this.root.setTop(this.headerContainer);

		final Scene scene = new Scene(
			this.root, 600, 600);

		scene.getStylesheets()
				.addAll("style.css");

		primaryStage.setScene(scene);
		primaryStage.show();

		this.currentScreen.set(this.loginScreen);

	}

	private void switchScreens(final Screen previous, final Screen next) {

		// Animation for switching screens fades out old screen (if there is one),
		// then fades new screen (if there is one) in:
		final SequentialTransition sceneSwitch = new SequentialTransition();

		if (previous != null) {
			// fade out simultaneously fades out header and content:
			final ParallelTransition fadeOut = new ParallelTransition(
				createFade(1, 0, previous.getHeader()), createFade(1, 0, previous.getContent()));

			// when fade out is complete, replace content with new content:
			fadeOut.setOnFinished(e -> {
				this.headerContainer.getChildren()
						.setAll(next.getHeader());
				this.root.setCenter(next.getContent());
			});

			sceneSwitch.getChildren()
					.add(fadeOut);
		}
		if (next != null) {
			// fade in simultaneously fades in header and content:
			final ParallelTransition fadeIn = new ParallelTransition(
				createFade(0, 1, next.getHeader()), createFade(0, 1, next.getContent()));

			// when fade in starts, replace content with new content:
			fadeIn.statusProperty()
					.addListener((obs, oldStatus, newStatus) -> {
						if (newStatus == Animation.Status.RUNNING) {
							this.headerContainer.getChildren()
									.setAll(next.getHeader());
							this.root.setCenter(next.getContent());
						}
					});

			sceneSwitch.getChildren()
					.add(fadeIn);
		}

		// play fade out, then fade in:
		sceneSwitch.play();
	}

	private FadeTransition createFade(final double start, final double end, final Node node) {

		final FadeTransition fade = new FadeTransition(
			this.fadeTime, node);
		fade.setFromValue(start);
		fade.setToValue(end);
		return fade;
	}

	private Node createLoginContent() {

		final GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		final ColumnConstraints leftCol = new ColumnConstraints();
		leftCol.setHgrow(Priority.NEVER);
		leftCol.setHalignment(HPos.RIGHT);
		final ColumnConstraints rightCol = new ColumnConstraints();
		rightCol.setHgrow(Priority.ALWAYS);
		grid.getColumnConstraints()
				.addAll(leftCol, rightCol);

		grid.add(new Label(
			"Username:"), 0, 0);
		grid.add(new TextField(), 1, 0);
		grid.add(new Label(
			"Password:"), 0, 1);
		grid.add(new PasswordField(), 1, 1);

		final Button loginButton = new Button(
			"Login");
		loginButton.setOnAction(e -> this.currentScreen.set(this.mainScreen));
		GridPane.setHalignment(loginButton, HPos.CENTER);
		grid.add(loginButton, 0, 2, 2, 1);

		return grid;
	}

	/**
	 * @return
	 */
	private Node createMainContent() {

		final ObservableList<Person> personData = FXCollections.observableArrayList();

		final Label label = new Label(
			"Here is the application");
		final Button logout = new Button(
			"Logout");
		logout.setOnAction(e -> this.currentScreen.set(this.loginScreen));
		final TableView<Person> table = new TableView<>();
		table.setEditable(true);
		final TableColumn<Person, Number> orderIdCol = new TableColumn<>(
			"Order Id");
		final TableColumn<Person, String> firstNameCol = new TableColumn<>(
			"First Name");
		final TableColumn<Person, String> middleCol = new TableColumn<>(
			"Middle Name");
		final TableColumn<Person, String> lastNameCol = new TableColumn<>(
			"Last Name");
		final TableColumn<Person, String> emailCol = new TableColumn<>(
			"Email");

		orderIdCol.setCellValueFactory(cellData -> cellData.getValue()
				.getOrderId());

		// orderIdCol.setCellValueFactory(cellData -> cellDatay(
		// new FormattedTableCellFactory<TableColumn<Person, Number>, TableCell<Person,
		// Number>>()));

		firstNameCol.setCellValueFactory(cellData -> cellData.getValue()
				.getFirstName());
		middleCol.setCellValueFactory(cellData -> cellData.getValue()
				.getMiddleName());
		lastNameCol.setCellValueFactory(cellData -> cellData.getValue()
				.getLastName());
		emailCol.setCellValueFactory(cellData -> cellData.getValue()
				.getEmail());

		table.getColumns()
				.addAll(orderIdCol, firstNameCol, middleCol, lastNameCol, emailCol);

		for (int idx = 1; idx < 100; idx++)
			personData.add(new Person(
				idx, "Andrzej", "Marek", "Radziszewski", "tes@wp.pl"));

		table.setItems(personData);
		// table.getStyleClass()
		// .add("table");

		final VBox vbox = new VBox();
		// vbox.setSpacing(5);
		vbox.setPadding(new Insets(
			0d));
		vbox.getChildren()
				.addAll(table);

		this.root.setBottom(vbox);

		return new VBox(
			label, logout, vbox);
	}

	/**
	 * @param text
	 * @param description
	 * @return
	 */
	private Pane createHeader(final String text, final String description) {

		final Label title = new Label(
			text);
		title.getStyleClass()
				.add("title");

		final Label descriptionLabel = new Label(
			description);

		return new VBox(
			title, descriptionLabel);
	}

	private static class Screen {

		private final Node	header;
		private final Node	content;

		public Screen(final Node header, final Node content) {

			this.header = header;
			this.content = content;

			header.getStyleClass()
					.add("screen-header");
			content.getStyleClass()
					.add("screen-content");
		}

		public Node getHeader() {

			return this.header;
		}

		public Node getContent() {

			return this.content;
		}

	}

	public static void main(final String[] args) {

		Application.launch(args);
	}
}

class Person {

	private IntegerProperty	orderId;
	private StringProperty	firstName;
	private StringProperty	middleName;
	private StringProperty	lastName;
	private StringProperty	email;

	public Person(final Integer orderId, final String fisrtName, final String middleName, final String lastName,
			final String emial) {

		this.orderId = new SimpleIntegerProperty(
			orderId);
		this.firstName = new SimpleStringProperty(
			fisrtName);
		this.middleName = new SimpleStringProperty(
			middleName);
		this.lastName = new SimpleStringProperty(
			lastName);
		this.email = new SimpleStringProperty(
			emial);
	}

	public void setOrderId(final IntegerProperty orderId) {

		this.orderId = orderId;
	}

	public IntegerProperty getOrderId() {

		return this.orderId;
	}

	public StringProperty getFirstName() {

		return this.firstName;
	}

	public void setFirstName(final StringProperty firstName) {

		this.firstName = firstName;
	}

	public StringProperty getMiddleName() {

		return this.middleName;
	}

	public void setMiddleName(final StringProperty middleName) {

		this.middleName = middleName;
	}

	public StringProperty getLastName() {

		return this.lastName;
	}

	public void setLastName(final StringProperty lastName) {

		this.lastName = lastName;
	}

	public StringProperty getEmail() {

		return this.email;
	}

	public void setEmail(final StringProperty email) {

		this.email = email;
	}

}
