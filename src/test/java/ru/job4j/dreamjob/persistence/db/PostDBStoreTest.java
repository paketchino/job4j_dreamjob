package ru.job4j.dreamjob.persistence.db;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;


public class PostDBStoreTest {

    @Test
    public void whenCreatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "Java Job", "desc", new City(1, "Penza"), LocalDateTime.now(), true);
        store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Ignore
    @Test
    public void whenFindByIdWithoutPostThenOptionalEmpty() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(23, "Middle", "Describe", new City(2, "Moscow"), LocalDateTime.now(), true);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb, is(Optional.empty()));
    }

    @Test
    public void whenUpdatePost() {
        PostDBStore store = new PostDBStore(new Main().loadPool());
        Post post = new Post(1, "Java Job", "desc", new City(1, "Penza"), LocalDateTime.now(), true);
        store.add(post);
        Post postUpdate = new Post(post.getId(), "MTS", "desc", post.getCity(), post.getCreated(), true);
        store.update(postUpdate);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(postUpdate.getName()));
    }

}