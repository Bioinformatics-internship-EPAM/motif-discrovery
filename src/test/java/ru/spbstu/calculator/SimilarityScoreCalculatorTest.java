package ru.spbstu.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

import ru.spbstu.calculator.DatasetScore;
import ru.spbstu.calculator.FastaDataset;
import ru.spbstu.calculator.PWMatrix;
import ru.spbstu.calculator.SimilarityScoreCalculator;

import java.util.HashMap;
import java.util.Map;

public class SimilarityScoreCalculatorTest {
    @Test public void testCountScore() {
        FastaDataset fastaDataset = new FastaDataset();
        fastaDataset.add("id1", "AAAAA");
        fastaDataset.add("id2", "ACTG");
        fastaDataset.add("id3", "ACC");

        double m[][] = {
                { 0.1D, 0.1D, 0.1D, 0.1D },
                { 0.2D, 0.2D, 0.2D, 0.2D },
                { 0.3D, 0.3D, 0.3D, 0.3D },
                { 0.4D, 0.4D, 0.4D, 0.4D },
        };
        PWMatrix pwm = new PWMatrix(m);

        SimilarityScoreCalculator ssc = new SimilarityScoreCalculator(4, fastaDataset);

        DatasetScore ds = ssc.calculateScore(pwm);

        Map<String, double[]> resultMap = new HashMap<>();
        resultMap.put("id1", new double[]{ 0.4D, 0.4D });
        resultMap.put("id2", new double[]{ 1.0D });
        resultMap.put("id3", new double[]{});

        assertArrayEquals(resultMap.get("id1"), ds.getByID("id1"), 0.0001);
        assertArrayEquals(resultMap.get("id2"), ds.getByID("id2"), 0.0001);
        assertArrayEquals(resultMap.get("id3"), ds.getByID("id3"), 0.0001);
    }
}
