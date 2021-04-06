package ru.spbstu.reporter;

import ru.spbstu.calculator.DatasetScore;

import java.io.PrintStream;
import java.util.Comparator;

public class MotifReporter {
    public static void reportTopResults(DatasetScore result, PrintStream output, int count) {
        result.getData().stream().sorted(Comparator.reverseOrder()).limit(count).forEach(output::println);
    }
}
