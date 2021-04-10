package ru.spbstu.calculator;

import ru.spbstu.fastafile.FastaFile;
import ru.spbstu.fastafile.FastaRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MotifSet {

    private List<Motif> data;
    private final int sequenceLength;
    private final FastaFile fastaFile;

    public MotifSet(FastaFile fastaFile, Policy policy, int sequenceLength) {
        this.sequenceLength = sequenceLength;
        this.fastaFile = fastaFile;

        List<Motif> result = new ArrayList<>();
        switch (policy) {
            case FIRST:
                fillWithFirst(result);
                break;
            case RANDOM:
                fillWithRandom(result);
                break;
        }
        this.data = result;
    }

    public List<String> getChains() {
        List<String> result = new ArrayList<>();

        List<FastaRecord> records = fastaFile.getFastaRecords();
        for (int i = 0; i < records.size(); i++) {
            FastaRecord record = records.get(i);
            Motif motif = data.get(i);

            if (record.getId() == motif.getRecordID()) {
                result.add(record.getChain().substring(motif.getPosition(), motif.getPosition() + sequenceLength));
            }
        }
        return result;
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

    private void fillWithFirst(List<Motif> data) {
        final int startPosition = 0;
        for (FastaRecord record: fastaFile.getFastaRecords()) {
            data.add(new Motif(
                    record.getId(),
                    startPosition));
        }
    }

    private void fillWithRandom(List<Motif> data) {
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
