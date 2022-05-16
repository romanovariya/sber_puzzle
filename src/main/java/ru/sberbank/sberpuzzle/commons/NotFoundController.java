package ru.sberbank.sberpuzzle.commons;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class NotFoundController implements ErrorController {
    @GetMapping
    public String error(Model model) {
        return "404";
    }
}
