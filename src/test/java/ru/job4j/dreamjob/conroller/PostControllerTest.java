package ru.job4j.dreamjob.conroller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;
import ru.job4j.dreamjob.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.mockito.Mockito.*;

public class PostControllerTest {

    private HttpSession session;

    @Ignore
    @Test
    public void whenPost() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        List<User> users = Arrays.asList(
                new User(1, "Riman", "dasdasd", "dasdajsd@gmail.com", new City(1, "Penza"))
        );
        UserService userService = mock(UserService.class);
        when(userService.findAll()).thenReturn(users);
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService, cityService
        );
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        String page = postController.posts(model, session, user);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

}