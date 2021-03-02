package processor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class SimilarityScoreCalculator {

    public static ArrayList<ArrayList<Double>> calculateScoreForDataset(int window_size, ArrayList<ArrayList<Character>> dataset, Map<Character, ArrayList<Double>> matrix) throws IllegalArgumentException {
        ArrayList<ArrayList<Double>> result = new ArrayList<>();

        if (window_size <= 0) {
            window_size = Constants.DEFAULT_WINDOW_SIZE;
        }

        for (Object  item : matrix.values()) {
            if (((ArrayList<Double>) item).size() != window_size) {
                throw new IllegalArgumentException("Window size is not available for given matrix");
            }
        }


        if (! matrix.keySet().equals(Set.of(
                'A',
                'C',
                'T',
                'G'))) throw new IllegalArgumentException("Matrix is not correct");

        for (ArrayList<Character> characters : dataset) {
            result.add(calculateScoreForSequence(window_size, characters, matrix));
        }

        return result;
    }

    public static ArrayList<Double> calculateScoreForSequence(int window_size, ArrayList<Character> sequence, Map<Character, ArrayList<Double>> matrix) {
        ArrayList<Double> result = new ArrayList<>();

        for (int i = 0; i <= sequence.size() - window_size; i++) {
            // TODO : now creates copy
            //  can be without sublist??
            result.add(calculateScoreForWindow(new ArrayList<>(sequence.subList(i, i + window_size)), matrix));
        }

        return result;
    }

    public static double calculateScoreForWindow(ArrayList<Character> window, Map<Character, ArrayList<Double>> matrix) {
        double sum = 0;

        for (int i = 0; i < window.size(); i++) {
            sum += matrix.get(window.get(i)).get(i);
        }

        return sum;
    }
}
