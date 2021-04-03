package ru.spbstu;


import ru.spbstu.calculator.*;
import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.reporter.MotifReporter;

import java.io.IOException;


public class Main {
    private static final String FASTA_FILENAME = "src/main/resources/PWMSample";
    public static void main(String[] args) throws IOException {
        FastaFile file = new FastaFile();
        file.readFastaFile(FASTA_FILENAME);
        PWCalculator pwCalculator = new PWCalculator();
        PWMatrix matrix = pwCalculator.calculateMatrix(file, new CalculationStrategy(Constants.frequency,
                Constants.DEFAULT_WINDOW_SIZE, Constants.calculationMethod.LOGLIKELIHOOD));
        SimilarityScoreCalculator smCalculator = new SimilarityScoreCalculator(Constants.DEFAULT_WINDOW_SIZE, file);
        DatasetScore resultScore = smCalculator.calculateScore(matrix);
        MotifReporter.reportTopFive(resultScore, System.out);
    }
}

