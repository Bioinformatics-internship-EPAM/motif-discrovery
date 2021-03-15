package ru.spbstu.calculator;

public class PWMatrix {

    private final double[][] data;

    public PWMatrix(double[][] data) {
        // TODO: https://github.com/Bioinformatics-internship-EPAM/motif-discrovery/issues/4
        this.data = data;
    }

    public double[] getRow(char n) throws IncorrectNucleotideException{
        return switch (n) {
            case 'A' -> data[0];
            case 'C' -> data[1];
            case 'T' -> data[2];
            case 'G' -> data[3];
            default -> throw new IncorrectNucleotideException(n);
        };
    }
}


