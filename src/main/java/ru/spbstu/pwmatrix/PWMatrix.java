package ru.spbstu.pwmatrix;

import ru.spbstu.fastafile.FastaFile;
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
    private int startPosition;

    public PWMatrix(int sequenceLength) {
        this.pwmatrix = new HashMap<>();
        for (char nucle: Constants.NUCLEOTIDES) {
            this.pwmatrix.put(nucle, new PWRow(sequenceLength));
        }

        this.sequenceLength = sequenceLength;
        this.numberOfSequences = 0;
        this.startPosition = 0;

    }

    public PWRow getRowByNucleotide(char nucleotide) {
        return pwmatrix.get(nucleotide);
    }

    /**
     * Calculating log-likelihood position weight matrix
     *
     * @param fastaFile - fastafile with necessary records
     * @param frequencyOfNucleotides - general frequency of nucleotides in fastafile
     */

    protected void calculateMatrix(FastaFile fastaFile, double frequencyOfNucleotides) {
        calculateAbsoluteFrequencies(fastaFile);
        calculateRelativeFrequencies();
        calculateLogLikelihood(frequencyOfNucleotides);
    }

    /**
     * Fills matrix with count of nucleotides on every position
     * in sequences with length == sequenceLength
     *
     * @param fastaFile - fastafile
     */
    private void calculateAbsoluteFrequencies(FastaFile fastaFile) {
        for (FastaRecord record: fastaFile.getFastaRecords()) {
            analyseSequence(record.getChain().substring(startPosition, startPosition + sequenceLength));
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
     * @param freqOfNucleotides - general frequency of nucleotides in fastafile
     */
    private void calculateLogLikelihood(double freqOfNucleotides) {
        for (char nucle: Constants.NUCLEOTIDES) {
            for (int idx = 0; idx < sequenceLength; idx++) {
               pwmatrix.get(nucle).calculateLogFreq(idx, freqOfNucleotides);
            }
        }
    }
}
