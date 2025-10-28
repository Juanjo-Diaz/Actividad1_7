package es.cifpcarlos3.actividad1_7;

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

    }
    public static List<Coches> getCoches(Path txt) {
        List<Coches> canciones = new ArrayList<>();
        int lineasLeidas = 0;
        int lineasValidas = 0;
        int lineaInvalidas = 0;
        try (var br = Files.newBufferedReader(txt, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                Coches coches = validacion(campos);
                if (coches != null) {
                    canciones.add(coches);
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
    public static Coches validacion(String[] linea) {
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
        Coches cancion = new Coches(linea[0], linea[1], linea[2], Integer.parseInt(linea[3]));
        return cancion;
    }
}
