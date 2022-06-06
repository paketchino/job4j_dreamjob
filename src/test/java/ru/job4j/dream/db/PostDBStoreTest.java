package ru.job4j.dream.db;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.persistence.db.PostDBStore;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "Java Job", "desc", true, new City(1, "Penza"), LocalDateTime.now());
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test(expected = NullPointerException.class)
    public void whenFindByIdWithoutPost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(15, "Middle", "Describe", true, new City(2, "Moscow"), LocalDateTime.now());
        Post postInDb = store.findById(post.getId());
        postInDb.getName();
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "Java Job", "desc", true, new City(1, "Penza"), LocalDateTime.now());
        store.add(post);
        Post postUpdate = new Post(post.getId(), "MTS", "desc", true, post.getCity(), post.getCreated());
        store.update(postUpdate);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(postUpdate.getName()));
    }

}