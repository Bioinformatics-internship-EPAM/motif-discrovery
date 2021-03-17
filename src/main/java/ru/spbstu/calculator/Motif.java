package ru.spbstu.calculator;

/**
 Represent one Motif in FastaFile
 */
public class Motif {

    private final String recordID;
    // Position of the first symbol in parent record
    private final int position;
    private final double score;

    /**
    Construct immutable Motif struct
    */
    public Motif(String recordID, int position, double score) {
        this.recordID = recordID;
        this.position = position;
        this.score = score;
    }

    public String getRecordID() {
        return recordID;
    }

    public int getPosition() {
        return position;
    }

    public double getScore() {
        return score;
    }
}
