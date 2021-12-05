package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "locomotive")
@XmlAccessorType(XmlAccessType.FIELD)
public class Locomotive {

    @XmlAttribute
    private boolean electric;
    @XmlAttribute
    private int motors;
    @XmlAttribute
    private String model;
    private Crew crew;

    @XmlElementWrapper(name = "equipments")
    @XmlElement(name = "equipment")
    private String[] equipments;

    public Locomotive() {
    }

    public Locomotive(boolean electric, int motors, String model, Crew crew, String[] equipments) {
        this.electric = electric;
        this.motors = motors;
        this.model = model;
        this.crew = crew;
        this.equipments = equipments;
    }

    @Override
    public String toString() {
        return "Locomotive{"
                + "electric=" + electric
                + ", motors=" + motors
                + ", model='" + model + '\''
                + ", crew=" + crew
                + ", equipments=" + Arrays.toString(equipments)
                + '}';
    }
}
