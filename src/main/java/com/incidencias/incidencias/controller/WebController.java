package com.incidencias.incidencias.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {
    // "./src/main/resources/static/index.html"
    @RequestMapping({
            "/incidencias/**",
            "/incidencia/**",
            "/profesores/**",
            "/departamentos/**",
            "/tipos_hardware/**",
            "/roles/**"
    })
    @ResponseBody
    public String welcome() {
        try {
            return new String(Files.readAllBytes(Paths.get("./src/main/resources/static/index.html")));
        } catch (IOException e) {
            return "Error 404: Page not found";
        }
    }
}
