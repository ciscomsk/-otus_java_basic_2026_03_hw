package ru.otus.java.basic.hw31;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {


    public static Stream<Arguments> checkArrayArgs() {
        List<Arguments> args = new ArrayList<>();
        args.add(Arguments.arguments(true, new int[]{1, 2}));
        args.add(Arguments.arguments(false, new int[]{1, 1}));
        args.add(Arguments.arguments(false, new int[]{1, 3}));
        args.add(Arguments.arguments(true, new int[]{1, 2, 2, 1}));

        return args.stream();
    }

    @Test
    void filterArray() {
        assertArrayEquals(new int[]{2, 2}, App.filterArray(new int[]{1, 2, 1, 2, 2}));
    }

    @Test
    void filterArrayException() {
        assertThrows(RuntimeException.class, () -> App.filterArray(new int[]{2, 2, 2, 2}));
    }

    @ParameterizedTest
    @MethodSource("checkArrayArgs")
    void checkArray(boolean expected, int[] arr) {
        assertEquals(expected, App.checkArray(arr));
    }
}
