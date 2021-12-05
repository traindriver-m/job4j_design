package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "crew")
public class Crew {

    @XmlAttribute
    private int quantity;

    public Crew() {
    }

    public Crew(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Crew{"
                + "quantity=" + quantity
                + '}';
    }
}
