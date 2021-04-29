package ru.spbstu.calculator;

/**
 Represent one Motif in FastaFile
 */
public class Motif {
    private static final int EPS_SCORE_COMPARE = 100;
    private final String recordID;
    // Position of the first symbol in parent record
    private final int position;

    /**
    Construct immutable Motif struct
    */
    public Motif(String recordID, int position) {
        this.recordID = recordID;
        this.position = position;
    }

    public String getRecordID() {
        return recordID;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Motif{" +
                "recordID='" + recordID + '\'' +
                ", position=" + position +
                '}';
    }
}
