package ru.spbstu.pwmatrix;

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
    public String resultFrequency;


    CalculationStrategy(double frequencyOfNucleotides, int sequenceLength, String resultFrequency) {
        this.frequencyOfNucleotides = frequencyOfNucleotides;
        this.sequenceLength = sequenceLength;
        this.resultFrequency = resultFrequency;
    }
}
