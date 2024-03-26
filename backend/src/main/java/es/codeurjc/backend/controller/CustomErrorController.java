package es.codeurjc.backend.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletResponse response, Model model) {
        int statusCode = response.getStatus();

        String errorMessage = "";
        switch(statusCode) {
            case 400:
                errorMessage = "Bad Request";
                break;
            case 401:
                errorMessage = "Unauthorized";
                break;
            case 403:
                errorMessage = "Forbidden";
                break;
            case 404:
                errorMessage = "Not Found";
                break;
            case 500:
                errorMessage = "Internal Server Error";
                break;
            default:
                errorMessage = "Unknown Error";
        }
        model.addAttribute("errorCode", statusCode);
        model.addAttribute("errorMessage", errorMessage);
        return "errorTemplate";
    }

}

//    @RequestMapping("/error/404")
//    public String handleNotFound() {
//        return "not-found"; // Redirigir a la página de error 404 personalizada
//    }
