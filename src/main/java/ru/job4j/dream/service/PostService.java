package ru.job4j.dream.service;

import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;
import java.util.Collection;

@Service
public class PostService {

    private final PostStore postStore;

    public PostService(PostStore postStore) {
        this.postStore = postStore;
    }

    public Collection<Post> findAll() {
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
