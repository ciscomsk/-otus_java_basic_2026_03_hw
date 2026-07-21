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
    public static Stream<Arguments> filterArrayArgs() {
        List<Arguments> args = new ArrayList<>();
        args.add(Arguments.arguments(new int[]{2, 2}, new int[]{1, 2, 1, 2, 2}));
        args.add(Arguments.arguments(new int[]{}, new int[]{2, 2, 2, 1}));

        return args.stream();
    }

    public static Stream<Arguments> checkArrayArgs() {
        List<Arguments> args = new ArrayList<>();
        args.add(Arguments.arguments(true, new int[]{1, 2}));
        args.add(Arguments.arguments(false, new int[]{1, 1}));
        args.add(Arguments.arguments(false, new int[]{1, 3}));
        args.add(Arguments.arguments(true, new int[]{1, 2, 2, 1}));

        return args.stream();
    }

    @ParameterizedTest
    @MethodSource("filterArrayArgs")
    void filterArray(int[] expected, int[] origin) {
        assertArrayEquals(expected, App.filterArray(origin));
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
