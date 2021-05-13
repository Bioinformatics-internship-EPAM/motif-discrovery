package ru.spbstu.calculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 Store scores of all possible motifs in fasta file
 */
public class DatasetScore {

    private List<ScoredMotif> data = new ArrayList<>();
    private final int windowSize;

    public DatasetScore(int windowSize) {
        this.windowSize = windowSize;
    }

    public void addAll(List<ScoredMotif> motifs) {
        data.addAll(motifs);
    }

    public void addAnother(DatasetScore ds) {
        data.addAll(ds.getData());
    }

    public int getWindowSize(){
        return windowSize;
    }

    public List<ScoredMotif> getData() {
        return data;
    }

    public ScoredMotif getMax() {
        return data.stream().max(ScoredMotif::compareTo).get();
    }
}
