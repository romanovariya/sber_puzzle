package ru.sberbank.sberpuzzle.path_three;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sberbank.sberpuzzle.dto.InputDTO;

@Controller
@RequestMapping("/path3")
public class PathThreeController {
    @Value("${path-three.docs}")
    String DOCS;
    @Value("${path-three.news}")
    String NEWS;
    @Value("${path-three.engineer}")
    String ENGINEER;
    @Value("${path-three.back-to-engineer}")
    String BACK_TO_ENGINEER;
    @Value("${path-three.flashlight}")
    String FLASHLIGHT;
    @Value("${path-three.lastone}")
    String LASTONE;
    @Value("${path-three.congrats}")
    String CONGRATS;

    @GetMapping("/${path-three.docs}")
    public String renderDocsPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_three/documentation";
    }
    @PostMapping("/${path-three.docs}")
    public String submitDocsPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if (text.equals("turn_off_botnet")) {
            return String.format("redirect:/path3/%s", NEWS);
        }
        return String.format("redirect:/path3/%s", DOCS);
    }

    @GetMapping("/${path-three.news}")
    public String renderNewsPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_three/manypages";
    }
    @PostMapping("/${path-three.news}")
    public String submitNewsPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if (text.equals("75-03-39")) {
            return String.format("redirect:/path3/%s", ENGINEER);
        }
        return String.format("redirect:/path3/%s", NEWS);
    }

    @GetMapping("/${path-three.engineer}")
    public String renderEngineerPage(Model model) {
        model.addAttribute("inputDTO", new InputDTO());
        return "path_three/laptop";
    }
    @PostMapping("/${path-three.engineer}")
    public String submitEngineerPage(@RequestParam("input") String input) {
        String text = input.toLowerCase().replace(" ", "");
        if (text.equals("3cb50a7b990798c0559b9032b5f8586f7de223217528591d6f3dce959180038b")) {
            return String.format("redirect:/path3/%s", BACK_TO_ENGINEER);
        }
        return String.format("redirect:/path3/%s", ENGINEER);
    }

    @GetMapping("/${path-three.back-to-engineer}")
    public String renderBackToEngineerPage() {
        return "path_three/page3";
    }

    @GetMapping("/${path-three.lastone}")
    public String renderFinalPage() {
        return "path_three/lastone";
    }

    @GetMapping("/${path-three.congrats}")
    public String renderCongratsPage() {
        return "path_three/congrats_three";
    }



}
