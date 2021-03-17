package ru.spbstu.pwmatrix;

import ru.spbstu.fastafile.FastaRecord;

import java.util.HashMap;
import java.util.Map;

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
        this.sequenceLength = sequenceLength;
        this.numberOfSequences = 0;
        for (char nucle: Constants.NUCLEOTIDES) {
            this.pwmatrix.put(nucle, new PWRow(sequenceLength));
        }
    }

    public PWRow getRowByNucleotide(char nucleotide) {
        return pwmatrix.get(nucleotide);
    }

    protected void calculateMatrix(FastaRecord record, double frequencyOfNucleotides) {
        calculateAbsoluteFrequencies(record);
        calculateRelativeFrequencies();
        calculateLogLikelihood(frequencyOfNucleotides);
    }

    /**
     * Fills matrix with count of nucleotides on every position
     * in sequences with length == rowLength
     *
     * @param record
     */
    private void calculateAbsoluteFrequencies(FastaRecord record) {
        for (int start = 0; start + sequenceLength <= record.getChain().length(); start += sequenceLength) {
            numberOfSequences++;
            analyseSequence(record.getChain().substring(start, start + sequenceLength));
        }
    }

    /**
     * Counting nucleotides in one sequence
     *
     * @param sequence
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
    private void calculateRelativeFrequencies() {
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
     * @param freqOfNucleotides
     */
    private void calculateLogLikelihood(double freqOfNucleotides) {
        for (char nucle: Constants.NUCLEOTIDES) {
            for (int idx = 0; idx < sequenceLength; idx++) {
               pwmatrix.get(nucle).calculateLogFreq(idx, freqOfNucleotides);
            }
        }
    }
}
