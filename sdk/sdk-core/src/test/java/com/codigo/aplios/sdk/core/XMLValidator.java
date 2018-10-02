package com.codigo.aplios.sdk.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    public static void main(String[] args) {
        final XMLValidator XMLValidator = new XMLValidator();
        final boolean valid = XMLValidator.validate(XMLValidator.XML_FILE,
                XMLValidator.SCHEMA_FILE);

        System.out.printf("%s validation = %b.", XMLValidator.XML_FILE, valid);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private boolean validate(String xmlFile, String schemaFile) {
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            final Schema schema = schemaFactory.newSchema(new File(this.getResource(schemaFile)));

            final Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(this.getResource(xmlFile))));
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private String getResource(String filename) throws FileNotFoundException {

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
