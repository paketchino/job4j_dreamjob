package ru.job4j.dreamjob.conroller;

import ru.job4j.dreamjob.model.User;

import javax.servlet.http.HttpSession;

public class SetUser {

    public void findUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
    }
}
