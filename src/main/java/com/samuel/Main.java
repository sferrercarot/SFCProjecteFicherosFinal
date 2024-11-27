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
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

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
}