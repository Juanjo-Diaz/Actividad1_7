package es.cifpcarlos3.actividad1_7.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coches {
    private String marca;
    private String modelo;
    private String color;
    @JacksonXmlProperty(localName = "anio")
    private int anyo;
}
