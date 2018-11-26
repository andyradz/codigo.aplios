package com.codigo.aplios.xbase.core;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SerpXmlValidator {

	private static Path fileLocation = Paths
			.get("d:/codigo.workspace/praca/aplios.toolbox/src/test/resources/OCL2017-02.xml");

	public static void main(final String argv[]) {

		try {

			final File fXmlFile = new File(
				"d:/codigo.workspace/praca/aplios.toolbox/src/test/resources/OCL2017-02.xml");

			final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			final Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement()
					.normalize();

			final NodeList nList = doc.getElementsByTagName("SellOrdrDtls");

			final String format = "|%010d|%s|%s|%s|%s|%-5s|%014.2f|%s|%014.2f|%014.2f|%s|";

			for (int temp = 0; temp < nList.getLength(); ++temp)
				try {

					final Node nNode = nList.item(temp);

					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						final Element eElement = (Element) nNode;

						System.out.printf(format, temp + 1, eElement.getElementsByTagName("FeeTblPosId")
								.item(0)
								.getTextContent(),

								eElement.getElementsByTagName("SellOrdrId")
										.item(0)
										.getTextContent(),

								eElement.getElementsByTagName("InvcRcvDtls")
										.item(0)
										.getChildNodes()
										.item(1)
										.getTextContent(),

								eElement.getElementsByTagName("InvcRcvDtls")
										.item(0)
										.getChildNodes()
										.item(3)
										.getTextContent(),

								eElement.getElementsByTagName("InvcRcvDtls")
										.item(0)
										.getChildNodes()
										.item(5)
										.getTextContent(),

								Double.valueOf(eElement.getElementsByTagName("FeeBas")
										.item(0)
										.getChildNodes()
										.item(1)
										.getTextContent()),

								eElement.getElementsByTagName("AddtlDtls")
										.item(0)
										.getChildNodes()
										.item(1)
										.getTextContent(),

								Double.valueOf(eElement.getElementsByTagName("FeeRate")
										.item(0)
										.getChildNodes()
										.item(1)
										.getTextContent()),

								Double.valueOf(eElement.getElementsByTagName("ClctdFee")
										.item(0)
										.getChildNodes()
										.item(0)
										.getTextContent()),

								eElement.getElementsByTagName("ClctdFee")
										.item(0)
										.getAttributes()
										.item(0)
										.getTextContent());
						System.out.println();
					}
				}
				catch (final Exception ex) {
					System.out.println("error");
				}
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}

}
