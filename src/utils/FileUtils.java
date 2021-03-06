package ru.spbstu.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * FileUtils contains static method, which helps to read fasta file and parse it
 */
public class FileUtils {
    /**
     * readFastaFileAndReturnFastaMap parses file with fasta format and return map,
     * which contains fasta id as a key and FastaFile object as a value
     * @param fileName
     * @return
     * @throws Exception
     */
    public static Map<String, FastaFile> readFastaFileAndReturnFastaMap(String fileName) throws Exception {
        Path path = Paths.get(fileName);
        List<String> lines = Files.readAllLines(path);
        Map<String, FastaFile> fastaFileMap = new HashMap<>();
        for (int i = 0; i < lines.size(); ) {
            FastaFile fastaFile = new FastaFile();
            List<String> chains = new ArrayList<>();
            // first line expected with >
            if (lines.get(i).contains(">")) {
                List<String> idComments = new ArrayList<>();
                Collections.addAll(idComments, lines.get(i).split(" "));
                fastaFile.setId(idComments.remove(0));
                fastaFile.setComments(idComments);
                i++;
            } else {
                throw new Exception("Invalid fasta format file");
            }
            // After line with > we try to read nucleotide chains until the next line with > appears
            for (int j = i; j < lines.size(); j++) {
                if (lines.get(j).contains(">")) {
                    fastaFile.setChain(chains);
                    break;
                }
                chains.add(lines.get(j));
                i++;
            }
            fastaFileMap.put(fastaFile.getId(), fastaFile);
        }
        return fastaFileMap;
    }
}