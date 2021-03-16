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
    private final Map<String, PWRow> pwmatrix;
    private final int rowLength;

    public PWMatrix(int size) {
        this.pwmatrix = new HashMap<>();
        this.rowLength = size;
        this.pwmatrix.put("A", new PWRow(size));
        this.pwmatrix.put("C", new PWRow(size));
        this.pwmatrix.put("G", new PWRow(size));
        this.pwmatrix.put("T", new PWRow(size));
    }

    public Map<String, PWRow> getMatrix() {
        return this.pwmatrix;
    }

    /**
     * Fills matrix with count of nucleotides on every position
     * in sequences with length == rowLength
     *
     * @param record
     */
    public void calculateAbsoluteFrequencies(FastaRecord record) {
        char[] sequence = new char[rowLength];
        for (int start = 0; start + rowLength <= record.getChain().length(); start += rowLength) {
            record.getChain().getChars(start, start + rowLength, sequence, 0);
            analyseSequence(sequence);
        }
    }

    /**
     * Counting nucleotides in one sequence
     *
     * @param sequence
     */
    private void analyseSequence(char[] sequence) {
        int index = 0;
        for (char nucleotide: sequence) {
            pwmatrix.get(Character.toString(nucleotide)).increaseByOne(index);
            index++;
        }
    }

    /**
     * Calculates frequency for every nucleotide position /relatively
     *
     * @param numberOfSequences
     */
    public void calculateRelativeFrequencies(int numberOfSequences) {
        for (String nucle: Constants.NUCLEOTIDES) {
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
        for (String nucle: Constants.NUCLEOTIDES) {
            for (int idx = 0; idx < rowLength; idx++) {
               pwmatrix.get(nucle).calculateLogFreq(idx, freqOfNucleotides);
            }
        }
    }

}
