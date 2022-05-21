package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private AtomicInteger postId = new AtomicInteger(4);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "500к/сек", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "800к/сек", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "Бесценен", LocalDateTime.now()));
    }

    public void add(Post post) {
        posts.put(post.setId(postId.getAndIncrement()), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public Post update(Post post) {
        return posts.replace(post.getId(), post);
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
