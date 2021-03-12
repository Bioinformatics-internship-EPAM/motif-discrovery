package ru.spbstu.fastafile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * FastaFile contains instances of FastaRecord class in list
 * It encapsulates methods to read those records from file
 * More about fasta format https://en.wikipedia.org/wiki/FASTA_format
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

    /**
     * IDENTIFIER is a character from single record is started, used to separate such records in single fasta file
     */
    private final static String IDENTIFIER = ">";


    public FastaFile() {
        this.fastaRecords = new ArrayList<>();
    }

    /**
     * readFastaFile read stream of lines from file with given name using java.nio.Files and delegates parsing
     * to parseFastaLine function. Return fastaRecords field
     * @param filePath is a path to fasta file
     * @return List of string
     * @throws IOException
     */
    public List<FastaRecord> readFastaFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Stream<String> lines = Files.lines(path);
        parseFastaLine(lines);
        return this.fastaRecords;
    }

    /**
     * Getter for fastaRecords field
     * @return fastaRecords
     */
    public List<FastaRecord> getFastaRecords() {
        return fastaRecords;
    }

    /** parseFastaLine loop throw stream of lines. It creates 2 StringBuilder instances for fasta file header id and chains
     * If line contains IDENTIFIER, function fills id StringBuilder, else it fill chain StringBuilder
     * If line contains IDENTIFIER and chain StringBuilder is not empty, then function calls fillFastaRecord
     * @param lines
     */
    private void parseFastaLine(Stream<String> lines) {
        this.fastaRecords = new ArrayList<>();
        lines.forEach(s -> {
               if (s.startsWith(IDENTIFIER)) {
                   fillFastaHeader(s.substring(s.indexOf(IDENTIFIER) + 1));
                } else {
                    fillFastaRecordChain(s);
                }
            }
        );
    }

    /**
     * fillFastaHeader get fasta record id
     * It creates new instance of FastaRecord and fill its id and comments field with given values
     * New FastaRecord is appended to fastaRecords list
     * @param id
     */
    private void fillFastaHeader(String id) {
        List<String> idComments = new ArrayList<>();
        Collections.addAll(idComments, id.split(" "));
        FastaRecord fastaRecord = new FastaRecord();
        fastaRecord.setId(idComments.remove(0));
        fastaRecord.setComments(String.join(" ", idComments));
        this.fastaRecords.add(fastaRecord);
    }

    /**
     * fillFastaRecordChain fills last added fasta record chain
     * If fasta record has already had set chain, it concat with new given chain value
     * @param s
     */
    private void fillFastaRecordChain (String s) {
        FastaRecord fastaRecord = this.fastaRecords.remove(this.fastaRecords.size() - 1);
        String chain = fastaRecord.getChain();
        StringBuilder sb = new StringBuilder();
        if (chain != null) {
            sb.append(chain);
        }
        sb.append(s);
        fastaRecord.setChain(sb.toString());
        this.fastaRecords.add(fastaRecord);
    }
}