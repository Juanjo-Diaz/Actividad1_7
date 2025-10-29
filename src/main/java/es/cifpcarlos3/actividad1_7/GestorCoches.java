package es.cifpcarlos3.actividad1_7;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import es.cifpcarlos3.actividad1_7.vo.CatalogoCoches;
import es.cifpcarlos3.actividad1_7.vo.Coches;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCoches {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_7");
        Path entrada = base.resolve("coches.txt");
        Path salida = base.resolve("coches.xml");

        // Leer coches
        List<Coches> coches = getCoches(entrada);

        // Preparar catálogo
        CatalogoCoches catalogo = new CatalogoCoches();
        catalogo.getCoche().addAll(coches);

        // Serializar a XML
        try {
            var mapper = new XmlMapper();
            var writer = mapper.writerWithDefaultPrettyPrinter();
            writer.writeValue(salida.toFile(), catalogo);
            System.out.println("XML generado en: \n" + salida);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al escribir el XML: " + e.getMessage());
        }
    }

    public static List<Coches> getCoches(Path entrada) {
        List<Coches> lista = new ArrayList<>();
        int lineasLeidas = 0;
        int lineasValidas = 0;
        int lineasInvalidas = 0;
        try (var br = Files.newBufferedReader(entrada, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineasLeidas++;
                String[] campos = linea.split(",");
                Coches coche = validacion(campos);
                if (coche != null) {
                    lista.add(coche);
                    lineasValidas++;
                } else {
                    lineasInvalidas++;
                    System.out.println("Error en la línea " + lineasLeidas);
                }
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al leer el fichero: " + e.getMessage());
        }
        System.out.println("Leídas: " + lineasLeidas + " | Válidas: " + lineasValidas + " | Ignoradas: " + lineasInvalidas);
        return lista;
    }

    public static Coches validacion(String[] linea) {
        if (linea.length != 4) {
            return null;
        }
        for (int i = 0; i < linea.length; i++) {
            linea[i] = linea[i].trim();
        }
        try {
            Integer.parseInt(linea[3]); // anio en la 4ª posición
        } catch (Exception e) {
            return null;
        }
        return new Coches(linea[0], linea[1], linea[2], Integer.parseInt(linea[3]));
    }
}
