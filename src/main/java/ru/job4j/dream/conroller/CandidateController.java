package ru.job4j.dream.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dream.store.CandidatesStore;

@Controller
public class CandidateController {

    private final CandidatesStore candidatesStore = CandidatesStore.instOff();

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", candidatesStore.findAll());
        return "candidates";
    }
}
