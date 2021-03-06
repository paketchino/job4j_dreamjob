package ru.job4j.dreamjob.conroller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@ThreadSafe
@Controller
public class UserController {

    private final UserService userService;

    private final CityService cityService;

    public UserController(UserService userService, CityService cityService) {
        this.userService = userService;
        this.cityService = cityService;
    }

    @GetMapping("/addUser")
    public String addUser(Model model,
                          HttpSession session,
                          @RequestParam(name = "fail", required = false) Boolean fail) {
        SetUser setUser = new SetUser();
        setUser.findUser(session);
        model.addAttribute("fail", fail != null);
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user, HttpSession session) {
        Optional regUser = userService.add(user);
        SetUser setUser = new SetUser();
        setUser.findUser(session);

        if (regUser.isEmpty()) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует");
            return "redirect:/fail";
        }
        return "redirect:/index";
    }

    @GetMapping("/fail")
    public String failRegistration(HttpSession session) {
        SetUser setUser = new SetUser();
        setUser.findUser(session);
        return "fail";
    }


    @GetMapping("/loginPage")
    public String loginPage(Model model,
                            @RequestParam(name = "fail", required = false) Boolean fail,
                            HttpSession session) {
        model.addAttribute("fail", fail != null);
        SetUser setUser = new SetUser();
        setUser.findUser(session);
        model.addAttribute("user", new User(
                0, "Enter email", "Enter password"));
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findUserByEmailAndPwd(
                user.getEmail(), user.getPassword()
        );
        if (userDb.isEmpty()) {
            return "redirect:/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        SetUser setUser = new SetUser();
        setUser.findUser(session);
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        SetUser setUser = new SetUser();
        setUser.findUser(session);
        session.invalidate();
        return "redirect:/loginPage";
    }

}
