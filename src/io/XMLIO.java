package io;


import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import card.CreditCard;
import cardReader.CreditCardCreater;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLIO extends DataIO {
	public List<CreditCard> readInputs(String path) {
		List<CreditCard> data = new ArrayList<CreditCard>();
		
		try {
            // Load and parse the XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(path); // Replace with the path to your XML file

            // Extract information about each credit card
            NodeList cardNodes = document.getElementsByTagName("CARD");
            for (int i = 0; i < cardNodes.getLength(); i++) {
                Node cardNode = cardNodes.item(i);

                String cardNumber = getChildValue(cardNode, "CARD_NUMBER");
                String expirationDate = getChildValue(cardNode, "EXPIRATION_DATE");
                String cardHolderName = getChildValue(cardNode, "CARD_HOLDER_NAME");
                data.add(CreditCardCreater.createCardInstance(cardNumber, expirationDate, cardHolderName));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return data;
	}
	
	private static String getChildValue(Node parentNode, String childTagName) {
        NodeList childNodes = parentNode.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE && childNode.getNodeName().equals(childTagName)) {
                return childNode.getTextContent();
            }
        }
        return null;
    }

	@Override
	public void writeOutputs(String path, List<CreditCard> data) {
		try {
			// Create a DocumentBuilder
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Create a new Document
            Document document = documentBuilder.newDocument();

            // Create the root element
            Element rootElement = document.createElement("CARDS");
            document.appendChild(rootElement);

            // Create elements for each credit card
            for (CreditCard creditCard : data) {
                Element cardElement = document.createElement("CARD");
                rootElement.appendChild(cardElement);

                // Create sub-elements for card details
                createElementWithTextContent(document, cardElement, "number", creditCard.getNumber());
                createElementWithTextContent(document, cardElement, "expireDate", creditCard.getExpireDate());
                createElementWithTextContent(document, cardElement, "holder", creditCard.getHolder());
                createElementWithTextContent(document, cardElement, "cardType", creditCard.getCardType());
            }

            // Transform the Document into XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(path));

            transformer.transform(domSource, streamResult);

            System.out.println("Credit cards written to " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	private static void createElementWithTextContent(Document document, Element parent, String elementName, String textContent) {
	    Element element = document.createElement(elementName);
	    element.appendChild(document.createTextNode(textContent));
	    parent.appendChild(element);
	}
}
