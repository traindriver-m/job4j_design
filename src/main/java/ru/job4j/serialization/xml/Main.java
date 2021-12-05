package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        Locomotive locomotive = new Locomotive(true, 8, "VL10", new Crew(2),
                new String[]{"fireExtinguisher", "brakeShoe"});
        JAXBContext context = JAXBContext.newInstance(Locomotive.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(locomotive, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Locomotive result = (Locomotive) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
