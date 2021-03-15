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
        DatasetScore datasetScore = new DatasetScore();

        for (FastaRecord record: dataset.getFastaRecords()) {
            datasetScore.addAll(calculateScoreForRecord(record, pwm));
        }

        return datasetScore;
    }

    private List<Motif> calculateScoreForRecord(FastaRecord record, PWMatrix pwm) {
        List<Motif> result = new ArrayList<>();

        int last = record.getChain().length() - windowSize;
        for (int i = 0; i <= last; i++) {
            result.add(new Motif(
                    record.getId(),
                    i,
                    windowSize,
                    calculateScoreForWindow(record.getChain(), i, pwm)
            ));
        }

        return result;
    }

    private double calculateScoreForWindow(String sequence, int position, PWMatrix pwm) {
        double sum = 0;

        for (int i = 0; i < windowSize; i++) {
            char ch = sequence.charAt(i + position);
            sum += pwm.getRow(ch)[i];
        }

        return sum;
    }
}
