import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExchangeRate {

    public static LocalDateTime ldt = LocalDateTime.now();

    public static void main(String[] args) {

        DateTimeFormatter formatt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatter = formatt.format(ldt);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("https://www.cbr.ru/scripts/XML_daily_eng.asp");
            doc.getDocumentElement().normalize();
            NodeList list = doc.getElementsByTagName("Valute");
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // get text
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String value = element.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.');
                    String nominal = element.getElementsByTagName("Nominal").item(0).getTextContent();
                    double value1 = Double.parseDouble(value);
                    double nominal1 = Double.parseDouble(nominal);
                    double currentValue = (value1/nominal1);
                    if (name.equals("Japanese Yen")) {
                        System.out.println("Exchange rate of Japanese Yen for " + formatter+":\n");
                        System.out.println(currentValue);
                    }
                }
            }

        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

    }
}