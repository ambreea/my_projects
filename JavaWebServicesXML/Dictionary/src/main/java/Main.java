import dictionary.Dictionary;
import dictionary.ObjectFactory;
import dictionary.Word;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException, DatatypeConfigurationException {

//        ObjectFactory objectFactory = new ObjectFactory();
//        Word word = objectFactory.createWord();
//
//        word.setEnglish("leaf");
//        word.setRomanian("frunza");
//        word.setUpdateDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2015,03,12)));

        JAXBContext jaxbContext = JAXBContext.newInstance("dictionary");
//        Marshaller marshaller = jaxbContext.createMarshaller();
//        marshaller.marshal(word,System.out);
//        marshaller.marshal(word,new File("out.xml"));
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Dictionary dictionary = (Dictionary) unmarshaller.unmarshal(new File("dictionary.xml"));

        List<Word> words = dictionary.getWord();

        for (Word word: words) {
            System.out.println("english: " + word.getEnglish() );
            System.out.println("romanian: " + word.getRomanian() );
            System.out.println("update date: " + word.getUpdateDate() + "\n" );
        }
    }
}
