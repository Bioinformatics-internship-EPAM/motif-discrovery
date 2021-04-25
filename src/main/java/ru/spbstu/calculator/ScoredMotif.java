package ru.spbstu.calculator;

public class ScoredMotif extends Motif implements Comparable<ScoredMotif> {
    private static final int EPS_SCORE_COMPARE = 100;
    private final double score;

    /**
     Construct immutable Motif struct
     */
    public ScoredMotif(String recordID, int position, double score) {
        super(recordID, position);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoredMotif o) {
        return Double.compare(this.score, o.score);
    }

    @Override
    public String toString() {
        return "Motif{" +
                "recordID='" + super.getRecordID() + '\'' +
                ", position=" + super.getPosition() +
                ", score=" + score +
                '}';
    }
}
