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
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModificationCounter {

    static HashSet<String> nameSet = new HashSet<String>();
    static String regex = "\\d\\.\\d\\s[A-Z]{2}\\s.\\d{2,4} л\\.\\с..";

    public static void main(String[] args) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse("https://auto-export.s3.yandex.net/auto/price-list/catalog/cars.xml");

                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("mark");
                for (int temp = 0; temp < list.getLength(); temp++) {
                    NodeList list1 = doc.getElementsByTagName("folder");
                    System.out.println("Processing");
                    for (int temp1 = 0; temp < list1.getLength(); temp1++) {
                        NodeList list2 = doc.getElementsByTagName("modification");
                        Node node = list2.item(temp1);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element element = (Element) node;
                            String name = element.getElementsByTagName("modification_id").item(0).getTextContent();
                            Matcher matcher = Pattern.compile(regex).matcher(name);
                            if (matcher.find()) {
                                nameSet.add(matcher.group());
                            }
                        }
                    }
                 }
            } catch (SAXException | IOException | ParserConfigurationException | NullPointerException e) {
                e.printStackTrace();
            }
        System.out.println(nameSet.size());
    }
}

