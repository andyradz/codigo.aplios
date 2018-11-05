package com.codigo.aplios.sdk.core.attribute;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Klasa typu atrybutu określa powód zmiany kodu aplikacji. Atrybut zasadniczo będzie tworzył
 * historię zmian poszczególnych elementów pakietu. Changelog będzie zapisywany w pliku
 * package-info.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category attribute
 */
@Target({ ElementType.TYPE, ElementType.PACKAGE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Changelogs.class)
public @interface Changelog {

	/**
	 * Klasa typu wyliczeniowego określającego kategorię/rodzaj realizowanej zmiany
	 */
	public enum Category {

		/**
		 * Kategoria określa dodanie nowej funkcjonalności
		 */
		New,
		/**
		 * Kategoria określa dodanie zmiany do istniejącego kodu
		 */
		Change,
		/**
		 * Kategoria określa wykonanie rekompilacji elemnentu pakietu
		 */
		Recompile,
		/**
		 * Kategoria określa krytyczną poprawkę kodu aplikacji
		 */
		HotFix,
		/**
		 * Kategoria określa poprawkę kodu aplikacji
		 */
		FixBug,
		/**
		 * Kategoria określa zmianę postaci kodu podyktowaną optymalizacją lub koniecznością zwiększenia
		 * czytelności kodu
		 */
		Refactoring

	}

	/**
	 * Właściwość przedstawia datę rejestracji zgłoszonej zmiany
	 *
	 * @return Wartość daty
	 */
	public String date() default "dd.MM.yyyy";

	/**
	 * Właściwość przedstawia kategorię zgłosznej zmiany
	 *
	 * @return
	 */
	public Category category();

	/**
	 * Właściwość przedstawia autora przypisanego do wykonania zmiany
	 *
	 * @return Wartość tekstowa
	 */
	public Author[] authors();

	/**
	 * Właściwość przedstawia temat zgłoszonej zmiany
	 *
	 * @return Wartość tekstowa
	 */
	public String title();

	/**
	 * Właściwość przedstawia opis zgłoszonej zmiany
	 *
	 * @return Wartość tekstowa
	 */
	public String descrition();

}
