package ru.spbstu.calculator;

/**
 * PWCalculator contains position weight matrix and method for calculating it
 */

public class PWCalculator {

    private final CalculationStrategy strategy;

    public PWCalculator(CalculationStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculate position weight matrix with necessary output format
     * @param motifSet - MotifSet
     * @return calculated position weight matrix
     */

    public PWMatrix calculateMatrix(MotifSet motifSet) {
        PWMatrix pwm = new PWMatrix(strategy.getSequenceLength());
        switch (strategy.getResultFrequency()) {
            case ABSOLUTE:
                calculateAbsoluteMatrix(pwm, motifSet);
                break;
            case RELATIVE:
                calculateRelativeMatrix(pwm, motifSet);
                break;
            case LOGLIKELIHOOD:
                calculateLogLikelihoodMatrix(pwm, motifSet, strategy.getFrequencyOfNucleotides());
                break;
        }
        return pwm;
    }

    private void calculateAbsoluteMatrix(PWMatrix pwm, MotifSet ms) {
        pwm.calculateAbsoluteFrequencies(ms);
    }
    private void calculateRelativeMatrix(PWMatrix pwm, MotifSet ms) {
        pwm.calculateAbsoluteFrequencies(ms);
        pwm.calculateRelativeFrequencies();
    }
    private void calculateLogLikelihoodMatrix(PWMatrix pwm, MotifSet ms, double frequencyOfNucleotides) {
        pwm.calculateAbsoluteFrequencies(ms);
        pwm.calculateRelativeFrequencies();
        pwm.calculateLogLikelihood(frequencyOfNucleotides);
    }
}
