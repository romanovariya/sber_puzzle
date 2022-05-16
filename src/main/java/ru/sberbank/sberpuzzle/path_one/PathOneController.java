package ru.sberbank.sberpuzzle.path_one;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberbank.sberpuzzle.dto.InputDTO;

@Controller
@RequestMapping("/path1")
public class PathOneController {

    @Value("${path-one.first}")
    String FIRST;

    @Value("${path-one.second}")
    String SECOND;

    @Value("${path-one.final}")
    String FINAL;

    @Value("${path-one.congrats}")
    String CONGRATS;

    @GetMapping("/${path-one.first}")
    public String renderFirstPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_one/path_one_first_page";
    }

    @PostMapping("/${path-one.first}")
    public String submitFirstPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if ("coffeeshop".equals(text)) {
            return String.format("redirect:/path1/%s", SECOND);
        }
        return String.format("redirect:/path1/%s", FIRST);
    }

    @GetMapping("/${path-one.second}")
    public String renderSecondPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_one/path_one_second_page";
    }

    @PostMapping("/${path-one.second}")
    public String submitSecondPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if ("moscow,vimishlennayastreet,3301".equals(text)) {
            return String.format("redirect:/path1/%s", FINAL);
        }
        return String.format("redirect:/path1/%s", SECOND);
    }

    @GetMapping("/${path-one.final}")
    public String renderLastPage(Model model) {
        return "path_one/path_one_final";
    }

    @GetMapping("/${path-one.congrats}")
    public String renderCongratsPage(Model model) {
        return "path_one/congrats_path_one";
    }
}
