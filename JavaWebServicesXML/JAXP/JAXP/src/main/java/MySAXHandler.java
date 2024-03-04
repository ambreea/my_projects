import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class MySAXHandler extends DefaultHandler {

    private String actualNodeName = "";
    private boolean bookActive = false;
    private String activeText = "";
    private String author, book_name, type, description;
    private Double price = 0.0;
    private LocalDate publish_date;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing start");
        System.out.println();
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Parsing end");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("book")) {
            bookActive = true;
        }
        this.actualNodeName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (!bookActive) {
            return;
        }

        if (qName.equals("author") && !this.activeText.trim().equals("")) {
            author = "Author: " + this.activeText.trim();
        }
        if (qName.equals("book_name") && !this.activeText.trim().equals("")) {
            book_name = "Book title: " + this.activeText.trim();
        }
        if (qName.equals("type") && !this.activeText.trim().equals("")) {
            type = "Type: " + this.activeText.trim();
        }
        if (qName.equals("price") && !this.activeText.trim().equals("")) {
            price = Double.valueOf(this.activeText.trim());
        }

        if (qName.equals("publish_date") && !this.activeText.trim().equals("")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault());
            publish_date = LocalDate.parse(this.activeText.trim(), formatter);

        }

        if (qName.equals("description") && !this.activeText.trim().equals("")) {
            description = "Description: " + this.activeText.trim();
            if (price > 10 && publish_date.getYear() > 2005) {
                System.out.println(author + "\n" + book_name + "\n" + type + "\n" + "Price: " + price + "\n" + "Publishing date: " + publish_date
                        + "\n" + description + "\n");
            } else {
                return;
            }
        }

        activeText = "";


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (!bookActive) {
            return;
        }
        String txt = new String(ch, start, length);
        this.activeText += txt;
    }

}

