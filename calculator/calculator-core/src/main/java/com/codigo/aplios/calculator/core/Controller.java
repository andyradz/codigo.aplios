//package com.codigo.aplios.calculator.core;
//
//import static javafx.scene.input.KeyCode.ADD;
//import static javafx.scene.input.KeyCode.DECIMAL;
//import static javafx.scene.input.KeyCode.DELETE;
//import static javafx.scene.input.KeyCode.DIGIT0;
//import static javafx.scene.input.KeyCode.DIGIT1;
//import static javafx.scene.input.KeyCode.DIGIT2;
//import static javafx.scene.input.KeyCode.DIGIT3;
//import static javafx.scene.input.KeyCode.DIGIT4;
//import static javafx.scene.input.KeyCode.DIGIT5;
//import static javafx.scene.input.KeyCode.DIGIT6;
//import static javafx.scene.input.KeyCode.DIGIT7;
//import static javafx.scene.input.KeyCode.DIGIT8;
//import static javafx.scene.input.KeyCode.DIGIT9;
//import static javafx.scene.input.KeyCode.DIVIDE;
//import static javafx.scene.input.KeyCode.ENTER;
//import static javafx.scene.input.KeyCode.EQUALS;
//import static javafx.scene.input.KeyCode.MINUS;
//import static javafx.scene.input.KeyCode.MULTIPLY;
//import static javafx.scene.input.KeyCode.NUMPAD0;
//import static javafx.scene.input.KeyCode.NUMPAD1;
//import static javafx.scene.input.KeyCode.NUMPAD2;
//import static javafx.scene.input.KeyCode.NUMPAD3;
//import static javafx.scene.input.KeyCode.NUMPAD4;
//import static javafx.scene.input.KeyCode.NUMPAD5;
//import static javafx.scene.input.KeyCode.NUMPAD6;
//import static javafx.scene.input.KeyCode.NUMPAD7;
//import static javafx.scene.input.KeyCode.NUMPAD8;
//import static javafx.scene.input.KeyCode.NUMPAD9;
//import static javafx.scene.input.KeyCode.PERIOD;
//import static javafx.scene.input.KeyCode.SLASH;
//import static javafx.scene.input.KeyCode.SUBTRACT;
//
//import java.awt.Toolkit;
//import java.awt.datatransfer.Clipboard;
//import java.awt.datatransfer.StringSelection;
//
//import javafx.application.Platform;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.MenuItem;
//import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyEvent;
//
///**
// * @author Michal Nowinski
// * @sience 2016-11-16
// */
//public class Controller {
//
//	@FXML
//	private Label output;
//
//	private double number1 = 0;
//
//	private String operator = "";
//
//	private boolean start = true;
//
//	private final int MAX_OUTPUT_LENGTH = 19;
//
//	private final Model model = new Model();
//
//	// Zbiory przyjmowanych przyciskow
//	KeyCode[] numpadKey = {
//			NUMPAD0,
//			NUMPAD1,
//			NUMPAD2,
//			NUMPAD3,
//			NUMPAD4,
//			NUMPAD5,
//			NUMPAD6,
//			NUMPAD7,
//			NUMPAD8,
//			NUMPAD9,
//			DECIMAL,
//			DIGIT0,
//			DIGIT1,
//			DIGIT2,
//			DIGIT3,
//			DIGIT4,
//			DIGIT5,
//			DIGIT6,
//			DIGIT7,
//			DIGIT8,
//			DIGIT9,
//			PERIOD };
//
//	KeyCode[] operatorKey = { DIVIDE, MULTIPLY, SUBTRACT, ADD, MINUS, SLASH };
//
//	@FXML
//	public void processMenu(final ActionEvent event) {
//
//		final String value = ((MenuItem) event.getSource()).getText();
//		if (value.equals("Copy")) {
//			final StringSelection stringSelection = new StringSelection(
//				this.output.getText());
//			final Clipboard clpbrd = Toolkit.getDefaultToolkit()
//					.getSystemClipboard();
//			clpbrd.setContents(stringSelection, null);
//		}
//		if (value.equals("Exit")) {
//			Platform.exit();
//			System.exit(0);
//		}
//	}
//
//	/**
//	 * Obsluga interfejsu klawiatury numerycznej
//	 *
//	 * @param event
//	 *        przechwytuje nacisniecia przyciskow
//	 */
//	@FXML
//	public void processNumpad(final ActionEvent event) {
//
//		final String value = ((Button) event.getSource()).getText();
//		addText(value);
//	}
//
//	/**
//	 * Obsluga interfejsu klawiatury operatorow matematycznych
//	 *
//	 * @param event
//	 *        przechwytuje nacisniecia przyciskow
//	 */
//	@FXML
//	public void processOperator(final ActionEvent event) {
//
//		final String value = ((Button) event.getSource()).getText();
//		addOperator(value);
//	}
//
//	/**
//	 * Ustawia text w widoku aplikacji
//	 *
//	 * @param value
//	 *        przyjmuje String interfejsu klawiatury numerycznej
//	 */
//	public void addText(final String value) {
//
//		if (this.start) {
//			this.output.setText("");
//			this.start = false;
//		}
//
//		// Aby nie przekorczyc dlugosci okna wyswietlania
//		if (!(this.output.getText()
//				.length() == this.MAX_OUTPUT_LENGTH)) {
//
//			// Jezeli pierwszym znakiem jest kropka (.) dodaj 0 przed znakiem
//			if ((this.output.getText()
//					.length() == 0) && value.equals("."))
//				this.output.setText("0");
//			// Jezeli w lancuchu znakow jest kropka nie pozwol na dodanie kolejnej
//			if (value.equals(".")) {
//				int dot = 1;
//				for (int i = 0; i < this.output.getText()
//						.length(); ++i) {
//					if (this.output.getText()
//							.charAt(i) == '.')
//						dot += 1;
//					if (dot == 2)
//						return;
//				}
//			}
//			// Jezeli pierwsza liczba to zero nie dodawaj kolejnego zera
//			if ((this.output.getText()
//					.length() == 1)
//					&& (this.output.getText()
//							.charAt(0) == '0')
//					&& value.equals("0"))
//				return;
//			// Jezeli pierwsza liczba to zero a drugi znak nie jest kropka skasuj zero
//			if ((this.output.getText()
//					.length() == 1)
//					&& (this.output.getText()
//							.charAt(0) == '0')
//					&& !value.equals("."))
//				this.output.setText(this.output.getText()
//						.substring(0, 0));
//
//			this.output.setText(this.output.getText() + value);
//		}
//		else
//			return;
//	}
//
//	/**
//	 * Ustawia operator, oraz ustawia wyniki dzialan matematycznych
//	 *
//	 * @param value
//	 *        przyjmuje String interfejsu operatorow matematycznych
//	 */
//	public void addOperator(final String value) {
//
//		// Kasowanie wyniku/wyswietlacza
//		if (value.equals("C"))
//			cleanOutput();
//		// Jezeli brak liczb, nic nie rob przy kliknieciu operoatorow
//		if (this.output.getText()
//				.length() == 0)
//			return;
//
//		// Jezeli operatorem nie jest znak =
//		if (!"=".equals(value))
//
//			// if(number1>0 && !operator.isEmpty() && output.getText()==null){
//			// System.out.println("Return");
//			// return;
//			// }
//			// Funkcje operujace na jednej liczbie
//			if (value.equals("sqrt") || value.equals("+/-") || value.equals("%"))
//				try {
//					this.output.setText(
//							String.valueOf(this.model.calculate(Double.parseDouble(this.output.getText()), value)));
//				}
//				catch (final CalculationError e) {
//					e.printStackTrace();
//					this.output.setText("ERR");
//				}
//
//			else {
//				this.operator = value;
//				// Jezeli jest juz pierwsza liczba i podana druga, przyciskajac operator matematyczny - oblicza i
//				// podaje
//				// wynik, przypisuje go do liczby1
//				if (this.number1 > 0) {
//					this.output.setText(String.valueOf(this.model.calculate(this.number1,
//							Double.parseDouble(this.output.getText()), this.operator)));
//					this.number1 = Double.parseDouble(this.output.getText());
//					this.start = true;
//				} // jezeli to pierwsza liczba, przypisuje ja d number1
//				else
//					this.number1 = Double.parseDouble(this.output.getText());
//				this.start = true;
//				// output.setText("");
//			}
//		// Jezeli operatorem jest znak =
//		else {
//			if (this.operator.isEmpty())
//				return;
//			prepareOutput(); // wynik
//
//		}
//	}
//
//	/**
//	 * Obsluga klawiatury
//	 *
//	 * @param event
//	 *        przyjmuje parametr po nacisnieciu przycisku
//	 */
//	@FXML
//	public void setOnKeyPressed(final KeyEvent event) {
//
//		String value = event.getText();
//
//		// Jezeli enter albo przycisk =, daj wynik
//		if ((event.getCode() == ENTER) || (event.getCode() == EQUALS)) {
//			if (this.operator.isEmpty())
//				return;
//			prepareOutput(); // wynik
//			return;
//		}
//		// Jezeli przycisk DEL kasuj
//		if (event.getCode() == DELETE) {
//			cleanOutput();
//			return;
//		}
//		// dodaj wartosc jezeli przycisk sie zgadza z tabela numeryczna
//		for (final KeyCode keyCode : this.numpadKey)
//			if (event.getCode() == keyCode) {
//				if (event.getCode() == DECIMAL)
//					value = ".";
//				addText(value);
//				break;
//			}
//		// dodaj opertator jezeli przysick zgadza sie z tabela operatorow
//		for (final KeyCode keyCode : this.operatorKey)
//			if (keyCode == event.getCode()) {
//				addOperator(value);
//				break;
//			}
//	}
//
//	public void cleanOutput() {
//
//		this.output.setText("");
//		this.operator = "";
//		this.number1 = 0;
//		this.start = true;
//	}
//
//	public void prepareOutput() {
//
//		// Pobiera pierwsza liczbe i przesyla druga wraz z operatorem
//		// String do sprawdzenia ostatnich cyfer
//		System.out.println(this.operator);
//		System.out.println(this.number1);
//		System.out.println(this.output.getText());
//		final String checkString = String
//				.valueOf(this.model.calculate(this.number1, Double.parseDouble(this.output.getText()), this.operator));
//
//		// Jezeli na koncu wyniku jest (.0) skasuj
//		if ((checkString.charAt(checkString.length() - 1) == '0')
//				&& (checkString.charAt(checkString.length() - 2) == '.'))
//			this.output.setText(checkString.substring(0, checkString.length() - 2));
//		else
//			this.output.setText(checkString);
//		this.number1 = 0;
//		this.operator = "";
//		this.start = true;
//	}
//
//}
