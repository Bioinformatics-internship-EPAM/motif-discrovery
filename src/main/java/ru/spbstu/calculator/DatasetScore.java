package ru.spbstu.calculator;

import java.util.HashMap;
import java.util.Map;

public class DatasetScore {

    private Map<String, double[]> data = new HashMap<>();

    public DatasetScore() {}

    public void add(String id, double[] scores) {
        data.put(id, scores);
    }

    //for testing
    public double[] getByID(String id) {
        return data.get(id);
    }
}
