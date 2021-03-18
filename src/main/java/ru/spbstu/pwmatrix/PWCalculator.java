package ru.spbstu.pwmatrix;

import ru.spbstu.fastafile.FastaFile;

/**
 * PWCalculator contains position weight matrix and method for calculating it
 */

public class PWCalculator {

    /**
     * Fastafile - fasta dataset
     * PWMatrix - position weight matrix
     */

    private final FastaFile fastaFile;
    private PWMatrix data;


    public PWCalculator(FastaFile fastaFile, int sequenceLength) {
        this.fastaFile = fastaFile;
        this.data = new PWMatrix(sequenceLength);
    }

    public PWMatrix calculateMatrix(double frequencyOfNucleotides) {
        data.calculateMatrix(fastaFile, frequencyOfNucleotides);
        return data;
    }
}
