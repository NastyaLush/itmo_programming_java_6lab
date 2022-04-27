package test.laba.common.dataClasses;



import test.laba.common.IO.Console;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Comparator;

/**
 * data class
 */
@XmlRootElement(name = "location")
@XmlType(propOrder = { "x", "y", "name" })
public class Location implements Comparable<Location>, Serializable {
    private Long x;
    private Integer y;
    private String name; //Строка не может быть пустой, Поле не может быть null

    public Location() {
    }
    @SuppressWarnings("all")
    /**
     * the constructor create location object
     */
    public Location(Long x, Integer y, String s) {
        if (x != null) {
            this.x = x;
        }
        if (y != null) {
            this.y = y;
        }
        if (s == null || s.isEmpty()) {
           /* console.printError("Строка не может быть пустой, Поле не может быть null\n"
                    + "ваше значение: " + s);*/
            // TODO: 16.04.2022 error handling
        }
        name = s;
    }

    public Long getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getName() {
        return name;
    }

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location"
                + "x=" + x
                + ", y=" + y
                + ", name=" + name;
    }
    public boolean isRightLocation() {
        return name != null && !"".equals(name);
    }

    @Override
    public int compareTo(Location o) {
        return Comparator.comparing(Location :: getX).
                thenComparing(Location :: getY).
                thenComparing(Location :: getName).compare(this, o);
    }
}
