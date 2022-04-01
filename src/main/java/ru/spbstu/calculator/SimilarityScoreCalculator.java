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

    public SimilarityScoreCalculator(FastaFile dataset) {
        this.windowSize = Constants.DEFAULT_WINDOW_SIZE;
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

        for (FastaRecord record: dataset.getFastaRecords()) {
            datasetScore.addAnother(calculateScoreForRecord(record, pwm));
        }

        return datasetScore;
    }

    public DatasetScore calculateScoreForRecord(FastaRecord record, PWMatrix pwm) {
        this.PWM = pwm;

        List<ScoredMotif> result = new ArrayList<>();

        int last = record.getChain().length() - windowSize;
        for (int i = 0; i <= last; i++) {
            double score = calculateScoreForWindow(record.getChain(), i);
            final ScoredMotif motif = new ScoredMotif(record.getId(), i, score);
            result.add(motif);
        }

        DatasetScore ds = new DatasetScore(windowSize);
        ds.addAll(result);
        return ds;
    }

    private double calculateScoreForWindow(String sequence, int position) {
        double sum = 0;

        for (int i = 0; i < windowSize; i++) {
            char ch = sequence.charAt(i + position);
            sum += PWM.getRowByNucleotide(ch).getByIndex(i);
        }

        return sum;
    }
}
