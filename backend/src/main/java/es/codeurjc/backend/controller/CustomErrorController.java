package es.codeurjc.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletResponse response) {
        int statusCode = response.getStatus();

        if (statusCode == 404) {
            return "not-found";  // Página de error personalizada para error 404
        } else if (statusCode == 403) {
            return "access-denied";  // Página de error personalizada para error 403
        }

        // En otros casos, redirige a una página de error general
        return "not-found";
    }
}

//    @RequestMapping("/error/404")
//    public String handleNotFound() {
//        return "not-found"; // Redirigir a la página de error 404 personalizada
//    }
