package ru.job4j.dreamjob.conroller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CandidateService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@ThreadSafe
@Controller
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/candidates")
    public String candidates(Model model, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        model.addAttribute("candidates", candidateService.findAll());
        return "candidates";
    }

    @GetMapping("/addCandidate")
    public String addCandidate(Model model, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        model.addAttribute("Candidate", new Candidate(0, "", "", new byte[1024], true, LocalDateTime.now()));
        return "addCandidate";
    }

    @PostMapping("/createCandidate")
    public String createCandidate(@ModelAttribute Candidate candidate,
                                  @RequestParam("file") MultipartFile file, HttpSession session, User user) throws Exception {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        candidate.setPhoto(file.getBytes());
        candidateService.add(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/updateCandidate/{candidateId}")
    public String updateCandidate(Model model, @PathVariable("candidateId") int id, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        model.addAttribute("candidate", candidateService.findById(id));
        return "updateCandidate";
    }

    @PostMapping("/updateCandidate")
    public String updateCandidate(@ModelAttribute Candidate candidate,
                                  @RequestParam("file") MultipartFile file, HttpSession session, User user) throws Exception {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        candidate.setPhoto(file.getBytes());
        candidateService.update(candidate);
        return "redirect:/candidates";
    }

    @GetMapping("/photoCandidate/{candidateId}")
    public ResponseEntity<Resource> download(@PathVariable("candidateId") Integer candidateId)  {
        Optional<Candidate> candidate = candidateService.findById(candidateId);
        return ResponseEntity.ok()
                .headers(new HttpHeaders())
                .contentLength(candidate.get().getPhoto().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayResource(candidate.get().getPhoto()));
    }

}
