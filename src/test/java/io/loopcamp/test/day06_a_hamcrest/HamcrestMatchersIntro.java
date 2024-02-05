package io.loopcamp.test.day06_a_hamcrest;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {
    @Test
    public void numberTest() {
        assertThat(1 + 3, is(4));
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 15, is(equalTo(20)));
        assertThat(100 + 4, greaterThan(100));
        int num = 10 + 2;
        assertThat(num, not(5));
        assertThat(num, not(equalTo(5)));
    }

    @Test
    public void stringTest() {
        String word = "loopcamp";
        assertThat(word, is("loopcamp"));
        assertThat(word, equalTo("loopcamp"));
        assertThat(word, startsWith("loop"));
        assertThat(word, startsWithIgnoringCase("LOOP"));
        assertThat(word, endsWith("camp"));
        assertThat(word, endsWithIgnoringCase("CAMP"));
        assertThat(word, containsString("pc"));
        assertThat(word, containsStringIgnoringCase("PC"));
        assertThat("", emptyString());
        assertThat("", emptyOrNullString());
        assertThat(" ", blankString());
    }

    @Test
    public void listTest() {
        List<Integer> nums = Arrays.asList(5, 20, 1, 54, 0);
        List<String> words = Arrays.asList("java", "selenium", "git", "maven", "api");
        assertThat(nums, hasSize(5));
        assertThat(words, hasSize(5));
        assertThat(nums, hasItem(20));
        assertThat(words, hasItem("git"));
        assertThat(words, hasItems("git", "api"));
        assertThat(nums, containsInAnyOrder(1, 20, 0, 54, 5));      //We've to provide all elements
        assertThat(nums, everyItem(greaterThanOrEqualTo(0)));
        assertThat(words, everyItem(not(blankString())));
    }
}