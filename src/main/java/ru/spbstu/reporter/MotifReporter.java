package ru.spbstu.reporter;

import ru.spbstu.calculator.DatasetScore;

public class MotifReporter {
    public static void reportTopFive(DatasetScore result) {
        result.getData().stream().sorted().limit(5).forEach(System.out::println);
    }
}
