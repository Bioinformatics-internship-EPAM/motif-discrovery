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
    private final int rowLength;
    private int numberOfSequences;

    public PWMatrix(int size) {
        this.pwmatrix = new HashMap<>();
        this.rowLength = size;
        this.numberOfSequences = 0;
        for (char nucle: Constants.NUCLEOTIDES) {
            this.pwmatrix.put(nucle, new PWRow(size));
        }
    }

    public Map<Character, PWRow> getMatrix() {
        return this.pwmatrix;
    }

    /**
     * Fills matrix with count of nucleotides on every position
     * in sequences with length == rowLength
     *
     * @param record
     */
    public void calculateAbsoluteFrequencies(FastaRecord record) {
        for (int start = 0; start + rowLength <= record.getChain().length(); start += rowLength) {
            numberOfSequences++;
            analyseSequence(record.getChain().substring(start, start + rowLength));
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
    public void calculateRelativeFrequencies() {
        for (char nucle: Constants.NUCLEOTIDES) {
            for (int idx = 0; idx < rowLength; idx++) {
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
    public void calculateLogLikelihood(double freqOfNucleotides) {
        for (char nucle: Constants.NUCLEOTIDES) {
            for (int idx = 0; idx < rowLength; idx++) {
               pwmatrix.get(nucle).calculateLogFreq(idx, freqOfNucleotides);
            }
        }
    }

}
