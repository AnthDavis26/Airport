package com.solvd.airport.utils;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public final class Wrapper<T> {
    private List<T> objects;

    @XmlAnyElement(lax=true)
    public List<T> getAll() {
        return objects;
    }

    public void setAll(List<T> objects) {this.objects = objects;}
}
