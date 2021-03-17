package ru.spbstu.pwmatrix;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaRecord;

public class PWCalculator {

    private final FastaFile fastaFile;
    private final int sequenceLength;
    private PWData pwdata;


    public PWCalculator(FastaFile fastaFile, int sequenceLength) {
        this.fastaFile = fastaFile;
        this.sequenceLength = sequenceLength;
        this.pwdata = new PWData();
    }

    public PWData calculateMatrix(double frequencyOfNucleotides) {
        for (FastaRecord record: fastaFile.getFastaRecords()) {
            pwdata.addNewMatrix(calculateOneMatrix(record, frequencyOfNucleotides));
        }
        return pwdata;
    }

    private PWMatrix calculateOneMatrix(FastaRecord record, double frequencyOfNucleotides) {
        PWMatrix matrix = new PWMatrix(sequenceLength);
        matrix.calculateMatrix(record, frequencyOfNucleotides);
        return matrix;
    }
}
