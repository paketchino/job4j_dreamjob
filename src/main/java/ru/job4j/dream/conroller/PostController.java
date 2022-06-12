package ru.job4j.dream.conroller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import ru.job4j.dream.service.CityService;
import ru.job4j.dream.service.PostService;

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
    public String posts(Model model, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/addPost")
    public String addPost(Model model, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        model.addAttribute("cities", cityService.getAllCities());
        return "addPost";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        City city = cityService.findById(post.getCity().getId());
        post.setCity(city);
        postService.add(post);
        return "redirect:/posts";
    }

    @GetMapping("/updatePost/{postId}")
    public String updatePost(Model model, @PathVariable("postId") int id, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("cities", cityService.getAllCities());
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        City city = cityService.findById(post.getCity().getId());
        post.setCity(city);
        postService.update(post);
        return "redirect:/posts";
    }

}
