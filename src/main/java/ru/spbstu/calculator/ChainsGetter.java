package ru.spbstu.calculator;

import ru.spbstu.fastafile.FastaRecord;

import java.util.ArrayList;
import java.util.List;

public class ChainsGetter {
    public static List<String> getChains(MotifSet motifSet) {
        List<String> result = new ArrayList<>();

        List<FastaRecord> records = motifSet.getFastaFile().getFastaRecords();
        for (int i = 0; i < records.size(); i++) {
            FastaRecord record = records.get(i);
            Motif motif = motifSet.getData().get(i);

            if (record.getId() == motif.getRecordID()) {
                result.add(record.getChain().substring(motif.getPosition(), motif.getPosition() + motifSet.getSequenceLength()));
            }
        }
        return result;
    }
}
