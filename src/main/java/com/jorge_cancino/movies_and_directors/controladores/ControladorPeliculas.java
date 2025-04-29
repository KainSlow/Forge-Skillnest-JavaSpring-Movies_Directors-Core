package com.jorge_cancino.movies_and_directors.controladores;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ControladorPeliculas {
    private static HashMap<String, String> listaPeliculas = new HashMap<>();

    public ControladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");
        listaPeliculas.put("Big Hero 6", "Don Hall");
    }

    @GetMapping("/")
    public String init() {
        return "<a href=\"/peliculas\">Ver Listado de Películas</a>";
    }

    @GetMapping("/peliculas")
    public String getPeliculas() {
        String result = "<h1>Lista de Películas:</h1> <ul>";
        var peliculasKeys = listaPeliculas.keySet();
        for (var pelicula : peliculasKeys) {
            var director = listaPeliculas.get(pelicula);
            result += "<li> " + getPeliElement(pelicula) + ", director: " + getDirectorElement(director) + " </li>";
        }
        result += "</ul>";

        return result;
    }

    @GetMapping("/peliculas/{nombre}")
    public String getPeliInfo(@PathVariable("nombre") String param) {
        if (!listaPeliculas.containsKey(param)) {
            return "La película no se encuentra en la lista.";
        }
        return "<h1>" + param + "</h1><p>Información de la película:</p>" + "<p>Título: \"" + param
                + "\", director: "
                + getDirectorElement(listaPeliculas.get(param)) + "</p>";
    }

    @GetMapping("/peliculas/director/{nombre}")
    public String getDirectorInfo(@PathVariable("nombre") String param) {

        if (!listaPeliculas.containsValue(param)) {
            return "No contamos con las películas de ese director.";
        }
        String result = "<h1>" + param + "</h1><p>Películas dirigidas:</p><ul>";

        var set = listaPeliculas.keySet();
        for (var key : set) {
            if (listaPeliculas.get(key).equals(param)) {
                result += "<li>" + getPeliElement(key) + "</li>";
            }
        }

        result += "</ul>";
        return result;
    }

    private String getPeliElement(String nombre) {
        if (!listaPeliculas.containsKey(nombre)) {
            return null;
        }
        return "<a href=\"/peliculas/" + nombre + "\">" + nombre + "</a>";
    }

    private String getDirectorElement(String nombre) {

        if (!listaPeliculas.containsValue(nombre)) {
            return null;
        }
        return "<a href=\"/peliculas/director/" + nombre + " \">" + nombre + "</a>";
    }

}
