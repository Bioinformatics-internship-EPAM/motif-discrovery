package ru.spbstu.fastafile;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class FastaFileTest {

    private FastaFileParser fastaFile;

    @Before
    public void setup() {
        fastaFile = new FastaFileParser();
    }

    @Test
    public void readFastaFileSuccess() throws IOException {
        final String fileName = "src/test/resources/test";
        List<FastaRecord> fastaRecords = fastaFile.parseData(fileName);
        Assert.assertEquals(2, fastaRecords.size());
        FastaRecord fastaRecord = fastaRecords.get(0);
        Assert.assertEquals("bglr1", fastaRecord.getId());
        Assert.assertEquals("76", fastaRecord.getComments());
        Assert.assertEquals("ACAAATCCCAATAACTTAATTATTGGGATTTGTTATATATAACTTTATAAATTCCTAAAATTACACAAAGTTAATAACTGTGAGCATGGTCATATTTTTATCAAT", fastaRecord.getChain());
        fastaRecord = fastaRecords.get(1);
        Assert.assertEquals("crp", fastaRecord.getId());
        Assert.assertEquals("63 423", fastaRecord.getComments());
        Assert.assertEquals("CACAAAGCGAAAGCTATGCTAAAACAGTCAGGATGCTACAGTAATACATTGATGTACTGCATGTATGCAAAGGACGTCAC", fastaRecord.getChain());
    }
}
