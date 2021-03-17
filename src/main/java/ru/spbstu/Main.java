package ru.spbstu;

import ru.spbstu.reporter.MotifReporter;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public String getPrintList() {
        return "Hello, motif discovery";
    }

    public static void main(String[] args) {
        List<String> motifs = new ArrayList<>();
        motifs.add("CAAA");
        motifs.add("DBBB");
        motifs.add("ACCC");
        motifs.add("BDDD");
        motifs.add("FDDD");
        motifs.add("ESDDD");
        MotifReporter.reportTopFive(motifs);
    }
}
