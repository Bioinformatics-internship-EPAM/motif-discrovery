package ru.spbstu.calculator;

import ru.spbstu.fastafile.FastaFile;

/**
 * PWCalculator contains position weight matrix and method for calculating it
 */

public class PWCalculator {

    /**
     * Calculate position weight matrix with necessary output format
     * @param fastaFile - fasta file with dataset
     * @param strategy - strategy for calculation
     * @return calculated position weight matrix
     */

    public PWMatrix calculateMatrix(FastaFile fastaFile, CalculationStrategy strategy) {
        PWMatrix pwm = new PWMatrix(strategy.getSequenceLength());
        switch (strategy.getResultFrequency()) {
            case ABSOLUTE:
                calculateAbsoluteMatrix(pwm, fastaFile);
                break;
            case RELATIVE:
                calculateRelativeMatrix(pwm, fastaFile);
                break;
            case LOGLIKELIHOOD:
                calculateLogLikelihoodMatrix(pwm, fastaFile, strategy.getFrequencyOfNucleotides());
                break;
        }
        return pwm;
    }

    private void calculateAbsoluteMatrix(PWMatrix pwm, FastaFile fastaFile) {
        pwm.calculateAbsoluteFrequencies(fastaFile);
    }
    private void calculateRelativeMatrix(PWMatrix pwm, FastaFile fastaFile) {
        pwm.calculateAbsoluteFrequencies(fastaFile);
        pwm.calculateRelativeFrequencies();
    }
    private void calculateLogLikelihoodMatrix(PWMatrix pwm, FastaFile fastaFile, double frequencyOfNucleotides) {
        pwm.calculateAbsoluteFrequencies(fastaFile);
        pwm.calculateRelativeFrequencies();
        pwm.calculateLogLikelihood(frequencyOfNucleotides);
    }
}