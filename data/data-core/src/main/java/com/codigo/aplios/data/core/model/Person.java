package com.codigo.aplios.data.core.model;

import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;

public final class Person implements Formattable {

	public static void main(final String[] args) {

		final Person e = new Person(
			"Andrzej", "Radziszewski", (byte) 37);

		System.out.printf("%#s\n", e);
		System.out.printf("%20s\n", e);
		System.out.printf("%#30S\n", e);
		System.out.printf("%#.10S\n", e);
	}

	private String name;

	private String surname;

	private byte age;

	// -----------------------------------------------------------------------------------------------------------------
	public Person(final String name, final String surname, final byte age) {

		setName(name);
		setSurname(surname);
		setAge(age);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public String getSurname() {

		return this.surname;

	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * Właściwość pobiera informacje o nazwie osoby
	 *
	 * @return Informacja o nazwie osoby
	 */
	public String getName() {

		return this.name;
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * @param name
	 */
	private void setName(final String name) {

		this.name = name;
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void setSurname(final String surname) {

		this.surname = surname;
	}

	public byte getAge() {

		return this.age;
	}

	private void setAge(final byte age) {

		this.age = age;
	}

	@Override
	public String toString() {

		return "person" + "{" + "name:" + "\"" + getName() + "\"" + "," + "surname:" + "\"" + getSurname() + "\"" + ","
				+ "age:" + "\"" + getAge() + "\"" + "}";
	}

	@Override
	public void formatTo(final Formatter formatter, final int flags, final int width, final int precision) {

		String txt = this.surname;
		if ((flags & FormattableFlags.ALTERNATE) == FormattableFlags.ALTERNATE)
			txt += ' ' + this.name;
		String fs = "%";
		if ((flags & FormattableFlags.LEFT_JUSTIFY) == FormattableFlags.LEFT_JUSTIFY)
			fs += '-';
		if (width >= 0)
			fs += width;
		if (precision >= 0)
			fs += "." + precision;
		fs += ((flags & FormattableFlags.UPPERCASE) == FormattableFlags.UPPERCASE) ? "S" : "s";
		formatter.format(fs, txt);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
