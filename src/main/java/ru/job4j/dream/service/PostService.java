package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.persistence.db.PostDBStore;
import ru.job4j.dream.model.Post;
import java.util.List;
import java.util.Optional;

@ThreadSafe
@Service
public class PostService {

    private final PostDBStore postStore;
    private CityService cityService;

    public PostService(PostDBStore postStore, CityService cityService) {
        this.postStore = postStore;
        this.cityService = cityService;
    }

    public List<Post> findAll() {
        List<Post> posts = postStore.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return posts;
    }

    public void add(Post post) {
        postStore.add(post);
    }

    public Optional<Post> findById(int id) {
        return postStore.findById(id);
    }

    public Optional<Post> update(Post post) {
        return postStore.update(post);
    }

}
