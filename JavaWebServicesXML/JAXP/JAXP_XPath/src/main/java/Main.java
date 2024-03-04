import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
        LocalDate date = LocalDate.parse("2005-12-31", formatter);

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        XPathExpression xPathExpression = xPath.compile("//book[(price>10 )and (number(substring(publish_date,1,4))>2005)]/*");
        File file = new File("books.xml");
        InputSource inputSource = new InputSource(new FileInputStream(file));
        Object object = xPathExpression.evaluate(inputSource, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) object;

        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.print(nodeList.item(i).getNodeName() + ": ");
            System.out.println(nodeList.item(i).getTextContent());
            for (int j = 5; j < nodeList.getLength(); j += 6) {
                if (i == j)
                    System.out.println();
            }

        }
    }
}
