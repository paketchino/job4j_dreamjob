package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.db.PostDBStore;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;
import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class PostService {

    private final PostDBStore postStore;
    private CityService cityService;

    public PostService(PostDBStore postStore) {
        this.postStore = postStore;
    }

    public List<Post> findAll() {
        List<Post> posts = postStore.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return postStore.findAll();
    }

    public void add(Post post) {
        postStore.add(post);
    }

    public Post findById(int id) {
        return postStore.findById(id);
    }

    public Post update(Post post) {
        return postStore.update(post);
    }

}
