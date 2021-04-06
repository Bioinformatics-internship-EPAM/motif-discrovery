package ru.spbstu.calculator;

import ru.spbstu.calculator.Constants.calculationMethod;
/**
 * Represents parameters for calculation position weight matrix
 *
 * frequencyOfNucleotides - average frerquency of nucleotides
 * sequenceLength - length of sequence for one iteration
 * resultFrequency - format of output data
 */
public class CalculationStrategy {

    private final double frequencyOfNucleotides;
    private final int sequenceLength;
    private final calculationMethod resultFrequency;

    public static class Builder {

        private double frequencyOfNucleotides = Constants.frequency;
        private final int sequenceLength;
        private calculationMethod calculationType = calculationMethod.LOGLIKELIHOOD;

        public Builder(int sequenceLength) {
            this.sequenceLength = sequenceLength;
        }

        public Builder frequencyOfNucleotides(double value) {
            this.frequencyOfNucleotides = value;
            return this;
        }

        public Builder calculationType(calculationMethod value) {
            this.calculationType = value;
            return this;
        }

        public CalculationStrategy build() {
            return new CalculationStrategy(this.frequencyOfNucleotides, this.sequenceLength, this.calculationType);
        }
    }



    private CalculationStrategy(double frequencyOfNucleotides, int sequenceLength, calculationMethod resultFrequency) {
        this.frequencyOfNucleotides = frequencyOfNucleotides;
        this.sequenceLength = sequenceLength;
        this.resultFrequency = resultFrequency;
    }

    public double getFrequencyOfNucleotides() {
        return frequencyOfNucleotides;
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    public calculationMethod getResultFrequency() {
        return resultFrequency;
    }
}
