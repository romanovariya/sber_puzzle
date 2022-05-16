package ru.sberbank.sberpuzzle.path_two;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberbank.sberpuzzle.dto.InputDTO;

@Controller
@RequestMapping("/path2")
public class PathTwoController {
    @Value("${path-two.first}")
    String FIRST;
    @Value("${path-two.book}")
    String BOOK;
    @Value("${path-two.ssh}")
    String SSH;
    @Value("${path-two.final}")
    String FINAL;
    @Value("${path-two.congrats}")
    String CONGRATS;

    @GetMapping("/${path-two.first}")
    public String renderPaperPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_two/paper";
    }

    @PostMapping("/${path-two.first}")
    public String submitPaperPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if ("z1on0101".equals(text)) {
            return String.format("redirect:/path2/%s", BOOK);
        }
        return String.format("redirect:/path2/%s", FIRST);
    }

    @GetMapping("/${path-two.book}")
    public String renderBookPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_two/book";
    }

    @PostMapping("/${path-two.book}")
    public String submitBookPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if ("justonesecond".equals(text)) {
            return String.format("redirect:/path2/%s", SSH);
        }
        return String.format("redirect:/path2/%s", BOOK);
    }

    @GetMapping("/${path-two.ssh}")
    public String renderSshPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_two/ssh";
    }

    @PostMapping("/${path-two.ssh}")
    public String submitSshPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if (text.equals("disable-the-villainous-botnet")) {
            return String.format("redirect:/path2/%s", FINAL);
        }
        return String.format("redirect:/path2/%s", SSH);
    }

    @GetMapping("/${path-two.numbers}")
    public String renderNumbersPage() {
        return "path_two/numbers";
    }

    @GetMapping("/${path-two.hidden}")
    public String renderHiddenPage() {
        return "path_two/empty";
    }

    @GetMapping("${path-two.final}")
    public String renderPartTwoFinalPage(Model model) {
        return "path_two/path_two_final";
    }

    @GetMapping("/${path-two.congrats}")
    public String renderCongratsPartTwoPage(Model model) {
        return "path_two/congrats_two";
    }
}
