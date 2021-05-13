package ru.spbstu.calculator;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MotifSet {

    List<Motif> data;
    final int sequenceLength;
    final FastaFile fastaFile;

    public MotifSet(FastaFile fastaFile, Policy policy, int sequenceLength) {
        this.sequenceLength = sequenceLength;
        this.fastaFile = fastaFile;

        this.data = new ArrayList<>();
        switch (policy) {
            case FIRST:
                fillWithFirst();
                break;
            case RANDOM:
                fillWithRandom();
                break;
        }
    }

    public int getSequenceLength() {
        return sequenceLength;
    }

    public FastaFile getFastaFile() {
        return fastaFile;
    }

    public List<Motif> getData() {
        return data;
    }

    public void setMotif(Motif motif) {
        Motif current = data.stream().
                filter(m -> m.getRecordID().equals(motif.getRecordID())).
                findAny()
                .get();
        data.set(data.indexOf(current), motif);
    }

    public enum Policy {
        FIRST,
        RANDOM
    }

    private void fillWithFirst() {
        final int startPosition = 0;
        for (FastaRecord record: fastaFile.getFastaRecords()) {
            data.add(new Motif(
                    record.getId(),
                    startPosition));
        }
    }

    private void fillWithRandom() {
        Random random = new Random();
        for (FastaRecord record: fastaFile.getFastaRecords()) {
            String chain = record.getChain();
            int startPosition = random.nextInt(chain.length() - sequenceLength);
            data.add(new Motif(
                    record.getId(),
                    startPosition));
        }
    }
}
