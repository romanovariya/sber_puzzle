package ru.sberbank.sberpuzzle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sberbank.sberpuzzle.dto.InputDTO;

@Slf4j
@Controller
@RequestMapping({"/", "/index"})
public class MainController {

    @GetMapping
    public String renderMainPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "index";
    }

    @PostMapping
    public String mainPageForm(@RequestParam("input") String input) {
        switch (input) {
            case "COFFEESHOP": return "redirect:/path1/78d5fb36-217e-43c1-b386-d332afd590d7";
            case "123": return "redirect:/path2/alice";
            case "321": return "redirect:/path3/manypages";
            default: return "redirect:/";
        }
    }
}
