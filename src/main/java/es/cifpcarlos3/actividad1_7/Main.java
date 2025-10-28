package es.cifpcarlos3.actividad1_7;


import es.cifpcarlos3.actividad1_6.vo.Cancion;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.json.JsonMapper;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GestorCanciones {
    public static void main(String[] args) {
        Path dir_base = Path.of("src","main","java","es","cifpcarlos3","actividad1_6");
        Path txt = dir_base.resolve("canciones.txt");
        Path json = dir_base.resolve("canciones.json");

        List<Cancion> canciones = getCanciones(txt);
        getJson(canciones, json);
    }

    public static List<Cancion> getCanciones(Path txt) {
        List<Cancion> canciones = new ArrayList<>();
        int lineasLeidas = 0;
        int lineasValidas = 0;
        int lineaInvalidas = 0;
        try (var br = Files.newBufferedReader(txt, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                Cancion cancion = validacion(campos);
                if (cancion != null) {
                    canciones.add(cancion);
                    lineasValidas++;
                    lineasLeidas++;
                }
                else{
                    lineaInvalidas++;
                    lineasLeidas++;
                }
            }
        }
        catch (Exception e){
            System.out.println("Ha ocurrido un error al leer el fichero: " + e.getMessage());
        }
        System.out.println("Líneas leidas : " + lineasLeidas );
        System.out.println("Canciones válidas : " + lineasValidas);
        System.out.println("Líneas ignoradas : " + lineaInvalidas);
        return canciones;
    }
    public static Cancion validacion(String[] linea) {
        if (linea.length != 5) {
            return null;
        }
        for (int i = 0; i < linea.length; i++) {
            linea[i] = linea[i].trim();
        }
        try {
            int anyo = Integer.parseInt(linea[0]);
        }
        catch (Exception e){
            return null;
        }
        try {
            Boolean.parseBoolean(linea[4]);
        }
        catch (Exception e){
            return null;

        }
        Cancion cancion = new Cancion(Integer.parseInt(linea[0]), linea[1], linea[2], linea[3], Boolean.parseBoolean(linea[4]));
        return cancion;
    }
    public static void getJson(List<Cancion> canciones, Path json) {
        var mapper = JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).build();
        mapper.writeValue(json.toFile(), canciones);
        System.out.println("JSON generado en: " +"\n" +json);
    }
}
