package ru.spbstu.fastafile;

/**
 * FastaRecord represents one record from fasta file format
 * Example:
 * >ce1cg 17 61
 * TAATGTTTGTGCTGGTTTTTGTGGCATCGGGCGAGAATAGCGCGTGGTGTGAAAGACTGTTTTTTTGATCGTTTTCACAA
 * AAATGGAAGTCCACAGTCTTGACAG
 */
public class FastaRecord {

    /**
     * id is next String after > character
     */
    private String id;
    /**
     * comments is a next characters after id in one string
     */
    private String comments;

    /**
     * chain is next strings after string with id and comments
     */
    private String chain;

    public FastaRecord() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getChain() {
        return chain;
    }

    public void setChain(String chain) {
        this.chain = chain;
    }
}
