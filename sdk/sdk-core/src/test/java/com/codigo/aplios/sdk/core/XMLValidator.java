package com.codigo.aplios.sdk.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Vector;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidator {

	// -----------------------------------------------------------------------------------------------------------------
	public static final String XML_FILE = "customer.xml";

	// -----------------------------------------------------------------------------------------------------------------
	public static final String SCHEMA_FILE = "customer.xsd";

	// -----------------------------------------------------------------------------------------------------------------
	public static void main(final String[] args) throws IOException {

		final MyProperties props = new MyProperties();
		final InputStream stream = XMLValidator.class.getClassLoader()
				.getResourceAsStream("serpconfig.properties");

		props.load(stream);

		final Map<String, String> sortedMap = new TreeMap(
			props);

		// output sorted properties (key=value)
		String lastGroup = "";
		for (final String key : sortedMap.keySet()) {
			final int index = key.indexOf(".");

			if (-1 != index) {
				final String group = key.substring(0, index);

				if (!group.equals(lastGroup)) {
					lastGroup = group;
					final String value = sortedMap.get(group + ".path");
					System.out.println(group + "::" + value);

					for (final String item : sortedMap.keySet())
						if (item.startsWith(group))
							System.out.println(group + item.substring(index));
				}
			}
		}

		stream.close();

		final XMLValidator XMLValidator = new XMLValidator();
		XMLValidator.validate(com.codigo.aplios.sdk.core.XMLValidator.XML_FILE,
				com.codigo.aplios.sdk.core.XMLValidator.SCHEMA_FILE);

		// System.out.printf("%s validation = %b.", com.codigo.aplios.sdk.core.XMLValidator.XML_FILE,
		// valid);
	}

	// -----------------------------------------------------------------------------------------------------------------
	private boolean validate(final String xmlFile, final String schemaFile) {

		final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		try {
			final Schema schema = schemaFactory.newSchema(new File(
				getResource(schemaFile)));

			final Validator validator = schema.newValidator();
			validator.validate(new StreamSource(
				new File(
					getResource(xmlFile))));
			return true;
		}
		catch (SAXException | IOException e) {
			return false;
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	private String getResource(final String filename) throws FileNotFoundException {

		URL resource = this.getClass()
				.getClassLoader()
				.getResource("");
		resource = this.getClass()
				.getClassLoader()
				.getResource(filename);
		Objects.requireNonNull(resource);

		return resource.getFile();
	}

	// -----------------------------------------------------------------------------------------------------------------
}

class MyProperties extends Properties {
	private static final long serialVersionUID = 1L;

	@Override
	public Enumeration<Object> keys() {

		final Enumeration<Object> keysEnum = super.keys();
		final Vector<Object> keyList = new Vector<>();

		while (keysEnum.hasMoreElements())
			keyList.add(keysEnum.nextElement());

		Collections.sort(keyList, (o1, o2) -> o1.toString()
				.compareTo(o2.toString()));

		return keyList.elements();
	}
}
