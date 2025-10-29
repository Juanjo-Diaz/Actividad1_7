package es.cifpcarlos3.actividad1_7.vo;

import lombok.Data;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "catalogo")
public class CatalogoCoches {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "coche")
    private List<Coches> coche = new ArrayList<>();

    public void add(Coches c) {
        this.coche.add(c);
    }
}
