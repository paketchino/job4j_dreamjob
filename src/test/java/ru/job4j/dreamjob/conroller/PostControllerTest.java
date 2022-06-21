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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import static org.mockito.Mockito.*;

public class PostControllerTest {

    @Test
    public void whenPost() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        Model model = mock(Model.class);
        HttpSession modelHttpSession = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(
                postService, cityService
        );
        String page = postController.posts(model, modelHttpSession);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "New Post", "desc", new City(1, "London"), LocalDateTime.now());
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        HttpSession session = mock(HttpSession.class);
        String page = postController.createPost(input, session);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenFindByIdPost() {
        Post input = new Post(1, "New Post", "desc", new City(1, "London"), LocalDateTime.now());
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostController postController = new PostController(postService, cityService);
        HttpSession session = mock(HttpSession.class);
        postService.add(input);
        when(postService.findById(1)).thenReturn(input);
        assertThat(Optional.of(input), is(postService.findById(1)));
    }

    @Test
    public void whenPostUpdate() {
        Post oldPost = new Post(1, "New Post", "desc", new City(1, "London"), LocalDateTime.now());
        Post updatePost = new Post(1, "Update Post", "Update Describing", oldPost.getCity(), LocalDateTime.now());
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        postService.add(oldPost);
        when(postService.add(oldPost)).thenReturn(Optional.of(oldPost));
        postService.update(updatePost);
        when(postService.update(updatePost)).thenReturn(Optional.of(updatePost));
        assertThat(postService.findAll(), is(updatePost));
    }

}