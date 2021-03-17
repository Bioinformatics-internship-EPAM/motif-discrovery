package ru.spbstu.reporter;

import java.io.Writer;
import java.util.List;

public class MotifReporter {
    public static void reportTopFive(List<String> results) {
        results.stream().sorted().limit(5).forEach(System.out::println);
    }
}
