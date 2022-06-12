package ru.job4j.dream.conroller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dream.model.User;
import javax.servlet.http.HttpSession;

@ThreadSafe
@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession session, User user) {
        SetUser setUser = new SetUser();
        setUser.findUser(user, session);
        model.addAttribute("user", user);
        return "index";
    }
}
