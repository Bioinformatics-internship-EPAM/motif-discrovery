package ru.spbstu.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 Store scores of all possible motifs in fasta file
 */
public class DatasetScore {

    private List<Motif> data = new ArrayList<>();
    private final int windowSize;

    public DatasetScore(int windowSize) {
        this.windowSize = windowSize;
    }

    public void addAll(List<Motif> motifs) {
        data.addAll(motifs);
    }

    public int getWindowSize(){
        return windowSize;
    }

    public List<Motif> getData() {
        return data;
    }
}
