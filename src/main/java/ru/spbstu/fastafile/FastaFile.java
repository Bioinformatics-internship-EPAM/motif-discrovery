package ru.spbstu.fastafile;

import java.util.List;
/**
 * FastaFile contains instances of FastaRecord class in list
 * FastaFile represents such format https://meme-suite.org/meme/doc/examples/example-datasets/crp0.fna
 */
public class FastaFile {
    /**
     * fastaRecords contains list of records from fasta file
     * example of single record:
     * >ce1cg 17 61
     * TAATGTTTGTGCTGGTTTTTGTGGCATCGGGCGAGAATAGCGCGTGGTGTGAAAGACTGTTTTTTTGATCGTTTTCACAA
     * AAATGGAAGTCCACAGTCTTGACAG
     */
    private List<FastaRecord> fastaRecords;

    public FastaFile(List<FastaRecord> fastaRecords) {
        this.fastaRecords = fastaRecords;
    }

    public List<FastaRecord> getFastaRecords() {
        return fastaRecords;
    }
}
