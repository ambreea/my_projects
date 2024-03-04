import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("books.xml");

        Element root = document.getDocumentElement();
        NodeList books = root.getElementsByTagName("book");

        for (int i = 0; i < books.getLength(); i++) {

            Element book = (Element) books.item(i);

            NodeList author = book.getElementsByTagName("author");
            NodeList book_name = book.getElementsByTagName("book_name");
            NodeList type = book.getElementsByTagName("type");
            NodeList price = book.getElementsByTagName("price");
            NodeList publish_date = book.getElementsByTagName("publish_date");
            NodeList description = book.getElementsByTagName("description");

            for (int j = 0; j < author.getLength(); j++) {
                if ((Double.parseDouble(price.item(j).getTextContent()) > 10) && (Integer.parseInt(publish_date.item(j).getTextContent().substring(0, 4)) > 2005)) {
                    System.out.println("author: " + author.item(j).getTextContent());
                    System.out.println("book_name: " + book_name.item(j).getTextContent());
                    System.out.println("type: " + type.item(j).getTextContent());
                    System.out.println("price: " + price.item(j).getTextContent());
                    System.out.println("publish_date: " + publish_date.item(j).getTextContent());
                    System.out.println("description: " + description.item(j).getTextContent());
                    System.out.println();
                }
            }

        }
    }
}
