package ru.spbstu.calculator;

/**
 Represent one Motif in FastaFile
 */
public class Motif {

    private final String recordID;
    // Position of the first symbol in parent record
    private final int position;
    // Motif.length = window size
    private final int length;
    private final double score;

    /**
    Construct immutable Motif struct
    */
    public Motif(String recordID, int position, int length, double score) {
        this.recordID = recordID;
        this.position = position;
        this.length = length;
        this.score = score;
    }

    public String getRecordID() {
        return recordID;
    }

    public int getPosition() {
        return position;
    }

    public int getLength() {
        return length;
    }

    public double getScore() {
        return score;
    }
}
