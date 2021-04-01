package ru.spbstu.reporter;

import ru.spbstu.calculator.DatasetScore;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Comparator;

public class MotifReporter {
    public static void reportTopFive(DatasetScore result, PrintStream output) {
        result.getData().stream().sorted(Comparator.reverseOrder()).limit(5).forEach(output::println);
    }
}
