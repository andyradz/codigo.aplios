package com.codigo.aplios.sdk.serp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class LogOutputStream extends OutputStream {

	static Logger logger = Logger.getLogger(LogOutputStream.class.getName());

	private char lineSeparatorEnd;

	private String lineSeparator;

	private StringBuilder buffer;

	public LogOutputStream() {

		lineSeparatorEnd = 'n';
		lineSeparator = System.getProperty("line.separator");
		buffer = new StringBuilder();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
	public void write(int input) throws IOException {

		char ch = (char) input;
		this.buffer.append(ch);

		if (ch == this.lineSeparatorEnd) {
			// Check on a char by char basis for speed

			final String s = buffer.toString();

			if (s.indexOf(lineSeparator) != -1) {
				// The whole separator string is written
				logger.info(s.substring(0, s.length() - lineSeparator.length()));
				buffer.setLength(0);
			}
		}
	}
}

public class SerpXmlValidator {

	static {
		Properties properties;

		InputStream input = null;

		try {
			properties = new Properties();

			final String filename = "serpconfig.properties";
			input = SerpXmlValidator.class.getClassLoader()
					.getResourceAsStream(filename);

			if (input == null)
				System.out.println("Sorry, unable to find " + filename);

			// load a properties file from class path, inside static method
			properties.load(input);
			System.out.println(properties.getProperty("gnlinf.path"));

			// get the property value and print it out
			// System.out.println(properties.getProperty("database"));
			// System.out.println(properties.getProperty("dbuser"));
			// System.out.println(properties.getProperty("dbpassword"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void displayNode(int index, Element parent, String childName, String label) {

		//final String format = "|'%04d'|'%s'|'%s'|'%s'|'%3s'|'%s'|'%s'|'%s'|'%s'|'%8s'|'%016.2f'|'%s'|'%03.8f'|'%016.2f'|'%s'|";
		final String format = "|'%04d'|'%s'|'%5s'|'%s'|'%3s'|'%s'|'%s'|'%s'|'%s'|'%8s'|'%03.8f'|'%016.2f'|'%s'|";

		System.out.println(String.format(format,

				index,

				parent.getElementsByTagName("SellOrdrId")
						.item(0)
						.getTextContent(),

				parent.getElementsByTagName("LndgBrrwgRef")
						.item(0)
						.getTextContent(),

				parent.getElementsByTagName("FeeTblPosId")
						.item(0)
						.getTextContent(),

				parent.getElementsByTagName("InvcRcvDtls")
						.item(0)
						.getChildNodes()
						.item(1)
						.getTextContent(),

				parent.getElementsByTagName("InvcRcvDtls")
						.item(0)
						.getChildNodes()
						.item(3)
						.getTextContent(),

				parent.getElementsByTagName("InvcRcvDtls")
						.item(0)
						.getChildNodes()
						.item(5)
						.getTextContent(),

				parent.getElementsByTagName("InvcRcvDtls")
						.item(0)
						.getChildNodes()
						.item(7)
						.getTextContent(),

				parent.getElementsByTagName("InvcRcvDtls")
						.item(0)
						.getChildNodes()
						.item(9)
						.getTextContent(),

				parent.getElementsByTagName("InvcRcvDtls")
						.item(0)
						.getChildNodes()
						.item(11)
						.getTextContent(),
						

//				Double.valueOf(parent.getElementsByTagName("FeeBas")
//						.item(0)
//						.getChildNodes()
//						.item(1)
//						.getTextContent()),
//
//				parent.getElementsByTagName("FeeBas")
//						.item(0)
//						.getChildNodes()
//						.item(1)
//						.getAttributes()
//						.item(0)
//						.getTextContent(),

				Double.valueOf(parent.getElementsByTagName("FeeRate")
						.item(0)
						.getChildNodes()
						.item(1)
						.getTextContent()),

				Double.valueOf(parent.getElementsByTagName("ClctdFee")
						.item(0)
						.getTextContent()),

				parent.getElementsByTagName("ClctdFee")
						.item(0)
						.getAttributes()
						.item(0)
						.getTextContent()));
		;

	}

	@Test
	public void run() {

		try {

			final File fXmlFile = new File("d:/07000001.XML");

			final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			final Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement()
					.normalize();

			XPath xPath = XPathFactory.newInstance()
					.newXPath();
			String expression = "//KDPWDocument/invc.slo.001.05/SellOrdrDtls";
			// NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			// NodeList nList = (NodeList) xPath.compile(expression)
			// .evaluate(doc, XPathConstants.NODESET);

			expression = "count(//KDPWDocument/invc.slo.001.05/SellOrdrDtls)";
			Number computerCount = (Number) xPath.compile(expression)
					.evaluate(doc, XPathConstants.NUMBER);
			System.out.println(String.format("Ilość rekordów w pliku: %.0f", computerCount));
			expression = "sum(//KDPWDocument/invc.slo.001.05/SellOrdrDtls/ClctdFee)";
			Number computerSum = (Number) xPath.compile(expression)
					.evaluate(doc, XPathConstants.NUMBER);

			BigDecimal decimal = new BigDecimal(computerSum.doubleValue()).setScale(2, RoundingMode.HALF_UP);
			System.out.println("Suma opłaty: " + decimal);

			expression = "sum(//KDPWDocument/invc.slo.001.05/SellOrdrDtls/FeeBas/BVal)";
			computerSum = (Number) xPath.compile(expression)
					.evaluate(doc, XPathConstants.NUMBER);

			decimal = new BigDecimal(computerSum.doubleValue()).setScale(2, RoundingMode.HALF_UP);
			System.out.println(String.format("Suma pożyczek: %-21.2f ", decimal));
			System.out
					.println("--------------------------------------------------------------------------------------");

			// then sets the value of each node using the name attribute.
			expression = "//SellOrdrId[@name]/@name";
			NodeList resultNodeList = (NodeList) xPath.compile(expression)
					.evaluate(doc, XPathConstants.NODESET);

			if (resultNodeList != null) {
				int vendorCount = resultNodeList.getLength();

				for (int i = 0; i < vendorCount; i++) {
					Node vendorNode = resultNodeList.item(i);
					String name = vendorNode.getNodeValue();
					System.out.println(name);
				}
			}

			Element rootElement = doc.getDocumentElement();
			NodeList modelNodeList = rootElement.getElementsByTagName("SellOrdrDtls");

			if (modelNodeList != null && modelNodeList.getLength() > 0) {

				for (int i = 0; i < modelNodeList.getLength(); i++) {

					Node node = modelNodeList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element e = (Element) node;
						displayNode(i + 1, e, "model", "Model           : ");
						// System.out.println();
					}
				}
			}

		} catch (SAXException e) {
			// Even though we are using a DOM parser a SAXException is thrown
			// if the DocumentBuilder cannot parse the XML file
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
}
