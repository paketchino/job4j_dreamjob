package ru.job4j.conroller;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IndexControlTest {

    @Test
    public void as() {
        int x = 1;
        int y = 2;
        int c = x + y;
        assertThat(c, is(3));
    }

}