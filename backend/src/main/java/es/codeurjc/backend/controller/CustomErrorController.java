package es.codeurjc.backend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "not-found"; // Redirigir a la página de error personalizada
    }

    @RequestMapping("/error/404")
    public String handleNotFound() {
        return "not-found"; // Redirigir a la página de error 404 personalizada
    }
}
