/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.sdk.core.attribute;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Klasa typu atrybutu określającego grupę zmian realizowanych w danym pakiecie.
 * Ta definicja umożliwia dodawanie wielokronte atrybutu <code>@Changelog</code>
 * do definicji pakietu
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 * @category attribute
 */
@Target({ElementType.TYPE, ElementType.PACKAGE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Changelogs {

    /**
     * Właściwość określa listę zmian realizowanych w danym pakiecie
     *
     * @return Kolekcja elementów typu <code>@Changelog</code>
     */
    Changelog[] value();

}
