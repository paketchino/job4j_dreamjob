package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<AtomicInteger, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(new AtomicInteger(3), new Post(1, "Junior Java Job", "500к/сек", LocalDateTime.now()));
        posts.put(new AtomicInteger(2), new Post(2, "Middle Java Job", "800к/сек", LocalDateTime.now()));
        posts.put(new AtomicInteger(1), new Post(3, "Senior Java Job", "Бесценен", LocalDateTime.now()));
    }

    public void add(Post post) {
        posts.put(new AtomicInteger(post.getId()), post);
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
