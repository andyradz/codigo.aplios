package com.codigo.aplios.xbase.core.codepage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Typ adnotacji umożliwia definicję domyślnego kodowania znaków dla formatu danych XBase. Domyślne
 * kodowanie jest ustawiona jako ASCII.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@interface XbDefaultCharset {

	/**
	 * Właściwość wskazuje domyślny rodzaj kodowania znaków dla formatu danych XBase
	 *
	 * @return Standard kodowania znaków (domyślnie ASCII)
	 */
	XbCharset value() default XbCharset.ASCII;

}
