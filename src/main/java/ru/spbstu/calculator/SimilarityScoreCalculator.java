package ru.spbstu.calculator;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaRecord;

import java.util.ArrayList;
import java.util.List;

/**
 * SimilarityScoreCalculator provides method to calculate similarity score for all records in fasta file
 */
public class SimilarityScoreCalculator {

    private final int windowSize;
    private final FastaFile dataset;

    private PWMatrix PWM;

    /**
     * Must be constructed one time before loop of finding the best motif
     */
    public SimilarityScoreCalculator(int windowSize, FastaFile dataset) {
        this.windowSize = windowSize;
        this.dataset = dataset;
    }

    /**
     * Count score for each window right to left
     * Example:
     * windowSize = 4
     * first sequence = "ACCTT"
     * second = "CTGC"
     *
     * output : [[score(^ACCT^T), score(A^CCTT^)],
     *           [score(^CTGC^)]]
     */
    public DatasetScore calculateScore(PWMatrix pwm) {
        DatasetScore datasetScore = new DatasetScore(windowSize);

        this.PWM = pwm;

        for (FastaRecord record: dataset.getFastaRecords()) {
            datasetScore.addAll(calculateScoreForRecord(record));
        }

        return datasetScore;
    }

    private List<Motif> calculateScoreForRecord(FastaRecord record) {
        List<Motif> result = new ArrayList<>();

        int last = record.getChain().length() - windowSize;
        for (int i = 0; i <= last; i++) {
            double score = calculateScoreForWindow(record.getChain(), i);
            final Motif motif = new Motif(record.getId(), i, score);
            result.add(motif);
        }

        return result;
    }

    private double calculateScoreForWindow(String sequence, int position) {
        double sum = 0;

        for (int i = 0; i < windowSize; i++) {
            char ch = sequence.charAt(i + position);
            sum += PWM.getRow(ch)[i];
        }

        return sum;
    }
}
