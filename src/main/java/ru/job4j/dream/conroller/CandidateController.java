package ru.job4j.dream.conroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.CandidateStore;

import java.time.LocalDateTime;

@Controller
public class CandidateController {

    private final CandidateStore candidatesStore = CandidateStore.instOff();

    @GetMapping("/candidates")
    public String candidates(Model model) {
        model.addAttribute("candidates", candidatesStore.findAll());
        return "candidates";
    }

    @GetMapping("/addCandidate")
    public String addCandidate(Model model) {
        model.addAttribute("Candidate", new Candidate(0, "", "", LocalDateTime.now()));
        return "addCandidate";
    }

    @PostMapping("/createCandidate")
    public String createCandidate(@ModelAttribute Candidate candidate) {
        candidatesStore.add(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/updateCandidate/{candidateId}")
    public String updateCandidate(Model model, @PathVariable("candidateId") int id) {
        model.addAttribute("candidate", candidatesStore.findById(id));
        return "updateCandidate";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidate(@ModelAttribute Candidate candidate) {
        candidatesStore.updateCandidate(candidate);
        return "redirect:/candidates";
    }
}
