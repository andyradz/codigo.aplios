package com.codigo.aplios.sdk;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SerpXmlValidator {

	public static void displayNode(Element parent, String childName, String label) {
		
		System.out.println(parent.getElementsByTagName("SellOrdrId")
				.item(0)
				.getTextContent());
		
		System.out.println(parent.getElementsByTagName("LndgBrrwgRef")
				.item(0)
				.getTextContent());
		
		System.out.println(parent.getElementsByTagName("FeeTblPosId")
				.item(0)
				.getTextContent());
		
		System.out.println(String.format("%016.6f", Double.valueOf(parent.getElementsByTagName("ClctdFee")
				.item(0)
				.getTextContent())));
	}

	@Test
	public void run() {

		try {

			final File fXmlFile = new File("d:/TS_DP0470_CCM.xml");

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
			String expression = "/KDPWDocument/invc.slo.001.05/SellOrdrDtls";
			// NodeList nList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
//			NodeList nList = (NodeList) xPath.compile(expression)
//					.evaluate(doc, XPathConstants.NODESET);

			expression = "count(//KDPWDocument/invc.slo.001.05/SellOrdrDtls)";

			Number computerCount = (Number) xPath.compile(expression)
					.evaluate(doc, XPathConstants.NUMBER);

			// then sets the value of each node using the name attribute.
			expression = "//SellOrdrId[@name]/@name";
			NodeList resultNodeList = (NodeList) xPath.compile(expression)
					.evaluate(doc, XPathConstants.NODESET);

			if (resultNodeList != null) {
				int vendorCount = resultNodeList.getLength();
				System.out.println("2. There are " + vendorCount + " vendors:");

				for (int i = 0; i < vendorCount; i++) {
					Node vendorNode = resultNodeList.item(i);
					String name = vendorNode.getNodeValue();
					System.out.println(name);
				}
			}

			Element rootElement = doc.getDocumentElement();
			NodeList modelNodeList = rootElement.getElementsByTagName("SellOrdrDtls");

			System.out.println("3. Computer models in inventory:");

			if (modelNodeList != null && modelNodeList.getLength() > 0) {

				for (int i = 0; i < modelNodeList.getLength(); i++) {
					
					Node node = modelNodeList.item(i);

					if (node.getNodeType() == Node.ELEMENT_NODE) {

						Element e = (Element) node;
						displayNode(e, "model", "Model           : ");
						System.out.println();
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

		// final NodeList nList = doc.getElementsByTagName("SellOrdrDtls");
		//
		// final String format =
		// "|'%04d'|'%s'|'%s'|'%7s'|'%s'|'%s|'%s|'%s'|'%s'|'%8s'|'%016.2f'|'%3s'|'%08.7f'|'%016.2f'|'%3s'|";
		//
		// for (int temp = 0; temp < nList.getLength(); ++temp) {
		//
		// final Node nNode = nList.item(temp);
		//
		// if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		//
		// final Element eElement = (Element) nNode;
		//
		// System.out.printf(format,
		// temp + 1,
		// eElement.getElementsByTagName("SellOrdrId")
		// .item(0)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("LndgBrrwgRef")
		// .item(0)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("FeeTblPosId")
		// .item(0)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("InvcRcvDtls")
		// .item(0)
		// .getChildNodes()
		// .item(1)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("InvcRcvDtls")
		// .item(0)
		// .getChildNodes()
		// .item(3)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("InvcRcvDtls")
		// .item(0)
		// .getChildNodes()
		// .item(5)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("InvcRcvDtls")
		// .item(0)
		// .getChildNodes()
		// .item(7)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("InvcRcvDtls")
		// .item(0)
		// .getChildNodes()
		// .item(9)
		// .getTextContent(),
		//
		// eElement.getElementsByTagName("InvcRcvDtls")
		// .item(0)
		// .getChildNodes()
		// .item(11)
		// .getTextContent(),
		//
		// Double.valueOf(eElement.getElementsByTagName("FeeBas")
		// .item(0)
		// .getChildNodes()
		// .item(1)
		// .getTextContent()),
		//
		// "PLN",
		//
		// Double.valueOf(eElement.getElementsByTagName("FeeRate")
		// .item(0)
		// .getChildNodes()
		// .item(1)
		// .getTextContent()),
		//
		// Double.valueOf(eElement.getElementsByTagName("ClctdFee")
		// .item(0)
		// .getChildNodes()
		// .item(0)
		// .getTextContent()),
		//
		// eElement.getElementsByTagName("ClctdFee")
		// .item(0)
		// .getAttributes()
		// .item(0)
		// .getTextContent());
		//
		// System.out.println();
		// }
		// }

		// } catch (final Exception e) {
		// System.out.println("błąd");
		// }
	}

}

class Test1 {

	public static void run() {

		Test1 test = new Test1();
		test.readXML();
	}

	private void readXML() {

		Document doc = null;
		try {
			doc = parseXML("d:/TS_DP0470.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (doc != null) {
			NodeList nList = doc.getElementsByTagName("SellOrdrDtls");
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				System.out.println("\nCurrent Element :" + i + '-' + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("SellOrdrId : " + eElement.getElementsByTagName("SellOrdrId")
							.item(0)
							.getTextContent());
					System.out.println(eElement.getElementsByTagName("FeeTblPosId")
							.item(0)
							.getTextContent());
					System.out.println(eElement.getElementsByTagName("FeeBas")
							.item(0)
							.getTextContent());
					System.out.println(eElement.getElementsByTagName("ClctdFee")
							.item(0)
							.getTextContent());

				}
				// System.out.println(child);
			}
		}
	}

	private Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(filePath);
		doc.getDocumentElement()
				.normalize();
		return doc;
	}
}
