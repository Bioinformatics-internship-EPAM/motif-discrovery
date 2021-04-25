package ru.spbstu;


import ru.spbstu.calculator.*;
import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.reporter.MotifReporter;

import java.io.IOException;


public class Main {

    private static final int TOP_COUNT = 5;
    private static final String FASTA_FILENAME = "src/main/resources/PWMSample";


    public static void main(String[] args) throws IOException {
        FastaFile file = new FastaFile();
        file.readFastaFile(FASTA_FILENAME);

        PWCalculator pwCalculator = new PWCalculator();
        PWMatrix matrix = pwCalculator.calculateMatrix(file, CalculationStrategy.builder().build());
        SimilarityScoreCalculator smCalculator = new SimilarityScoreCalculator(file);
        DatasetScore resultScore = smCalculator.calculateScore(matrix);

        MotifReporter.reportTopResults(resultScore, System.out, TOP_COUNT);
    }
}
