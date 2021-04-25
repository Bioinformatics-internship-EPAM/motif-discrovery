package ru.spbstu.calculator;

import java.security.InvalidAlgorithmParameterException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

/**
 * PWMatrix represents position weights matrix for nucleotides
 * Contains map which keys are nucleotides and values - PWRow
 * Also store value of PWRow length
 */

public class PWMatrix {

    private final Map<Character, PWRow> pwmatrix;
    private final int sequenceLength;
    private int numberOfSequences;

    public PWMatrix(int sequenceLength) {
        this.pwmatrix = new HashMap<>();
        for (char nucle: Constants.NUCLEOTIDES) {
            this.pwmatrix.put(nucle, new PWRow(sequenceLength));
        }

        this.sequenceLength = sequenceLength;
        this.numberOfSequences = 0;
    }

    public PWRow getRowByNucleotide(char nucleotide) {
        return pwmatrix.get(nucleotide);
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    @Override
    public String toString() {
        String s = "";

        for (char nucle: Constants.NUCLEOTIDES) {
            s += nucle + " : ";
            for (double d : pwmatrix.get(nucle).getRow()) {
                s += new Formatter().format("%.2f", d);
                s += '\t';
            }
            s += '\n';
        }
        return s;
    }

    public double countDelta(PWMatrix other) throws InvalidAlgorithmParameterException {
        if (sequenceLength != other.getSequenceLength()) {
            throw new InvalidAlgorithmParameterException();
        }

        double delta = 0.0;

        for (char nucle: Constants.NUCLEOTIDES) {
            double[] thisRow = pwmatrix.get(nucle).getRow();
            double[] otherRow = other.getRowByNucleotide(nucle).getRow();

            for (int i = 0; i < thisRow.length; i ++) {
                if (!((Double.isInfinite(thisRow[i]) && Double.isInfinite(otherRow[i])))) {
                    delta += Math.pow((thisRow[i] - otherRow[i]), 2);
                }
            }
        }

        return delta;
    }

    /**
     * Fills matrix with count of nucleotides on every position
     * in sequences with length == sequenceLength
     *
     * @param motifSet - motifSet
     */
    protected void calculateAbsoluteFrequencies(MotifSet motifSet) {
        for (String chain: ChainsGetter.getChains(motifSet)) {
            analyseSequence(chain);
            numberOfSequences++;
        }
    }

    /**
     * Counting nucleotides in one sequence
     *
     * @param sequence motif from record
     */
    private void analyseSequence(String sequence) {
        int index = 0;
        for (char nucleotide: sequence.toCharArray()) {
            pwmatrix.get(nucleotide).increaseByOne(index);
            index++;
        }
    }

    /**
     * Calculates frequency for every nucleotide position /relatively
     */
    protected void calculateRelativeFrequencies() {
        for (char nucle: Constants.NUCLEOTIDES) {
            for (int idx = 0; idx < sequenceLength; idx++) {
                pwmatrix.get(nucle).divideByNumber(idx, numberOfSequences);
            }
        }
    }

    /**
     * Calculates logarithm e for every relative frequency divided by total
     * nucleotides frequency in motif
     *
     * @param freqOfNucleotides - general frequency of nucleotides in fastafile
     */
    protected void calculateLogLikelihood(double freqOfNucleotides) {
        for (char nucle: Constants.NUCLEOTIDES) {
            for (int idx = 0; idx < sequenceLength; idx++) {
               pwmatrix.get(nucle).calculateLogFreq(idx, freqOfNucleotides);
            }
        }
    }
}
