package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private AtomicInteger postId = new AtomicInteger(4);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "500к/сек", true, new City(), LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "800к/сек", true, new City(), LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", "Бесценен", true, new City(), LocalDateTime.now()));
    }


    public void add(Post post) {
        post.setCreated(LocalDateTime.now());
        posts.put(post.setId(postId.getAndIncrement()), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public Post update(Post post) {
        post.setCreated(LocalDateTime.now());
        return posts.replace(post.getId(), post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
