package com.example.currency_server.parser;


import com.currencyServer.grpc.Currency;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyRateParserXml implements CurrencyRateParser{
    @Override
    public List<com.currencyServer.grpc.Currency> parse(String ratesAsString) {
        List<com.currencyServer.grpc.Currency> rates = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            StringReader reader = new StringReader(ratesAsString);
            Document doc = documentBuilder.parse(new InputSource(reader));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("Valute");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    com.currencyServer.grpc.Currency rate = Currency.newBuilder()
                            .setNumCode(element.getElementsByTagName("NumCode").item(0).getTextContent())
                            .setCharCode(element.getElementsByTagName("CharCode").item(0).getTextContent())
                            .setNominal(element.getElementsByTagName("Nominal").item(0).getTextContent())
                            .setName(element.getElementsByTagName("Name").item(0).getTextContent())
                            .setValue(element.getElementsByTagName("Value").item(0).getTextContent())
                            .build();
                    System.out.println(rate);
                    rates.add(rate);
                }
            }

        }catch (Exception e ){
            System.out.println(e.getMessage());
            return null;
        }
      return rates;
    }
}
