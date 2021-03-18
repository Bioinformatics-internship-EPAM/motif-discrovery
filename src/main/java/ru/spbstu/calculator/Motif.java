package ru.spbstu.calculator;

/**
 Represent one Motif in FastaFile
 */
public class Motif implements Comparable<Motif>{
    private static final int EPS_SCORE_COMPARE = 100;
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

    @Override
    public int compareTo(Motif o) {
        return (int) (this.score - o.score) * EPS_SCORE_COMPARE;
    }
}
