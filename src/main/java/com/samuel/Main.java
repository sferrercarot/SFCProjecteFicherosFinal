package com.samuel;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ClassLoaderTemplateResolver plantillas = new ClassLoaderTemplateResolver();
        plantillas.setPrefix("templates/");
        plantillas.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(plantillas);

        Context context = new Context();

        ListaEquipos equipos = cargarDatosDesdeJSON("src/main/resources/Equipos.json");

        if (equipos != null) {
            context.setVariable("equipos", equipos.getEquipos());

            String contenidoHTML = templateEngine.process("ListaEquipos", context);
            escribirHTML(contenidoHTML, "src/main/resources/static/index.html");

            for (Equipo equipo : equipos.getEquipos()) {
                Context contextoDetalles = new Context();
                contextoDetalles.setVariable("equipo", equipo);

                String detallesHTML = templateEngine.process("Equipo.html", contextoDetalles);
                String nombreArchivo = "src/main/resources/static/detalles_" + equipo.getNombre().replaceAll(" ", "_") + ".html";

                escribirHTML(detallesHTML, nombreArchivo);
            }
        } else {
            System.out.println("Error al cargar los datos desde el archivo JSON.");
        }
            ListaEquipos equiposRss = cargarDatosDesdeJSON("src/main/resources/Equipos.json");

            if (equiposRss != null) {
                String rutaRSS = "src/main/resources/static/EquiposRSS.xml";
                String titulo = "Equipos de Fútbol - RSS Feed";
                String descripcion = "RSS con información sobre equipos y jugadores destacados.";
                generaRSS(equiposRss, rutaRSS, titulo, descripcion);

                System.out.println("Generación de RSS completada.");
            } else {
                System.out.println("Error al cargar los datos desde el archivo JSON.");
            }
        }


    public static ListaEquipos cargarDatosDesdeJSON(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), ListaEquipos.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void escribirHTML(String contenido, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generaRSS(ListaEquipos equipos, String rutaRSS, String titulo, String descripcion) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaRSS))) {
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bw.write("<rss version=\"2.0\">\n");
            bw.write("<channel>\n");

            bw.write("<title>" + titulo + "</title>\n");
            bw.write("<link>http://localhost:8080</link>\n");
            bw.write("<description>" + descripcion + "</description>\n");

            for (Equipo equipo : equipos.getEquipos()) {
                bw.write("<item>\n");
                bw.write("<title>" + equipo.getNombre() + "</title>\n");
                bw.write("<link>http://localhost:8080/detalles_" + equipo.getNombre().replaceAll(" ", "_") + ".html</link>\n");
                bw.write("<description>\n");
                bw.write("Equipo fundado en " + equipo.getFundado() + " en la ciudad de " + equipo.getCiudad() +
                        ". Estadio: " + equipo.getEstadio() + ". Entrenador: " + equipo.getEntrenador() + ".\n");
                bw.write("</description>\n");

                for (Jugador jugador : equipo.getJugadores()) {
                    bw.write("<item>\n");
                    bw.write("<title>" + jugador.getNombre() + " (" + jugador.getPosicion() + ")</title>\n");
                    bw.write("<link>" + jugador.getImagen() + "</link>\n");
                    bw.write("<description>" + jugador.getPosicion() + ", Nacionalidad: " + jugador.getNacionalidad() +
                            ", Edad: " + jugador.getEdad() + " años.\n</description>\n");
                    bw.write("</item>\n");
                }

                bw.write("</item>\n");
            }

            bw.write("</channel>\n");
            bw.write("</rss>\n");

            System.out.println("RSS generado correctamente en " + rutaRSS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}