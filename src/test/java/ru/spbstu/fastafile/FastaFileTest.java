package ru.spbstu.fastafile;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.spbstu.reader.DefaultReader;
import ru.spbstu.reader.Reader;

import java.io.IOException;
import java.util.List;

public class FastaFileTest {

    @Test
    public void readFastaFileSuccess() throws Exception {
        final String fileName = "src/test/resources/test";
        Parser fastaFile = new FastaFileParser();
        Reader defaultReader = new DefaultReader(fileName);
        List<FastaRecord> fastaRecords = fastaFile.parseData(defaultReader.readData());
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
