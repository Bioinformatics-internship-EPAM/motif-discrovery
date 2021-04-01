package ru.spbstu;

import ru.spbstu.calculator.*;
import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.reporter.MotifReporter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;


public class Main {
    private static final String FASTA_FILENAME = "src/main/resources/PWMSample";
    public static void main(String[] args) {
        FastaFile file = new FastaFile();
        try {
            file.readFastaFile(FASTA_FILENAME);
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(0);
        }
        PWCalculator pwCalculator = new PWCalculator();
        PWMatrix matrix = pwCalculator.calculateMatrix(file, new CalculationStrategy(Constants.frequency,
                Constants.DEFAULT_WINDOW_SIZE, "loglikelihood"));
        SimilarityScoreCalculator smCalculator = new SimilarityScoreCalculator(Constants.DEFAULT_WINDOW_SIZE, file);
        DatasetScore resultScore = smCalculator.calculateScore(matrix);
        MotifReporter.reportTopFive(resultScore, System.out);
    }
}

