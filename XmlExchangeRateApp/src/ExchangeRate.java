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

    private static String highestValueDate;
    private static String highestValueName;
    private static double highestValue;
    private static String lowestValueDate;
    private static String lowestValueName;
    private static double lowestValue = 1000;
    public static LocalDateTime ldt = LocalDateTime.now();
    public static double allTimePrice = 0;

    public static void main(String[] args) {

        // Instantiate the Factory
        for (int i = 0; i < 90; i++) {
            ldt = ldt.minusDays(1);
            DateTimeFormatter formatt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formatter = formatt.format(ldt);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse("https://www.cbr.ru/scripts/XML_daily_eng.asp?date_req="+formatter);
                // optional, but recommended
                // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                doc.getDocumentElement().normalize();
                System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
                System.out.println("------");

                // get <valute>
                NodeList list = doc.getElementsByTagName("Valute");
                for (int temp = 0; temp < list.getLength(); temp++) {
                    Node node = list.item(temp);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        // get valutes attribute
                        String id = element.getAttribute("ID");
                        // get text
                        String name = element.getElementsByTagName("Name").item(0).getTextContent();
                        String value = element.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.');
                        double currentValue = Double.parseDouble(value);
                        allTimePrice += currentValue;
                        if (currentValue > highestValue) {
                            highestValueDate = formatter;
                            highestValue = currentValue;
                            highestValueName = name;
                        }
                        else if (currentValue < lowestValue) {
                            lowestValueDate = formatter;
                            lowestValue = currentValue;
                            lowestValueName = name;
                        }
                        System.out.println("Highest valute name: " + highestValueName);
                        System.out.println("Highest valute exchange rate: " + highestValue);
                        System.out.println("Highest value date: " + highestValueDate);
                        System.out.println("Lowest valute name: " + lowestValueName);
                        System.out.println("Lowest valute exchange rate: " + lowestValue);
                        System.out.println("Lowest valute Date: " + lowestValueDate);
                        System.out.println(formatter+"\n");
                    }
                }
            } catch (SAXException | IOException | ParserConfigurationException e) {
                e.printStackTrace();
            }
            System.out.println("Roubles average rate for 90 days: " +allTimePrice/3060);
        }
    }
}

