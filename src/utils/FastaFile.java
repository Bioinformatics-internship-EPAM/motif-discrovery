package ru.spbstu.utils;

import java.util.List;

/**
 * FastaFile represents fasta format file
 */
public class FastaFile {
    private String id;
    private List<String> comments;
    private List<String> chain;

    public FastaFile(String id, List<String> comments, List<String> chain) {
        this.id = id;
        this.comments = comments;
        this.chain = chain;
    }

    public FastaFile() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getChain() {
        return chain;
    }

    public void setChain(List<String> chain) {
        this.chain = chain;
    }
}
