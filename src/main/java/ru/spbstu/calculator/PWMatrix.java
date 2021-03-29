package ru.spbstu.calculator;

public class PWMatrix {

    private final double[][] data;

    public PWMatrix(double[][] data) {
        // TODO: https://github.com/Bioinformatics-internship-EPAM/motif-discrovery/issues/4
        this.data = data;
    }

    public double[] getRow(char n) throws IncorrectNucleotideException{
        switch (n) {
            case 'A' : return data[0];
            case 'C' : return data[1];
            case 'T' : return data[2];
            case 'G' : return data[3];
            default : throw new IncorrectNucleotideException(n);
        }
    }
}


