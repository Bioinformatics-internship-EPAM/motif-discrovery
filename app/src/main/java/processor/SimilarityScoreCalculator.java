package processor;

import java.util.Map;

/**
 * SimilarityScoreCalculator provides method to calculate similarity score
 */
public class SimilarityScoreCalculator {

    private final int windowSize;
    private final FastaDataset dataset;

    /**
     * Must be constructed one time before loop of finding the best motif
     */
    public SimilarityScoreCalculator(int windowSize, FastaDataset dataset) {
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
     * result : [[score(^ACCT^T), score(A^CCTT^)],
     *           [score(^CTGC^)]]
     */
    public DatasetScore calculateScore(PWMatrix pwm) {
        DatasetScore result = new DatasetScore();

        for (Map.Entry<String, String> entry : dataset.data().entrySet()) {
            result.add(entry.getKey(), calculateScoreForSequence(entry.getValue(), pwm));
        }

        return result;
    }

    private double[] calculateScoreForSequence(String sequence, PWMatrix pwm) {
        double[] result = new double[sequence.length() - windowSize + 1];

        for (int i = 0; i <= sequence.length() - windowSize; i++) {
            result[i] = calculateScoreForWindow(sequence.substring(i, i + windowSize), pwm);
        }

        return result;
    }

    private double calculateScoreForWindow(String window, PWMatrix pwm) {
        double sum = 0;
        int i = 0;

        for (char ch : window.toCharArray()) {
            sum += pwm.getRow(ch)[i];
            i++;
        }

        return sum;
    }
}
