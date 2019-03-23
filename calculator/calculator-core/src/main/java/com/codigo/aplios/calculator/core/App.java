//package com.codigo.aplios.calculator.core;
//
//import java.io.File;
//
//import javafx.application.Application;
//import javafx.application.Platform;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.Menu;
//import javafx.scene.control.MenuBar;
//import javafx.scene.control.MenuItem;
//import javafx.scene.control.RadioMenuItem;
//import javafx.scene.control.SeparatorMenuItem;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.image.Image;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.VBox;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
///**
// * Created by Michal Nowinski on 2016-11-16.
// */
//public class App extends Application {
//
//	private final String	pathStyleCss0	= "/css/view0.css";
//	private final String	pathStyleCss1	= "/css/view1.css";
//	private final String	pathStyleCss2	= "/css/view2.css";
//	private final String	pathStyleCss3	= "/css/view3.css";
//
//	private File stylesheet;
//
//	@Override
//	public void start(final Stage primaryStage) throws Exception {
//
//		// RadioMenuItems
//		final ToggleGroup toggle = new ToggleGroup();
//		final RadioMenuItem showView1 = new RadioMenuItem(
//			"View Classic");
//		final RadioMenuItem showView2 = new RadioMenuItem(
//			"View Orange");
//		final RadioMenuItem showView3 = new RadioMenuItem(
//			"View White");
//		final RadioMenuItem showView4 = new RadioMenuItem(
//			"View Pink");
//		final MenuItem showOther = new MenuItem(
//			"Open your css file...");
//		showView2.setSelected(true);
//		showView1.setToggleGroup(toggle);
//		showView2.setToggleGroup(toggle);
//		showView3.setToggleGroup(toggle);
//		showView4.setToggleGroup(toggle);
//
//		final MenuItem itemExit = new MenuItem(
//			"Exit");
//		// Menu one
//		final Menu menu1 = new Menu(
//			"Menu");
//		menu1.setMnemonicParsing(false);
//		menu1.getItems()
//				.addAll(showView1, showView2, showView3, showView4, showOther, new SeparatorMenuItem(), itemExit);
//		final MenuItem itemAbout = new MenuItem(
//			"About me");
//		// Menu two
//		final Menu menu2 = new Menu(
//			"Info");
//		menu2.getItems()
//				.add(itemAbout);
//		// MenuBar
//		final MenuBar menuBar = new MenuBar();
//		menuBar.getMenus()
//				.addAll(menu1, menu2);
//
//		final Pane window1 = FXMLLoader.load(getClass().getResource("fxml/ui.fxml"));
//		final BorderPane window2 = new BorderPane(
//			window1);
//		window2.setTop(menuBar);
//		final Scene scene = new Scene(
//			window2);
//		scene.getStylesheets()
//				.add(getClass().getResource("/css/view1.css")
//						.toExternalForm());
//
//		// Obsluga zdarzen menu
//		showView1.setOnAction(e -> {
//			changeCSS(scene, this.pathStyleCss0);
//		});
//		showView2.setOnAction(e -> {
//			changeCSS(scene, this.pathStyleCss1);
//		});
//		showView3.setOnAction(e -> {
//			changeCSS(scene, this.pathStyleCss2);
//		});
//		showView4.setOnAction(e -> {
//			changeCSS(scene, this.pathStyleCss3);
//		});
//		// wskaz sciezke do wlasnego pliku css
//		showOther.setOnAction(e -> {
//			// Jezeli radio menu sa zaznaczone, odznacz
//			if (toggle.getSelectedToggle() != null)
//				toggle.getSelectedToggle()
//						.setSelected(false);
//
//			final FileChooser fileChooser = new FileChooser();
//			fileChooser.setTitle("Choose stylesheet");
//			// katalog otwierania (tam gdzie jest plik kalkulatora)
//			final String currentDir = System.getProperty("user.dir") + File.separator;
//			final File file = new File(
//				currentDir);
//			fileChooser.setInitialDirectory(file);
//			fileChooser.getExtensionFilters()
//					.add(new FileChooser.ExtensionFilter(
//						"CSS Stylesheets", "*.css"));
//			final File styleFile = fileChooser.showOpenDialog(primaryStage.getScene()
//					.getWindow());
//
//			if (styleFile != null) {
//				this.stylesheet = styleFile;
//				scene.getStylesheets()
//						.clear();
//				Application.setUserAgentStylesheet(null);
//				scene.getStylesheets()
//						.add("file:///" + this.stylesheet.getAbsolutePath()
//								.replace("\\", "/"));
//			}
//		});
//		itemExit.setOnAction(e -> {
//			Platform.exit();
//			System.exit(0);
//		});
//		itemAbout.setOnAction(t -> {
//
//			// window about me
//			final Stage stage = new Stage();
//			stage.initOwner(primaryStage);
//			final VBox vbox = new VBox();
//			vbox.setPrefHeight(100);
//			vbox.setPrefWidth(150);
//			vbox.setStyle("-fx-background-color: #d6d6d6;");
//			final Label title = new Label(
//				"OwiCalculator");
//			title.setStyle("-fx-padding: 10;" + "-fx-font-weight: bold;" + "-fx-font-size: 18;");
//			final Label createBy = new Label(
//				"create by Michał Nowiński");
//			createBy.setStyle("-fx-font-style: oblique;" + "-fx-font-size: 12px;");
//			final Label dateCreate = new Label(
//				"on 2016-11-16");
//			dateCreate.setStyle("-fx-font-style: oblique;" + "-fx-font-size: 12px;");
//
//			vbox.setAlignment(Pos.TOP_CENTER);
//			vbox.getChildren()
//					.addAll(title, createBy, dateCreate);
//			final Scene scene1 = new Scene(
//				vbox);
//
//			stage.setTitle("About me");
//			stage.getIcons()
//					.add(new Image(
//						"/graphics/calculator_icon.png"));
//			stage.setScene(scene1);
//			stage.show();
//		});
//
//		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);
//		primaryStage.setWidth(206);
//		primaryStage.setHeight(252);
//		primaryStage.getIcons()
//				.add(new Image(
//					"/graphics/calculator_icon.png"));
//		primaryStage.setTitle("OwiCalculator");
//		primaryStage.show();
//
//	}
//
//	/**
//	 * Metoda zmieniajaca styl CSS
//	 *
//	 * @param scene
//	 *        przyjmuje scene
//	 * @param pathStyleCss
//	 *        przyjmuje string ze sciezka do pliku css
//	 */
//	public void changeCSS(final Scene scene, final String pathStyleCss) {
//
//		scene.getStylesheets()
//				.clear();
//		Application.setUserAgentStylesheet(null);
//		scene.getStylesheets()
//				.add(getClass().getResource(pathStyleCss)
//						.toExternalForm());
//	}
//
//	public static void main(final String[] args) {
//
//		Application.launch(args);
//	}
//}
