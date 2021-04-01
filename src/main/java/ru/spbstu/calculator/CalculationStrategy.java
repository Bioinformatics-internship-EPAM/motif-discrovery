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

    public double frequencyOfNucleotides;
    public int sequenceLength;
    public calculationMethod resultFrequency;


    public CalculationStrategy(double frequencyOfNucleotides, int sequenceLength, calculationMethod resultFrequency) {
        this.frequencyOfNucleotides = frequencyOfNucleotides;
        this.sequenceLength = sequenceLength;
        this.resultFrequency = resultFrequency;
    }
}
