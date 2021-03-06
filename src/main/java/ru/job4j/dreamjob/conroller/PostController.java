package ru.job4j.dreamjob.conroller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class PostController {

    private final PostService postService;
    private final CityService cityService;

    public PostController(PostService postService, CityService cityService) {
        this.postService = postService;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model, HttpSession session) {
        findUser(model, session);
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/addPost")
    public String addPost(Model model, HttpSession session) {
        findUser(model, session);
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post, HttpSession session) {
        SetUser setUser = new SetUser();
        setUser.findUser(session);
        City city = cityService.findById(post.getCity().getId());
        post.setCity(city);
        postService.add(post);
        return "redirect:/posts";
    }

    @GetMapping("/updatePost/{postId}")
    public String updatePost(Model model, @PathVariable("postId") int id, HttpSession session) {
        findUser(model, session);
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.update(post);
        return "redirect:/posts";
    }

    private void findUser(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("??????????");
        }
        model.addAttribute("user", user);
    }
}
