package ru.spbstu.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 Store scores of all possible motifs in fasta file
 */
public class DatasetScore {

    private List<Motif> data = new ArrayList<>();

    public DatasetScore() {
    }

    public void add(Motif motif) {
        data.add(motif);
    }

    public void addAll(List<Motif> motifs) {
        data.addAll(motifs);
    }

    //for testing
    public List<Motif> getData() {
        return data;
    }
}
